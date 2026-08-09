package com.greatfree.cluster.ecommerce.v3.message;

import edu.greatfree.cluster.message.InterChildrenNotification;


public class InterUpdateStockQuantityNotification extends InterChildrenNotification{

	private static final long serialVersionUID = 31713898096038201L;

	public InterUpdateStockQuantityNotification(UpdateStockQuantityNotification request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

}
