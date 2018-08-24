package object;

/*
Creates a SystemAdmin Object
*/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import persistent.AdminDA;
import persistent.BookDA;
import persistent.DbAccessImpl;
import persistent.UserDA;

public class SystemAdmin extends User {
	
	/*
	  Parameters: None
	  Return Value: Constructor
	  Description: Creates Object
	  */
	
	public SystemAdmin () {
		
	}
	
	/*
	Parameters: None
	Return Value: List<User>
	Description: returns a list of users in the database.
	*/

	public List<User> viewUsers() {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		ResultSet set = UserDA.getUsers(con);
		List<User> userList = new ArrayList<User>();
		
		try {
			while (set.next())
			{
				User user = new User();
				user.setUserId(set.getInt("userID"));
				user.setUserType(set.getString("userType"));
				user.setFname(set.getString("fName"));
				user.setLname(set.getString("lName"));
				user.setEmail(set.getString("email"));
				user.setStatus(set.getString("status"));
				userList.add(user);
			}
		} catch (SQLException e) {
			userList.clear();
		}
		return userList;
	}
	
	/*
	Parameters: int userID, int value
	Return Value: int
	Description: authorizes a user and returns a 1 or 0 if the request was succssful or unsuccessful respectively
	*/

	public int authorizeUser(int userID, int value) {
		// TODO Auto-generated method stub
		return AdminDA.authorizeUser(userID, value);
	}
	
	/*
	Parameters: int userID
	Return Value: int
	Description: suspends a user and returns a 1 or 0 if the request was succssful or unsuccessful respectively
	*/

	public int suspendUser(int userID) {
		return AdminDA.suspendUser(userID);
	}
	
	/*
	Parameters: int userID
	Return Value: int
	Description: unsuspends a user and returns a 1 or 0 if the request was succssful or unsuccessful respectively
	*/

	public int unsuspendUser(int userID) {
		// TODO Auto-generated method stub
		return AdminDA.unsuspendUser(userID);
	}
	
	/*
	Parameters: int isbn
	Return Value: int
	Description: deletes a book and returns a 1 or 0 if the request was succssful or unsuccessful respectively
	*/

	public int deleteBook(int isbn) {
		// TODO Auto-generated method stub
		return BookDA.deleteBook(isbn);
	}

}
