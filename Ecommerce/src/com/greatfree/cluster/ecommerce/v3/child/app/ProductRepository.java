package com.greatfree.cluster.ecommerce.v3.child.app;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import com.greatfree.cluster.ecommerce.data.Product;



public final class ProductRepository {
	
	private final static Logger log = Logger.getLogger("com.greatfree.cluster.ecommerce.v3.app");
	
	private Map<String, Product > products;
	private Map<String, List<String>> storeToProductKeys;
	private Map<String, String> productKeysToName;
	
   	private ProductRepository() 
   	{
   		this.products = new ConcurrentHashMap<String, Product>();
   		this.storeToProductKeys = new ConcurrentHashMap<String, List<String>>();
   		this.productKeysToName = new ConcurrentHashMap<String, String>();
   	}
	
	private static ProductRepository instance = new ProductRepository();
	
	public static ProductRepository PR() {
		   if (instance == null) {
		       
		     instance = new ProductRepository();
		     return instance;
		   } 
		 
		     
	  return instance;
	}

	public static Logger getLog() {
		return log;
	}
	
	public Product getProduct(String productKey) {
		return this.products.get(productKey);
	}

	public Map<String, String> getProductKeysToName() {
		return productKeysToName;
	}
	
	public void registerProduct(String productKey, String productName) {
		this.productKeysToName.put(productKey, productName);
	}
	
	public boolean isRegistered(String producKey) {
		if(this.productKeysToName.containsKey(producKey)) {
			return true;
		}else {
			return false;
		}
	}
	public void registerToStore(String storeName, String productKey) {
		this.storeToProductKeys.get(storeName).add(productKey);	
	}
	
	public void UnregistryProducts(String productKey) {
		this.productKeysToName.remove(productKey);
	}

	public Map<String, List<String>> getStoreToProductKeys() {
		return storeToProductKeys;
	}
	
	public boolean registerStore(String storeName) {
		if(!storeToProductKeys.containsKey(storeName)) {
			storeToProductKeys.put(storeName, new ArrayList<>());
			return true;
		}else {
			return false;
		}
	}
	public void addProduct(Product product) {
		this.products.put(product.getKey(), product);
	}

	public List<String> searchProducts(String keyword) {
	    List<String> matchingKeys = new ArrayList<>();
	    
	    if (keyword == null || keyword.isEmpty()) {
	        return matchingKeys;  // Return empty list for null or empty keyword
	    }
	    
	    String keywordLower = keyword.toLowerCase();  // Case-insensitive search
	    
	    for (Map.Entry<String, String> entry : productKeysToName.entrySet()) {
	        String productName = entry.getValue();
	        if (productName != null && productName.toLowerCase().contains(keywordLower)) {
	            matchingKeys.add(entry.getKey());
	        }
	    }
	    
	    return matchingKeys;
	}
	
	public Map<Integer, Product> getMatchingProducts(List<String> productKeys) {
	    Map<Integer, Product> result = new LinkedHashMap<>();
	    
	    if (productKeys == null || productKeys.isEmpty()) {
	        return result;
	    }
	    
	    int counter = 1;
	    for (String key : productKeys) {
	        if (key != null) {
	            Product product = products.get(key);
	            if (product != null) {
	                result.put(counter++, product);
	            }
	        }
	    }
	    
	    return result;
	}
	

}
