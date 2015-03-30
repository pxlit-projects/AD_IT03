package be.pxl.database;

import java.sql.SQLException;
import java.util.Calendar;

import be.pxl.objects.User;

public class AddUser {

	public AddUser(User user) {
		DatabaseConnection connection;
		try {

			connection = new DatabaseConnection();
			Calendar cal = Calendar.getInstance();
			cal.setTime(user.getBirthDate());
			String date = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
			System.out.println("add user:" + date);
			String query = "INSERT INTO user (login, firstname,lastname, password, email, street, town, zipCode, birthdate, type) VALUES ('"
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
					+ user.getTown() 
					+ "', '" 
					+ user.getZipCode() 
					+ "', '"
					+ date
					+ "', '"
					+ user.getType() + "')";

			connection.ExecuteUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

}
