package edu.greatfree.cluster.ecommerce.data;

import java.io.Serializable;

import org.greatfree.util.Tools;

public class Product implements Serializable {

    private static final long serialVersionUID = 5466785191679916911L;
    
    private String productName;
    private String key;
    private int stockQuantity;
    private double price;
    private String storeName;
    
    public Product(String productName,  int stockQuantity, double price,String storeName) {
        this.productName = productName;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.storeName = storeName;
        this.setKey(Tools.generateUniqueKey());
    }
    public Product(String productName, double price) {
    	this.price = price;
    	this.productName = productName;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setproductName(String productName) {
        this.productName = productName;
    }
    
    
    public int getStockQuantity() {
        return stockQuantity;
    }
    
    public void setStockQuantity(int quantity) {
        this.stockQuantity = quantity;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String toString() {
        return String.format("%-30s | %-8d | $%-8.2f | %-20s", 
            productName, 
            stockQuantity, 
            price, 
            storeName);
    }

	public void increaseQuantity(int quantity) {
		this.stockQuantity += quantity;
		
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
}


