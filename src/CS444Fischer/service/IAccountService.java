/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS444Fischer.service;

import CS444Fischer.domain.Account;
import java.util.Collection;

/**
 *
 * @author Brody Fischer
 */
public interface IAccountService extends IService{
    public Account createAccount(Account account);
    public Account getAccountByID(int ID);
    public Collection<Account> getAllAccounts();
    public Boolean updateAccount(Account account);
    public Boolean deleteAccount(Account account);
}
