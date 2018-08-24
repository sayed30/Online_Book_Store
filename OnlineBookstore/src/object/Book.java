package object;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import entity.IBook;
import persistent.BookDA;
import persistent.DbAccessImpl;

/*
Creates an object to represent a book from the inventory
*/

public class Book implements IBook{
	private int isbn;
	private String category;
	private String author;
	private String title;
	private int edition;
	private String publisher;
	private int year;
	private int quantity;
	private int threshold;
	private String picture;
	private Double buyingPrice;
	private Double sellingPrice;
	private String description;
	private double rating;
	
	
	public Book(int isbn, String cat, String author, String title, int edition, 
			String publisher, int year, int thresh, int quantity, Double buyingPrice, Double 
			sellingPrice, String url, String description, double rating)
	{
		setIsbn(isbn);
		setCategory(cat);
		setAuthor(author);
		setTitle(title);
		setEdition(edition);
		setPublisher(publisher);
		setYear(year);
		setQuantity(quantity);
		setBuyingPrice(buyingPrice);
		setSellingPrice(sellingPrice);
		setPicture(url);
		setThreshold(thresh);
		setDescription(description);
		setRating(rating);
	}
	
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: Returns description
	*/
	

	public String getDescription() {
		return description;
	}

	/*
	Parameters: String description
	Return Value: void
	Description: sets description
	*/
	
	public void setDescription(String description) {
		this.description = description;
	}

	/*
	Parameters: None
	Return Value: Constructor
	Description: Creates Book Object
	*/
	
	public Book() {
		
	}
	
	/*
	Parameters: int isbn, String cat, String author, String title, int edition, String publisher, int year, int thresh, int quantity, Double buyingPrice, Double sellingPrice, String url, String description
	Return Value: Constructor
	Description: Creates Book Object
	*/
	
	public Book(int isbn, String cat, String author, String title, int edition, 
			String publisher, int year, int thresh, int quantity, Double buyingPrice, Double 
			sellingPrice, String url, String description)
	{
		setIsbn(isbn);
		setCategory(cat);
		setAuthor(author);
		setTitle(title);
		setEdition(edition);
		setPublisher(publisher);
		setYear(year);
		setQuantity(quantity);
		setBuyingPrice(buyingPrice);
		setSellingPrice(sellingPrice);
		setPicture(url);
		setThreshold(thresh);
		setDescription(description);
	}
	
	/*
	Parameters: None
	Return Value: int
	Description: Returns isbn
	*/
	
	public int getIsbn() {
		return isbn;
	}
	
	/*
	Parameters: int isbn
	Return Value: void
	Description: sets isbn
	*/
	
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: Returns category
	*/
	
	public String getCategory() {
		return category;
	}
	
	/*
	Parameters: String category
	Return Value: void
	Description: sets category
	*/
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: Returns author
	*/
	
	public String getAuthor() {
		return author;
	}
	
	/*
	Parameters: void
	Return Value: void
	Description: sets author
	*/
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: Returns title
	*/
	
	public String getTitle() {
		return title;
	}
	
	/*
	Parameters: String title
	Return Value: void
	Description: sets title
	*/
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: Returns edition
	*/
	
	public int getEdition() {
		return edition;
	}
	
	/*
	Parameters: int edition
	Return Value: void
	Description: sets edition
	*/
	
	public void setEdition(int edition) {
		this.edition = edition;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: Returns publisher
	*/
	
	public String getPublisher() {
		return publisher;
	}
	
	/*
	Parameters: String publisher
	Return Value: void
	Description: sets publisher
	*/
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	/*
	Parameters: None
	Return Value: int
	Description: Returns year
	*/
	
	public int getYear() {
		return year;
	}
	
	/*
	Parameters: int year
	Return Value: void
	Description: sets year
	*/
	
	public void setYear(int year) {
		this.year = year;
	}
	
	/*
	Parameters: None
	Return Value: int
	Description: Returns quantity
	*/
	
	public int getQuantity() {
		return quantity;
	}
	
	/*
	Parameters: int quantity
	Return Value: void
	Description: sets quantity
	*/
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/*
	Parameters: None
	Return Value: int
	Description: Returns threshold
	*/
	
	public int getThreshold() {
		return threshold;
	}
	
	/*
	Parameters: int threshold
	Return Value: void
	Description: sets threshold
	*/
	
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: Returns picture
	*/
	
	public String getPicture() {
		return picture;
	}
	
	/*
	Parameters: String picture
	Return Value: void
	Description: sets picture
	*/
	
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	/*
	Parameters: None
	Return Value: double
	Description: Returns buyingPrice
	*/
	
	public Double getBuyingPrice() {
		return buyingPrice;
	}
	
	/*
	Parameters: Double buyingPrice
	Return Value: void
	Description: sets buyingPrice
	*/
	
	public void setBuyingPrice(Double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}
	
	/*
	Parameters: None
	Return Value: double
	Description: Returns sellingPrice
	*/
	
	public Double getSellingPrice() {
		return sellingPrice;
	}
	
	/*
	Parameters: Double sellingPrice
	Return Value: void
	Description: sets sellingPrice
	*/
	
	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	/*
	Parameters: None
	Return Value: int
	Description: adds book to database and returns 1 or 0 if actions was successful or not respectively
	*/

	public int addBook() {
		int value = BookDA.addBookToDA(isbn, category, author, title, edition, publisher, year, quantity, threshold, picture,
				buyingPrice, sellingPrice, description);
		return value;
	}
	
	/*
	Parameters: None
	Return Value: int
	Description: edits book to database and returns 1 or 0 if actions was successful or not respectively
	*/
	
	public int editBook() {
		int value = BookDA.editBookDA(isbn, category, author, title, edition, publisher, year, quantity, threshold, picture,
				buyingPrice, sellingPrice, description);
		return value;
	}
	
	/*
	Parameters: None
	Return Value: int
	Description: gets book info from database and returns 1 or 0 if actions was successful or not respectively
	*/
	
	public int getBookInfo(int isbn) {
		Connection con = (Connection) DbAccessImpl.connect();
		ResultSet set = BookDA.getBookInfo(isbn, con);
		int check = 0;
		try {
			if (set.next())
			{
				setIsbn(set.getInt("isbn"));
				setCategory(set.getString("category"));
				setAuthor(set.getString("authorName"));
				setTitle(set.getString("title"));
				setEdition(set.getInt("edition"));
				setPublisher(set.getString("publisher"));
				setYear(set.getInt("publicationYear"));
				setQuantity(set.getInt("qtyInStock"));
				setBuyingPrice(set.getDouble("buyingPrice"));
				setSellingPrice(set.getDouble("sellingPrice"));
				setPicture(set.getString("picture"));
				setThreshold(set.getInt("minThreshold"));
				setDescription(set.getString("description"));
				check = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbAccessImpl.disconnect(con);
		return check;
	}
	
	/*
	Parameters: None
	Return Value: void
	Description: prints book details from database
	*/
	
	public void printBook() {
		System.out.println(title);
		System.out.println(author);
		System.out.println(description);
		System.out.println(category);
		System.out.println(isbn);
		System.out.println(publisher);
		System.out.println(year);
		System.out.println(edition);
		System.out.println(threshold);
		System.out.println(quantity);
		System.out.println(buyingPrice);
		System.out.println(sellingPrice);
		System.out.println(picture);
	}
	
	public Book(int isbn, String author, String title, int quantity, int thresh) {
		setIsbn(isbn);
		setAuthor(author);
		setTitle(title);
		setQuantity(quantity);
		setThreshold(thresh);
		}
}
