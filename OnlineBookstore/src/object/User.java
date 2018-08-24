package object;

/*
Creates User Object
*/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import entity.IBook;
import persistent.BookDA;
import persistent.CustomerDA;
import persistent.DbAccessImpl;
import persistent.EmailUtility;
import persistent.UserDA;

public class User {
	String fname;
	String lname;
	String email;
	String password;
	int userId;
	String userType;
	int code;
	String status;
	Boolean subscribed;
	String phone;
	
	/*
	Parameters: None
	Return Value: String
	Description: gets phone
	*/
	
	public String getPhone() {
		return phone;
	}
	
	/*
	Parameters: String phone
	Return Value: void
	Description: sets phone
	*/

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/*
	Parameters: None
	Return Value: boolean
	Description: gets subscribed
	*/

	public Boolean getSubscribed() {
		return subscribed;
	}
	
	/*
	Parameters: Boolean subscribed
	Return Value: void
	Description: sets subscribed
	*/

	public void setSubscribed(Boolean subscribed) {
		this.subscribed = subscribed;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: gets userType
	*/

	public String getUserType() {
		return userType;
	}
	
	/*
	Parameters: String userType
	Return Value: void
	Description: sets userType
	*/

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	/*
	Parameters: None
	Return Value: int
	Description: gets userID
	*/

	public int getUserId() {
		return userId;
	}
	
	/*
	Parameters: int userId
	Return Value: void
	Description: sets userId
	*/

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/*
	  Parameters: String first, String last, String email, String pass, int code
	  Return Value: Constructor
	  Description: Creates a User Object
	  */

	public User(String first, String last, String email, String pass, int code)
	{
		setFname(first);
		setLname(last);
		setEmail(email);
		setPassword(pass);
		setCode(code);
	}
	
	/*
	Parameters: None
	Return Value: int
	Description: gets code
	*/

	public int getCode() {
		return code;
	}
	
	/*
	Parameters: int code
	Return Value: void
	Description: sets code
	*/

	public void setCode(int code) {
		this.code = code;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: gets status
	*/

	public String getStatus() {
		return status;
	}
	
	/*
	Parameters: String status
	Return Value: void
	Description: sets status
	*/

	public void setStatus(String status) {
		this.status = status;
	}
	
	/*
	Parameters: None
	Return Value: Constructor
	Description: Creates a User Object
	*/

	public User() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	Parameters: String fname2, String lname2, String email2, String password2, int code2, Boolean subscribe
	Return Value: Constructor
	Description: Creates a User Object
	*/

	public User(String fname2, String lname2, String email2, String password2, int code2, Boolean subscribe) {
		// TODO Auto-generated constructor stub
		setFname(fname2);
		setLname(lname2);
		setEmail(email2);
		setPassword(password2);
		setCode(code2);
		setSubscribed(subscribe);
	}
	
	/*
	Parameters: String fname2, String lname2, String email2, String password2, int code2, Boolean sub, String phone
	Return Value: Constructor
	Description: Creates a User Object
	*/

	public User(String fname2, String lname2, String email2, String password2, int code2, Boolean sub, String phone) {
		// TODO Auto-generated constructor stub
		setFname(fname2);
		setLname(lname2);
		setEmail(email2);
		setPassword(password2);
		setCode(code2);
		setSubscribed(sub);
		setPhone(phone);
	}
	
	
	/*
	Parameters: None
	Return Value: String
	Description: gets fname
	*/
	
	public String getFname() {
		return fname;
	}
	
	/*
	Parameters: String fname
	Return Value: void
	Description: sets fname
	*/

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: gets lname
	*/

	public String getLname() {
		return lname;
	}

	/*
	Parameters: String lname
	Return Value: void
	Description: sets lname
	*/
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: gets email
	*/

	public String getEmail() {
		return email;
	}
	
	/*
	Parameters: String email
	Return Value: void
	Description: sets email
	*/

	public void setEmail(String email) {
		this.email = email;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: gets password
	*/

	public String getPassword() {
		return password;
	}
	
	/*
	Parameters: String password
	Return Value: void
	Description: sets password
	*/

	public void setPassword(String password) {
		this.password = password;
	}
	
	/*
	Parameters: None
	Return Value: int
	Description: Creates New User in the database and returns 1 or 0 if the request was successful or not respectively
	*/

	public int createNewUser() {
		CustomerDA da = new CustomerDA();
		int value = da.createNewCustomer(fname, lname, email, password, code, subscribed, phone);
		return value;
	}
	
	/*
	Parameters: String email2, String pass
	Return Value: int
	Description: validates user login from the database and returns 1 or 0 if the request was successful or not respectively
	*/

	public int checkLogin(String email2, String pass) {
		UserDA da = new UserDA();
		int value = 0;
		Connection con = (Connection) DbAccessImpl.connect();
		ResultSet set = da.checkLogin(email2, pass, con);
		try
		{
		if (set.next())
		{
			value = 1;
		}
		else
		{
			value = 0;
		}
		}
		catch (Exception ex)
		{
			value = 0;
		}
		DbAccessImpl.disconnect(con);
		return value;
	}
	
	/*
	Parameters: String email2, String pass
	Return Value: User
	Description: Gets user info from the database and returns a User object
	*/

	public User getUserInfo(String email2, String pass) {
		UserDA da = new UserDA();
		Connection con = (Connection) DbAccessImpl.connect();
		User user = da.getUserInfo(email2, pass, con);
		DbAccessImpl.disconnect(con);
		return user;
	}
	
	/*
	Parameters: String attribute
	Return Value: int
	Description: returns a 1 or 0 if the request for verification was successful or not
	*/

	public int verify(String attribute) {
		// TODO Auto-generated method stub
		UserDA da = new UserDA();
		int value = da.verifyAccount(attribute);
		return value;
	}
	
	/*
	Parameters: None
	Return Value: List<IBook>
	Description: returns a list of all the books in the database
	*/

	public List<IBook> browseBooks() {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		ResultSet set = BookDA.browseBooks(con);
		List<IBook> temp = new ArrayList<IBook>();
		if (set != null)
		{
			try {
				while (set.next())
				{
					IBook book = new Book(set.getInt("isbn"), set.getString("category"), 
							set.getString("authorName"), set.getString("title"), 
							set.getInt("edition"), set.getString("publisher"), 
							set.getInt("publicationYear"), set.getInt("minThreshold"), 
							set.getInt("qtyInStock"), set.getDouble("buyingPrice"), 
							set.getDouble("sellingPrice"), set.getString("picture"), 
							set.getString("description"), set.getDouble("rating"));
					temp.add(book);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return null;
			}
		}
		else
		{
			return null;
		}
		DbAccessImpl.disconnect(con);
		return temp;
	}
	
	/*
	Parameters: String email2, String oldPassword, String newPassword
	Return Value: int
	Description: returns a  1 or 0 if the request for password change was successful or not respectively
	*/

	public int changePassword(String email2, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		UserDA da = new UserDA();
		int value = da.changePassword(email2, oldPassword, newPassword);
		return value;
	}
	
	/*
	Parameters: String email2, String host, String user2, String port, String pass
	Return Value: int
	Description: returns a  1 or 0 if the request for password recovery was successful or not respectively
	*/

	public int recoverPassword(String email2, String host, String user2, String port, String pass) {
		// TODO Auto-generated method stub
		String letters = "ABCDE#FGHIJKLab$cdefghMNO%PQ@RSTU&VWXYZ!ijklmnop567qrstuvwxyz1234890";
        StringBuilder sb = new StringBuilder();
        int value;
        for(int i = 0; i < 8; i++) {
            double index = Math.random() * letters.length();
            sb.append(letters.charAt((int)index));
        }
        String newPass = sb.toString();
        
        UserDA da = new UserDA();
        
        try
        {
        	EmailUtility.sendNewPassword(email2, host, user2, pass, port, newPass);
        	value = da.recoverPassword(email2, newPass);
        }
        catch (Exception e)
        {
        	value = 0;
        }
		return value;
	}
	
	/*
	Parameters: String email2
	Return Value: boolean
	Description: returns a  1 if an email exists in the databse and 0 if it doesn't.
	*/

	public boolean checkEmail(String email2) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		ResultSet set = null;
		set = UserDA.checkEmail(con, email2);
		boolean check = true;
		try {
			if (set.next())
			{
				check = true;
			}
			else
			{
				check = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}
	
	/*
	Parameters: String cat, String term
	Return Value: List<IBook>
	Description: Returns a specific list of books as searched by the user
	*/
	
	public List<IBook> searchBooks(String cat, String term) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DbAccessImpl.connect();
		ResultSet set = BookDA.searchBooks(con, cat, term);
		List<IBook> temp = new ArrayList<IBook>();
			try {
				while (set.next())
				{
					IBook book = new Book(set.getInt("isbn"), set.getString("category"), 
							set.getString("authorName"), set.getString("title"), 
							set.getInt("edition"), set.getString("publisher"), 
							set.getInt("publicationYear"), set.getInt("minThreshold"), 
							set.getInt("qtyInStock"), set.getDouble("buyingPrice"), 
							set.getDouble("sellingPrice"), set.getString("picture"), 
							set.getString("description"), set.getDouble("rating"));
					temp.add(book);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return null;
			}
		DbAccessImpl.disconnect(con);
		return temp;
	}
	
	/*
	Parameters: String email2, String fname2, String lname2, String phone, Boolean subscribe
	Return Value: int
	Description: Saves profile after user edited it and returns 1 if it was successful or 0 if it wasn't
	*/

	public int saveProfile(String email2, String fname2, String lname2, String phone, Boolean subscribe) {
		// TODO Auto-generated method stub
		return UserDA.saveProfile(email2, fname2, lname2, phone, subscribe);
	}
}
