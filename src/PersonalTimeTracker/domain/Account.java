/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonalTimeTracker.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Brody Fischer
 */
public class Account {
    private int ID;
    private Login login;
    private ArrayList<TimeEntry> timeEntries;
    private TreeSet<String> categories;
    
    public Account(Login login, Collection<TimeEntry> timeEntries, Set<String> categories){
        this.login = login;
        this.timeEntries = new ArrayList(timeEntries);
        this.categories = new TreeSet(categories);
    }
    
    public Account(Login login){
        this(login, new ArrayList<TimeEntry>(), new TreeSet<String>());
    }
    
    public int getID(){
        return ID;
    }
    
    public Login getLogin(){
        return login;
    }
    
    public Collection<TimeEntry> getTimeEntries(){
        return timeEntries;
    }
    
    public Set<String> getCategories(){
        return categories;
    }
    
    public void setID(int ID){
        this.ID = ID;
    }
    
    public void setLogin(Login login){
        this.login = login;
    }
    
    public void setTimeEntries(Collection<TimeEntry> timeEntries){
        this.timeEntries = new ArrayList(timeEntries);
    }
    
    public void setCategories(Set<String> categories){
        this.categories = new TreeSet(categories);
    }
    
    public Boolean validate(){
        return ID > 0 && login != null && timeEntries != null && categories != null;
    }
}
