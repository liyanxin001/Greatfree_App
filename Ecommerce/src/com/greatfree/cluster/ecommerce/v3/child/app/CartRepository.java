package com.greatfree.cluster.ecommerce.v3.child.app;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.greatfree.cluster.ecommerce.v3.data.Cart;





public class CartRepository {
	  
    private static CartRepository instance;
    
    private final Map<String, Cart> carts = new ConcurrentHashMap<>();
    
    
    public static CartRepository CR() {
        if (instance == null) {
        	
            instance = new CartRepository();
            return instance;
        }
        return instance;
    }
    
    
    public Cart getOrCreateCart(String userName) {
        Cart cart = carts.get(userName);
        if (cart == null) {
            cart = new Cart(userName);
            carts.put(userName, cart);
        }
        return cart;
    }

    public Cart getCart(String userName) {
    	return this.carts.get(userName);
    }
}    
