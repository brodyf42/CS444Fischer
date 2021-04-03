/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonalTimeTracker.presentation;

import java.util.Set;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author brody
 */
public class CategoryTableModel extends AbstractTableModel{

	private String[] columnNames = {"Category"};
	private String[] categories;
	
	public void setCategories(Set<String> categories){
		int index = 0;
		this.categories = new String[categories.size()];
		
		for(String cat : categories){
			this.categories[index++] = cat;
		}
	}
	
	@Override
	public int getRowCount() {
		return categories.length;
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return categories[row];
	}
	
	@Override
	public String getColumnName(int col){
		return columnNames[col];
	}
}
