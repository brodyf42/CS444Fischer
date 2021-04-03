/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonalTimeTracker.business;

import PersonalTimeTracker.presentation.ChangeDateFrame;
import PersonalTimeTracker.presentation.LoginFrame;
import PersonalTimeTracker.presentation.CategoryFrame;
import PersonalTimeTracker.presentation.CreateAccountFrame;
import PersonalTimeTracker.presentation.MainFrame;
import PersonalTimeTracker.presentation.DetailsFrame;
import PersonalTimeTracker.service.IAccountService;
import PersonalTimeTracker.service.ServiceFactory;
import PersonalTimeTracker.service.ITimeEntryService;
import PersonalTimeTracker.service.ILoginService;
import PersonalTimeTracker.domain.Account;
import PersonalTimeTracker.domain.Login;
import PersonalTimeTracker.domain.TimeEntry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Brody Fischer
 */
public class ProgramManager {

    // UI objects
    private static LoginFrame loginFrame;
    private static CreateAccountFrame createAccountFrame;
    private static MainFrame mainFrame;
    private static DetailsFrame detailsFrame;
    private static CategoryFrame categoryFrame;
    private static ChangeDateFrame changeDateFrame;

    // Service objects
    private static ILoginService loginSvc;
    private static IAccountService accountSvc;
    private static ITimeEntryService timeEntrySvc;

    // Domain objects
    private static Account activeAccount;
	
	// Helper objects
	private static Date displayDate;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("M/dd/yyyy");
	private static SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM yyyy");
	private static SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
	private static String summaryPeriod = "Day";

    static {
		try {
			loginSvc = (ILoginService) ServiceFactory.getService(ServiceFactory.Service.LOGIN_SERVICE);
			accountSvc = (IAccountService) ServiceFactory.getService(ServiceFactory.Service.ACCOUINT_SERVICE);
			timeEntrySvc = (ITimeEntryService) ServiceFactory.getService(ServiceFactory.Service.TIME_ENTRY_SERVICE);
		} catch (Exception e) {
			Logger.getLogger(ProgramManager.class.getName()).log(Level.SEVERE, null, e);
			reportFatalError();
		}
    }

    public static void main(String args[]) {
		loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
    }

    // <editor-fold defaultstate="collapsed" desc="Navigation Methods">
    public static void goToCreateAccount(JFrame sourceFrame) {
		sourceFrame.dispose();
		createAccountFrame = new CreateAccountFrame();
		createAccountFrame.setVisible(true);
    }

    public static void goToLogin(JFrame sourceFrame) {
		sourceFrame.dispose();
		loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
    }

    public static void goToMainFrame(JFrame sourceFrame) {
		sourceFrame.dispose();
		mainFrame = new MainFrame();
		mainFrame.setVisible(true);
		if(activeAccount != null){
			displayDate = new Date();
			populateMainFrame();
		}
    }
	
	public static void openChangeDateFrame(){
		mainFrame.setEnabled(false);
		changeDateFrame = new ChangeDateFrame();
		changeDateFrame.setDateFld(dateFormat.format(displayDate));
		changeDateFrame.setVisible(true);
	}
	
	public static void closeChangeDateFrame(){
		changeDateFrame.dispose();
		mainFrame.setEnabled(true);
	}
	
	public static void openDetailsFrame(TimeEntry timeEntry){
		mainFrame.setEnabled(false);
		detailsFrame = new DetailsFrame();
		detailsFrame.setCategories(activeAccount.getCategories());
		detailsFrame.setTimeEntry(timeEntry);
		detailsFrame.setVisible(true);
	}
	
	public static void closeDetailsFrame(){
		detailsFrame.dispose();
		mainFrame.setEnabled(true);
		// populate the main frame in case the categories were edited.
		populateMainFrame();
	}
	
	public static void openCategoryFrame(JFrame parent){
		parent.setEnabled(false);
		categoryFrame = new CategoryFrame();
		categoryFrame.setParentFrame(parent);
		categoryFrame.getCategoryTableModel().setCategories(activeAccount.getCategories());
		categoryFrame.getCategoryTableModel().fireTableDataChanged();
		categoryFrame.setUsername(activeAccount.getLogin().getUsername());
		categoryFrame.setVisible(true);
	}
	
	public static void closeCategoryFrame(){
		JFrame parent = categoryFrame.getParentFrame();
		parent.setEnabled(true);
		if(parent.equals(mainFrame)){
			populateMainFrame();
		}else{
			detailsFrame.setCategories(activeAccount.getCategories());
		}
		
		categoryFrame.dispose();
	}
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Account and Login Methods">
    public static void attemptLogin(String username, String password) {
		Login login = new Login(username, password);

		for (Login login2 : loginSvc.getAllLogins()) {
			if (login.equals(login2)) {
			activeAccount = accountSvc.getAccountByID(login2.getAccountID());
			goToMainFrame(loginFrame);
			return;
			}
		}

		// if login not found show error
		JOptionPane.showMessageDialog(loginFrame, "Login was unsuccessful.\nMissing input or account not found.\nPlease try again.", "Login Failure", JOptionPane.ERROR_MESSAGE);
    }

    public static void attemptCreateAccount(String username, String password, String password2) {
		// validate input
		if (!password.equals(password2)) {
			JOptionPane.showMessageDialog(createAccountFrame, "Password fields do notmatch.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// create and validate login object
		Login login = new Login(username, password);
		if (!login.validate()) {
			JOptionPane.showMessageDialog(createAccountFrame, "Username or password does not meet requirements.\n\n" + Login.REQUIREMENTS, "Invalid Input", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// check if login for username already exists
		for (Login login2 : loginSvc.getAllLogins()) {
			if (username.equals(login2.getUsername())) {
			JOptionPane.showMessageDialog(createAccountFrame, "The given username already exists.\nPlease select another username.", "Username Unavailable", JOptionPane.WARNING_MESSAGE);
			return;
			}
		}

		// store new validated login object
		login = loginSvc.createLogin(login);
		// show error and close program if login creation failed
		if (login == null) { reportFatalError(); }

		// create account object
		Account account = new Account(login);
		account = accountSvc.createAccount(account);
		// show error and close program if account creation failed
		if (account == null) { reportFatalError(); }

		// update stored Login with account ID
		login.setAccountID(account.getID());
		// show error and close program if login update failed
		if (!loginSvc.updateLogin(login)) {	reportFatalError();	}
		
		account.getCategories().add("uncategorized");
		accountSvc.updateAccount(account);

		JOptionPane.showMessageDialog(createAccountFrame, "Account created successfully.\nLogging in with new account.", "Account Created", JOptionPane.INFORMATION_MESSAGE);

		activeAccount = account;
		goToMainFrame(createAccountFrame);
    }

    public static void logout() {
		activeAccount = null;
		displayDate = null;
		goToLogin(mainFrame);
    }
    // </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Time Entry Creation Methods">
    public static void createTimeEntry(String title, String notes, String duration, String date, String category) {
		Date newDate;
		double newDuration;
				
		// validate input
		if(title.equals("")){
			JOptionPane.showMessageDialog(mainFrame, "A title is required", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(duration.equals("")){
			JOptionPane.showMessageDialog(mainFrame, "A duration is required", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		newDuration = Double.parseDouble(duration);
		if(newDuration <= 0.00){
			JOptionPane.showMessageDialog(mainFrame, "Duration must be a positive value", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(date.equals("")){
			JOptionPane.showMessageDialog(mainFrame, "A date is required", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			newDate = dateFormat.parse(date);
			TimeEntry newTE = new TimeEntry(activeAccount.getID(),title, newDate, newDuration, category, notes);
			newTE = timeEntrySvc.createTimeEntry(newTE);
			if(newTE == null){ reportFatalError(); }
		} catch (ParseException e) {
			Logger.getLogger(ProgramManager.class.getName()).log(Level.SEVERE, null, e);
			reportFatalError();
		}
		
		activeAccount.setTimeEntries(timeEntrySvc.getTimeEntriesforAccountID(activeAccount.getID()));
		accountSvc.updateAccount(activeAccount);
		
		mainFrame.clearTimeEntryForm();
		populateMainFrame();
    }
	// </editor-fold>
	
	// <editor-fold defaultstate="collapsed" desc="Time Entry Retrieval Methods">
	private static void populateMainFrame(){
		mainFrame.setTitle("Personal Time Tracker | " + activeAccount.getLogin().getUsername());
		mainFrame.setDisplayedDate(dateFormat.format(displayDate));
		mainFrame.setCategories(activeAccount.getCategories());
		mainFrame.getTimeEntryTableModel().setTimeEntries(getTimeEntriesForDate(displayDate));
		mainFrame.getTimeEntryTableModel().fireTableDataChanged();
		mainFrame.getSummaryTableModel().setCategories(activeAccount.getCategories());
		
		switch(summaryPeriod){
			case "Year":
				mainFrame.getSummaryTableModel().setTimeEntries(getTimeEntriesForYear(displayDate));
				mainFrame.setDisplayedSummaryDate(yearFormat.format(displayDate));
				break;
			case "Month":
				mainFrame.getSummaryTableModel().setTimeEntries(getTimeEntriesForMonth(displayDate));
				mainFrame.setDisplayedSummaryDate(monthFormat.format(displayDate));
				break;
			case "Day":
			default:
				mainFrame.getSummaryTableModel().setTimeEntries(getTimeEntriesForDate(displayDate));
				mainFrame.setDisplayedSummaryDate(dateFormat.format(displayDate));
		}
		
		mainFrame.getSummaryTableModel().fireTableDataChanged();
	}

	public static void changeDisplayDate(String dateString) {
		try {
			displayDate = dateFormat.parse(dateString);
		} catch (ParseException ex) {
			Logger.getLogger(ProgramManager.class.getName()).log(Level.SEVERE, null, ex);
			reportFatalError();
		}
		
		closeChangeDateFrame();
		populateMainFrame();
	}
	
	private static List<TimeEntry> getTimeEntriesForDate(Date date){
		ArrayList list = new ArrayList<TimeEntry>();
		for(TimeEntry te : activeAccount.getTimeEntries()){
			if (te.getDate().getDay() == date.getDay() &&
				te.getDate().getMonth() == date.getMonth() &&
				te.getDate().getYear() == date.getYear()){
				list.add(te);
			}
		}
		return list;
	}
	
	private static List<TimeEntry> getTimeEntriesForMonth(Date date){
		ArrayList list = new ArrayList<TimeEntry>();
		for(TimeEntry te : activeAccount.getTimeEntries()){
			if (te.getDate().getMonth() == date.getMonth() && te.getDate().getYear() == date.getYear()){
				list.add(te);
			}
		}
		return list;
	}
	
	private static List<TimeEntry> getTimeEntriesForYear(Date date){
		ArrayList list = new ArrayList<TimeEntry>();
		for(TimeEntry te : activeAccount.getTimeEntries()){
			if (te.getDate().getYear() == date.getYear()){
				list.add(te);
			}
		}
		return list;
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="collapsed" desc="Time Entry Update and Delete Methods">
	public static void editTimeEntryAtIndex(int index) {
		if(index == -1){
			JOptionPane.showMessageDialog(null, "No item is selected.\nPlease select an item to edit.", "Edit Warning", JOptionPane.WARNING_MESSAGE);
		}else{
			openDetailsFrame(getTimeEntriesForDate(displayDate).get(index));
		}
	}
	
	public static void updateTimeEntry(TimeEntry timeEntry){
		
		if(timeEntry.getTitle().equals("")){
			JOptionPane.showMessageDialog(detailsFrame, "A title is required", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(timeEntry.getDuration() <= 0.00){
			JOptionPane.showMessageDialog(detailsFrame, "Duration must be a positive value", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		timeEntrySvc.updateTimeEntry(timeEntry);
		activeAccount.setTimeEntries(timeEntrySvc.getTimeEntriesforAccountID(activeAccount.getID()));
		accountSvc.updateAccount(activeAccount);
		
		closeDetailsFrame();
		populateMainFrame();
	}
	
	public static void deleteTimeEntryAtIndex(int index) {
		if(index == -1){
			JOptionPane.showMessageDialog(null, "No item is selected.\nPlease select an item to delete.", "Deletion Warning", JOptionPane.WARNING_MESSAGE);
		}else{
			int confirmDelete = JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to delete the selected item?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
			if(confirmDelete == JOptionPane.YES_OPTION){
				
				//delete selected entry from TimeEntry master list
				timeEntrySvc.deleteTimeEntry(getTimeEntriesForDate(displayDate).get(index));
				
				//update account
				activeAccount.setTimeEntries(timeEntrySvc.getTimeEntriesforAccountID(activeAccount.getID()));
				accountSvc.updateAccount(activeAccount);
				
				//populate main frame
				populateMainFrame();
			}
		}		
	}
	// </editor-fold>
	
	// <editor-fold defaultstate="collapsed" desc="Category Update and Delete Methods">
	public static void addCategory(String category) {
		if(category.equals("")){
			JOptionPane.showMessageDialog(categoryFrame, "Category name is blank.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(activeAccount.getCategories().contains(category)){
			JOptionPane.showMessageDialog(categoryFrame, "Category already exists. Please try again", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		activeAccount.getCategories().add(category);
		accountSvc.updateAccount(activeAccount);
		categoryFrame.getCategoryTableModel().setCategories(activeAccount.getCategories());
		categoryFrame.getCategoryTableModel().fireTableDataChanged();
	}
	
	public static void deleteCategory(String category){
		if(category.equals("")){
			JOptionPane.showMessageDialog(categoryFrame, "No item is selected.\nPlease select an item to delete.", "Deletion Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if(activeAccount.getCategories().size() == 1){
			JOptionPane.showMessageDialog(categoryFrame, "A user must have at least one category.\nCannot delete category.", "Deletion Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		for(TimeEntry te : activeAccount.getTimeEntries()){
			if(category.equals(te.getCategory())){
				JOptionPane.showMessageDialog(categoryFrame, "An entry with this category still exists.\nCannot delete category.", "Deletion Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
		int confirmDelete = JOptionPane.showConfirmDialog(categoryFrame, "Are you sure you want to delete the selected item?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
		if(confirmDelete == JOptionPane.YES_OPTION){
			activeAccount.getCategories().remove(category);
			accountSvc.updateAccount(activeAccount);
			categoryFrame.getCategoryTableModel().setCategories(activeAccount.getCategories());
			categoryFrame.getCategoryTableModel().fireTableDataChanged();
		}
		
	}
	
	public static void updateSummaryPeriod(String period){
		summaryPeriod = period;
		populateMainFrame();
	}
	// </editor-fold>
	
	private static void reportFatalError() {
		JOptionPane.showMessageDialog(null, "The program has encountered a serious error and will now close", "Fatal Error", JOptionPane.ERROR_MESSAGE);
		closeProgram();
    }

    public static void closeProgram() {
		System.exit(0);
    }
}
