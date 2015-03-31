package be.pxl.database;

import java.sql.SQLException;
import java.util.Calendar;

import be.pxl.objects.User;

public class UpdateUser {

	public UpdateUser(User user) {
		
		DatabaseConnection connection = null;
		try {

			Calendar cal = Calendar.getInstance();
			cal.setTime(user.getBirthDate());
			String date = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);
			connection = new DatabaseConnection();
			String query = "UPDATE user SET login = '"+ user.getLogin() + "', firstname = '" 
					+ user.getFirstname() + "', lastname = '" + user.getLastname() + "', password = '" 
					+ user.getPassword() + "', email = '" + user.getEmail() + "', type = " + user.getType() 
					+ ", street = '" + user.getStreet() + "', town = '" + user.getTown() + "', zipcode = " + user.getZipCode() + ", birthdate = '" + date
					+ "' WHERE id = " + user.getId();
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
