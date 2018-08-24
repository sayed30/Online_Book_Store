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

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import logic.ShippingController;
import object.Order;

/* Authors: Bradley Reeves, Lakshay Sharma,  Aditya Yadav,  Dhanashree Joshi, Sayed Hussaini   
 * 
 * Description: A servlet used for the Shipping Employee.
 */

/**
 * Servlet implementation class ShippingServlet
 */
@WebServlet("/ShippingServlet")
public class ShippingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String templateDir = "/WEB-INF/templates";
	
	private TemplateProcessor process;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShippingServlet() {
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
		String orderChange = request.getParameter("orderChange");
		String viewOrders = request.getParameter("viewOrders");
		
		// Displays the orders to the shipping employee
		
		if (viewOrders != null)
		{
			viewOrders(request, response);
		}
		
		// Lets the shipping employee update the status of orders.
		
		else if (orderChange != null)
		{
			changeOrderStatus(request, response);
		}
	}

	// Displays a list of order to the shipping employee based on date and order status.
	
	private void viewOrders(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ShippingController sCtrl = new ShippingController();
		
		List<Order> orderList = new ArrayList<Order>();
		
		// Retrieves a list of orders.
		
		orderList = sCtrl.viewOrders();
		
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		
		// Puts the list in root then processes the template.
		
		root.put("orders", orderList);
		String templateName = "viewOrders.ftl";
		process.processTemplate(templateName, root, request, response);
	}

	// Changes the status of an order.
	
	private void changeOrderStatus(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String status = request.getParameter("status");
		String orderID = request.getParameter("orderChange");
		
		ShippingController sCtrl = new ShippingController();
		
		// Changes status in database.
		
		int check = sCtrl.changeOrderStatus(orderID, status);
		
		// Returns an int in order to tell if the change was successful.
		
		Gson gson = new Gson();
        try {
			response.getWriter().write(gson.toJson(check));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
