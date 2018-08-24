package entity;

import java.util.ArrayList;

import object.Cart;

public interface ICart {
	public int getCartID();
	public void setCartID(int cartID);
	
	public int getPromoID();
	public void setPromoID(int promoID);
	
	public int getUserID();
	public void setUserID(int userID);
	
	public int getIsbn();
	public void setIsbn(int isbn);
	
	public int getQty();
	public void setQty(int qty);
	
	public double getTotal();
	public void setTotal(double total);
	
	public int addToCart();
	public int deleteFromCart();
	public int updateCart();
	public ArrayList<Cart> getCart(int userID);
}
