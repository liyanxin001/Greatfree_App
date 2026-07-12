package com.greatfree.cluster.ecommerce.v2.message;

import edu.greatfree.cluster.message.InterChildrenNotification;


public class InterRemoveFromCartNotification extends InterChildrenNotification{
	
	
	private static final long serialVersionUID = 7827647751933467150L;

     

	private int quantity;

	public InterRemoveFromCartNotification(RemoveFromCartNotification notification, int quantity) {
		super(notification);
		this.setQuantity(quantity);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
