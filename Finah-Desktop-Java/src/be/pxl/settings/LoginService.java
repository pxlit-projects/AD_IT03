package be.pxl.settings;

import java.sql.ResultSet;

import be.pxl.database.DatabaseConnection;

public class LoginService {
	public LoginService() {}
	
	public boolean loginCheck(String login, String password){
	    String query;
	    boolean state = false;

	    try {
	      
	    	DatabaseConnection connection = new DatabaseConnection();
	    	
	        query = "SELECT login, password FROM user WHERE login='" + login + "' AND password='" + password + "';";
	        
	       ResultSet result = connection.ExecuteQuery(query);
	       
	        state = result.first(); 
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return state;
	}
}
