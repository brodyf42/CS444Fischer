/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS444Fischer.service;

import CS444Fischer.domain.TimeEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Brody Fischer
 */
public class TimeEntryServiceImp implements ITimeEntryService{

    private static final TimeEntryServiceImp INSTANCE;
    private static final HashMap<Integer,TimeEntry> TIME_ENTRIES;
    private static int nextID;
    
    static {
        INSTANCE = new TimeEntryServiceImp();
        TIME_ENTRIES = new HashMap<>();
        nextID = 0;
    }
    
    public static ITimeEntryService instance() {
        return INSTANCE;
    }
    
    @Override
    public TimeEntry createTimeEntry(TimeEntry timeEntry) {
        timeEntry.setID(++nextID);
        if(timeEntry.validate()){
            TIME_ENTRIES.put(timeEntry.getID(), timeEntry);
            return timeEntry;
        }
            
        return null;
    }

    @Override
    public TimeEntry getTimeEntryByID(int ID) {
        return TIME_ENTRIES.get(ID);
    }

    @Override
    public Collection<TimeEntry> getTimeEntriesforAccountID(int accountID) {
        ArrayList list = new ArrayList();
        
        for (var timeEntry : TIME_ENTRIES.values()) {
            if(timeEntry.getAccountID() == accountID){
                list.add(timeEntry);
            }
        }
        
        return list;
    }

    @Override
    public Boolean updateTimeEntry(TimeEntry timeEntry) {
        if (timeEntry.validate() && TIME_ENTRIES.keySet().contains(timeEntry.getID())){
            TIME_ENTRIES.put(timeEntry.getID(), timeEntry);
            return true;
        }
            
        return false;
    }

    @Override
    public Boolean deleteTimeEntry(TimeEntry timeEntry) {
        if (timeEntry.validate() && TIME_ENTRIES.keySet().contains(timeEntry.getID())){
            TIME_ENTRIES.remove(timeEntry.getID());
            return true;
        }
            
        return false;
    }
}
