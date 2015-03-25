package be.pxl.database;

import java.sql.SQLException;

import be.pxl.objects.User;

public class AddUser {

	public AddUser(User user) {
		DatabaseConnection connection;
		try {

			connection = new DatabaseConnection();

			String query = "INSERT INTO user (login, firstname,lastname, password, email, street, town, zipCode, type) VALUES ('"
					+ user.getLogin()
					+ "', '"
					+ user.getFirstname()
					+ "', '"
					+ user.getLastname()
					+ "', '"
					+ user.getPassword()
					+ "', '"
					+ user.getEmail()
					+ "', '"
					+ user.getStreet()
					+ "', '"
					+ user.getTown() + "', '" + user.getZipCode() + "', '"
					// + user.getBirthDate()
					// + "', '"
					+ user.getType() + "')";

			connection.ExecuteUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

}
