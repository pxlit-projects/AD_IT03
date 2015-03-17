/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Jordy
 */
public class User {
    private int ID;
    private String Firstname;
    private String Lastname;
    private String ScreenName;
    private String Password;
    private String Email;
    private UserType Type;
    
    
    public User(int ID, String Firstname, String Lastname, String ScreenName, String Password, String Email, UserType Type) {
    this.ID = ID;
    this.Firstname = Firstname;
    this.Lastname = Lastname;
    this.ScreenName = ScreenName;
    this.Password = Password;
    this.Email = Email;
    this.Type = Type;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the Firstname
     */
    public String getFirstname() {
        return Firstname;
    }

    /**
     * @param Firstname the Firstname to set
     */
    public void setFirstname(String Firstname) {
        this.Firstname = Firstname;
    }

    /**
     * @return the Lastname
     */
    public String getLastname() {
        return Lastname;
    }

    /**
     * @param Lastname the Lastname to set
     */
    public void setLastname(String Lastname) {
        this.Lastname = Lastname;
    }

    /**
     * @return the ScreenName
     */
    public String getScreenName() {
        return ScreenName;
    }

    /**
     * @param ScreenName the ScreenName to set
     */
    public void setScreenName(String ScreenName) {
        this.ScreenName = ScreenName;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    
}
