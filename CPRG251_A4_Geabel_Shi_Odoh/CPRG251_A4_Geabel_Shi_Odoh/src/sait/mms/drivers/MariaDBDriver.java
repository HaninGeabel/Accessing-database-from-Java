package sait.mms.drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sait.mms.contracts.DatabaseDriver;

/**
 * This class implements the DatabaseDriver interface
 * @author Deborah Odoh
 * @author Hanin Geabel
 * @author Henry Shi
 *
 */
public class MariaDBDriver implements DatabaseDriver {
	private static final String SERVER = "localhost";
	private static final int PORT = 3306;
	private static final String DATABASE = "cprg251";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "password";
	Connection connection = null;

	/**
	 * Connects to the database.
	 * @throws SQLException
	 */
	@Override
	public void connect() throws SQLException {
		String dsn = this.getDsn();
		connection = DriverManager.getConnection(dsn);
	}
	
	
	/**
	 * Retrieves the data source name
	 * @return
	 */
	private String getDsn() {
		String dataSourceName = String.format("jdbc:mariadb://%s:%d/%s?user=%s&password=%s", SERVER, PORT, DATABASE, USERNAME, PASSWORD);
		return dataSourceName;
		
	}

	/**
	 * Performs a retrieval from the database (ie: SELECT)
	 * @param query Query to send to database.
	 * @return Returns the results as a ResultSet
	 * @throws SQLException Thrown if problem performing query.
	 */
	@Override
	public ResultSet get(String query) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet results = statement.executeQuery(query);
		return results;
	}

	/**
	 * Performs an update query (UPDATE, DELETE, DROP, etc.) on the database.
	 * @param query Query to send to database.
	 * @return Number of rows modified.
	 * @throws SQLException
	 */
	@Override
	public int update(String query) throws SQLException {
		Statement statement = connection.createStatement();
		int rows = statement.executeUpdate(query);
		return rows;
	}

	/**
	 * Disconnects from the database.
	 * @throws SQLException
	 */
	@Override
	public void disconnect() throws SQLException {
		if(connection != null && !connection.isClosed())
			connection.close();
		
	}

}
