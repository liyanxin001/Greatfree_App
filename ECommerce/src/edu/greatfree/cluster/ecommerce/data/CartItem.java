package edu.greatfree.cluster.ecommerce.data;

import java.io.Serializable;

public class CartItem implements Serializable{

	private static final long serialVersionUID = 5965701792116873575L;

	
	private Product product;
	private int quantity;
	
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
	
	public Product getProduct() { 
		return product; 
		
	}
    public int getQuantity() { 
    	
    	return quantity; 
    }
    
    public void setQuantity(int quantity) { 
        if (quantity >= 0) {
            this.quantity = quantity;
        }
    }
    
    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }
    
    public void decreaseQuantity(int amount) {
        this.quantity = Math.max(0, this.quantity - amount);
    }
    
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
    
    public String toString() {
        return String.format("%s x%d = $%.2f", 
            product.getProductName(), quantity, getTotalPrice());
    }	

}
