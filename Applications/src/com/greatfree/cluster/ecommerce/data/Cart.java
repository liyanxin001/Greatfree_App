package com.greatfree.cluster.ecommerce.data;

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
    public void addItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        
        if (quantity > product.getStockQuantity()) {
            throw new IllegalArgumentException("Insufficient stock. Available: " + product.getStockQuantity());
        }
        
        String productName = product.getProductName();
        if (items.containsKey(productName)) {
            CartItem existingItem = items.get(productName);
            int newQuantity = existingItem.getQuantity() + quantity;
            
            if (newQuantity > product.getStockQuantity()) {
                throw new IllegalArgumentException("Cannot add. Would exceed available stock.");
            }
            
            existingItem.increaseQuantity(quantity);
        } else {
            items.put(productName, new CartItem(product, quantity));
        }
        
        // Update product stock
        product.setStockQuantity(product.getStockQuantity() - quantity);
    }
    
    
    // Remove item from cart
    public void removeItem(String productName) {
        if (items.containsKey(productName)) {
            CartItem item = items.get(productName);
            // Restore stock
            Product product = item.getProduct();
            product.setStockQuantity(product.getStockQuantity() + item.getQuantity());
            
            items.remove(productName);
        }
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
    public void checkout() {
    	
    	items.clear();
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

