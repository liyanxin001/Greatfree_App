package com.greatfree.cluster.ecommerce.v3.message;

import edu.greatfree.cluster.message.InterChildrenNotification;


public class InterUpdateStockQuantityNotification extends InterChildrenNotification{

	private static final long serialVersionUID = 31713898096038201L;
	
	private boolean isRegistered;
    
	public InterUpdateStockQuantityNotification(UpdateStockQuantityNotification request, boolean isRegistered) {
		super(request);
	}

	public boolean isRegistered() {
		return isRegistered;
	}

	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

}
