package be.pxl.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import be.pxl.objects.User;
import be.pxl.windows.UsersPanel;

public class DeleteUser {

	public DeleteUser(User user, UsersPanel usersPanel) {
		int id = user.getId();
		DatabaseConnection connection = null;
		try {

			connection = new DatabaseConnection();
			String url = connection.getConnectionURL();

			Connection conn = DriverManager.getConnection(url, "luke",
					"lukeluke");
			Statement st = conn.createStatement();

			String query = "DELETE FROM user WHERE id = " + String.valueOf(id);

			st.execute(query);
			usersPanel.refreshTable();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				connection.deleteConnection();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
		}
	}
}
