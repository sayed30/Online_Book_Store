package persistent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Connection;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;

import object.Order;
import object.ReportEntry;
import object.Transaction;

public class OrderDA {
	
	 // Gets a list of orders with their respective transactions for the user to view.

	 public static List<Order> viewHistory(int parseInt) {
			Connection con = (Connection) DbAccessImpl.connect();
			String query = "SELECT * FROM orders WHERE userID = '" + parseInt + "' ORDER BY orderDate DESC";
			ResultSet set = DbAccessImpl.retrieve(con, query);
			List<Order> list = new ArrayList<Order>();
			try {
				while(set.next())
				{
					int num = set.getInt("orderNumber");
					String stat = set.getString("orderStatus");
					Date date = set.getDate("orderDate");
					String sAdd = set.getString("billingAddress"); // Add getting shipping address
					String bAdd = set.getString("shippingAddress"); // add getting billing address
					String pay = set.getString("paymentMethod");
					int conNum = set.getInt("confirmationNumber");
					int userID = set.getInt("userID");
					double total = set.getDouble("orderTotal");
					
					Order order = new Order(num, stat, date, sAdd, bAdd, pay, conNum, userID, total);
					list.add(order);	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			int len = list.size();
			for(int i=0; i<len; i++) {
				int oNum = list.get(i).getOrderNumber();
				query = "SELECT * FROM transactions JOIN book ON book.isbn = transactions.isbn WHERE orderNumber = '" + oNum + "'";
				set = DbAccessImpl.retrieve(con, query);
				ArrayList<Transaction> tList = new ArrayList<Transaction>();
				
				try {
					while(set.next()) {
						int isbn = set.getInt("isbn");
						String title = set.getString("title");
						String author = set.getString("authorName");
						int orderNum = set.getInt("orderNumber");
						int qty = set.getInt("qty");
						int promoID = set.getInt("promoID");
						double tTotal = set.getDouble("total");
						double rating = set.getDouble("rating");
						Transaction transaction = new Transaction(orderNum, isbn, qty, promoID, tTotal, author, title , rating);
						tList.add(transaction);
						
					}
					list.get(i).setTransactionList(tList);
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			DbAccessImpl.disconnect(con);
			return list;
		}
	 
	 // Gets a list of orders for the Shipping Employee to view.
	
	public static List<Order> viewOrders() {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "SELECT * FROM orders ORDER BY FIELD(orderStatus, 'pending','shipping', 'completed', 'canceled'), orderDate DESC";
		ResultSet set = DbAccessImpl.retrieve(con, query);
		List<Order> list = new ArrayList<Order>();
		
		try {
			while (set.next())
			{
				int num = set.getInt("orderNumber");
				String stat = set.getString("orderStatus");
				Date date = set.getDate("orderDate");
				String sAdd = set.getString("shippingAddress"); // Add getting shipping address
				String bAdd = set.getString("billingAddress"); // add getting billing address
				String pay = set.getString("paymentMethod");
				int conNum = set.getInt("confirmationNumber");
				int userID = set.getInt("userID");
				double total = set.getDouble("orderTotal");
				
				Order order = new Order(num, stat, date, sAdd, bAdd, pay, conNum, userID, total);
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbAccessImpl.disconnect(con);
		return list;
	}
	
	// Changes a certain order's status in the database.

	public static int changeOrderStatus(String orderID, String status) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "UPDATE orders SET orderStatus = '" + status + "' WHERE orderNumber = '" + orderID + "'";
		int check = DbAccessImpl.update(con, query);
		return check;
	}
	
	// Gets a sales report for the current day.

	public static SimpleHash getSalesReport() {
        Connection con = (Connection) DbAccessImpl.connect();
        Date date = new Date();
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Double total = 0.0;
        String newDate = format.format(date) + " 00:00:00";
        
        
        String newQuery = "SELECT isbn FROM book";
        List<Integer> bookList = new ArrayList<Integer>();
        ResultSet set = DbAccessImpl.retrieve(con, newQuery);
        
        // Gets the list of isbn's of all books
        
        try {
			while (set.next())
			{
				bookList.add(set.getInt("isbn"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        ArrayList<ReportEntry> entries = new ArrayList<ReportEntry>();
        
        // Gets all of the transactions and orders for each book isbn.
        
        for (int i = 0; i < bookList.size(); i++)
        {
        	String orderQuery = "Select orders.orderNumber, orderTotal, orderDate, book.isbn, qty, total, authorName, title FROM orders JOIN transactions ON transactions.orderNumber = orders.orderNumber JOIN book ON transactions.isbn = book.isbn WHERE orderDate ='" + newDate + "' AND transactions.isbn = '" + bookList.get(i) + "'";
        	ResultSet newSet = DbAccessImpl.retrieve(con, orderQuery);
        	int quantity = 0;
        	double bookTotal = 0.0;
        	int check = 0;
        	Date tempDate = new Date();
        	int tempIsbn = 0;
        	String tempAuthor = "";
        	String tempTitle = "";
        	boolean checkBook = true;
        	try {
				if (newSet.next())
				{
					tempDate = newSet.getDate("orderDate");
					
						tempIsbn = newSet.getInt("isbn");
						tempAuthor = newSet.getString("authorName");
						tempTitle = newSet.getString("title");
					quantity += newSet.getInt("qty");
					bookTotal += newSet.getDouble("total");
				}
				else
				{
					checkBook = false;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	// Only computes sales for books that are in the transaction table
        	
        	if (checkBook == true)
        	{
        	try {
				while (newSet.next())
				{
					if (check == 0)
					{
						tempDate = newSet.getDate("orderDate");
						tempIsbn = newSet.getInt("isbn");
						tempAuthor = newSet.getString("authorName");
						tempTitle = newSet.getString("title");
						check++;
					}
					quantity += newSet.getInt("qty");
					bookTotal += newSet.getDouble("total");
				}
				ReportEntry entry = new ReportEntry(tempDate, tempIsbn, quantity, bookTotal, tempAuthor, tempTitle);
				
				total += entry.getTotal();
				entries.add(entry);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	}
        }

		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		root.put("entries", entries);
		root.put("total", total);
        return root;
    }
	
	// Creates an order in the database.
	
	public static int addtoOrders(int orderNumber, int agencyID, String orderStatus, String orderDate, String shippingAddress, String billingAddress, String paymentMethod, String confirmationNumber, int userID, double orderTotal) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "INSERT INTO orders(orderNumber,orderStatus,orderDate,shippingAddress,billingAddress,paymentMethod,confirmationNumber,userID,orderTotal) VALUES (" + "'" + orderNumber + "', 'pending', '" + orderDate + "','" + shippingAddress + "', '" + billingAddress + "', '" + paymentMethod + "', '" + confirmationNumber + "', '" + userID + "', '" + orderTotal + "')";
		
		return DbAccessImpl.create(con, query);
	}
	
	// Gets the latest orderNumber in the database.
	
	public static int getMaxOrderNumber() {
		Connection con = (Connection) DbAccessImpl.connect();
		String query = "SELECT * FROM orders where orderNumber = (SELECT MAX(orderNumber) from orders)";
		ResultSet set = null;
		set = DbAccessImpl.retrieve(con, query);
		int value = 0;
		try {
			if (set.next()) {
				value = set.getInt("orderNumber");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value+1;
	}
	
}
