package object;
import persistent.EmailUtility;

import persistent.PromotionDA;

/*
Creates Promotion Object
*/

public class Promotion {
	private int promoID;
	private String name;
	private double percent;
	private String expiration;
	
	/*
	Parameters: int promoID, String name, double percent, String expiration
	Return Value: Constructor
	Description: Creates Object
	*/
	
	public Promotion(int promoID, String name, double percent, String expiration) {
		setPromoID(promoID);
		setName(name);
		setPercent(percent);
		setExpiration(expiration);
	}
	
	/*
	Parameters: None
	Return Value: Constructor
	Description: Creates Object
	*/
	
	public Promotion() {
		// TODO Auto-generated constructor stub
	}

	/*
	Parameters: String userEmail, String host, String senderPassword, String port
	Return Value: int
	Description: adds promotion to the database and sends an email to the user, informing them of the new promo code.
	Returns 1 or 0 if the action succeeds or fails respectively.
	*/
	
	public int addPromo(String userEmail, String host, String senderPassword, String port) {
		int value = PromotionDA.addPromoToDA(promoID, name, percent, expiration);
		
		if(value > 0) {
            try {
                EmailUtility.sendPromotion(userEmail, host, senderPassword, port, promoID, name, percent, expiration, PromotionDA.getEmailList());
            } catch( Exception e) {
                e.printStackTrace();
            }
        }
		return value;
	}
	
	/*
	Parameters: int promoID
	Return Value: void
	Description: sets promoID
	*/
	
	public void setPromoID(int promoID) {
		this.promoID = promoID;
	}
	
	/*
	Parameters: None
	Return Value: int
	Description: gets promoID
	*/
	
	public int getPromoID() {
		return promoID;
	}
	
	/*
	Parameters: String name
	Return Value: void
	Description: sets name
	*/
	
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: gets name
	*/
	
	public String getName() {
		return name;
	}
	
	/*
	Parameters: None
	Return Value: double
	Description: gets percent
	*/
	
	public double getPercent() {
		return percent;
	}
	
	/*
	Parameters: double percent
	Return Value: void
	Description: sets percent
	*/
	
	public void setPercent(double percent) {
		this.percent = percent;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: gets expiration
	*/
	
	public String getExpiration() {
		return expiration;
	}
	
	/*
	Parameters: String expiration
	Return Value: void
	Description: sets expiration
	*/
	
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	
	/*
	Parameters: int parseInt
	Return Value: int
	Description: Checks if promo is valid and returns the percentage discount or 0 representing failure
	*/

	public int checkPromo(int parseInt) {
		// TODO Auto-generated method stub
		return PromotionDA.checkPromo(parseInt);
	}
	
}
