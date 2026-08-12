package com.greatfree.cluster.ecommerce.v3.message;

import edu.greatfree.cluster.message.InterChildrenNotification;


public class InterRemoveFromSaleNotification extends InterChildrenNotification{

	private static final long serialVersionUID = -6931931079669390967L;
	
	
	private boolean isRegistered;

	public InterRemoveFromSaleNotification(RemoveFromSaleNotification notification, boolean isRegistered) {
		super(notification);
		this.isRegistered = isRegistered;
	}

	public boolean isUnRegistered() {
		return isRegistered;
	}

	public void setUnRegistered(boolean isUnRegistered) {
		this.isRegistered = isUnRegistered;
	}

}
