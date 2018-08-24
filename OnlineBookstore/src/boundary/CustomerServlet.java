package boundary;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.mysql.jdbc.Connection;

import entity.IBook;
import entity.ICart;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import logic.CustomerController;
import logic.UserController;
import object.Address;
import object.Book;
import object.Cart;
import object.CreditCard;
import object.Order;
import persistent.CartDA;
import persistent.DbAccessImpl;
import persistent.EmailUtility;
import persistent.OrderDA;
import persistent.PromotionDA;
import persistent.TransactionDA;

/* Authors: Bradley Reeves, Lakshay Sharma,  Aditya Yadav,  Dhanashree Joshi, Sayed Hussaini   
 * 
 * Description: A servlet used for the Customers that allow them to browse and search books,
 *  add items to their carts, checkout, and other things.
 */

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
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
    public CustomerServlet() {
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
		String browse = request.getParameter("browse");
		String viewAddress = request.getParameter("viewAddress");
		String addAddress = request.getParameter("addAddress");
		String editAddress = request.getParameter("editAddress");
		String deleteAddress = request.getParameter("deleteAddress");
		String addCard = request.getParameter("addCard");
		String viewCards = request.getParameter("viewCreditCard");
		String deleteCard = request.getParameter("deleteCard");
		String searchBooks = request.getParameter("searchBooks");
		
		String viewHistory = request.getParameter("viewHistory");
		String isbn = request.getParameter("addtocart");
		String applyPromo = request.getParameter("applyPromo");
		String getCart = request.getParameter("getCart");
		String updateCart = request.getParameter("updateItem");
		String deleteFromCart = request.getParameter("deleteItem");
		String checkOut = request.getParameter("checkoutCart");
		String continueToCheckOut = request.getParameter("continueToCheckOut");
		String completePurchase = request.getParameter("completePurchase");
		String rateBook = request.getParameter("rateBook");
		
		// Displays a list of books for the Customer
		
		if (browse != null)
		{
			browseBooks(request, response, "");
		}
		
		// Display a page for the Customer to view, edit, add, and delete addresses.
		
		else if (viewAddress != null)
		{
			viewAddresses(request, response, "");
		}
		
		// Adds an address for the Customer.
		
		else if (addAddress != null)
		{
			addAddress(request, response);
		}
		
		// Edits a Customer's address
		
		else if (editAddress != null)
		{
			editAddress(request, response);
		}
		
		// Deletes a Customer's address
		
		else if (deleteAddress != null)
		{
			deleteAddress(request, response);
		}
		
		// Adds a CreditCard to a customer's account.
		
		else if (addCard != null)
		{
			addCard(request, response);
		}
		
		// Displays a list of all credit cards for a customer.
		
		else if (viewCards != null)
		{
			viewCards(request, response, "");
		}
		
		// Deletes a Customer's credit card.
		
		else if (deleteCard != null)
		{
			deleteCard(request, response);
		}
		
		// Displays a list of books based on a search term.
		
		else if (searchBooks != null)
		{
			searchBooks(request, response, "");
		}
		
		else if (viewHistory != null)
		{
			viewHistory(request, response);
		}
		else if(isbn != null)
		{
			int value = addToCart(request,response, isbn);
			if(value == 1) {
				browseBooks(request, response,"Item added to Cart");
			}else {
				browseBooks(request, response,"Item out of Stock");
			}
		}
		else if(applyPromo != null)
		{
			showCart(request, response, null);
		}
		else if(getCart != null) {
			showCart(request, response, null);
		}
		else if(updateCart != null) {
			updateCart(request, response, updateCart);
		}
		else if(deleteFromCart != null) {
			deleteFromCart(request, response, deleteFromCart);
		}
		else if(checkOut != null) {
			showAddressAndPayment(request, response, null);
		}
		else if(continueToCheckOut != null) {
			continueToCheckOut(request,response);
		}
		else if(completePurchase != null) {
			completePurchase(request,response);
		}
		else if (rateBook != null)
		{
			rateBook(request, response);
		}
	}
	
	private void rateBook(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String isbn = request.getParameter("rateBook");
		int rating = Integer.parseInt(request.getParameter("rating"));
		int order = Integer.parseInt(request.getParameter("order"));
		int check = 0;
		CustomerController cCtrl = new CustomerController();
		int numIsbn = Integer.parseInt(isbn);
		check = cCtrl.rateBook(order, numIsbn, rating);
			
			Gson gson = new Gson();
	        try {
				response.getWriter().write(gson.toJson(check));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	// Overridden version of browse books.
	
	private void browseBooks(HttpServletRequest request, HttpServletResponse response, String message) {
		// TODO Auto-generated method stub
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		CustomerController custCtrl = new CustomerController();
		
		ArrayList<String> errorList = new ArrayList<String>();
		if(message == null) {
			errorList.add("");
		}else {
			errorList.add(message);
		}
		
		List<IBook> bookList = custCtrl.browseBooks();
		root.put("books", bookList);
		root.put("error", errorList);
		String templateName = "customerBrowse.ftl";
		process.processTemplate(templateName, root, request, response);
	}
	
	// Completes the order.
	
	private void completePurchase(HttpServletRequest request, HttpServletResponse response) {
		//Get MAX order number
		CustomerController custCtrl = new CustomerController();
		int orderNumber = custCtrl.getMaxOrderNumber();
		ArrayList<String> orderNumberList = new ArrayList<String>();
		orderNumberList.add(Integer.toString(orderNumber));
		
		//Compare credit card with valid card. Set order status to completed
		String card = request.getParameter("billCard");
		boolean isValidCard = true;
		String status = "pending";
		if(isValidCard) {status = "completed";}
		
		//Set today's date as order date
		Date date = new Date();
		String orderDate = Integer.toString(1900+date.getYear()) + '-' + Integer.toString(date.getMonth()+1) + '-' + Integer.toString(date.getDate());
		ArrayList<String> orderDateList = new ArrayList<String>();
		orderDateList.add(orderDate);
		
		//shipping address
		String shippingAddress = request.getParameter("shipAddress");
		ArrayList<String> shippingAddressList = new ArrayList<String>();
		shippingAddressList.add(shippingAddress);
		
		//billing address
		String billingAddress = request.getParameter("billAddress");
		ArrayList<String> billingAddressList = new ArrayList<String>();
		billingAddressList.add(billingAddress);
		
		//Set payment method as type and last four digits
		String[] splitCard = card.split(",");
		String paymentMethod = splitCard[0] + " " + splitCard[2].substring(splitCard[2].length() - 4);
		ArrayList<String> paymentMethodList = new ArrayList<String>();
		paymentMethodList.add(paymentMethod);
		
		//set MAX confirmation Number
		String confirmationNumber = Integer.toString(orderNumber);
		ArrayList<String> confirmationNumberList = new ArrayList<String>();
		confirmationNumberList.add(confirmationNumber);
		
		//set user id
		int userId = getUserId(request);
		
		//set total
		String orderTotal = request.getParameter("orderTotal");
		orderTotal = orderTotal.replace(",", "");
		
		double total = Double.parseDouble(orderTotal);
		total = Math.round(total*100.0)/100.0;
		int success = OrderDA.addtoOrders(orderNumber, 0, status, orderDate, shippingAddress, billingAddress, paymentMethod, confirmationNumber, userId, total);
		
		ArrayList<String> titles = new ArrayList<String>();
		ArrayList<Integer> quantities = new ArrayList<Integer>();
		int emailTotal = 0;
		
		if(success != 0) {
			String promoCode = request.getParameter("promoCode");
			if(promoCode != null) {
				promoCode = promoCode.replaceAll("[^0-9]", "");
			}
			double percent = 0.0;
			
			int promo = 0;
			if(promoCode != null && !promoCode.equals("")) {
				promo = Integer.parseInt(promoCode);
				ResultSet set = PromotionDA.getPromotion(promo);
				if(set != null) {
					try {
						if(set.next()) {
							String expirationDate = set.getString("expiration");
							try {
								Date expiry = new Date();
								try {
									expiry = new SimpleDateFormat("yyyy-MM-dd").parse(expirationDate);
								} catch (java.text.ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								Date currentDate = new Date();
								if(expiry.compareTo(currentDate) >= 0) {
									percent = Double.parseDouble(set.getString("percentage"));
								}
							}catch(Exception e) {
								e.printStackTrace();
							}
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
			ICart cart1 = new Cart(0, 0, userId, 0, 0, 0);
			List<Cart> cartList = cart1.getCart(userId);
			for(ICart cart:cartList) {
				double total1 = cart.getTotal();
				double discount = (total1*percent)/100;
				total1 = total1 - discount;
				cart.setTotal(total1);
				int transactionId = TransactionDA.getMaxTrasactionId();
				int isbn = cart.getIsbn();
				int qty = cart.getQty();
				success = TransactionDA.addToTransaction(orderNumber,transactionId,isbn,qty,promo,total1);
				emailTotal += total1;
				titles.add(custCtrl.getTitleOfBook(isbn));
				quantities.add(qty);
			}
		}
		
		if(success != 0) {
			CartDA.deleteCartItems(userId);
		}
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		root.put("orderNumber",orderNumberList);
		root.put("shipAddress",shippingAddressList);
		root.put("billAddress", billingAddressList);
		root.put("paymentMethod", paymentMethodList);
		root.put("confNumber", confirmationNumberList);
		
		try {
			EmailUtility.sendOrderConfirmation((String)request.getSession(false).getAttribute("email"), host, port, user, pass, orderNumberList, shippingAddressList, billingAddressList, paymentMethodList, confirmationNumberList, titles, quantities, total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		process.processTemplate("confirmationPurchase.ftl", root, request, response);
	}

	// gets the user's id.
	
	public int getUserId(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		int userID = 0;
		if (session != null) 
		{
			userID = (int)session.getAttribute("userID");
		}
		return userID;
	}
	
	// Goes to the checkout page.
	
	private void continueToCheckOut(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String error = "";
		String selectedShippingAddress = request.getParameter("selectShipAddress");
		String selectedBillingAddress = request.getParameter("selectBillAddress");
		String selectedCard = request.getParameter("selectCard");
		
		Address address = new Address();
		if(selectedShippingAddress.equals("newAddress"))
		{
			address.setStreet(request.getParameter("newshipstreet"));
			address.setCity(request.getParameter("newshipcity"));
			address.setState(request.getParameter("newshipstate"));
			address.setZip(request.getParameter("newshipzip"));
			if(address.getStreet().equals("") || address.getCity().equals("") || address.getState().equals("") || address.getZip().equals("")) {
				error += "** Please Enter Shipping Address Details";
			} else {
				CustomerController custCtrl = new CustomerController();
				custCtrl.addAddress(this.getUserId(request), request.getParameter("newshipstreet"), request.getParameter("newshipcity"), request.getParameter("newshipstate"), request.getParameter("newshipzip"));
			}
		}
		else
		{
			address = Address.getAddressById(Integer.parseInt(selectedShippingAddress));
		}
		ArrayList<Address> shippingAddress = new ArrayList<Address>();
		shippingAddress.add(address);
		
		if(selectedBillingAddress.equals("newAddress"))
		{
			address.setStreet(request.getParameter("newbillstreet"));
			address.setCity(request.getParameter("newbillcity"));
			address.setState(request.getParameter("newbillstate"));
			address.setZip(request.getParameter("newbillzip"));
			if(address.getStreet().equals("") || address.getCity().equals("") || address.getState().equals("") || address.getZip().equals("")) {
				error += "** Please Enter Billing Address Details";
			}
		}
		else
		{
			address = Address.getAddressById(Integer.parseInt(selectedBillingAddress));
		}
		ArrayList<Address> billingAddress = new ArrayList<Address>();
		billingAddress.add(address);
		
		CreditCard card= new CreditCard();
		if(selectedCard.equals("newCard"))
		{
			card.setNumber(request.getParameter("newcardnumber"));
			card.setType(request.getParameter("newcardtype"));
			card.setExpirationDate(request.getParameter("newcardexpiration"));
			card.setCsc(request.getParameter("newccid"));
			if(card.getNumber().equals("") || card.getExpirationDate().equals("") || card.getCsc().equals("")) {
				error += "** Please enter Credit Card Details or select an existing one";
			}else{
				CustomerController addCard = new CustomerController();
				addCard.addCard(request.getParameter("newcardnumber"), request.getParameter("newcardexpiration"), request.getParameter("newcardtype"), getUserId(request), request.getParameter("newccid"));
			}
		}
		else
		{
			card = CreditCard.getCreditCardById(Integer.parseInt(selectedCard));
		}
		
		if(error == null || error.equals("")) {
			ArrayList<CreditCard> billingCard = new ArrayList<CreditCard>();
			billingCard.add(card);
			
			ArrayList<String> orderTotalList = new ArrayList<String>();
			orderTotalList.add(request.getParameter("orderTotal"));
			
	
			ArrayList<String> promoCodeList = new ArrayList<String>();
			promoCodeList.add(request.getParameter("promoCode"));
			
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			root = getCart(request,response);
			root.put("shippingAddress", shippingAddress);
			root.put("billingAddress", billingAddress);
			root.put("billingCard", billingCard);
			root.put("orderTotal", orderTotalList);
			root.put("promo", promoCodeList);
			process.processTemplate("completePurchase.ftl", root, request, response);
		}
		else
		{
			showAddressAndPayment(request, response, error);
		}
	}
	
	// Gets the addresses and payments
	
	protected SimpleHash getAddressAndPayment(HttpServletRequest request, HttpServletResponse response) {
		int userID = getUserId(request);
		CustomerController custCtrl = new CustomerController();
		
		ArrayList<Address> addressList = new ArrayList<Address>();
		addressList = (ArrayList<Address>) custCtrl.getAddresses(userID);
		
		List<CreditCard> cardList = new ArrayList<CreditCard>();
		cardList = custCtrl.viewCards(userID);
		
		ArrayList<String> orderTotalList = new ArrayList<String>();
		orderTotalList.add(request.getParameter("orderTotal"));
		
		ArrayList<String> promoCodeList = new ArrayList<String>();
		if(request.getParameter("promoCode") != null) {
			promoCodeList.add(request.getParameter("promoCode"));
		}else {
			promoCodeList.add(request.getParameter("No Promo Code"));
		}
		
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		
		root.put("address", addressList);
		root.put("card", cardList);
		root.put("total", orderTotalList);
		root.put("promo", promoCodeList);
		
		return root;
	}
	
	// Shows address and payment page.
	
	protected void showAddressAndPayment(HttpServletRequest request, HttpServletResponse response, String message) {
		int userID = getUserId(request);
		Connection con=(Connection) DbAccessImpl.connect();
		String cartIds = "";
		ResultSet set= CartDA.getCart(userID, con);
		try {
			while(set.next()) {
				cartIds += Integer.toString(set.getInt("cartID")); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(cartIds.equals("") || cartIds == null) {
			showCart(request, response, "Cart is Empty. Please Add Items from Browse Books");
		}else {
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			ArrayList<String> errorList = new ArrayList<String>();
			if(message == null) {
				errorList.add("");
			} else {
				errorList.add(message);
			}
			
			
			
			root = getAddressAndPayment(request,response);
			root.put("error",errorList);
			process.processTemplate("addressAndPayment.ftl", root, request, response);
		}
	}
	
	// Deletes an item from the cart.
	
	protected void deleteFromCart(HttpServletRequest request, HttpServletResponse response, String updateCart) {
		int isbn = Integer.parseInt(updateCart.replaceAll("[^0-9]", ""));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int cartId = Integer.parseInt(request.getParameter("cartID"));
		int userID = getUserId(request);
		
		CustomerController custCtrl = new CustomerController();
		IBook book = custCtrl.getBookInfo(isbn);
		
		ICart cart = new Cart(cartId, userID, 0, isbn, 0, 0);
		
		IBook newBook = new Book(book.getIsbn(), book.getCategory(), book.getAuthor(), book.getTitle(), book.getEdition(), book.getPublisher(), book.getYear(), book.getThreshold(), book.getQuantity()+quantity, book.getBuyingPrice(), book.getSellingPrice(), book.getPicture(), book.getDescription());
		
		int newValue = newBook.editBook();
		
		int value = 0;
		
		value = cart.deleteFromCart();
		
		if(value != 0) {
			showCart(request, response, "Item Deleted From Cart");
		}
	}
	
	// Updates the Cart.
	
	protected void updateCart(HttpServletRequest request, HttpServletResponse response, String updateCart) {
		int isbn = Integer.parseInt(updateCart.replaceAll("[^0-9]", ""));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int cartId = Integer.parseInt(request.getParameter("cartID"));
		int userID = getUserId(request);
		
		CustomerController custCtrl = new CustomerController();
		ICart oldCart = custCtrl.getCartByID(cartId);
		IBook book = custCtrl.getBookInfo(isbn);
		
		if(quantity <= oldCart.getQty()) {
			int difference = oldCart.getQty() - quantity;
			IBook newBook = new Book(book.getIsbn(), book.getCategory(), book.getAuthor(), book.getTitle(), book.getEdition(), book.getPublisher(), book.getYear(), book.getThreshold(), book.getQuantity()+difference, book.getBuyingPrice(), book.getSellingPrice(), book.getPicture(), book.getDescription());
			newBook.editBook();
			double quant = book.getSellingPrice()*quantity;
			ICart cart = new Cart(cartId, userID, 0, isbn, quantity, quant);
			int value = 0;
			value = cart.updateCart();
			if(value != 0) {
				showCart(request, response,null);
			}
		} else {
			int difference = quantity - oldCart.getQty();
			if(book.getQuantity() >= difference) {
				IBook newBook = new Book(book.getIsbn(), book.getCategory(), book.getAuthor(), book.getTitle(), book.getEdition(), book.getPublisher(), book.getYear(), book.getThreshold(), book.getQuantity()-difference, book.getBuyingPrice(), book.getSellingPrice(), book.getPicture(), book.getDescription());
				newBook.editBook();
				double quant = book.getSellingPrice()*quantity;
				ICart cart = new Cart(cartId, userID, 0, isbn, quantity, quant);
				int value = 0;
				value = cart.updateCart();
				if(value != 0) {
					showCart(request, response,null);
				}
			} else {
				showCart(request, response, "Cart wasn't updated since item not in stock");
			}
		}
		
	}
	
	// Displays the User's cart
	
	protected void showCart(HttpServletRequest request, HttpServletResponse response, String message) {
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		root = getCart(request,response);
		ArrayList<String> errorList = new ArrayList<String>();
		if(message == null) {
			errorList.add("");
		} else {
			errorList.add(message);
		}
		root.put("error",errorList);
		process.processTemplate("cartBrowse.ftl", root, request, response);
	}
	
	// Gets the User's Cart
	
	protected SimpleHash getCart(HttpServletRequest request, HttpServletResponse response) {
		int userID = getUserId(request);
		
		ICart cart1 = new Cart(0, 0, userID, 0, 0, 0);
		List<Cart> cartList = cart1.getCart(userID);
		
		String promoCode = request.getParameter("promoCode");
		if(promoCode != null) {
			promoCode = promoCode.replaceAll("[^0-9]", "");
		}
		ArrayList<String> promoCodeList = new ArrayList<String>();
		double percent = 0.0;
		
		if(promoCode == null || promoCode.equals("")) {
			promoCodeList.add("Enter Promo Code");
		} else {
			int promo = Integer.parseInt(promoCode);
			ResultSet set = PromotionDA.getPromotion(promo);
			if(set == null) {
				promoCodeList.add("Promo Code Invalid");
			} else {
				try {
					if(set.next()) {
						String expirationDate = set.getString("expiration");
						try {
							Date expiry = new Date();
							try {
								expiry = new SimpleDateFormat("yyyy-MM-dd").parse(expirationDate);
							} catch (java.text.ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Date currentDate = new Date();
							if(expiry.compareTo(currentDate) < 0) {
								promoCodeList.add("PROMO CODE INVALID");
							}else{
								percent = Double.parseDouble(set.getString("percentage"));
								promoCodeList.add(promoCode);
							}
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		ArrayList<Book> titleList = new ArrayList<Book>();
		double sum = 0.0;
		for (Cart cart : cartList) {
			double total = cart.getTotal();
			double discount = (total*percent)/100;
			total = total - discount;
			cart.setTotal(total);
			sum = sum + total;
			CustomerController custCtrl = new CustomerController();
			IBook book = custCtrl.getBookInfo(cart.getIsbn());
			titleList.add((Book) book);
		}
		double cartTotal = sum;
		//cartTotal.add(Double.toString(sum));
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		
		root.put("cart", cartList);
		root.put("book", titleList);
		root.put("total", cartTotal);
		root.put("promo", promoCodeList);
		
		return root;
	}
	
	// Adds a book to the cart.
	
	protected int addToCart(HttpServletRequest request, HttpServletResponse response, String isbn) throws ServletException, IOException {
		// TODO Auto-generated method stub
		isbn = isbn.replaceAll("[^0-9]", "");
		
		CustomerController custCtrl = new CustomerController();
		IBook book = custCtrl.getBookInfo(Integer.parseInt(isbn));
		if(book.getQuantity() > 0) {
			int userID = getUserId(request);
			int cartID = CartDA.getCartID();
			ICart cart = new Cart(cartID, userID, 0, Integer.parseInt(isbn), 1, book.getSellingPrice());
			IBook newBook = new Book(book.getIsbn(), book.getCategory(), book.getAuthor(), book.getTitle(), book.getEdition(), book.getPublisher(), book.getYear(), book.getThreshold(), book.getQuantity()-1, book.getBuyingPrice(), book.getSellingPrice(), book.getPicture(), book.getDescription());
			@SuppressWarnings("unused")
			int newValue = newBook.editBook();
			return (cart.addToCart());
		} else {
			return 0;
		}
		
	}
	
	// Displays the user's order history.
	
	private void viewHistory(HttpServletRequest request, HttpServletResponse response) {
		CustomerController custCtrl = new CustomerController();
		List<Order> orderList = new ArrayList<Order>();
		
		HttpSession sess = request.getSession(false);
		String id = sess.getAttribute("userID").toString();
		orderList = custCtrl.viewHistory(Integer.parseInt(id));
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		root.put("orderList", orderList);
		String templateName = "viewOrders.ftl";
		process.processTemplate(templateName, root, request, response);
	}

	// Displays a list of books based on a search term and category.
	
	private void searchBooks(HttpServletRequest request, HttpServletResponse response, String message) {
		// TODO Auto-generated method stub
		String term = request.getParameter("term");
		int temp = Integer.parseInt(request.getParameter("category"));
		String cat = "";
		UserController userCtrl = new UserController();
		
		List<IBook> bookList = new ArrayList<IBook>();
		
		// Sets the category from the select tag.
		
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
		
		// Retrieves a list of books.
		
		bookList = userCtrl.searchBooks(cat, term);
		
		ArrayList<String> errorList = new ArrayList<String>();
		if(message == null) {
			errorList.add("");
		}else {
			errorList.add(message);
		}
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		
		root.put("books", bookList);
		root.put("searchTerm", term);
		root.put("error", errorList);
		String templateName = "customerSearch.ftl";
		process.processTemplate(templateName, root, request, response);
	}

	// Deletes a Credit card from a user's account.
	
	private void deleteCard(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		// Checks if a credit card is selected.
		
		if (!request.getParameter("deleteCard").equals(""))
		{
			int id = Integer.parseInt(request.getParameter("deleteCard"));
			
			CustomerController custCtrl = new CustomerController();
			
			// Deletes the card.
			
			int check = custCtrl.deleteCard(id);
			
			// If it succeeded, reload the page with a success message.
			
			if (check == 1)
			{
				viewCards(request, response, "Successfully deleted this card.");
			}
			
			// Else, reload the page with an error message.
			
			else
			{
				viewCards(request, response, "Failed to delete this card.");
			}
		}
		
		// Reload the page, then display a message telling the user to select a card.
		
		else
		{
			viewCards(request, response, "You must select a card to delete");
		}
	}

	// Displays the page for viewing a user's credit cards.
	
	private void viewCards(HttpServletRequest request, HttpServletResponse response, String message) {
		// TODO Auto-generated method stub
		HttpSession sess = request.getSession(false);
		
		int userID = (int)sess.getAttribute("userID");
		List<CreditCard> cardList = new ArrayList<CreditCard>();
		
		CustomerController custCtrl = new CustomerController();
		
		// Gets the list of a user's credit cards.
		
		cardList = custCtrl.viewCards(userID);
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		root.put("cardList", cardList);
		root.put("message", message);
		String templateName = "creditcardPage.ftl";
		process.processTemplate(templateName, root, request, response);
	}

	// Adds a credit card to a user's account.
	
	private void addCard(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String number = request.getParameter("number");
		String type = request.getParameter("type");
		String csc = request.getParameter("security");
		
		// Gets the date for the credit card in the correct format.
		
		long timeStamp = Long.parseLong(request.getParameter("expiration"));
		Date date = Date.from( Instant.ofEpochSecond( timeStamp ) );
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		String dateTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
		
		CustomerController custCtrl = new CustomerController();
		
		HttpSession sess = request.getSession(false);
		int userID = (int)sess.getAttribute("userID");
		
		// Adds the card.
		
		int check = custCtrl.addCard(number, dateTime, type, userID, csc);
		
		// Reloads the page with messages depending on if it succeeded in adding a credit card.
		
		if (check == 1)
		{
			viewCards(request, response, "Successfully added a credit card to your account.");
		}
		else
		{
			viewCards(request, response, "Failed to add the credit card to your account.");
		}
	}

	// Deletes an address from a user's account.
	
	private void deleteAddress(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		// Checks if an address is selected.
		
		if (!request.getParameter("deleteAddress").equals(""))
		{
			int id = Integer.parseInt(request.getParameter("deleteAddress"));
			
			CustomerController custCtrl = new CustomerController();
			
			int check = custCtrl.deleteAddress(id);
			
			// Prints out a message for the user.
			
			if (check == 1)
			{
				viewAddresses(request, response, "An address was deleted.");
			}
			else
			{
				viewAddresses(request, response, "Failed to delete an address.");
			}
		}
		else
		{
			viewAddresses(request, response, "You must select an address to delete");
		}
	}

	// Edits a selected address.
	
	private void editAddress(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		// Checks if an address is selected.
		
		if (!request.getParameter("editAddress").equals(""))
		{
			int id = Integer.parseInt(request.getParameter("editAddress"));
			
			String street = request.getParameter("street");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String zip = request.getParameter("zip");
			
			CustomerController custCtrl = new CustomerController();
			
			// Updates the address in the database.
			
			int check = custCtrl.editAddress(id, street, city, state, zip);
			
			// Prints out messages for the user.
			
			if (check >= 1)
			{
				viewAddresses(request, response, "An address has been changed.");
			}
			else
			{
				viewAddresses(request, response, "Failed to change an address.");
			}
		}
		else
		{
			viewAddresses(request, response, "You must first select an address to edit it.");
		}
	}

	// Adds an address to the database.
	
	private void addAddress(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		
		CustomerController custCtrl = new CustomerController();
		HttpSession sess = request.getSession(false);
		int userId = (int)sess.getAttribute("userID");
		
		int check = custCtrl.addAddress(userId, street, city, state, zip);
		
		if (check == 0)
		{
			viewAddresses(request, response, "Failed to add an address");
		}
		else
		{
			viewAddresses(request, response, "An address has been added.");
		}
	}

	// Displays a page for viewing, adding, editing, and deleting addresses.
	
	private void viewAddresses(HttpServletRequest request, HttpServletResponse response, String message) {
		// TODO Auto-generated method stub
		CustomerController custCtrl = new CustomerController();
		
		List<Address> addressList = new ArrayList<Address>();
		
		HttpSession sess = request.getSession(false);
		String id = sess.getAttribute("userID").toString();
		addressList = custCtrl.getAddresses(Integer.parseInt(id));
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		root.put("addressList", addressList);
		root.put("message", message);
		String templateName = "addressPage.ftl";
		process.processTemplate(templateName, root, request, response);
	}

	// Displays a list of books for the Customer to browse.
	
	private void browseBooks(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		CustomerController custCtrl = new CustomerController();
		
		List<IBook> bookList = custCtrl.browseBooks();
		root.put("books", bookList);
		String templateName = "customerBrowse.ftl";
		process.processTemplate(templateName, root, request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
