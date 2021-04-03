/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonalTimeTracker.service;

/**
 *
 * @author Brody Fischer
 */
public class ServiceFactory {
    
    public static enum Service{ ACCOUINT_SERVICE, LOGIN_SERVICE, TIME_ENTRY_SERVICE; }
        
    public static IService getService(Service service) throws Exception {
        
        switch (service){
            
            case ACCOUINT_SERVICE:
                return AccountServiceImp.instance();

            case LOGIN_SERVICE:
               return LoginServiceImp.instance();
                
            case TIME_ENTRY_SERVICE:
                return TimeEntryServiceImp.instance();
                
            default:
                return null;
        }
    }
}
