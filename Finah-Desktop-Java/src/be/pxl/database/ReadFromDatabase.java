package be.pxl.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.pxl.objects.User;
import be.pxl.objects.UserType;

public class ReadFromDatabase {
	
	private List<User> users;
	private List<UserType> userTypes;

	public ReadFromDatabase() {
		
	}
	
	public List<User> readUsers() {
		users = new ArrayList<User>();
		DatabaseConnection connection = null;
		try {
			connection = new DatabaseConnection();
			String query = "SELECT * FROM user";
			ResultSet result = connection.ExecuteQuery(query);
			users = new ArrayList<User>();
			while (result.next()) {
				User user = new User();
				int id = result.getInt("id");
				String login = result.getString("login");
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String password = result.getString("password");
				String email = result.getString("email");
				String street = result.getString("street");
				String town = result.getString("town");
				int zipcode = result.getInt("zipcode");
//				Date birthdate = result.getDate("birthdate");
				int type = Integer.parseInt(result.getString("type"));
				
				user.setId(id);
				user.setLogin(login);
				user.setFirstname(firstname);
				user.setLastname(lastname);
				user.setPassword(password);
				user.setEmail(email);
				user.setStreet(street);
				user.setTown(town);
				user.setZipCode(zipcode);
//				user.setBirthDate(birthdate);
				user.setType(type);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.deleteConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return users;
	}
	
	public List<UserType> readUserTypes() {
		userTypes = new ArrayList<UserType>();
		DatabaseConnection connection = null;
		try {
			connection = new DatabaseConnection();
			String query = ""
					+ "SELECT id, description, screenname "
					+ "FROM usertype";
			ResultSet result = connection.ExecuteQuery(query);
			users = new ArrayList<User>();
			while (result.next()) {
				int id = result.getInt("id");
				String typeName = result.getString("screenname");
				String description = result.getString("description");
				
				userTypes.add(new UserType(id, typeName, description));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.deleteConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return userTypes;
	}

}