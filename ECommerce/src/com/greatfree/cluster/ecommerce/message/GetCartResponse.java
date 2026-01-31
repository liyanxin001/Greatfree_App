package edu.greatfree.cluster.ecommerce.message;

import edu.greatfree.cluster.ecommerce.data.Cart;
import edu.greatfree.multicast.message.MulticastResponse;

public class GetCartResponse extends MulticastResponse {

	private static final long serialVersionUID = -7710071050837523546L;
	
	private Cart cart;
	
	public GetCartResponse(Cart cart, String collaboratorKey) {
		super(AppID.GET_CART_RESPONSE, collaboratorKey);
		this.setCart(cart);
	
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}




	
	

}
