package edu.greatfree.cluster.ecommerce.data;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store implements Serializable {

	private static final long serialVersionUID = 7432301991141842474L;
	
	private String userName;
	private String storeName;
	private Map<String, Product> products;

    public Store() {
    	this.setStoreItems(new HashMap<>());
    }
    
    public Store(String storeName) {
    	this.setStoreItems(new HashMap<>());
    	this.storeName = storeName;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Map<String, Product> getStoreItems() {
		return products;
	}

	public void setStoreItems(Map<String, Product> storeItems) {
		this.products = storeItems;
	}
	
	public Store getStore() {
		return null;
		
	}
	
	public List<Product> getProducts() {
	    if (products == null || products.isEmpty()) {
	        return Collections.emptyList();
	    }
	    return List.copyOf(products.values()); // Java 10+ immutable list
	}
	
    public Product getProductByName(String productName) {
        return products.get(productName);
    }
	
	public void addProduct(Product product) {
		
	    String productName = product.getProductName();
	        
	        products.put(productName, product);		
	}
	
    public void removeProduct(String productName) {
        if (products.containsKey(productName)) {
         
            products.remove(productName);
        }
    }
    
    public void updateStockQuantity(String productName, int newQuantity) {
        if (!products.containsKey(productName)) {
            throw new IllegalArgumentException("Product not in store");
        }
        
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        
        Product product = products.get(productName);
        
        if (newQuantity == 0) {
            removeProduct(productName);
        } else {
            product.setStockQuantity(newQuantity);
        }
    }
    
    public boolean isEmpty() {
        return products.isEmpty();
    }
    
    public int getUniqueItemCount() {
        return products.size();
    }
    
    public void displayStore() {
        if (isEmpty()) {
            System.out.println("Your store is empty.");
            return;
        }
        
        System.out.println("\n=== Your Store ===");
        System.out.println("products on sale: " + getUniqueItemCount() + " (" + getUniqueItemCount() + " unique)");
        System.out.println("---------------------");
        
        products.values().forEach(product -> {
            System.out.println(product.toString());
        });
        
        System.out.println("=====================\n");
    }

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
}
