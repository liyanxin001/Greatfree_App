package com.greatfree.cluster.ecommerce.v3.message;

import edu.greatfree.cluster.message.InterChildrenNotification;


public class InterPutOnSaleNotification extends InterChildrenNotification{
	
	private static final long serialVersionUID = -8012127983674942302L;

	public InterPutOnSaleNotification(PutOnSaleNotification notification) {
		super(notification);
		// TODO Auto-generated constructor stub
	}

}
