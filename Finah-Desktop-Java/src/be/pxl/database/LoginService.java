package be.pxl.database;

import java.security.MessageDigest;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import be.pxl.json.UserDb;
import be.pxl.objects.User;

public class LoginService {
	public LoginService() {
	}

	public boolean loginCheck(String login, String password) {
		String query;
		boolean state = false;
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
	    	System.out.println("Digest(in hex format):: " + hexString.toString()); 
			password = hexString.toString();
	 } catch (Exception	ex) {
		 System.out.print("exx");
	 }

		try {

//			DatabaseConnection connection = new DatabaseConnection();
//
//			query = "SELECT login, password FROM user WHERE login='" + login
//					+ "' AND password='" + password + "';";
//
//			ResultSet result = connection.ExecuteQuery(query);

			UserDb users = new UserDb();
			List<User> userList = new ArrayList<User>();
			userList = users.readUsers();
			
			for (User user : userList) {
				if (user.getLogin().equalsIgnoreCase(login) && user.getPassword().equalsIgnoreCase(password)){
				state = true;
				return state;
				}
				state=false;
			}
			
			
			
			//state = result.first();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return state;
	}
}
