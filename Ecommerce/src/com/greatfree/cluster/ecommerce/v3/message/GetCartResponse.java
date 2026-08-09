package com.greatfree.cluster.ecommerce.v3.message;

import com.greatfree.cluster.ecommerce.v3.data.Cart;
import com.greatfree.cluster.ecommerce.v1.message.AppID;

import edu.greatfree.multicast.message.MulticastResponse;

public class GetCartResponse extends MulticastResponse{

	private static final long serialVersionUID = 2374598869607715710L;
	
	private Cart cart;

	public GetCartResponse(Cart cart, String collaboratorKey) {
		super(AppID.GET_CART_RESPONSE, collaboratorKey);
		this.cart = cart;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

}
