package edu.greatfree.cluster.ecommerce.child.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import edu.greatfree.cluster.ecommerce.data.Product;
import edu.greatfree.cluster.ecommerce.data.Store;


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
    public void addStore(String userName, String storeName) {
    	
 
    	   userToStore.put(userName, storeName);
    	    Store store = new Store(storeName);
    	    stores.put(storeName, store);	
    	
    	
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

}
