/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.pxl.objects;

import java.awt.Image;
import java.util.Date;


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
    private String street;
    private String town;
    private String zipCode;
    private Date birthDate = new Date();
    private Image profilePicture;
    
    public User() {
    	
    }
    
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

	public UserType getType() {
		return Type;
	}

	public void setType(UserType type) {
		Type = type;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Image getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(Image profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}
    
}
