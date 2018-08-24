package persistent;

/* Bradley Reeves
 * 
 * 
 * This is a class that connects to the database
 * and has function to retrieve, edit, insert,
 * and delete data from the database.
 * 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbAccessImpl extends DbAccessConfiguration {

	// Creates a static Connection object to use in the class.
	
	//private static Connection c = null;
	
	// Connects to the Database.
	
	public static Connection connect() {
		Connection c = null;
		try {
			Class.forName(DB_DRIVER_NAME);
			c = DriverManager.getConnection(DB_CONNECTION_URL, DB_CONNECTION_USERNAME, DB_CONNECTION_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	} // connect()
	
	// Retrieves a ResultSet for a Query with the static connection.
	
	public static ResultSet retrieve(Connection c, String query) {
		if (c == null)
		{
			connect();
		}
		ResultSet set = null;
		try {
			Statement state = c.createStatement();
			set = state.executeQuery(query);
			return set;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return set;
	} // retrieve()
	
	// Inserts data into the database.
	
	public static int create(Connection c, String query) {
		if (c == null)
		{
			connect();
		}
		int check = 0;
		try {
			Statement state = c.createStatement();
			check = state.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect(c);
		return check;
	} // create()
	
	// Updates data in the database.
	
	public static int update(Connection c, String query) {
		//Connection c = connect();
		if (c == null)
		{
			connect();//connect();
		}
		int check = 0;
		try {
			Statement state = c.createStatement();
			check = state.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		disconnect(c);
		return check;
	} // update()
	
	// Deletes data from the database.
	
	public static int delete(Connection c, String query) {
		//Connection c = connect();
		if (c == null)
		{
			connect();//connect();
		}
		int check = 0;
		try {
			Statement state = c.createStatement();
			check = state.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect(c);
		return check;
	} // delete()
	
	// Disconnects from the database using the static connection.
	
	public static void disconnect(Connection c) {
		try {
			if (c != null) {
				c.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // disconnect()
}
