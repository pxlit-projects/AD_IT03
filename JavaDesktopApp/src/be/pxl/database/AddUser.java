package be.pxl.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import be.pxl.objects.User;

public class AddUser {

	public AddUser(User user) {
		try {

			DatabaseConnection connectie = new DatabaseConnection();
			String url = connectie.getConnectionURL();

			Connection conn = DriverManager.getConnection(url, "luke",
					"lukeluke");
			java.sql.Statement st = conn.createStatement();

			String query = "INSERT INTO user (login, firstname,lastname, password, email, type) VALUES ('"
					+ user.getLogin()
					+ "', '"
					+ user.getFirstname()
					+ "', '"
					+ user.getLastname()
					+ "', '"
					+ user.getPassword()
					+ "', '"
					+ user.getEmail() + "' + '" + user.getType().getId() + "')";

			st.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

}
