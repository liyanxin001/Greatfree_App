package com.greatfree.cluster.ecommerce.v3.child.app;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import com.greatfree.cluster.ecommerce.data.Product;



public final class ProductRepository {
	
	private final static Logger log = Logger.getLogger("com.greatfree.cluster.ecommerce.v3.app");
	
	private Map<String, Product > products;
	private Map<String, List<String>> StoreToProductKeys;
	private Map<String, String> productKeysToName;
	
   	private ProductRepository() 
   	{
   		this.setProducts(new ConcurrentHashMap<String, Product>());
   		this.setStoreToProductKeys(new ConcurrentHashMap<String, List<String>>());
   		this.setProductKeysToName(new ConcurrentHashMap<String, String>());
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

	public Map<String, String> getProductKeysToName() {
		return productKeysToName;
	}

	public void setProductKeysToName(Map<String, String> productKeysToName) {
		this.productKeysToName = productKeysToName;
	}

	public Map<String, List<String>> getStoreToProductKeys() {
		return StoreToProductKeys;
	}

	public void setStoreToProductKeys(Map<String, List<String>> storeToProductKeys) {
		StoreToProductKeys = storeToProductKeys;
	}

	public Map<String, Product > getProducts() {
		return products;
	}

	public void setProducts(Map<String, Product > products) {
		this.products = products;
	}



}
