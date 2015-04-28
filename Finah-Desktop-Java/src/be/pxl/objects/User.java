/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.pxl.objects;

import java.awt.Image;
import java.util.Date;

import com.google.gson.annotations.SerializedName;


public class User {
    private int id;
    private String firstname;
    private String lastname;
    private String login;
    private String password;
    private String email;
    @SerializedName("type") private int Type;
    private String street;
    private String town;
    private int zipCode;
    private Date birthDate = new Date();
    private Image profilePicture;
    
    public User() {
    	
    }
    
    public User(int id, String firstname, String lastname, String login, String password, String email, int type) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.login = login;
    this.password = password;
    this.email = email;
    this.Type = type;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
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

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + ", login=" + login + ", password=" + password
				+ ", email=" + email + ", Type=" + Type + ", street=" + street
				+ ", town=" + town + ", zipCode=" + zipCode + ", birthDate="
				+ birthDate + ", profilePicture=" + profilePicture + "]";
	}
    
    
    
}
