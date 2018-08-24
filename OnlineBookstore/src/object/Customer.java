package object;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
Represents a Customer Object
*/

import java.util.List;

import persistent.BookDA;
import persistent.CartDA;
import persistent.CreditCardDA;
import persistent.CustomerDA;
import persistent.OrderDA;

public class Customer extends User {
	
	/*
	Parameters: int parseInt
	Return Value: List<Address>
	Description: returns user addresses
	*/

	public List<Address> getAddresses(int parseInt) {
		// TODO Auto-generated method stub
		return CustomerDA.getAddresses(parseInt);
	}
	
	/*
	Parameters: int userID
	Return Value: List<CreditCard>
	Description: returns user credit cards
	*/

	public List<CreditCard> viewCards(int userID) {
		// TODO Auto-generated method stub
		return CreditCardDA.viewCards(userID);
	}
	
    public List<Order> viewHistory(int parseInt) {
		return OrderDA.viewHistory(parseInt);
	}
    
    public int getMaxOrderNumber() {
		return Order.getMaxOrderNumber();
	}
	
	public Cart getCartByID(int id) {
		Cart cart = new Cart();
		ResultSet set = CartDA.getCartByID(id);
		try {
			while(set.next()) {
				cart.setCartID(id);
				cart.setIsbn(set.getInt("isbn"));
				cart.setPromoID(0);
				cart.setQty(set.getInt("qty"));
				cart.setTotal(set.getDouble("total"));
				cart.setUserID(set.getInt("userID"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return cart;
	}

	public int rateBook(int order, int numIsbn, int rating) {
		// TODO Auto-generated method stub
		return BookDA.rateBook(order, numIsbn, rating);
	}

}
