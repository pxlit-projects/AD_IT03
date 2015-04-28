package be.pxl.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

	private String url = "jdbc:mysql://81.4.126.109:3306/db_project";
	// Class.forName("com.mysql.jdbc.Driver");

	private final String USER = "luke";
	private final String PASS = "lukeluke";
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
