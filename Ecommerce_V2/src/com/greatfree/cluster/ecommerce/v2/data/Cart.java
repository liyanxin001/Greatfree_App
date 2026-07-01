package com.greatfree.cluster.ecommerce.v2.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Serializable {

	private static final long serialVersionUID = 1066498182968266724L;
	
	private Map<String, CartItem> items;
	private String userName;
	
	public Cart(String userName) {
		this.items = new HashMap<>();
		this.userName = userName;
	}
	
	// Add product to cart
    public boolean addItem(String productName, CartItem item) {

        
       int quantity = item.getQuantity();
        
        
        if (items.containsKey(productName)) {
            CartItem existingItem = items.get(productName);
         

            
            existingItem.increaseQuantity(quantity);
        } else {
            items.put(productName, item);
        }
        
       return true;  
    }
    
    
    // Remove item from cart
    public boolean removeItem(String productName) {
    	items.remove(productName);
        return true;
    }
    
    // Update item quantity
    public void updateQuantity(String productName, int newQuantity) {
        if (!items.containsKey(productName)) {
            throw new IllegalArgumentException("Product not in cart");
        }
        
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        
        CartItem item = items.get(productName);
        Product product = item.getProduct();
        
        // Calculate stock adjustment
        int quantityDifference = newQuantity - item.getQuantity();
        
        if (quantityDifference > product.getStockQuantity()) {
            throw new IllegalArgumentException("Insufficient stock");
        }
        
        // Update product stock
        product.setStockQuantity(product.getStockQuantity() - quantityDifference);
        
        if (newQuantity == 0) {
            removeItem(productName);
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
        
        items.values().forEach(item -> {
            System.out.println(item.toString());
        });
        
        System.out.println("---------------------");
        System.out.printf("Total: $%.2f%n", getTotal());
        System.out.println("=====================\n");
    }



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


}

