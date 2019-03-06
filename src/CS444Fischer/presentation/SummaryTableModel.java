/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS444Fischer.presentation;

import CS444Fischer.domain.TimeEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author brody
 */
public class SummaryTableModel extends AbstractTableModel{

	private String[] columnNames = {"Category", "Total"};
	private String[] categories = {};
	private List<TimeEntry> timeEntries;
	
	public SummaryTableModel(){
		timeEntries = new ArrayList<TimeEntry>();
	}
	
	public void setCategories(Set<String> categories){
		int index = 0;
		this.categories = new String[categories.size()];
		
		for(String cat : categories){
			this.categories[index++] = cat;
		}
	}
	
	public void setTimeEntries(List<TimeEntry> timeEntries){
		this.timeEntries = timeEntries;
	}
	
	@Override
	public int getRowCount() {
		return categories.length;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		String category = categories[row];
		if(col == 0){ return category; }

		Double sum = 0.0;
		for(TimeEntry te : timeEntries){
			if(te.getCategory().equals(category)){
				sum += te.getDuration();
			}
		}
		
		return sum;
	}
	
	@Override
	public String getColumnName(int col){
		return columnNames[col];
	}
}
