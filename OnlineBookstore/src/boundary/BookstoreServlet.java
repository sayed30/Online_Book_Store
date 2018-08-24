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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import entity.IBook;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import logic.UserController;
import object.User;
import object.UserProfile;
import persistent.EmailUtility;
/* Authors: Bradley Reeves, Lakshay Sharma,  Aditya Yadav,  Dhanashree Joshi, Sayed Hussaini   
 * 
 * Description: A servlet used for unregistered users and general methods that
 * do not fit the other servlets.
 */

/**
 * Servlet implementation class BookstoreServlet
 */
@WebServlet("/BookstoreServlet")
public class BookstoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Path for templates
	
	private String templateDir = "/WEB-INF/templates";
	
	// Used to process templates
	
	private TemplateProcessor process;

	// Used for sending emails to the users.
	
    final String host = "smtp.gmail.com";
    final String user = "ecommerce4050@gmail.com";
    final String pass = "ecommercecsci4050";
    final String port = "587";

    /**
     * Default constructor. 
     */
    public BookstoreServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		process = new TemplateProcessor(templateDir, getServletContext());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String signup = request.getParameter("signup");
		String login = request.getParameter("login");
		String getName = request.getParameter("name");
		String verify = request.getParameter("verify");
		String newPass = request.getParameter("newPassword");
		String forgotPass = request.getParameter("forgotPass");
		String checkLogin = request.getParameter("checkLogin");
		String logout = request.getParameter("logout");
		String changePass = request.getParameter("changePass");
		String viewProfile = request.getParameter("viewProfile");
		String browse = request.getParameter("browse");
		String searchBooks = request.getParameter("searchBooks");
		String saveProfile = request.getParameter("saveProfile");
		String changeHome = request.getParameter("changeHome");
		String cancel = request.getParameter("cancel");
		
		// If signup is clicked then register the user.
		
		if (signup != null)
		{
			registerUser(request, response);
		}
		
		// If login is clicked then login the user.
		
		else if (login != null)
		{
			loginUser(request, response);
		}
		
		// Retrieves the name from the user's session.
		
		else if (getName != null)
		{
			retrieveName(request, response);
		}
		
		// Verifies the account of a user.
		
		else if (verify != null)
		{
			verifyAccount(request, response);
		}
		
		// Changes the password of a user.
		
		else if (newPass != null)
		{
			changePassword(request, response);
		}
		
		// Helps a user reset their password.
		
		else if (forgotPass != null)
		{
			recoverPassword(request, response);
		}
		
		// Checks to see if the user is logged in.
		
		else if (checkLogin != null)
		{
			checkSession(request, response);
		}
		
		// Logs the user out.
		
		else if (logout != null)
		{
			logout(request, response);
		}
		
		// Changes the user's password.
		
		else if (changePass != null)
		{
			changePassword(request, response);
		}
		
		// Retrieves the user's information for their profile.
		
		else if(viewProfile != null) {
            viewProfile(request, response);
        }
		
		// Lets unregistered users browse books.
		
		else if (browse != null)
		{
			browseBooks(request, response);
		}
		
		// Lets unregistered users search books.
		
		else if (searchBooks != null)
		{
			searchBooks(request, response);
		}
		
		// Edits the information for a user's profile.
		
		else if (saveProfile != null)
		{
			saveProfile(request, response);
		}
		
		// Changes the settings page based on user type.
		
		else if (changeHome != null)
		{
			changeHome(request, response);
		}
		
		// Cancels code verification.
		
		else if (cancel != null)
		{
			cancelVerification(request, response);
		}
	}
	
	// Cancels the code verification for a user.
	
	private void cancelVerification(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession sess = request.getSession(false);
		
		// Invalidates session and redirects to homepage.
		
		if (sess != null)
		{
			sess.invalidate();
		}
		try {
			response.sendRedirect("index.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Updates home tab for settings based on usertype.
	
	private void changeHome(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession sess = request.getSession(false);
		String home = "index.html";
		if (sess != null)
		{
			String type = (String)sess.getAttribute("userType");
			if (type.equals("SystemAdmin"))
			{
				home = "Admin.html";
			}
			else if (type.equals("Customer"))
			{
				home = "Customer.html";
			}
			else if (type.equals("Manager"))
			{
				home = "Manager_d.html";
			}
			else if (type.equals("Shipping"))
			{
				home = "Shipmentview_d.html";
			}
			Gson gson = new Gson();
	        try {
				response.getWriter().write(gson.toJson(home));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			Gson gson = new Gson();
	        try {
				response.getWriter().write(gson.toJson(home));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Saves the changed information on the user's profile.
	
	private void saveProfile(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(false);

		String email = (String)session.getAttribute("email");

		String fname = request.getParameter("fname");

		String lname = request.getParameter("lname");

		String phone = request.getParameter("phone");
		
		String sub = request.getParameter("sub");
		
		Boolean subscribe = null;
		
		if (sub.equals("true"))
		{
			subscribe = true;
		}
		else
		{
			subscribe = false;
		}

		UserController userCtrl = new UserController();

		// Returns a number after updating the database.
		
		int check = userCtrl.saveProfile(email, fname, lname, phone, subscribe);

		// if check == 1 then it succeeded.
		
		if(check == 1)
		{
			try {
				response.getWriter().write("Success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				response.getWriter().write("Failure");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// Displays a list of books that the user searched for.
	
	private void searchBooks(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String term = request.getParameter("term");
		int temp = Integer.parseInt(request.getParameter("category"));
		String cat = "";
		UserController userCtrl = new UserController();
		
		List<IBook> bookList = new ArrayList<IBook>();
		
		// Sets the category for the search.
		
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
		
		// Gets the list of books.
		
		bookList = userCtrl.searchBooks(cat, term);
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		
		// Puts the list of books and the searchTerm into a SimpleHash to process in the 
		// .ftl template processor.
		
		root.put("books", bookList);
		root.put("searchTerm", term);
		String templateName = "userSearch.ftl";
		process.processTemplate(templateName, root, request, response);
	}
	
	// Displays a list of books for the user.
	
	private void browseBooks(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		UserController uCtrl = new UserController();
		
		// Gets the list of books.
		
		List<IBook> bookList = uCtrl.browseBooks();
		
		// Puts the list into root and processes the template.
		
		root.put("books", bookList);
		String templateName = "userBrowse.ftl";
		process.processTemplate(templateName, root, request, response);
	}
	
	// Gets the user information that can be changed on Settings.html
	
	private void viewProfile(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        String email = (String)session.getAttribute("email");
        
        UserController userCtrl = new UserController();
        UserProfile profile = userCtrl.viewProfile(email);
        
        // Set session values.
        
        session.setAttribute("subscribe", profile.getSubscribe());
        session.setAttribute("fName", profile.getFname());
		session.setAttribute("lName", profile.getLname());
		session.setAttribute("email", profile.getEmail());
        
		// Return the profile information to Settings.html
		
        Gson gson = new Gson();
        try {
			response.getWriter().write(gson.toJson(profile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	// Invalidates a user's session which logs them out.
	
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			session.invalidate();
		}
	}

	// Checks to see if a user has a session and returns the usertype.
	
	private void checkSession(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		Gson gson = new Gson();
		int check;
		if (session != null)
		{
			check = 1;
		}
		else
		{
			check = 0;
		}
		
		// If there is a session then return the usertype.
		
		if (check == 1)
		{
			String type = (String)session.getAttribute("userType");
			
			response.setContentType("application/json");
			try {
				response.getWriter().write(gson.toJson(type));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Otherwise return check;
		
		else
		{
		response.setContentType("application/json");
		try {
			response.getWriter().write(gson.toJson(check));
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}

	// Sends the user a newly generated password so that they can log into their account.
	
	private void recoverPassword(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		UserController userCtrl = new UserController();
		
		// Generates a new password and emails it to the user.
		
		int check = userCtrl.recoverPassword(email, host, user, port, pass);
		if (check == 0)
		{
		}
		else
		{
			try {
				response.sendRedirect("login.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Changes the user's password when they are logged in.
	
	private void changePassword(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        HttpSession session = request.getSession(false);
      
        UserController userCtrl = new UserController();
        
        // Changes password in database.
        
        int check = userCtrl.changePassword((String)session.getAttribute("email"), oldPassword, newPassword);
        if (check == 0)
        {
        	try {
				response.sendError(500);
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
        }
        
        // If successful return Success.
        
        else
        {
        	try {
				response.getWriter().write("Success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}

	// Verifies a user's account using a code that was emailed to them.
	
	private void verifyAccount(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String code = request.getParameter("code");
		HttpSession session = request.getSession(false);
		UserController userCtrl = new UserController();
		
		// Checks if the codes match.
		
		if (code.equals(session.getAttribute("userCode").toString()))
		{
			// Verifies the account in the database.
			int check = userCtrl.verifyAccount((String)session.getAttribute("email"));
			if (check == 1)
			{
				try {
					response.sendRedirect("Customer.html");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else
			{
				try {
					response.sendRedirect("verifycode.html");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else
		{
			try {
				response.sendRedirect("verifycode.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Returns the name of the current user.
	
	private void retrieveName(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Gson g = new Gson();
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			String name = (String)session.getAttribute("fName");
			response.setContentType("application/json");
			try {
				response.getWriter().write(g.toJson(name));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Logs the user in by creating a session.

	private void loginUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		UserController userCtrl = new UserController();
		
		// Checks username and password
		int check = userCtrl.checkLogin(email, pass);
		
		if (check == 1)
		{
			// Gets user info
			User user = userCtrl.GetUserInfo(email, pass);
			if (user != null)
			{
				// Creates a session for the user
				HttpSession session = request.getSession();
				synchronized(session) {
					session.setMaxInactiveInterval(-1);
					session.setAttribute("userID", user.getUserId());
					session.setAttribute("fName", user.getFname());
					session.setAttribute("lName", user.getLname());
					session.setAttribute("email", user.getEmail());
					session.setAttribute("userType", user.getUserType());
					session.setAttribute("userCode", user.getCode());
					session.setAttribute("status", user.getStatus());
					session.setAttribute("loggedin", "false");
					session.setAttribute("subscribe", user.getSubscribed());
				}
				String stat = (String)session.getAttribute("status");
				
				// Checks status of user
				
				if (stat.equals("verified"))
				{
					session.setAttribute("loggedin", "true");
					
					// Opens home pages depending on userType.
					if (session.getAttribute("userType").equals("Customer"))
					{
						try {
							response.sendRedirect("Customer.html");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else if (session.getAttribute("userType").equals("SystemAdmin"))
					{
						try {
							response.sendRedirect("Admin.html");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else if (session.getAttribute("userType").equals("Shipping"))
					{
						try {
							response.sendRedirect("Shipmentview_d.html");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else if (session.getAttribute("userType").equals("Manager"))
					{
						try {
							response.sendRedirect("Manager_d.html");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else
					{
						try {
							response.sendRedirect("login.html");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				else if (stat.equals("unverified"))
				{
					try {
						response.sendRedirect("verifycode.html");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if (stat.equals("suspended"))
				{
					try {
						response.sendRedirect("suspended.html");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			// Prints out an error message to the user.
			
			else
			{
				DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(df.build());
				
				root.put("message", "Sorry, we can not log you in at this time.");
				String templateName = "loginError.ftl";
				process.processTemplate(templateName, root, request, response);
			}
		}
		
		// Prints out a message that the email or password is incorrect.
		
		else
		{
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			
			root.put("message", "Email or password is incorrect.");
			String templateName = "loginError.ftl";
			process.processTemplate(templateName, root, request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	// Generates a 4-digit verification code.
	
	private int createCode()
	{
		int value;
		do
		{
			value = (int)Math.floor(Math.random()*9999 + 1000);
		}
		while (!(value >= 1000 && value <= 9999));
		return value;
	}

	// Registers the user
	
	private void registerUser(HttpServletRequest request, HttpServletResponse response) {
		String fname = request.getParameter("first_name");
		String lname = request.getParameter("last_name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passConfirmation = request.getParameter("password_confirmation");
		String subscribe = request.getParameter("sub");
		String phone = request.getParameter("phoneNumber");
		
		Boolean sub = null;
		if (subscribe != null)
		{
			sub = true;
		}
		else
		{
			sub = false;
		}
		
		UserController userCtrl = new UserController();
		
		// Checks password
		
		if (password.equals(passConfirmation))
		{
			// Checks if the email is already taken.
			boolean checkEmail = userCtrl.checkEmail(email);
			if (checkEmail == false)
			{
				// Create verification code.
				int code = createCode();
				// Store user info
				User newUser = new User(fname, lname, email, password, code, sub, phone);
				// Create new row in the database
				int check = userCtrl.CreateNewUser(newUser);
				if (check == 1)
				{
					// Sends an email with the verification to the user.
			        try {
			            EmailUtility.sendConfirmation(newUser.getEmail(), host, user, pass, port, code);
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
	
					try {
						response.sendRedirect("RegistrationConfirmation.html");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else
				{
					DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
					SimpleHash root = new SimpleHash(df.build());
					
					root.put("check", 3);
					String templateName = "registrationError.ftl";
					process.processTemplate(templateName, root, request, response);
				}
			}
			
			// Prints out an error if the email is already being used.
			
			else
			{
				
				DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(df.build());
				
				root.put("check", 0);
				String templateName = "registrationError.ftl";
				process.processTemplate(templateName, root, request, response);
			}
		}
		
		// Prints out an error if the passwords are different.
		
		else
		{
			
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			
			root.put("check", 1);
			String templateName = "registrationError.ftl";
			process.processTemplate(templateName, root, request, response);
		}
	}


}
