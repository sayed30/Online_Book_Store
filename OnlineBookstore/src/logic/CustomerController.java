package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import entity.IBook;
import object.Address;
import object.Book;
import object.Cart;
import object.CreditCard;
import object.Customer;
import object.Order;
import persistent.BookDA;
import persistent.CartDA;
import persistent.DbAccessImpl;

public class CustomerController {
	
	//This function returns a list of books for the customer to browse
	
	public List<IBook> browseBooks() {
		// TODO Auto-generated method stub
		List<IBook> returnList = new ArrayList<IBook>();
		Customer customer = new Customer();
		returnList = customer.browseBooks();
		return returnList;
	}
	
	//This function gets the addresses of the customer

	public List<Address> getAddresses(int parseInt) {
		// TODO Auto-generated method stub
		Customer cust = new Customer();
		return cust.getAddresses(parseInt);
	}
	
	//This function lets the customer add an address to his profile

	public int addAddress(int userId, String street, String city, String state, String zip) {
		// TODO Auto-generated method stub
		Address add = new Address(street, city, state, zip);
		return add.addAddress(userId);
	}
	
	//This function lets the customer edit an address

	public int editAddress(int id, String street, String city, String state, String zip) {
		// TODO Auto-generated method stub
		Address add = new Address(street, city, state, zip, id);
		return add.editAddress();
	}
	
	//This function lets the cutomer delete address	

	public int deleteAddress(int id) {
		// TODO Auto-generated method stub
		Address add = new Address();
		add.setId(id);
		return add.deleteAddress();
	}
	
	//This function lets the customer add a credit card to their account.

	public int addCard(String number, String expire, String type, int userID, String csc) {
		// TODO Auto-generated method stub
		CreditCard card = new CreditCard(number, expire, type, csc);
		return card.addCard(userID);
	}
	
	//This function lets the customer view list of cards.

	public List<CreditCard> viewCards(int userID) {
		// TODO Auto-generated method stub
		Customer cust = new Customer();
		return cust.viewCards(userID);
	}
	
	//This function deletes the card

	public int deleteCard(int id) {
		// TODO Auto-generated method stub
		CreditCard card = new CreditCard();
		card.setId(id);
		return card.deleteCard();
	}
	
	//This function lets the customer to view order history	
	
	public List<Order> viewHistory(int parseInt) {
		Customer cust = new Customer();
		return cust.viewHistory(parseInt);
	}
	
	//This function gets the book info for customer	
	
	public IBook getBookInfo(int isbn) {
		IBook book = new Book();
		book.getBookInfo(isbn);
		return book;
	}
	
	//This function gets the maximum order number from the order table
	
	public int getMaxOrderNumber() {
		return Order.getMaxOrderNumber();
	}
	
	//This function gets the maximum cart id from the cart table
	
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
	
	//This function gets the title of the book through isbn	
	
	public String getTitleOfBook(int isbn) {
		Connection con = (Connection) DbAccessImpl.connect();
		ResultSet rs = BookDA.getBookInfo(isbn, con);
		try {
			if(rs.next()) {
				return rs.getString("title");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//This function lets the customer rate a book

	public int rateBook(int order, int numIsbn, int rating) {
		// TODO Auto-generated method stub
		Customer customer = new Customer();
		return customer.rateBook(order, numIsbn, rating);
	}
}
