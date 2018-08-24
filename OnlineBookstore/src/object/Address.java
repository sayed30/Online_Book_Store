package object;

import persistent.AddressDA;

/*
Creates an Address Object that represents user's physical address
*/

public class Address {
	String street;
	String state;
	String zip;
	String city;
	int id;
	
	/*
	Parameters: None
	Return Value: int
	Description: Returns user id
	*/
	
	public int getId() {
		return id;
	}

	/*
	Parameters: int id
	Return Value: void
	Description: Sets user id
	*/
	
	public void setId(int id) {
		this.id = id;
	}

	/*
	Parameters: String street, String city, String state, String zip
	Return Value: Constructor
	Description: Creates Address Object
	*/
	
	public Address(String street, String city, String state, String zip)
	{
		this.setStreet(street);
		this.setCity(city);
		this.setState(state);
		this.setZip(zip);
	}
	
	/*
	Parameters: String street, String city, String state, String zip, int id
	Return Value: Constructor
	Description: Creates Address Object
	*/
	
	public Address(String street, String city, String state, String zip, int id)
	{
		this.setStreet(street);
		this.setCity(city);
		this.setState(state);
		this.setZip(zip);
		this.setId(id);
	}
	
	/*
	Parameters: Default
	Return Value: Constructor
	Description: Creates Address Object
	*/
	
	public Address()
	{
		
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: Returns street name
	*/
	
	public String getStreet() {
		return street;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: Returns street name
	*/
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: Returns state name
	*/
	
	public String getState() {
		return state;
	}
	
	/*
	Parameters: String state
	Return Value: void
	Description: Sets state name
	*/
	
	public void setState(String state) {
		this.state = state;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: Returns zip code
	*/
	
	public String getZip() {
		return zip;
	}
	
	/*
	Parameters: String zip
	Return Value: void
	Description: Sets zip code
	*/
	
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: Returns city name
	*/
	
	public String getCity() {
		return city;
	}
	
	/*
	Parameters: String city
	Return Value: void
	Description: Sets city name
	*/
	
	public void setCity(String city) {
		this.city = city;
	}
	
	/*
	Parameters: int userId
	Return Value: int
	Description: Returns 1 or 0 representing success or failure respectively while adding address
	*/

	public int addAddress(int userId) {
		// TODO Auto-generated method stub
		return AddressDA.addAddress(userId, street, city, state, zip);
	}
	
	/*
	Parameters: none
	Return Value: int
	Description: Returns 1 or 0 representing success or failure respectively while editing address
	*/

	public int editAddress() {
		// TODO Auto-generated method stub
		return AddressDA.editAddress(id, street, city, state, zip);
	}
	
	/*
	Parameters: none
	Return Value: int
	Description: Returns 1 or 0 representing success or failure respectively while deleting address
	*/

	public int deleteAddress() {
		// TODO Auto-generated method stub
		return AddressDA.deleteAddress(id);
	}
	
	public static Address getAddressById(int id){
		return AddressDA.getAddressById(id);
	}
}
