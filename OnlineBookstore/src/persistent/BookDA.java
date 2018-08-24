package persistent;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;

import object.Book;
import com.mysql.jdbc.Connection;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;

public class BookDA {
	
	// Adds a book to the database.

	public static int addBookToDA(int isbn, String category, String author, String title, int edition, String publisher,
			int year, int quantity, int threshold, String picture, Double buyingPrice, Double sellingPrice, String description) {
		Boolean checkIsbn = true;
		Connection con = (Connection) DbAccessImpl.connect();
		String checkQuery = "SELECT * FROM book WHERE isbn = '" + isbn + "'";
		ResultSet set = DbAccessImpl.retrieve(con, checkQuery);
		try {
			if (set.next())
			{
				checkIsbn = true;
			}
			else
			{
				checkIsbn = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (checkIsbn == false)
		{
			String query = "INSERT INTO book (isbn, category, authorName, title, picture, edition,"
					+ " publisher, publicationYear, qtyInStock, minThreshold, buyingPrice, sellingPrice, description) VALUES"
					+ " ('" + isbn + "', '" + category + "', '" + author + "', '" + title + "', '" + picture + "', '" + edition + "', '" + 
					publisher + "', '" + year + "', '" + quantity + "', '" + threshold + "', '" + buyingPrice + 
					"', '" + sellingPrice + "', '" + description + "')";
			
			
			return DbAccessImpl.create(con, query);
		}
		else
		{
			DbAccessImpl.disconnect(con);
			return -2;
		}
	}
	
	// Returns a ResultSet of all of the Active books in the database.

	public static ResultSet browseBooks(Connection con) {
		// TODO Auto-generated method stub
		String query = "SELECT * from book WHERE status = 'Active' ORDER BY title ASC";
		ResultSet set = null;
		set = DbAccessImpl.retrieve(con, query);
		return set;
	}
	
	// Returns the info for a book in the database.
	
	public static ResultSet getBookInfo(int isbn, Connection con) {
		String query = "SELECT * from book WHERE isbn = '" + isbn + "'";
		ResultSet set = null;
		set = DbAccessImpl.retrieve(con, query);
		return set;
	}
	
	// Edits a certain book in the database.

	public static int editBookDA(int isbn, String category, String author, String title, int edition, String publisher,
			int year, int quantity, int threshold, String picture, Double buyingPrice, Double sellingPrice,
			String description) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "UPDATE book SET category = '" + category + "', authorName = '" +  author + "', title = '" + title 
				+ "', picture = '" + picture + "', edition = '" + edition + "', publisher = '" + publisher + "', publicationYear = '"
				+ year + "', qtyInStock = '" + quantity + "', minThreshold = '" + threshold + "', buyingPrice = '" + buyingPrice + "',"
				+ " sellingPrice = '" + sellingPrice + "', description = '" + description + "' WHERE isbn = '" + isbn + "'";
		
		int value = DbAccessImpl.update(con, query);
		return value;
	}
	
	// Returns a ResultSet of active books based on search criteria.

	public static ResultSet searchBooks(Connection con, String cat, String term) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM book WHERE status = 'Active' AND " + cat + " LIKE '%" + term + "%' ORDER BY " + cat + " ASC";
		
		return DbAccessImpl.retrieve(con, query);
	}
	
	// Deletes a book by setting it's status to 'Deleted'.

	public static int deleteBook(int isbn) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		
		String query = "UPDATE book SET status = 'Deleted' WHERE isbn = '" + isbn + "'";
		int check = DbAccessImpl.update(con, query);
		DbAccessImpl.disconnect(con);
		return check;
	}
	
	// Gets all of the books that have quantities less than their thresholds.
	
	public static SimpleHash getBookReport() {
			Connection con = (Connection) DbAccessImpl.connect();
			String query = "SELECT * FROM book WHERE qtyInStock <= minThreshold AND status = 'Active'";
			ResultSet rs = DbAccessImpl.retrieve(con, query);
			ArrayList<Book> books = new ArrayList<Book>();
			try {
				while(rs.next()) {
					
				Book book = new Book(rs.getInt("isbn"), rs.getString("authorName"), rs.getString("title"), rs.getInt("qtyInStock"), rs.getInt("minThreshold"));
				books.add(book);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			root.put("books", books);
			return root;
		}
	
		// Updates the quantity of a book.

		public static int updateQuantityOfBook(int quantity, int isbn) {
			Connection con = (Connection) DbAccessImpl.connect();
			String query = "UPDATE book SET qtyInStock = qtyInStock + '" + quantity + "' WHERE isbn = '" + isbn + "'";
			return DbAccessImpl.update(con, query);
		}
		
		// Rates a book in the database.
	
		public static int rateBook(int orderID, int isbn, int rating) {
			Connection con = (Connection) DbAccessImpl.connect();
			// Updates the book in the transaction table.
			String query = "UPDATE transactions SET rating = '" + rating + "' WHERE isbn = '" + isbn + "' AND orderNumber = '" + orderID + "'";
			
			int check = DbAccessImpl.update(con, query);
			con = (Connection) DbAccessImpl.connect();
			if (check == 1)
			{
				// Gets all ratings in transactions and calculates total rating.
				String newQuery = "SELECT rating FROM transactions WHERE isbn = '" + isbn + "' AND rating != '0'";
				
				ResultSet set = DbAccessImpl.retrieve(con, newQuery);
				double totalRating = 0.0;
				int number = 0;
				try {
					while (set.next())
					{
						number++;
						totalRating += set.getDouble("rating");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				double finalRating = totalRating/number;
				finalRating = Math.round(finalRating*100.0)/100.0;
				String finalQuery = "UPDATE book SET rating = '" + finalRating + "' WHERE isbn = '" + isbn + "'";
				check = DbAccessImpl.update(con, finalQuery);
				DbAccessImpl.disconnect(con);
				return check;
			}
			else
			{
				DbAccessImpl.disconnect(con);
				return check;
			}
		}
		
}
