/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS444Fischer.service;

import CS444Fischer.domain.Login;
import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author brody
 */
public class LoginServiceImpTest extends TestCase {
    
    public LoginServiceImpTest(String testName) {
        super(testName);
    }

    public void testCRUD() {
        LoginServiceImp imp = (LoginServiceImp) LoginServiceImp.instance();
        
        //test create
        Login l = new Login(1,"name","Pass123");
        l = imp.createLogin(l);
        assertNotNull(l);
        assertEquals(l.getID(), 1);
        
        //test get
        Login l2 = imp.getLoginByID(1);
        assertNotNull(l2);
        assertEquals(l2.getID(), 1);
        
        ArrayList logins = new ArrayList(imp.getAllLogins());
        assertNotNull(logins);
        assertEquals(logins.size(), 1);
        
        //test update
        l.setID(0);
        assertFalse(imp.updateLogin(l));
        l.setID(1);
        assertTrue(imp.updateLogin(l));
        
        //test delete
        assertTrue(imp.deleteLogin(l));
        logins = new ArrayList(imp.getAllLogins());
        assertEquals(logins.size(), 0);
    }
    
}
