/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS444Fischer.domain;

import java.util.ArrayList;
import java.util.TreeSet;
import junit.framework.TestCase;

/**
 *
 * @author brody
 */
public class AccountTest extends TestCase {
    
    public AccountTest(String testName) {
        super(testName);
    }

    public void testValidate() {
        Account account = new Account(new Login(1,"name","Password1"));
        Boolean result = account.validate();
        assertFalse(result);
        
        account.setID(0);
        result = account.validate();
        assertFalse(result);
        
        account.setID(1);
        result = account.validate();
        assertTrue(result);
    }
}
