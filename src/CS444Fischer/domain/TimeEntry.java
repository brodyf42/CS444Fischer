/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS444Fischer.domain;

import java.util.Date;

/**
 *
 * @author Brody Fischer
 */
public class TimeEntry {
    private int ID;
    private int accountID;
    private String title;
    private Date date;
    private double duration;
    private String category;
    private String notes;
    
    public TimeEntry(int accountID, String title, Date date, double duration, String category, String notes){
        this.accountID= accountID;
        this.title = title;
        this.date = date;
        this.duration = duration;
        this.category = category;
        this.notes = notes;
    }
    
    public int getID(){
        return ID;
    }
    
    public int getAccountID(){
        return accountID;
    }
    
    public String getTitle(){
        return title;
    }
    
    public Date getDate(){
        return date;
    }
    
    public double getDuration(){
        return duration;
    }
    
    public String getCategory(){
        return category;
    }
    
    public String getNotes(){
        return notes;
    }

    public void setID(int ID){
        this.ID = ID;
    }
    
    public void setAccountID(int accountID){
        this.accountID = accountID;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public void setDate(Date date){
        this.date = date;
    }
    
    public void setDuration(double duration){
        this.duration = duration;
    }
    
    public void setCategory(String category){
        this.category = category;
    }
    
    public void setNotes(String notes){
        this.notes = notes;
    }
    
    public Boolean validate(){
        return (ID > 0 && accountID > 0 && title != null && !title.equals("") && date != null && duration > 0);
    }   
}
