package com.greatfree.cluster.ecommerce.v3.message;

import com.greatfree.cluster.ecommerce.v3.data.Order;

import edu.greatfree.cluster.message.ClusterNotification;

public class PlaceOrderNotification extends ClusterNotification{

	private static final long serialVersionUID = -2118636454798586349L;
	
	private Order order;

	public PlaceOrderNotification(Order order) {
		super(order.getProduct().getStoreName() , TRAppID.PLACE_ORDER_NOTIFICATION);
		this.order = order;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
