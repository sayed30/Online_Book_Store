package object;

/*
Creates CreditCard Object
*/

import persistent.CreditCardDA;

public class CreditCard {
	String number;
	String expirationDate;
	String type;
	String csc;
	int id;
	
	/*
	Parameters: None
	Return Value: String
	Description: returns csc
	*/
	
	public String getCsc() {
		return csc;
	}

	/*
	Parameters: String csc
	Return Value: void
	Description: sets csc
	*/
	
	public void setCsc(String csc) {
		this.csc = csc;
	}
	
	/*
	Parameters: String number, String expire, String type, int id
	Return Value: Constructor
	Description: Creates Object
	*/
	
	public CreditCard (String number, String expire, String type, int id)
	{
		this.setNumber(number);
		this.setExpirationDate(expire);
		this.setType(type);
		this.setId(id);
	}
	
	/*
	Parameters: String number, String expire, String type
	Return Value: Constructor
	Description: Creates Object
	*/
	
	public CreditCard (String number, String expire, String type)
	{
		this.setNumber(number);
		this.setExpirationDate(expire);
		this.setType(type);
	}
	
	/*
	Parameters: None
	Return Value: Constructor
	Description: Creates Object
	*/
	
	public CreditCard ()
	{
		
	}
	
	/*
	Parameters: String number2, String expire, String type2, String csc
	Return Value: Constructor
	Description: Creates Object
	*/
	
	public CreditCard(String number2, String expire, String type2, String csc) {
		// TODO Auto-generated constructor stub
		this.setNumber(number2);
		this.setExpirationDate(expire);
		this.setType(type2);
		this.setCsc(csc);
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: returns number
	*/

	public String getNumber() {
		return number;
	}
	
	/*
	Parameters: String number
	Return Value: void
	Description: sets number
	*/
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: returns expirationDate
	*/
	
	public String getExpirationDate() {
		return expirationDate;
	}
	
	/*
	Parameters: String expirationDate
	Return Value: void
	Description: sets expirationDate
	*/
	
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: returns type
	*/
	
	public String getType() {
		return type;
	}
	
	/*
	Parameters: String type
	Return Value: void
	Description: sets type
	*/
	
	public void setType(String type) {
		this.type = type;
	}
	
	/*
	Parameters: None
	Return Value: int
	Description: returns id
	*/
	
	public int getId() {
		return id;
	}
	
	/*
	Parameters: int id
	Return Value: void
	Description: sets id
	*/
	
	public void setId(int id) {
		this.id = id;
	}
	
	/*
	Parameters: int userID
	Return Value: int
	Description: Adds card to database based on userID and returns 1 or 0 if the action succeeded or failed
	*/

	public int addCard(int userID) {
		// TODO Auto-generated method stub
		return CreditCardDA.addCard(userID, number, expirationDate, type, csc);
	}
	
	/*
	Parameters: None
	Return Value: int
	Description: deletes card from database and returns 1 or 0 if the action succeeded or failed
	*/

	public int deleteCard() {
		// TODO Auto-generated method stub
		return CreditCardDA.deleteCard(id);
	}
	
	public static CreditCard getCreditCardById(int id){
		return CreditCardDA.getCreditCardById(id);
	}
}
