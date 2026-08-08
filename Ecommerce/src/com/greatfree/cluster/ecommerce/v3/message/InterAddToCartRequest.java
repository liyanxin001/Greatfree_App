package com.greatfree.cluster.ecommerce.v3.message;

import com.greatfree.cluster.ecommerce.data.CartItem;

import edu.greatfree.cluster.message.InterChildrenRequest;


public class InterAddToCartRequest extends InterChildrenRequest{

	private static final long serialVersionUID = 3291649250115136316L;
	
    private CartItem item;
    
	public InterAddToCartRequest(AddToCartRequest request, CartItem item) {
		super(request);
		this.item = item;
	}

	public CartItem getItem() {
		return item;
	}

	public void setItem(CartItem item) {
		this.item = item;
	}

}
