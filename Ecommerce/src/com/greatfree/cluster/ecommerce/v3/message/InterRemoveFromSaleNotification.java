package com.greatfree.cluster.ecommerce.v3.message;

import edu.greatfree.cluster.message.InterChildrenNotification;


public class InterRemoveFromSaleNotification extends InterChildrenNotification{

	private static final long serialVersionUID = -6931931079669390967L;
	
	
	private boolean isUnRegistered;

	public InterRemoveFromSaleNotification(RemoveFromSaleNotification notification) {
		super(notification);
		// TODO Auto-generated constructor stub
	}

}
