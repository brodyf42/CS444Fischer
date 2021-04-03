/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonalTimeTracker.service;

import PersonalTimeTracker.service.AccountServiceImp;
import PersonalTimeTracker.domain.Account;
import PersonalTimeTracker.domain.Login;
import java.util.ArrayList;
import java.util.TreeSet;
import junit.framework.TestCase;

/**
 *
 * @author brody
 */
public class AccountServiceImpTest extends TestCase {
    
    public AccountServiceImpTest(String testName) {
        super(testName);
    }

    public void testCRUD() {
        AccountServiceImp imp = (AccountServiceImp)AccountServiceImp.instance();
        
        // test create
        Login l = new Login(1,"user","Pass123");
        Account acc = new Account(l);
        acc.setCategories(new TreeSet<>());
        acc.setTimeEntries(new ArrayList<>());
        acc = imp.createAccount(acc);
        assertNotNull(acc);
        assertEquals(acc.getID(), 1);
        
        //test get
        Account acc2 = imp.getAccountByID(1);
        assertNotNull(acc);
        assertEquals(acc.getID(), 1);
        
        ArrayList accounts = new ArrayList(imp.getAllAccounts());
        assertNotNull(accounts);
        assertEquals(accounts.size(), 1);
        
        //test update
        acc.setID(2);
        assertFalse(imp.updateAccount(acc));
        acc.setID(1);
        assertTrue(imp.updateAccount(acc));
        
        //test delete
        assertTrue(imp.deleteAccount(acc));
        accounts = new ArrayList(imp.getAllAccounts());
        assertEquals(accounts.size(), 0);
    }
    
}
