package com.greatfree.cluster.ecommerce.v3.data;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.Map;

import com.greatfree.cluster.ecommerce.data.CartItem;
import com.greatfree.cluster.ecommerce.data.Product;

public class Cart implements Serializable {

	private static final long serialVersionUID = 1066498182968266724L;
	
	private Map<Integer, CartItem> items;

	private int count;
	
	public Cart(String userName) {
		this.items = new LinkedHashMap<>();

		this.count = 0;
	}
	

    public boolean addItem(CartItem item) {	
    	if(item == null) {
    	}   
        count++;
        items.put(count, item);
        
               return true;  
    }
    
    public Map<Integer, CartItem> getItems(){
    	return this.items;
    }
    
    
    // Remove item from cart
    public boolean removeItem(int number) {
    	items.remove(number);
        return true;
    }
    
    public int getItemQuantity(int number) {
    	return items.get(number).getQuantity();
    }
    
    // Get Total
    public double getTotal() {
        return items.values().stream()
            .mapToDouble(CartItem::getTotalPrice)
            .sum();
    }
     
    // Get number of items in cart
    public int getItemCount() {
        return items.values().stream()
            .mapToInt(CartItem::getQuantity)
            .sum();
    }
    
    // Get number of unique items
    public int getUniqueItemCount() {
        return items.size();
    }
    
    // Check if cart is empty
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    // Clear the cart
    public void clear() {
        // Restore all stock before clearing
        items.values().forEach(item -> {
            Product product = item.getProduct();
            product.setStockQuantity(product.getStockQuantity() + item.getQuantity());
        });
        
        items.clear();
    }
    //Checkout
    public boolean checkout() {
    	
    	items.clear();
    	return true;
    }


    // Display cart contents
    public void displayCart() {
        if (isEmpty()) {
            System.out.println("Your shopping cart is empty.");
            return;
        }
        
        System.out.println("\n=== SHOPPING CART ===");
        System.out.println("Items in cart: " + getItemCount() + " (" + getUniqueItemCount() + " unique)");
        System.out.println("---------------------");
        
        
        int counter = 1;
        for (CartItem item : items.values()) {
            System.out.printf("%d. %s%n", counter++, item.toString());
        }
        
        System.out.println("---------------------");
        System.out.printf("Total: $%.2f%n", getTotal());
        System.out.println("=====================\n");
    }





}

