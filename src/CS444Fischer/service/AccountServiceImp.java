/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS444Fischer.service;

import CS444Fischer.domain.Account;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Brody Fischer
 */
public class AccountServiceImp implements IAccountService{

    private static final AccountServiceImp INSTANCE;
    private static final HashMap<Integer,Account> ACCOUNTS;
    private static int nextID;
   
    static {
        INSTANCE = new AccountServiceImp();
        ACCOUNTS = new HashMap<>();
        nextID = 0;
    }
    
    public static IAccountService instance() {
        return INSTANCE;
    }
    
    @Override
    public Account createAccount(Account account) {
        account.setID(++nextID);
        if(account.validate()){
            ACCOUNTS.put(account.getID(), account);
            return account;
        }

        return null;
    }

    @Override
    public Account getAccountByID(int ID) {
        return ACCOUNTS.get(ID);
    }
    
    @Override
    public Collection<Account> getAllAccounts() {
        return ACCOUNTS.values();
    }

    @Override
    public Boolean updateAccount(Account account) {
        if (account.validate() && ACCOUNTS.keySet().contains(account.getID())){
            ACCOUNTS.put(account.getID(), account);
            return true;
        }
        
        return false;
    }

    @Override
    public Boolean deleteAccount(Account account) {
        if(account.validate() && ACCOUNTS.keySet().contains(account.getID())){
            ACCOUNTS.remove(account.getID());
            return true;
        }
        
        return false;
    }
}
