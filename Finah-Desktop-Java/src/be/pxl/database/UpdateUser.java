package be.pxl.database;

import java.sql.SQLException;

import be.pxl.objects.User;

public class UpdateUser {

	public UpdateUser(User user) {
		
		DatabaseConnection connection = null;
		try {

			connection = new DatabaseConnection();
			String query = "UPDATE user SET login = '"+ user.getLogin() + "', firstname = '" 
					+ user.getFirstname() + "', lastname = '" + user.getLastname() + "', password = '" 
					+ user.getPassword() + "', email = '" + user.getEmail() + "', type = " + user.getType() 
					+ ", street = '" + user.getStreet() + "', town = '" + user.getTown() + "', zipcode = " + user.getZipCode() 
					+ " WHERE id = " + user.getId();
			System.out.println(query);
			connection.ExecuteUpdate(query);
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
	}

}
