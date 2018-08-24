package logic;

import java.util.ArrayList;
import java.util.List;

import entity.IBook;
import freemarker.template.SimpleHash;
import object.Book;
import object.Promotion;
import object.SystemAdmin;
import object.User;
import persistent.BookDA;
import persistent.OrderDA;

public class AdminController {

	//This function adds book to the database
	
	public int addNewBook(String title, String author, int edition, String category, int isbn, String publisher, int year,
			int thresh, int quantity, Double buyprice, Double sellprice, String url, String description) {
		IBook newBook = new Book(isbn, category, author, title, edition, publisher, year, thresh, quantity, buyprice, 
				sellprice, url, description);
		
		int value = newBook.addBook();
		return value;
	}
	
	//This function lets the admin to browse book

	public List<IBook> browseBooks() {
		// TODO Auto-generated method stub
		List<IBook> returnList = new ArrayList<IBook>();
		SystemAdmin admin = new SystemAdmin();
		returnList = admin.browseBooks();
		return returnList;
	}
	
	//This function gets the book information

	public IBook getBookInfo(int isbn) {
		// TODO Auto-generated method stub
		IBook book = new Book();
		int check = book.getBookInfo(isbn);
		if (check == 1)
		{
			return book;
		}
		else
		{
			return null;
		}
	}
	
	//This function enables the admin to edit book info

	public int editBook(String title, String author, int edition, String category, int isbn, String publisher, int year,
			int thresh, int quantity, Double buyprice, Double sellprice, String url, String description) {
		IBook newBook = new Book(isbn, category, author, title, edition, publisher, year, thresh, quantity, buyprice, 
				sellprice, url, description);
		int value = newBook.editBook();
		return value;
	}
	
	//This function adds a new promotion to the database and sends emails to the customers
		
	public int addPromotion(int promoID, String name, double percent, String expiration, String userEmail, String host, String senderPassword, 
			String port) {
		Promotion newPromo = new Promotion(promoID, name, percent, expiration);
		int value = newPromo.addPromo(userEmail, host, senderPassword, port);
		return value;
	}
	
	//This function enables the admin to view users

	public List<User> viewUsers() {
		SystemAdmin admin = new SystemAdmin();
		return admin.viewUsers();
	}
	
	//This function lets the admin authorize user

	public int authorizeUser(int userID, int value) {
		// TODO Auto-generated method stub
		SystemAdmin admin = new SystemAdmin();
		return admin.authorizeUser(userID, value);
	}
	
	//This function suspends user based on the user id

	public int suspendUser(int userID) {
		// TODO Auto-generated method stub
		SystemAdmin admin = new SystemAdmin();
		return admin.suspendUser(userID);
	}
	
	// This function unsuspends user by userid

	public int unsuspendUser(int userID) {
		SystemAdmin admin = new SystemAdmin();
		return admin.unsuspendUser(userID);
	}
	
	//This function checks promotion

	public int checkPromo(int parseInt) {
		// TODO Auto-generated method stub
		Promotion promo = new Promotion();
		return promo.checkPromo(parseInt);
	}
	
	//This function deletes book from database

	public int deleteBook(int isbn) {
		// TODO Auto-generated method stub
		SystemAdmin admin = new SystemAdmin();
		return admin.deleteBook(isbn);
	}
	
	//This function gets the sales report

	public SimpleHash getSalesReport() {
        return OrderDA.getSalesReport();
    }
	
	//This function gets the book report
	
	public SimpleHash getBookReport() {
        return BookDA.getBookReport();
	}
	
	//This function updates the quantity of book

public int updateQuantityOfBook(int quantity, int isbn) {
	return BookDA.updateQuantityOfBook(quantity, isbn);
	}
}
