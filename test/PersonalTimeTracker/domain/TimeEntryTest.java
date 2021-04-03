/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonalTimeTracker.domain;

import PersonalTimeTracker.domain.TimeEntry;
import java.util.Date;
import junit.framework.TestCase;

/**
 *
 * @author brody
 */
public class TimeEntryTest extends TestCase {
    
    public TimeEntryTest(String testName) {
        super(testName);
    }

    public void testValidate() {
        TimeEntry te = new TimeEntry(0,null,null,0.0,"","");
        Boolean result = te.validate();
        assertFalse(result);
        
        te.setID(1);
        result = te.validate();
        assertFalse(result);
        
        te.setAccountID(1);
        result = te.validate();
        assertFalse(result);
        
        te.setTitle("Work");
        result = te.validate();
        assertFalse(result);
        
        te.setDate(new Date());
        result = te.validate();
        assertFalse(result);
        
        te.setDuration(8.3);
        result = te.validate();
        assertTrue(result);
    }
    
}
