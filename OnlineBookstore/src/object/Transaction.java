package object;

public class Transaction {

	int orderNumber;
	int isbn;
	int qty;
	int promoID;
	double total;
	String title;
	String author;
	double rating;
	
	public Transaction() {
		
	}
	
	public Transaction(int num, int isbn, int qty, int promoID, double total, String author, String title) {
		this.setOrderNumber(num);
		this.setTransactionISBN(isbn);
		this.setTransactionQty(qty);
		this.setTransactionPromoID(promoID);
		this.setTransactionTotal(total);
		this.setTitle(title);
		this.setAuthor(author);
	}
	
	public Transaction(int num, int isbn, int qty, int promoID, double total, String author, String title, double rating) {
		this.setOrderNumber(num);
		this.setTransactionISBN(isbn);
		this.setTransactionQty(qty);
		this.setTransactionPromoID(promoID);
		this.setTransactionTotal(total);
		this.setTitle(title);
		this.setAuthor(author);
		this.setRating(rating);
	}
	
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getTransactionISBN() {
		return isbn;
	}
	public void setTransactionISBN(int isbn) {
		this.isbn = isbn;
	}
	public int getTransactionQty() {
		return qty;
	}
	public void setTransactionQty(int qty) {
		this.qty = qty;
	}
	public int getTransactionPromoID() {
		return promoID;
	}
	public void setTransactionPromoID(int promo) {
		this.promoID = promo;
	}
	public double getTransactionTotal() {
		return total;
	}
	public void setTransactionTotal(double total) {
		this.total = total;
	}

}

