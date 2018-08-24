package logic;

import java.util.ArrayList;
import java.util.List;

import entity.IBook;
import object.User;
import object.UserProfile;
import persistent.UserDA;

public class UserController {
	
	// Function creates a new user by calling the function createNewUser

	public int CreateNewUser(User newUser) {
		int value = newUser.createNewUser();
		return value;
	}
	
	//Functions checks the email and password entered by user for login

	public int checkLogin(String email, String pass) {
		// TODO Auto-generated method stub
		User user = new User();
		int value = user.checkLogin(email, pass);
		return value;
	}
	
	//Function gets the user information from email and password entered by user

	public User GetUserInfo(String email, String pass) {
		User user = new User();
		user = user.getUserInfo(email, pass);
		return user;
	}
	
	//Function verifies account of a user

	public int verifyAccount(String attribute) {
		// TODO Auto-generated method stub
		User user = new User();
		int value = user.verify(attribute);
		return value;
	}
	
	//Function changes the password with the help of email oldPassword and newPassword

	public int changePassword(String email, String oldPassword, String newPassword) {
		User user = new User();
		int value = user.changePassword(email, oldPassword, newPassword);
		return value;
	}
	
	//This function recovers password for a user 

	public int recoverPassword(String email, String host, String user2, String port, String pass) {
		// TODO Auto-generated method stub
		User user = new User();
		int value = user.recoverPassword(email, host, user2, port, pass);
		return value;
	}
	
	//This function checks whether email is valid or not

	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		User user = new User();
		return user.checkEmail(email);
	}
	
	//This function helps user to view their profile

	public UserProfile viewProfile(String email) {
		UserDA da = new UserDA();
        return da.getDetails(email);
    }
	
	//This function helps user to search books
	
	public List<IBook> searchBooks(String cat, String term) {
		// TODO Auto-generated method stub
		User user = new User();
		return user.searchBooks(cat, term);
	}
	
	// This function helps user to browse book
	
	public List<IBook> browseBooks() {
		// TODO Auto-generated method stub
		List<IBook> returnList = new ArrayList<IBook>();
		User user = new User();
		returnList = user.browseBooks();
		return returnList;
	}
	
	// This Function saves the user profile in the database	
	
	public int saveProfile(String email, String fname, String lname, String phone, Boolean subscribe) {
		User user = new User();
		return user.saveProfile(email, fname, lname, phone, subscribe);
	}
}
