package com.greatfree.cluster.ecommerce.v3.message;

import java.util.List;

import com.greatfree.cluster.ecommerce.v3.data.Order;

import edu.greatfree.multicast.message.MulticastResponse;

public class LatestOrdersResponse extends MulticastResponse{

	private static final long serialVersionUID = 7147595090741499408L;
	
	private List<Order> orders;

	public LatestOrdersResponse(List<Order> orders, String collaboratorKey) {
		super(TRAppID.LATEST_ORDERS_RESPONSE, collaboratorKey);
		this.orders = orders;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
