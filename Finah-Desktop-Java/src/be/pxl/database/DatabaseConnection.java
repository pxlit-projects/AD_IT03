package be.pxl.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

	private String url = "jdbc:mysql://http://138.91.50.27/:3306/db_project";
	// Class.forName("com.mysql.jdbc.Driver");

	private final String USER = "appdevit03";
	private final String PASS = "Azurelogin_03";
	private Connection connection;
	private ResultSet resultSet;
	private Statement statement;

	public DatabaseConnection() throws SQLException {
		connection = DriverManager.getConnection(url, USER, PASS);
		statement = connection.createStatement();
	}

	public ResultSet ExecuteQuery(String query) throws SQLException {
		resultSet = statement.executeQuery(query);
		return resultSet;
	}
	
	public void ExecuteUpdate(String query) throws SQLException {
		statement.executeUpdate(query);
	}

	public void deleteConnection() throws SQLException {
		if (statement != null)
			statement.close();
		if (resultSet != null)
			resultSet.close();
		if (connection != null)
			connection.close();
	}

	public String getConnectionURL() {
		return url;
	}
}
