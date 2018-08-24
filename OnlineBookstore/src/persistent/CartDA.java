package persistent;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class CartDA {
	
	// Adds an item to the cart in the database.
	
	public static int addtocartDA(int cartID, int userID, int promoID, int isbn, int qty, double total) {
		int value = 0;
		Connection con=(Connection) DbAccessImpl.connect();
		String query = "SELECT * FROM cart WHERE isbn = '" + isbn + "'";
		ResultSet set = DbAccessImpl.retrieve(con, query);
		
		try {
			if(set.next()) {
				qty = set.getInt("qty") + 1;
				total = qty*total;
				query = "UPDATE cart SET qty='" + qty + "', total='" + total + "' WHERE isbn='" + isbn + "'";
				
				value = DbAccessImpl.update(con, query);
			}else{
				query= "INSERT INTO cart (cartID, userID, promoID, isbn, qty, total) VALUES"
						+ " ('" + cartID + "', '" + userID + "', '" + promoID + "','" + isbn + "', '" + qty + "', '" + total + "')";
				
				value = DbAccessImpl.update(con, query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	
	// Gets the carts id.
	
	public static int getCartID() {
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "SELECT * FROM cart where cartID = (SELECT MAX(cartID) from cart)";
		ResultSet set = null;
		set = DbAccessImpl.retrieve(con, query);
		int value = 0;
		try {
			if (set.next()) {
				value = set.getInt("cartID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value+1;
	}
	
	// Updates a row in the cart table.
	
	public static int updateCart(int userID, int isbn, int qty, double total) {
		Connection con=(Connection) DbAccessImpl.connect();
		String query = "UPDATE cart SET qty='" + qty + "', total='" + total + "' WHERE isbn='" + isbn + "' AND userID='" + userID + "'";
		return DbAccessImpl.update(con, query);
	}
	
	// Deletes a row from the cart table.
	
	public static int deleteFromCartDA(int userID, int isbn) {
		Connection con=(Connection) DbAccessImpl.connect();
		String query = "DELETE FROM cart where userID = '" + userID + "'AND isbn = '" + isbn +"'";
		
		int value = DbAccessImpl.update(con, query);
		return value;
	}
	
	// Deletes all cart items.
	
	public static int deleteCartItems(int userID) {
		Connection con=(Connection) DbAccessImpl.connect();
		String query = "DELETE FROM cart where userID = '" + userID + "'";
		
		int value = DbAccessImpl.update(con, query);
		return value;
	}
	
	// Retrieves a user's cart from the database.
	
	public static ResultSet getCart(int userID, Connection con) {
		String query = "SELECT * FROM cart WHERE userID = '" + userID + "'";
		ResultSet set = null;
		set = DbAccessImpl.retrieve(con, query);
		return set;
	}
	
	// Retrieves a row from the cart based on the id.
	
	public static ResultSet getCartByID(int id) {
		Connection con=(Connection) DbAccessImpl.connect();
		String query = "SELECT * FROM cart WHERE cartID = '" + id + "'";
		ResultSet set = null;
		set = DbAccessImpl.retrieve(con, query);
		return set;
	}
}
