/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS444Fischer.service;

import CS444Fischer.domain.TimeEntry;
import java.util.Collection;

/**
 *
 * @author Brody Fischer
 */
public interface ITimeEntryService extends IService{
    public TimeEntry createTimeEntry(TimeEntry timeEntry);
    public TimeEntry getTimeEntryByID(int ID);
    public Collection<TimeEntry> getTimeEntriesforAccountID(int accountID);
    public Boolean updateTimeEntry(TimeEntry timeEntry);
    public Boolean deleteTimeEntry(TimeEntry timeEntry);
}
