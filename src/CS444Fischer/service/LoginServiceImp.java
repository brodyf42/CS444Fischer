/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS444Fischer.service;

import CS444Fischer.domain.Login;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Brody Fischer
 */
public class LoginServiceImp implements ILoginService{
    
    private static final LoginServiceImp INSTANCE;
    private static final HashMap<Integer,Login> LOGINS;
    private static int nextID;

    static {
        INSTANCE = new LoginServiceImp();
        LOGINS = new HashMap<>();
        nextID = 0;
    }
    
    public static ILoginService instance() {
        return INSTANCE;
    }
    
    @Override
    public Login createLogin(Login login) {
        login.setID(++nextID);
        if(login.validate()){
            LOGINS.put(login.getID(), login);
            return login;
        }

        return null;
    }

    @Override
    public Login getLoginByID(int ID) {
        return LOGINS.get(ID);
    }

    @Override
    public Collection<Login> getAllLogins() {
        return LOGINS.values();
    }

    @Override
    public Boolean updateLogin(Login login) {
        if (login.validate() && LOGINS.keySet().contains(login.getID())){
            LOGINS.put(login.getID(), login);
            return true;
        }
            
        return false;
    }

    @Override
    public Boolean deleteLogin(Login login) {
        if (login.validate() && LOGINS.keySet().contains(login.getID())){
            LOGINS.remove(login.getID());
            return true;
        }
            
        return false;
    }
}
