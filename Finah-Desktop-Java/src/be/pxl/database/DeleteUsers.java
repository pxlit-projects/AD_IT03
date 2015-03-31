package be.pxl.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.pxl.objects.User;
import be.pxl.windows.UsersPanel;

public class DeleteUsers {

	public DeleteUsers(List<User> users, UsersPanel usersPanel) {
		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < users.size(); i++) {
			ids.add(users.get(i).getId());
		}
		DatabaseConnection connection = null;
		try {

			connection = new DatabaseConnection();

			String query = "DELETE FROM user WHERE id IN ( " + String.valueOf(ids.get(0));
			for (int i = 1; i < ids.size(); i++) {
				query += ", " + String.valueOf(ids.get(i));
			}
			query += " )";
			System.out.println(query);

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