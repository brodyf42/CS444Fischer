/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonalTimeTracker.service;

import PersonalTimeTracker.domain.Login;
import java.util.Collection;

/**
 *
 * @author Brody Fischer
 */
public interface ILoginService extends IService{
    public Login createLogin(Login login);
    public Login getLoginByID(int ID);
    public Collection<Login> getAllLogins();
    public Boolean updateLogin(Login login);
    public Boolean deleteLogin(Login login);
}
