package object;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import entity.ICart;
import persistent.CartDA;
import persistent.DbAccessImpl;

public class Cart implements ICart{
	private int cartID;
	private int promoID;
	private int userID;
	private int isbn;
	private int qty;
	private double total;
	
	public Cart(int cartID, int userID, int promoID, int isbn, int qty, double total)
	{
		setCartID(cartID);
		setPromoID(promoID);
		setUserID(userID);
		setIsbn(isbn);
		setQty(qty);
		setTotal(total);
	}
	
	public Cart() {
		
	}
	
	public int getCartID() {
		return cartID;
	}
	
	public void setCartID(int cartID) {
		this.cartID = cartID;
	}
	
	public int getPromoID() {
		return promoID;
	}
	
	public void setPromoID(int promoID) {
		this.promoID = promoID;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getIsbn() {
		return isbn;
	}
	
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	
	public int getQty() {
		return qty;
	}
	
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public int addToCart() {
		return CartDA.addtocartDA(cartID, userID, promoID, isbn, qty, total);
	}
	
	public int updateCart() {
		return CartDA.updateCart(userID, isbn, qty, total);
	}
	
	public int deleteFromCart() {
		return CartDA.deleteFromCartDA(userID, isbn);
	}
	
	public ArrayList<Cart> getCart(int userID) {
		ResultSet set = null;
		Connection con = (Connection) DbAccessImpl.connect();
		set = CartDA.getCart(userID, con);
		ArrayList<Cart> totalCart = new ArrayList<Cart>();
		try {
			while (set.next()) {
				Cart cart = new Cart(set.getInt("cartID"), set.getInt("userID"), set.getInt("promoID"), set.getInt("isbn"), set.getInt("qty"), set.getDouble("total"));
				totalCart.add(cart);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalCart;
	}
}
