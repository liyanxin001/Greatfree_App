package com.greatfree.cluster.ecommerce.v3.child.app;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.greatfree.cluster.ecommerce.data.CartItem;
import com.greatfree.cluster.ecommerce.v3.data.Cart;
import com.greatfree.cluster.ecommerce.v3.data.Order;





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
    
    public List<Order> checkOut(String userName){
    	List<CartItem> cartList = new ArrayList<>(carts.get(userName).getItems().values());
    	List<Order> orderList = new ArrayList<>();
    	for(CartItem item: cartList) {
    		orderList.add(new Order(userName, item.getQuantity(), item.getProduct(), Calendar.getInstance().getTime()));
    	}
    	carts.get(userName).clear();
    	return orderList;
    }
  
}    
