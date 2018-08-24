package persistent;

import com.mysql.jdbc.Connection;

public class AdminDA {

	// Authorizes a user as a certain type of User.
	
	public static int authorizeUser(int userID, int value) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		String type = "";
		int check = -1;
		if (value != 0)
		{
			if (value == 1)
			{
				type = "Customer";
			}
			else if (value == 2)
			{
				type = "Shipping";
			}
			else if (value == 3)
			{
				type = "SystemAdmin";
			}
			else if (value == 4)
			{
				type = "Manager";
			}
			String query = "UPDATE user SET userType = '" + type + "' WHERE userID = '" + userID + "'";
			check = DbAccessImpl.update(con, query);
		}
		return check;
	}

	// Suspends a User's account.
	
	public static int suspendUser(int userID) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "UPDATE user SET status = 'suspended' WHERE userID = '" + userID + "'";
		int check = DbAccessImpl.update(con, query);
		return check;
	}
	
	// Unsuspend's a User's account.

	public static int unsuspendUser(int userID) {
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "UPDATE user SET status = 'verified' WHERE userID = '" + userID + "'";
		int check = DbAccessImpl.update(con, query);
		return check;
	}

}
