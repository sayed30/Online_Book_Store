package logic;

import java.util.List;

import object.Order;
import persistent.OrderDA;

public class ShippingController {

	//This function enables shipping employee to view a list of orders
	
	public List<Order> viewOrders() {
		// TODO Auto-generated method stub
		return OrderDA.viewOrders();
	}
	
	//This functions enables shipping employee to change the order status

	public int changeOrderStatus(String orderID, String status) {
		// TODO Auto-generated method stub
		Order order = new Order();
		return order.changeOrderStatus(orderID, status);
	}

}
