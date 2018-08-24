package persistent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

public class PromotionDA {
	
	// Adds a promotion to the database.

	public static int addPromoToDA(int promoID, String name, double percent, String expiration) {
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "INSERT INTO onlinebookstoredb.promotion (promoID, pName, percentage, expiration) VALUES"
				+ " ('" + promoID + "', '" + name + "', '" + percent + "', '" + expiration + "')";
		
		int value = DbAccessImpl.create(con, query);
		DbAccessImpl.disconnect(con);
		return value;
	}
	
	// Gets a list of emails for Customers that are subscribed to promotions.
	
	public static ArrayList<String> getEmailList() {
        ArrayList<String> list = new ArrayList<String>();
        Connection con = (Connection) DbAccessImpl.connect();
        String query = "SELECT email FROM user WHERE userType = 'Customer' AND subscribed = '1'";
        ResultSet rs = DbAccessImpl.retrieve(con, query);
        try {
			while(rs.next()) {
			    list.add(rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        DbAccessImpl.disconnect(con);
        return list;
    }
	
	// Checks to see if a promo code is already in use.

	public static int checkPromo(int parseInt) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		String checkQuery = "SELECT * from promotion WHERE promoID = '" + parseInt + "'";
		ResultSet set = DbAccessImpl.retrieve(con, checkQuery);
		try {
			if (set.next())
			{
				return 1;
			}
			else
			{
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	// Gets a ResultSet for a promotion from the database.
	
	public static ResultSet getPromotion(int promoID) {
		String query = "SELECT * FROM promotion WHERE promoID = '" + promoID + "'";
		Connection con = (Connection) DbAccessImpl.connect();
		ResultSet set = null;
		set = DbAccessImpl.retrieve(con, query);
		return set;
	}
}
