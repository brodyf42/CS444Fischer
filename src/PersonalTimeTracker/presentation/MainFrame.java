/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonalTimeTracker.presentation;

import PersonalTimeTracker.business.ProgramManager;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author Brody Fischer
 */
public class MainFrame extends javax.swing.JFrame {

	private TimeEntryTableModel timeEntryModel;
	private SummaryTableModel summaryModel;
	
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        setLocationRelativeTo(null);
        initComponents();
		timeEntryModel = new TimeEntryTableModel();
		timeEntryTbl.setModel(timeEntryModel);
		summaryModel = new SummaryTableModel();
		summaryTbl.setModel(summaryModel);
		getRootPane().setDefaultButton(addEntryBtn);
    }

	public TimeEntryTableModel getTimeEntryTableModel(){ return timeEntryModel; }
	public SummaryTableModel getSummaryTableModel(){ return summaryModel; }
	
	public void setDisplayedDate(String dateText){
		dateFld.setText(dateText);
		entryDateLbl.setText(dateText);
	}
	
	public void setDisplayedSummaryDate(String dateText){
		summaryDateLbl.setText(dateText);
	}
	
	public void setCategories(Set<String> categories){
		categoryCombo.setModel(new DefaultComboBoxModel(categories.toArray(new String[categories.size()])));
	}
	
	public void clearTimeEntryForm(){
		titleFld.setText("");
		notesFld.setText("");
		durationFld.setText("0.00");
	}
	
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        titleFld = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        notesFld = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        categoryCombo = new javax.swing.JComboBox<>();
        editCategoriesBtn = new javax.swing.JButton();
        durationFld = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        dateFld = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        addEntryBtn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        timeEntryTbl = new javax.swing.JTable();
        updateEntryBtn = new javax.swing.JButton();
        deleteEntryBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        summaryTbl = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        changeDateBtn = new javax.swing.JButton();
        entryDateLbl = new javax.swing.JLabel();
        summaryDateLbl = new javax.swing.JLabel();
        summaryCombo = new javax.swing.JComboBox<>();
        summaryUpdateBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Personal Time Tracker | Username");
        setMinimumSize(new java.awt.Dimension(960, 540));
        setPreferredSize(new java.awt.Dimension(960, 540));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Title:");

        titleFld.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        notesFld.setColumns(20);
        notesFld.setRows(5);
        jScrollPane1.setViewportView(notesFld);

        jLabel2.setText("Notes:");

        editCategoriesBtn.setText("Edit Categories");
        editCategoriesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editCategoriesBtnActionPerformed(evt);
            }
        });

        durationFld.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        durationFld.setToolTipText("hours");

        jLabel4.setText("Duration:");

        dateFld.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("M/d/yyyy"))));
        dateFld.setToolTipText("m/d/yyyy");

        jLabel5.setText("Date:");

        jLabel6.setText("Category:");

        addEntryBtn.setText("Add Entry");
        addEntryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEntryBtnActionPerformed(evt);
            }
        });

        jLabel10.setText("hours");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleFld)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                    .addComponent(categoryCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(editCategoriesBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addEntryBtn))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(durationFld)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateFld, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(durationFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addEntryBtn))
                    .addComponent(editCategoriesBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        timeEntryTbl.setRowHeight(24);
        timeEntryTbl.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        timeEntryTbl.getTableHeader().setReorderingAllowed(false);
        timeEntryTbl.setUpdateSelectionOnSort(false);
        jScrollPane3.setViewportView(timeEntryTbl);

        updateEntryBtn.setText("View/Edit Entry Details");
        updateEntryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateEntryBtnActionPerformed(evt);
            }
        });

        deleteEntryBtn.setText("Delete Selected Entry");
        deleteEntryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteEntryBtnActionPerformed(evt);
            }
        });

        jLabel3.setText("Sumary Period: ");

        summaryTbl.setToolTipText("");
        jScrollPane2.setViewportView(summaryTbl);

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel7.setText("Add New Time Entry");

        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel8.setText("Daily Time Entries:");

        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel9.setText("Time Usage Summary:");

        logoutBtn.setText("Log Out");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        changeDateBtn.setText("Change Displayed Date");
        changeDateBtn.setToolTipText("");
        changeDateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeDateBtnActionPerformed(evt);
            }
        });

        entryDateLbl.setText("M/D/YYYY");

        summaryDateLbl.setText("[Date|Month|Year]");

        summaryCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day", "Month", "Year" }));
        summaryCombo.setToolTipText("");

        summaryUpdateBtn.setText("Update");
        summaryUpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                summaryUpdateBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(updateEntryBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteEntryBtn)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(entryDateLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(changeDateBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logoutBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(summaryCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(summaryUpdateBtn))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(summaryDateLbl))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(changeDateBtn)
                    .addComponent(logoutBtn)
                    .addComponent(entryDateLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(summaryDateLbl))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(updateEntryBtn)
                        .addComponent(deleteEntryBtn))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(summaryCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(summaryUpdateBtn)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        ProgramManager.logout();
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void addEntryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEntryBtnActionPerformed
        ProgramManager.createTimeEntry(titleFld.getText(), notesFld.getText(), durationFld.getText(), dateFld.getText(), (String) categoryCombo.getSelectedItem());
    }//GEN-LAST:event_addEntryBtnActionPerformed

    private void changeDateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeDateBtnActionPerformed
        ProgramManager.openChangeDateFrame();
    }//GEN-LAST:event_changeDateBtnActionPerformed

    private void deleteEntryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteEntryBtnActionPerformed
        ProgramManager.deleteTimeEntryAtIndex(timeEntryTbl.getSelectedRow());
    }//GEN-LAST:event_deleteEntryBtnActionPerformed

    private void updateEntryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateEntryBtnActionPerformed
        ProgramManager.editTimeEntryAtIndex(timeEntryTbl.getSelectedRow());
    }//GEN-LAST:event_updateEntryBtnActionPerformed

    private void editCategoriesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editCategoriesBtnActionPerformed
        ProgramManager.openCategoryFrame(this);
    }//GEN-LAST:event_editCategoriesBtnActionPerformed

    private void summaryUpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_summaryUpdateBtnActionPerformed
        ProgramManager.updateSummaryPeriod((String) summaryCombo.getSelectedItem());
    }//GEN-LAST:event_summaryUpdateBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEntryBtn;
    private javax.swing.JComboBox<String> categoryCombo;
    private javax.swing.JButton changeDateBtn;
    private javax.swing.JFormattedTextField dateFld;
    private javax.swing.JButton deleteEntryBtn;
    private javax.swing.JFormattedTextField durationFld;
    private javax.swing.JButton editCategoriesBtn;
    private javax.swing.JLabel entryDateLbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JTextArea notesFld;
    private javax.swing.JComboBox<String> summaryCombo;
    private javax.swing.JLabel summaryDateLbl;
    private javax.swing.JTable summaryTbl;
    private javax.swing.JButton summaryUpdateBtn;
    private javax.swing.JTable timeEntryTbl;
    private javax.swing.JTextField titleFld;
    private javax.swing.JButton updateEntryBtn;
    // End of variables declaration//GEN-END:variables
}