package com.greatfree.cluster.ecommerce.v1.message;

import com.greatfree.cluster.ecommerce.v1.data.CartItem;

import edu.greatfree.multicast.message.MulticastResponse;

public class WithdrawFromStoreResponse extends MulticastResponse{
	
	
	private static final long serialVersionUID = -1948179343069080736L;
	
	private CartItem item;

	public WithdrawFromStoreResponse(CartItem item, String collaboratorKey) {
		super(AppID.WITHDRAW_FROM_STORE_RESPONSE, collaboratorKey);
		this.setItem(item);
		
	}

	public CartItem getItem() {
		return item;
	}

	public void setItem(CartItem item) {
		this.item = item;
	}


}
