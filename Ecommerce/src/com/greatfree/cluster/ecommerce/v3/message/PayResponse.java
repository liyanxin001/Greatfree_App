package com.greatfree.cluster.ecommerce.v3.message;

import java.util.List;

import com.greatfree.cluster.ecommerce.v1.message.AppID;
import com.greatfree.cluster.ecommerce.v3.data.Order;

import edu.greatfree.multicast.message.MulticastResponse;

public class PayResponse extends MulticastResponse{

	private static final long serialVersionUID = 6321244057329872433L;
	
	private List<Order> orders;

	public PayResponse(List<Order> orders, String collaboratorKey) {
		super(AppID.PAY_RESPONSE, collaboratorKey);
		
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
