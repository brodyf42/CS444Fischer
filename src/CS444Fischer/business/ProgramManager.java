/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS444Fischer.business;

import CS444Fischer.presentation.*;
import CS444Fischer.service.*;
import CS444Fischer.domain.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private static LoginServiceImp loginSvc;
    private static AccountServiceImp accountSvc;
    private static TimeEntryServiceImp timeEntrySvc;

    // Domain objects
    private static Account activeAccount;
	
	// Helpoer objects
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("M/dd/yyyy");

    static {
		try {
			loginSvc = (LoginServiceImp) ServiceFactory.getService(ServiceFactory.Service.LOGIN_SERVICE);
			accountSvc = (AccountServiceImp) ServiceFactory.getService(ServiceFactory.Service.ACCOUINT_SERVICE);
			timeEntrySvc = (TimeEntryServiceImp) ServiceFactory.getService(ServiceFactory.Service.TIME_ENTRY_SERVICE);
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
		populateMainFrame();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Account and Login Methods">
    public static void attemptLogin(String username, String password) {
		Login login = new Login(username, password);

		for (var login2 : loginSvc.getAllLogins()) {
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
		for (var login2 : loginSvc.getAllLogins()) {
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

		JOptionPane.showMessageDialog(createAccountFrame, "Account created successfully.\nLogging in with new account.", "Account Created", JOptionPane.INFORMATION_MESSAGE);

		activeAccount = account;
		goToMainFrame(createAccountFrame);
    }

    public static void logout() {
		activeAccount = null;
		goToLogin(mainFrame);
    }
    // </editor-fold>

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
		
		reportFatalError();
		
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
		
		// remove after retrieve operation is implemented
		JOptionPane.showMessageDialog(mainFrame, "New Entry with following information added successfully\n\nTitle: "+title+"\nNotes: "+notes+"\nDuration: "+duration+"\nDate: "+date+"\nCategory "+category, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

	private static void populateMainFrame(){
		mainFrame.setTitle("Personal Time Tracker | " + activeAccount.getLogin().getUsername());
		Date today = new Date();
		mainFrame.setEntryDateLbl(dateFormat.format(today));
		mainFrame.setUsageDateLbl(dateFormat.format(today));
		mainFrame.setDateFld(dateFormat.format(today));
	}

    private static void reportFatalError() {
		JOptionPane.showMessageDialog(null, "The program has encountered a serious error and will now close", "Fatal Error", JOptionPane.ERROR_MESSAGE);
		closeProgram();
    }

    public static void closeProgram() {
		System.exit(0);
    }
}
