package be.pxl.database;

import java.sql.SQLException;

import be.pxl.objects.User;
import be.pxl.windows.UsersPanel;

public class DeleteUser {

	public DeleteUser(User user, UsersPanel usersPanel) {
		int id = user.getId();
		DatabaseConnection connection = null;
		try {

			connection = new DatabaseConnection();

			String query = "DELETE FROM user WHERE id = " + String.valueOf(id);

			connection.ExecuteUpdate(query);
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
