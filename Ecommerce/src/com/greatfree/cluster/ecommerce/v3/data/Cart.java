package com.greatfree.cluster.ecommerce.v3.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.greatfree.cluster.ecommerce.data.CartItem;
import com.greatfree.cluster.ecommerce.data.Product;

public class Cart implements Serializable {

	private static final long serialVersionUID = 1066498182968266724L;
	
	private Map<String, CartItem> items;
	private Map<Integer, String> ItemIndex;
	private int count;
	
	public Cart(String userName) {
		this.items = new LinkedHashMap<>();
		this.ItemIndex = new HashMap<>();
		this.count = 0;
	}
	

    public boolean addItem(CartItem item) {	
    	if(item == null) {
    		return false;
    	}   
       int quantity = item.getQuantity();
       String productKey = item.getProduct().getKey();
        
        if (items.containsKey(productKey)) 
        {
            CartItem existingItem = items.get(productKey);
            existingItem.increaseQuantity(quantity);
        } 
        else 
        { 	
            items.put(productKey, item);
            count++;
            ItemIndex.put(count, productKey);
        }
        
       return true;  
    }
    
    public Map<Integer, String> getIndex(){
    	return this.ItemIndex;
    }
    
    
    // Remove item from cart
    public boolean removeItem(String productKey) {
    	items.remove(productKey);
        return true;
    }
    
    public int getItemQuantity(String productKey) {
    	return items.get(productKey).getQuantity();
    }
    
    // Update item quantity
    public void updateQuantity(String productKey, int newQuantity) {
        if (!items.containsKey(productKey)) {
            throw new IllegalArgumentException("Product not in cart");
        }
        
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        
        CartItem item = items.get(productKey);
        Product product = item.getProduct();
        
        // Calculate stock adjustment
        int quantityDifference = newQuantity - item.getQuantity();
        
        if (quantityDifference > product.getStockQuantity()) {
            throw new IllegalArgumentException("Insufficient stock");
        }
        
        // Update product stock
        product.setStockQuantity(product.getStockQuantity() - quantityDifference);
        
        if (newQuantity == 0) {
            removeItem(productKey);
        } else {
            item.setQuantity(newQuantity);
        }
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
    // Get all cart items
    public Map<String, CartItem> getItems() {
        return new HashMap<>(items); // Return copy to preserve encapsulation
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

