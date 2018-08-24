package persistent;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import object.User;
import object.UserProfile;

public class UserDA {

	// Retrieves a user's info from the database and returns a User object.
	
	public User getUserInfo(String email2, String pass, Connection con) {
		String query = "SELECT * FROM user WHERE email = '" + email2 + "' AND userPassword = '" + pass + "'";
		ResultSet set = DbAccessImpl.retrieve(con, query);
		User user = new User();
		try
		{
			if (set.next())
			{
				user.setFname(set.getString("fName"));
				user.setLname(set.getString("lName"));
				user.setEmail(set.getString("email"));
				user.setUserId(set.getInt("userID"));
				user.setUserType(set.getString("userType"));
				user.setCode(set.getInt("userCode"));
				user.setStatus(set.getString("status"));
				user.setSubscribed(set.getBoolean("subscribed"));
			}
			else
			{
				user = null;
			}
		}
		catch (Exception e)
		{
			user = null;
		}
		return user;
	}

	// Verifies a user's account
	
	public int verifyAccount(String attribute) {
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "UPDATE user SET status = 'verified' WHERE email = '" + attribute + "'";
		int value = DbAccessImpl.update(con, query);
		DbAccessImpl.disconnect(con);
		return value;
	}
	
	// Checks to see if an email and password combination exists in the database.
	
	public ResultSet checkLogin(String email2, String pass, Connection con) {
		String query = "SELECT * FROM user WHERE email = '" + email2 + "' AND userPassword = '" + pass + "'";
		ResultSet set = DbAccessImpl.retrieve(con, query);
		return set;
	}
	
	// Changes the password for a user.

	public int changePassword(String email2, String oldPassword, String newPassword) {


        Connection con = (Connection) DbAccessImpl.connect();
        String query = "SELECT * FROM user WHERE email = '" + email2 + "'";
        ResultSet set = DbAccessImpl.retrieve(con, query);
        int id = 0;
        int value;
        try {
        	if (set.next())
        	{
        		if (oldPassword.equals(set.getString("userPassword")))
        		{
	        		id = set.getInt("userID");
	        		query = "UPDATE user SET userPassword = '" + newPassword + "' WHERE userID = " + id;
	                value = DbAccessImpl.update(con, query);
        		}
        		else
        		{
        			value = 0;
        		}
        	}
        	else
        	{
        		value = 0;
        	}
        } catch (Exception e) {
            value = 0;
            
        }
        DbAccessImpl.disconnect(con);
        return value;
	}

	// Resets a user's password to a random string, which is then later mailed to the user.
	
	public int recoverPassword(String email2, String newPass) {
		// TODO Auto-generated method stub

        Connection con = (Connection) DbAccessImpl.connect();
        String query = "SELECT * FROM user WHERE email = '" + email2 + "'";
        ResultSet set = DbAccessImpl.retrieve(con, query);
        int id;
        int value = 0;
        try {
			if (set.next())
			{
				id = set.getInt("userID");
	        	query = "UPDATE user SET userPassword = '" + newPass + "' WHERE userID = '" + id + "'";
	        	value = DbAccessImpl.update(con, query);
			}
		} catch (SQLException e) {
			
		}
        DbAccessImpl.disconnect(con);
		return value;
	}
	
	// Gets a ResultSet of all of the users in the database.

	public static ResultSet getUsers(Connection con) {
		// TODO Auto-generated method stub
		String query = "SELECT userID, userType, fName, lName, email, status FROM user";
		ResultSet set = DbAccessImpl.retrieve(con, query);
		return set;
	}
	
	// Checks to see if an email already exists in the database.

	public static ResultSet checkEmail(Connection con, String email2) {
		// TODO Auto-generated method stub
		String query = "SELECT email FROM user WHERE email = '" + email2 + "'";
		ResultSet set = DbAccessImpl.retrieve(con, query);
		return set;
	}
	
	// Gets the details for a certain user based on their email.

	public UserProfile getDetails(String email) {
        Connection con = (Connection) DbAccessImpl.connect();
        String query = "SELECT * FROM user WHERE email = '" + email + "'";
        ResultSet rs = DbAccessImpl.retrieve(con, query);

        UserProfile userProfile = null;

        try {
			if(rs.next()) {
			    userProfile = new UserProfile(rs.getString("fname"), rs.getString("lname"), rs.getString("phone"), rs.getString("email"));
			    userProfile.setSubscribe(rs.getBoolean("subscribed"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return userProfile;
    }
	
	// Saves a user's changes to their profile information.
	
	public static int saveProfile(String email, String fname, String lname, String phone, Boolean subscribe) {
		Connection con = (Connection) DbAccessImpl.connect();
		int temp = 0;
		if (subscribe == true)
		{
			temp = 1;
		}
		else
		{
			temp = 0;
		}
		String query = "UPDATE user SET " + " fName = " + "'" + fname + "', " + " lName = " + "'" + lname + "', " + " phone = " + "'" + phone + "', " 
				+ "subscribed = '" + temp + "' WHERE email = " + "'" + email +"'";
		
		return DbAccessImpl.update(con, query);
	}
}
