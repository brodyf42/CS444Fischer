/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS444Fischer.presentation;

import CS444Fischer.domain.TimeEntry;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author brody
 */
public class TimeEntryTableModel extends AbstractTableModel{

	private String[] columnNames = {"Title", "Category", "Duration"};
	private List<TimeEntry> timeEntries;
	
	public TimeEntryTableModel(){
		timeEntries = new ArrayList<TimeEntry>();
	}
	
	public void setTimeEntries(List<TimeEntry> timeEntries){
		this.timeEntries = timeEntries;
	}
	
	@Override
	public int getRowCount() {
		return timeEntries.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		TimeEntry te = timeEntries.get(row);
		switch(col){
			case 0: return te.getTitle();
			case 1: return te.getCategory();
			case 2: return te.getDuration();
			default: return null;
		}
	}
	
	@Override
	public String getColumnName(int col){
		return columnNames[col];
	}
	
}
