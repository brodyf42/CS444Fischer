/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonalTimeTracker.domain;

/**
 *
 * @author Brody Fischer
 */
public class Login {
    private int ID;
    private int accountID;
    private String username;
    private String password;
    
    public static final String REQUIREMENTS = 
            "Username must be 3 or more characters in length\n"
            + "Password must be 6 or more characters in length.\n"
            + "Password must contain at least one of each of the following:\n"
            + "a digit, a lowercase letter, an uppercase letter";
    
    public Login(int accountID, String username, String password){
        this.accountID = accountID;
        this.username = username;
        this.password = password;
    }
    
    public Login(String username, String password){
        this(0, username, password);
    }
    
    public int getID(){
        return ID;
    }
    
    public int getAccountID(){
        return accountID;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setID(int ID){
        this.ID = ID;
    }
    
    public void setAccountID(int accountID){
        this.accountID = accountID;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public Boolean validate(){
        //check username existence and length
        if(username == null || username.equals("") || username.length() < 3)
            return false;
        
        //check password existence and length
        if(password == null || password.equals("") || password.length() < 6)
            return false;
        
        //check for required character types 
        //digit, lowercase character, and uppercase character
        return (password.matches("(.*)\\d(.*)") &&
                password.matches("(.*)[a-z](.*)") &&
                password.matches("(.*)[A-Z](.*)"));
    }
    
    public Boolean equals(Login other){
        return(this.username.equals(other.getUsername()) &&
               this.password.equals(other.getPassword()));
    }
}
