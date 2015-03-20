package be.pxl.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	
	private String url = "jdbc:mysql://81.4.126.109:3306/db_project";
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
	
	public void deleteConnection() throws SQLException {
		statement.close();
		resultSet.close();
		connection.close();
	}

}
