/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.pxl.daanvanrobays.pojo;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    private int zipcode;
    private Date birthdate = new Date();
    
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
		try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(password.getBytes());
	 
	        byte byteData[] = md.digest();
	 
	       
	        //convert the byte to hex format for md5
	        StringBuffer hexString = new StringBuffer();
	    	for (int i=0;i<byteData.length;i++) {
	    		String hex=Integer.toHexString(0xff & byteData[i]);
	   	     	if(hex.length()==1) hexString.append('0');
	   	     	hexString.append(hex);
	    	}
			this.password = hexString.toString();
	 } catch (Exception	ex) {
		 System.out.print("exx");
	 }
		
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

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + ", login=" + login + ", password=" + password
				+ ", email=" + email + ", Type=" + Type + ", street=" + street
				+ ", town=" + town + ", zipCode=" + zipcode + ", birthDate="
				+ birthdate + "]";
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		/*
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		String birth = f.format(birthdate.toString());
		try {
			birthdate = f.parse(birth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		this.birthdate = birthdate;
	}
    
    
    
}
