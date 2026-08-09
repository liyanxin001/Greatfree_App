package com.greatfree.cluster.ecommerce.v3.message;

import edu.greatfree.cluster.message.InterChildrenNotification;


public class InterPutOnSaleNotification extends InterChildrenNotification{
	
	private static final long serialVersionUID = -8012127983674942302L;
	
	private boolean isRegistered;

	public InterPutOnSaleNotification(PutOnSaleNotification notification, boolean isRegistered) {
		super(notification);
		this.isRegistered = isRegistered;
	}

	public boolean isRegistered() {
		return isRegistered;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

}
