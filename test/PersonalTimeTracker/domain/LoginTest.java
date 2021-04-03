/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonalTimeTracker.domain;

import PersonalTimeTracker.domain.Login;
import junit.framework.TestCase;

/**
 *
 * @author brody
 */
public class LoginTest extends TestCase {
    
    public LoginTest(String testName) {
        super(testName);
    }

    public void testValidate() {
        Login login = new Login(0,null,null);
        Boolean result = login.validate();
        assertFalse(result);
        
        login.setUsername("Bob");
        login.setPassword("pass");
        result = login.validate();
        assertFalse(result);
        
        login.setAccountID(3);
        result = login.validate();
        assertFalse(result);
        
        login.setID(7);
        result = login.validate();
        assertFalse(result);
        
        login.setPassword("Pass123");
        result = login.validate();
        assertTrue(result);
    }
    
}
