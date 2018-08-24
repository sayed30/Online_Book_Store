package object;

import java.util.ArrayList;

/*
Creates an Order Object
*/

import java.util.Date;

import persistent.OrderDA;

public class Order {
	int orderNumber;
	String orderStatus;
	Date date;
	String shippingAddress;
	String billingAddress;
	String paymentMethod;
	int confirmationNumber;
	int userID;
	double orderTotal;
	 ArrayList<Transaction> transactionList;
	
	/*
	Parameters: None
	Return Value: Constructor
	Description: Creates Object
	*/
	
	public Order() {
		
	}
	
	/*
	Parameters: int orderNumber, double orderTotal, Date orderDate
	Return Value: Constructor
	Description: Creates Object
	*/
	
	public Order(int orderNumber, double orderTotal, Date orderDate) {
        this.setOrderNumber(orderNumber);
        this.setOrderTotal(orderTotal);
        this.setDate(orderDate);
    }
	
	/*
	Parameters: int num, String stat, Date date, Address sAdd, Address bAdd, String pay, int conNum, int userID, double total
	Return Value: Constructor
	Description: Creates Object
	*/
	
	public Order(int num, String stat, Date date, String sAdd, String bAdd, String pay, int conNum, int userID, double total) {
		this.setOrderNumber(num);
		this.setOrderStatus(stat);
		this.setDate(date);
		this.setShippingAddress(sAdd);
		this.setBillingAddress(bAdd);
		this.setPaymentMethod(pay);
		this.setConfirmationNumber(conNum);
		this.setUserID(userID);
		this.setOrderTotal(total);
	}
	
	 public void setTransactionList(ArrayList<Transaction> Tlist) {
			this.transactionList = Tlist;
		}
	 
		public ArrayList<Transaction> getTransactionList(){
			return transactionList;
		}
		
		public String printTransactionList (){
			int len = transactionList.size();
			String toPrint = "";
			for(int i=0; i<len; i++) {
				int bookisbn = transactionList.get(i).isbn;
				int quantity = transactionList.get(i).qty;
				double tTotal = transactionList.get(i).total;
				toPrint+="Book ISBN: " + bookisbn + "; Quantity: " + quantity + "; Price: " + tTotal + "\n";
			}
			return toPrint;
		}
	
	/*
	Parameters: None
	Return Value: int
	Description: Returns orderNumber
	*/
	
	public int getOrderNumber() {
		return orderNumber;
	}
	
	/*
	Parameters: int orderNumber
	Return Value: void
	Description: sets orderNumber
	*/
	
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	/*
	Parameters: None
	Return Value: int
	Description: Returns orderStatus
	*/
	
	public String getOrderStatus() {
		return orderStatus;
	}
	
	/*
	Parameters: String orderStatus
	Return Value: void
	Description: sets orderStatus
	*/
	
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	/*
	Parameters: None
	Return Value: Date
	Description: Returns date
	*/
	
	public Date getDate() {
		return date;
	}
	
	/*
	Parameters: Date date
	Return Value: Date
	Description: sets date
	*/
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: Returns shippingAddress
	*/
	
	public String getShippingAddress() {
		return shippingAddress;
	}
	
	/*
	Parameters: String shippingAddress
	Return Value: void
	Description: sets shippingAddress
	*/
	
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: gets billingAddress
	*/
	
	public String getBillingAddress() {
		return billingAddress;
	}
	
	/*
	Parameters: String billingAddress
	Return Value: void
	Description: sets billingAddress
	*/
	
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	
	/*
	Parameters: None
	Return Value: String
	Description: gets paymentMethod
	*/
	
	public String getPaymentMethod() {
		return paymentMethod;
	}
	
	/*
	Parameters: String paymentMethod
	Return Value: void
	Description: sets paymentMethod
	*/
	
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	/*
	Parameters: None
	Return Value: int
	Description: gets confirmationNumber
	*/
	
	public int getConfirmationNumber() {
		return confirmationNumber;
	}
	
	/*
	Parameters: int confirmationNumber
	Return Value: void
	Description: sets confirmationnumber
	*/
	
	public void setConfirmationNumber(int confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}
	
	/*
	Parameters: None
	Return Value: int
	Description: gets userID
	*/
	
	public int getUserID() {
		return userID;
	}
	
	/*
	Parameters: int userID)
	Return Value: void
	Description: sets userID
	*/
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	/*
	Parameters: None
	Return Value: double
	Description: gets orderTotal
	*/
	
	public double getOrderTotal() {
		return orderTotal;
	}
	
	/*
	Parameters: double orderTotal
	Return Value: void
	Description: sets orderTotal
	*/
	
	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}
	
	/*
	Parameters: String orderID, String status
	Return Value: int
	Description: changes order status
	*/

	public int changeOrderStatus(String orderID, String status) {
		// TODO Auto-generated method stub
		return OrderDA.changeOrderStatus(orderID, status);
	}
	
	public static int getMaxOrderNumber() {
		return OrderDA.getMaxOrderNumber();
	}
}
