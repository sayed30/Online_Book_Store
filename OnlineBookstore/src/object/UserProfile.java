package object;

/*
Creates a UserProfile Object
*/

public class UserProfile {

  String fname;
  String lname;
  String phone;
  Boolean subscribe;
  String email;
  
  /*
  Parameters: None
  Return Value: boolean
  Description: gets the subscription property for a user profile
  */
  
  public Boolean getSubscribe() {
	return subscribe;
}
  
  /*
  Parameters: Boolean subscribe
  Return Value: void
  Description: sets the subscription property for a user profile
  */

public void setSubscribe(Boolean subscribe) {
	this.subscribe = subscribe;
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
Parameters: String fname, String lname, String phone, String email
Return Value: Contructor
Description: Creates Object
*/

  public UserProfile(String fname, String lname, String phone, String email) {
    this.fname = fname;
    this.lname = lname;
    this.phone = phone;
    this.email = email;
  }

}
