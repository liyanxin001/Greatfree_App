package com.greatfree.cluster.ecommerce.v2.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.greatfree.cluster.ecommerce.v2.data.Product;
import com.greatfree.cluster.ecommerce.v2.data.Store;



public class StoreRegistry {
	
	private Map<String, Store> stores = new ConcurrentHashMap<>();
	private Map<String, String> userToStore = new ConcurrentHashMap<>();
	
	private static StoreRegistry instance = new StoreRegistry();
	
	public static StoreRegistry SR() {
		   if (instance == null) {
		       
		     instance = new StoreRegistry();
		     return instance;
		   } 
		 
		     
	  return instance;
	}

	public Map<String, Store> getStores() {
		return stores;
	}

	public void setStores(Map<String, Store> stores) {
		this.stores = stores;
	}
	
    public Store getStore(String storeName) {
        if (storeName == null || storeName.trim().isEmpty()) {
            throw new IllegalArgumentException("Client ID cannot be null or empty");
        }
        
        return stores.get(storeName);
        
    }
    public List<Product> getAllProducts() {
        if (stores.isEmpty()) {
            return Collections.emptyList(); // Return empty list instead of null
        }
        
        List<Product> allProducts = new ArrayList<>();
        
        for (Store store : stores.values()) {
            try {
                List<Product> storeProducts = store.getProducts();
                if (storeProducts != null && !storeProducts.isEmpty()) {
                    allProducts.addAll(storeProducts);
                }
            } catch (Exception e) {
                // Log error but continue processing other stores
                System.err.println("Error getting products from store: " + e.getMessage());
            }
        }
        
        return allProducts;
    }
    public boolean addStore(String userName, String storeName) {
    	if(stores.containsKey(storeName)) {
    		return false;
    	}else 
    	{
    	   userToStore.put(userName, storeName);
    	    Store store = new Store(storeName);
    	    stores.put(storeName, store);	
    	    
    	    return true;
    	}
    	
    }

	public Map<String, String> getUserTostore() {
		return userToStore;
	}

	public void setUserTostore(Map<String, String> userTostore) {
		this.userToStore = userTostore;
	}

	public Map<String, String> getUserToStore() {
		return userToStore;
	}

	public void setUserToStore(Map<String, String> userToStore) {
		this.userToStore = userToStore;
	}
	
	public List<Product> searchProductsByKeyword(String keyword) {
	    List<Product> matchingProducts = new ArrayList<>();
	    
	    if (keyword == null || keyword.trim().isEmpty()) {
	        return matchingProducts; // Return empty list for null or empty keyword
	    }
	    
	    String searchKeyword = keyword.trim().toLowerCase();
	    
	    for (Store store :stores.values()) {
	        if (store.getProducts() != null) {
	            for (Product product : store.getProducts()) {
	                if (matchesKeyword(product, searchKeyword)) {
	                    matchingProducts.add(product);
	                }
	            }
	        }
	    }
	    
	    return matchingProducts;
	}

	private boolean matchesKeyword(Product product, String keyword) {
	    if (product == null) {
	        return false;
	    }
	    
	    // Search in product name
	    if (product.getProductName() != null && 
	        product.getProductName().toLowerCase().contains(keyword)) {
	        return true;
	    }
	    
	    // Search in store name (if you want to include this)
	    if (product.getStoreName() != null && 
	        product.getStoreName().toLowerCase().contains(keyword)) {
	        return true;
	    }
	    
	    return false;
	}

}
