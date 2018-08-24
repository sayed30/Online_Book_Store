package boundary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.IBook;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import logic.AdminController;
import logic.UserController;
import object.User;

/* Authors: Bradley Reeves, Lakshay Sharma,  Aditya Yadav,  Dhanashree Joshi, Sayed Hussaini   
 * 
 * Description: A servlet used for SystemAdmins to do things such as add books, edit books,
 *  authorize employees, etc....
 */

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String templateDir = "/WEB-INF/templates";
	
	private TemplateProcessor process;
	
	final String host = "smtp.gmail.com";
    final String user = "ecommerce4050@gmail.com";
    final String pass = "ecommercecsci4050";
    final String port = "587";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		process = new TemplateProcessor(templateDir, getServletContext());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String addbook = request.getParameter("addbook");
		String browse = request.getParameter("browse");
		String editbook = request.getParameter("editbook");
		String submitedit = request.getParameter("submitedit");
		String addpromotion = request.getParameter("addpromotion");
		String viewUsers = request.getParameter("viewUsers");
		String authorizeUser = request.getParameter("authorizeUser");
		String suspendUser = request.getParameter("suspendUser");
		String unsuspendUser = request.getParameter("unsuspendUser");
		String searchBooks = request.getParameter("searchBooks");
		String deletebook = request.getParameter("deletebook");
		String salesReport = request.getParameter("salesReport");
		String updateQuantity = request.getParameter("updateQuantity");

		String lowQty = request.getParameter("lowQty");
		
		// Adds a book to the database.
		
		if (addbook != null)
		{
			addBook(request, response);
		}
		
		// Displays a list of books to the admin.
		
		else if (browse != null)
		{
			browseBooks(request, response);
		}
		
		// Displays a book to edit.
		
		else if (editbook != null)
		{
			int tempIsbn = Integer.parseInt(editbook);
			showEditBook(request, response, "", tempIsbn);
		}
		
		// Edits a book in the database.
		
		else if (submitedit != null)
		{
			editBook(request, response);
		}
		
		// Adds a promotion to the database.
		
		else if (addpromotion != null) 
		{
			addPromotion(request, response);
		}
		
		// Displays a list of users to the admin.
		
		else if (viewUsers != null)
		{
			viewUsers(request, response);
		}
		
		// Authorizes the user as one of the user types.
		
		else if (authorizeUser != null)
		{
			authorizeUser(request, response);
		}
		
		// Suspends a user's account.
		
		else if (suspendUser != null)
		{
			suspendUser(request, response);
		}
		
		// Unsuspends a user's account.
		
		else if (unsuspendUser != null)
		{
			unsuspendUser(request, response);
		}
		
		// Displays a list of books based of a search term and category.
		
		else if (searchBooks != null)
		{
			searchBooks(request, response);
		}
		
		// Deletes a book from being shown.
		
		else if (deletebook != null)
		{
			deleteBook(request, response);
		}
		
		// Returns a sales report.
		
		else if(salesReport != null) {
            generateSalesReport(request, response);
        }
		
		// Updates the inventory quantity for a book.
		
		else if(updateQuantity != null) 
		{
			updateQuantityOfBook(request, response); 
		}
		
		// Returns a list of books that have quantities below their threshold.
		
		else if (lowQty != null) 
		{
			generateBookReport(request, response);
		}
	}
	
	// Updates the quantity in stock for a certain book.
	
	private void updateQuantityOfBook(HttpServletRequest request, HttpServletResponse response)  {
		AdminController adminCtrl = new AdminController();
		String updateQuantity = request.getParameter("updateQuantity");
		if (updateQuantity != "")
		{
			// Updates the quantity in the database and returns an integer.
			int check = adminCtrl.updateQuantityOfBook(Integer.parseInt(updateQuantity), Integer.parseInt(request.getParameter("isbn")));
			
			Gson gson = new Gson();
	        try {
				response.getWriter().write(gson.toJson(check));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}

		// Gets the books that have low quantities and displays them.
	
		private void generateBookReport(HttpServletRequest request, HttpServletResponse response) {
		       try{
		AdminController adminCtrl = new AdminController();
		process.processTemplate("bookReport.ftl", adminCtrl.getBookReport(), request, response);
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
	
		// generates an EOD sales report.
		
	private void generateSalesReport(HttpServletRequest request, HttpServletResponse response) {
        AdminController adminCtrl = new AdminController();
        process.processTemplate("salesReport.ftl", adminCtrl.getSalesReport(), request, response);
    }
	
	// Deletes a book from being shown by setting its status to Deleted.
	
	private void deleteBook(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		// Checks if the isbn is not provided.
		
		if (!request.getParameter("deletebook").equals(""))
		{
			int isbn = Integer.parseInt(request.getParameter("deletebook"));
			AdminController aCtrl = new AdminController();
			
			// Deletes the books and reloads the page.
			
			int check = aCtrl.deleteBook(isbn);
			if (check == 1)
			{
				browseBooks(request, response);
			}
			else
			{
				browseBooks(request, response);
			}
		}
	}

	// Displays a list of books for the admin to edit or delete based on a search term.
	
	private void searchBooks(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String term = request.getParameter("term");
		int temp = Integer.parseInt(request.getParameter("category"));
		String cat = "";
		UserController userCtrl = new UserController();
		
		List<IBook> bookList = new ArrayList<IBook>();
		
		// Sets category.
		
		if (temp == 0)
		{
			cat = "isbn";
		}
		else if (temp == 1)
		{
			cat = "authorName";
		}
		else if (temp == 2)
		{
			cat = "title";
		}
		else if (temp == 3)
		{
			cat = "category";
		}
		
		// Returns a list of books.
		
		bookList = userCtrl.searchBooks(cat, term);
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		
		// Puts the list and searchTerm in root and processes the template.
		
		root.put("books", bookList);
		root.put("searchTerm", term);
		String templateName = "adminSearch.ftl";
		process.processTemplate(templateName, root, request, response);
	}
	
	// Unsuspends a user.
	
	private void unsuspendUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int userID = Integer.parseInt(request.getParameter("id"));
		
		AdminController aCtrl = new AdminController();
		
		int check = aCtrl.unsuspendUser(userID);
		
		if (check > 0)
		{
			viewUsers(request, response);
		}
		else
		{
			viewUsers(request, response);
		}
	}

	// Suspends a user.
	
	private void suspendUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int userID = Integer.parseInt(request.getParameter("id"));
		
		AdminController aCtrl = new AdminController();
		
		int check = aCtrl.suspendUser(userID);
		
		if (check > 0)
		{
			viewUsers(request, response);
		}
		else
		{
			viewUsers(request, response);
		}
	}

	// Authorizes a user as a certain type.
	
	private void authorizeUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String tempvalue = request.getParameter("authorizeDrop");
		String tempUserID = request.getParameter("id");
		
		int value = Integer.parseInt(tempvalue);
		int userID = Integer.parseInt(tempUserID);
		AdminController aCtrl = new AdminController();
		
		// Authorizes user based on the selected value for user type.
		
		int check = aCtrl.authorizeUser(userID, value);
		
		// Does nothing if N/A is selected.
		
		if (check == -1)
		{
			viewUsers(request, response);
		}
		
		// Reloads page on failure.
		
		else if (check == 0)
		{
			viewUsers(request, response);
		}
		
		// Reloads page on success.
		
		else
		{
			viewUsers(request, response);
		}
	}

	// Displays a list of users to the SystemAdmin.
	
	private void viewUsers(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		AdminController aCtrl = new AdminController();
		
		List<User> userList = aCtrl.viewUsers();
		
		root.put("userList", userList);
		String templateName = "viewUsers.ftl";
		process.processTemplate(templateName, root, request, response);
	}

	// Adds a promotion to the database.
	
	private void addPromotion(HttpServletRequest request, HttpServletResponse response) {
		String promoID = request.getParameter("promoID");
		String name = request.getParameter("promoName");
		String percent = request.getParameter("percentage");
		String expiration = request.getParameter("expiration");
		
		AdminController aCtrl = new AdminController();
		
		// Checks to see if the promo code is already in use.
		
		int checkPromo = aCtrl.checkPromo(Integer.parseInt(promoID));
		
		// If promo code is not in use.
		
		if (checkPromo != 1)
		{
			// Add promotion.
			int check = aCtrl.addPromotion(Integer.parseInt(promoID), name, Double.parseDouble(percent), expiration, user, host, pass, port);
			
			// Promotion was successfully added.
			
			if(check >= 1) {
				DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(df.build());
				root.put("message", "Successfully added a new promotion.");
				
				String templateName = "AddPromo.ftl";
				process.processTemplate(templateName, root, request, response);
			}
			else
			{
				DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(df.build());
				root.put("message", "Failed to add a new promotion.");
				
				String templateName = "AddPromo.ftl";
				process.processTemplate(templateName, root, request, response);
			}
		}
		
		// If promo code is in use, print out an erro message.
		
		else
		{
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			root.put("message", "This promo code is already in use.");
			
			String templateName = "AddPromo.ftl";
			process.processTemplate(templateName, root, request, response);
		}
	}

	// Edits a book in the database.

	private void editBook(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		// All values for a book.
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String category = request.getParameter("category");
		String isbn = request.getParameter("isbn");
		String publisher = request.getParameter("publisher");
		String year = request.getParameter("year");
		String thresh = request.getParameter("threshold");
		String quantity = request.getParameter("quantity");
		String buyprice = request.getParameter("buyprice");
		String sellprice = request.getParameter("sellprice");
		String edition = request.getParameter("edition");
		String url = request.getParameter("picture");
		String description = request.getParameter("description");
		
		int temp = Integer.parseInt(isbn);
		AdminController aCtrl = new AdminController();
		
		// Edits a book in the database.
		
		int check = aCtrl.editBook(title, author, Integer.parseInt(edition), category, Integer.parseInt(isbn), publisher, Integer.parseInt(year), 
				Integer.parseInt(thresh), Integer.parseInt(quantity), Double.parseDouble(buyprice), Double.parseDouble(sellprice), url, description);
		
		// Successfully updated the book.
		
		if (check >= 1)
		{
			showEditBook(request, response, "Successfully updated the information for this book.", temp);
		}
		else
		{
			showEditBook(request, response, "Failed to update the information for this book.", temp);
		}
	}

	// Shows a book for the SystemAdmin to edit.
	
	private void showEditBook(HttpServletRequest request, HttpServletResponse response, String message, int temp) {
		// TODO Auto-generated method stub
		int isbn = temp;
		
		AdminController aCtrl = new AdminController();
		
		IBook book = aCtrl.getBookInfo(isbn);
		
		// Makes sure the books still exists.
		
		if (book != null)
		{
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			root.put("book", book);
			root.put("message", message);
			
			String templateName = "editBook.ftl";
			process.processTemplate(templateName, root, request, response);
		}
		
		// Otherwise reload page.
		
		else
		{
			browseBooks(request, response);
		}
	}

	// Displays a list of books for the SystemAdmin to edit or delete.
	
	private void browseBooks(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		AdminController aCtrl = new AdminController();
		
		List<IBook> bookList = aCtrl.browseBooks();
		root.put("books", bookList);

		String templateName = "adminBrowse.ftl";
		process.processTemplate(templateName, root, request, response);
	}

	// Adds a book to the database.
	
	private void addBook(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String category = request.getParameter("category");
		String isbn = request.getParameter("isbn");
		String publisher = request.getParameter("publisher");
		String year = request.getParameter("year");
		String thresh = request.getParameter("threshold");
		String quantity = request.getParameter("quantity");
		String buyprice = request.getParameter("buyprice");
		String sellprice = request.getParameter("sellprice");
		String edition = request.getParameter("edition");
		String url = request.getParameter("picture");
		String description = request.getParameter("description");
		
		AdminController aCtrl = new AdminController();
		
		// Adds book.
		
		int check = aCtrl.addNewBook(title, author, Integer.parseInt(edition), category, Integer.parseInt(isbn), publisher, Integer.parseInt(year), 
				Integer.parseInt(thresh), Integer.parseInt(quantity), Double.parseDouble(buyprice), Double.parseDouble(sellprice), url, description);
		
		// Display success message.
		if (check >= 1)
		{
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			root.put("message", "Successfully add a book to the store.");
			
			String templateName = "addBookMessage.ftl";
			process.processTemplate(templateName, root, request, response);
		}
		
		// Displays message that the isbn is already in use.
		
		else if (check == -2)
		{
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			root.put("message", "A book with this ISBN has already been added to the store.");
			
			String templateName = "addBookMessage.ftl";
			process.processTemplate(templateName, root, request, response);
		}
		
		// Displays error message.
		
		else
		{
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			root.put("message", "Failed to add a book to the store.");
			
			String templateName = "addBookMessage.ftl";
			process.processTemplate(templateName, root, request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
