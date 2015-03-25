package be.pxl.database;

import java.sql.SQLException;

import be.pxl.objects.User;
import be.pxl.windows.UsersPanel;

public class DeleteUser {

	public DeleteUser(User user, UsersPanel usersPanel) {

		try {

			int id = user.getId();
			DatabaseConnection connection = null;

			try {

				connection = new DatabaseConnection();
				String query = "" + "DELETE FROM user WHERE id = "
						+ String.valueOf(id);
				connection.ExecuteUpdate(query);
				
				usersPanel.refreshTable();

			} catch (Exception e) {
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
