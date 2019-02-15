/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS444Fischer.service;

import CS444Fischer.domain.TimeEntry;
import java.util.ArrayList;
import java.util.Date;
import junit.framework.TestCase;

/**
 *
 * @author brody
 */
public class TimeEntryServiceImpTest extends TestCase {
    
    public TimeEntryServiceImpTest(String testName) {
        super(testName);
    }

    public void testCRUD() {
        TimeEntryServiceImp imp = (TimeEntryServiceImp) TimeEntryServiceImp.instance();
        
        //test create
        TimeEntry te = new TimeEntry(1,"Title",new Date(),1.0,"","");
        te = imp.createTimeEntry(te);
        assertNotNull(te);
        assertEquals(te.getID(), 1);
        
        //test get
        TimeEntry te2 = imp.getTimeEntryByID(1);
        assertNotNull(te2);
        assertEquals(te2.getID(), 1);
        
        ArrayList entries = new ArrayList(imp.getTimeEntriesforAccountID(1));
        assertNotNull(entries);
        assertEquals(entries.size(), 1);
        
        //test update
        te.setID(0);
        assertFalse(imp.updateTimeEntry(te));
        te.setID(1);
        assertTrue(imp.updateTimeEntry(te));
        
        //test delete
        assertTrue(imp.deleteTimeEntry(te));
        entries = new ArrayList(imp.getTimeEntriesforAccountID(1));
        assertEquals(entries.size(), 0);
    }
    
}
