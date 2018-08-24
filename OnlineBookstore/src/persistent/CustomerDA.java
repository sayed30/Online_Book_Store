package persistent;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import object.Address;

public class CustomerDA {
	
	// Creates a new Customer in the database.
	
	public int createNewCustomer(String fname, String lname, String email, String password, int code, Boolean subscribe, String phone)
	{
		int sub = 0;
		if (subscribe == true)
		{
			sub = 1;
		}
		else
		{
			sub = 0;
		}
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "INSERT INTO user (fName, lName, email, userType, userPassword, userCode, subscribed, phone) VALUES"
				+ " ('" + fname + "', '" + lname + "', '" + email + "', 'Customer', '" + password + "', '" + code + "', '" + sub + "', '" + phone + "')";
		
		int value = DbAccessImpl.create(con, query);
		DbAccessImpl.disconnect(con);;
		return value;
	}
	
	// Gets all of the addresses for a user.

	public static List<Address> getAddresses(int parseInt) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "SELECT * FROM address WHERE userID = '" + parseInt + "'";
		ResultSet set = DbAccessImpl.retrieve(con, query);
		
		List<Address> addressList = new ArrayList<Address>();
		try {
			while (set.next())
			{
				String street = set.getString("street");
				String city = set.getString("city");
				String state = set.getString("state");
				String zip = set.getString("zipcode");
				int id = set.getInt("addressID");
				Address a = new Address(street, city, state, zip, id);
				addressList.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addressList;
	}
}
