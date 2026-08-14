package com.greatfree.cluster.ecommerce.v3.child.app;


import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import com.greatfree.cluster.ecommerce.data.CartItem;
import com.greatfree.cluster.ecommerce.data.Product;
import com.greatfree.cluster.ecommerce.v3.data.Order;





public final class ProductRepository {
	
	private final static Logger log = Logger.getLogger("com.greatfree.cluster.ecommerce.v3.app");
	
	private Map<String, Product > products;
	private Map<String, String> userToStore;
	private Map<String, List<String>> storeToProductKeys;
	private Map<String, String> productKeyToName;
	private Map<String, List<Order>> orders;
	
   	private ProductRepository() 
   	{
   		this.products = new ConcurrentHashMap<String, Product>();
   		this.userToStore = new ConcurrentHashMap<String, String>();
   		this.storeToProductKeys = new ConcurrentHashMap<String, List<String>>();
   		this.productKeyToName = new ConcurrentHashMap<String, String>();
   		this.orders = new ConcurrentHashMap<String, List<Order>>();
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
		return productKeyToName;
	}
	
	public void registerProduct(String productKey, String productName) {
		this.productKeyToName.put(productKey, productName);
	}
	
	public void UnregistryProducts(String productKey) {
		this.productKeyToName.remove(productKey);
	}
	
	public boolean isRegistered(String producKey) {
		if(this.productKeyToName.containsKey(producKey)) {
			return true;
		}else {
			return false;
		}
	}
	public void registerToStore(String storeName, String productKey) {
		this.storeToProductKeys.get(storeName).add(productKey);	
	}
	
	public void removeRegistryFromStore(String storeName, String productKey) {
		this.storeToProductKeys.get(storeName).remove(productKey);
	}
	
	public boolean isOwner(String userName, String storeName) 
	{
		if(userToStore.get(userName) == storeName) 
		{
			return true;
		}
		else 
		{
			return false;
		}		
	}
	public List<String> getStore(String userName, String storeName) 
	{
		if(userToStore.get(userName) == storeName) 
		{
			return storeToProductKeys.get(storeName);
		}
		else
		{
			return null;	
		}
	 	
	}
	
	public boolean registerStore(String userName, String storeName) {
		
		if(!userToStore.containsKey(userName))
		{
			if(!storeToProductKeys.containsKey(storeName)) {
				userToStore.put(userName, storeName);
				storeToProductKeys.put(storeName, new ArrayList<>());
				return true;
			}else {
				return false;
			}
				
		}else {
			return false;
		}
			
	}
	public void addProduct(Product product) {
		this.products.put(product.getKey(), product);
	}
	
	public void removeProduct(String productKey) {
		this.products.remove(productKey);
	}
	
	public CartItem packItem(String productKey, int quantity) {
		
	    Product product = this.products.get(productKey)	;
	    if(product.getStockQuantity() < quantity) {
	    	product.decreaseQuantity(quantity);
	    	return new CartItem(product, quantity);
	    }else {
	    	return null;
	    }
	    	
	}

	public List<String> searchProducts(String keyword) {
	    List<String> matchingKeys = new ArrayList<>();
	    
	    if (keyword == null || keyword.isEmpty()) {
	        return matchingKeys; 
	    }
	    
	    String keywordLower = keyword.toLowerCase();  
	    
	    for (Map.Entry<String, String> entry : productKeyToName.entrySet()) {
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
	
	public List<Order> getOrders(String userName, String storeName, Date timeStamp) {
	    log.info("timeStamp = " + String.valueOf(timeStamp) + " from " + userName);
	    
	    if(isOwner(userName, storeName))
	    {   
	      if (this.orders.containsKey(storeName)) {
	        
	        int lastIndex = this.orders.get(storeName).size() - 1;
	        if (lastIndex >= 0) {
	          
	          log.info("" + lastIndex + ") order's time = " + lastIndex);
	          
	          if (this.orders.get(storeName).get(lastIndex).getTime().after(timeStamp)) {
	            
	            int currentIndex = lastIndex;
	            List<Order> orders = new ArrayList<Order>();
	            
	            do {
	              orders.add(this.orders.get(storeName).get(currentIndex--));
	              if (currentIndex < 0)
	                continue; 
	              log.info("" + currentIndex + ") chat's time = " + currentIndex);
	              if (this.orders.get(storeName).get(currentIndex).getTime().before(timeStamp) || this.orders.get(storeName).get(currentIndex).getTime().equals(timeStamp))
	              {
	                break;    
	              }
	            }
	            while (currentIndex >= 0);
	            return orders;
	          } 
	        } 
	      } 
	    }
	    return null;
	  }

	public Map<String, List<Order>> getOrders() {
		return orders;
	}

	public void setOrders(Map<String, List<Order>> orders) {
		this.orders = orders;
	}
	
	public void addOrder(String storeName, Order order) {
		this.orders.get(storeName).add(order);
	}

	public Map<String, String> getUserToStore() {
		return userToStore;
	}

	public void setUserToStore(Map<String, String> userToStore) {
		this.userToStore = userToStore;
	}

}
