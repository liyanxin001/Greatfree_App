package com.greatfree.cluster.ecommerce.v3.data;

import java.io.Serializable;
import java.util.Date;

import org.greatfree.util.Tools;

import com.greatfree.cluster.ecommerce.data.Product;

public class Order implements Serializable{

	private static final long serialVersionUID = -1623496604490092358L;
	
	private String Key;
	private String userName;
	private int amount;
	private Product product;
	private Date time;
	
	public Order(String userName, int amount, Product product, Date time) {
		this.Key = Tools.generateUniqueKey();
		this.userName = userName;
		this.amount = amount;
		this.product = product;
		this.time = time;
	}
	
	@Override
	public String toString() {
		return userName + "bought" + amount + product.getProductName() + "!" + time;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getKey() {
		return Key;
	}

	public void setKey(String key) {
		Key = key;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
}
