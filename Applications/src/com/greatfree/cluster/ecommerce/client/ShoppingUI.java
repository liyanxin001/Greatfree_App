package com.greatfree.cluster.ecommerce.client;

import java.io.IOException;
import java.util.List;

import org.greatfree.exceptions.NullClassConversionException;
import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.util.Tools;

import com.greatfree.cluster.ecommerce.message.AddToCartRequest;
import com.greatfree.cluster.ecommerce.message.AddToCartResponse;
import com.greatfree.cluster.ecommerce.message.GetCartRequest;
import com.greatfree.cluster.ecommerce.message.GetCartResponse;
import com.greatfree.cluster.ecommerce.message.PayRequest;
import com.greatfree.cluster.ecommerce.message.PayResponse;
import com.greatfree.cluster.ecommerce.message.UpdateQuantityRequest;
import com.greatfree.cluster.ecommerce.message.UpdateQuantityResponse;

import edu.greatfree.framework.cluster.multicast.client.ClusterClient;

final class ShoppingUI {
	
	
	public static void printMenu() 
	{
		 System.out.println("\n========== Menu Head ===========");
	     System.out.println("\t1) Add item to your cart:");
	     System.out.println("\t2) Update item quantity ");
	     System.out.println("\t3) Check your cart");
	     System.out.println("\t4) Check out");
	     System.out.println("\t0) Quit");
	     System.out.println("========== Menu Tail ===========\n");
	     System.out.println("Input an option:");			
	}
	
	public static void execute(String userName, int option) throws ClassNotFoundException, RemoteReadException, IOException, NullClassConversionException
	{
		switch(option)
		{
		    case ShoppingMenuOptions.ADD_TO_CART:
		    	System.out.println("Which item do you want to add to your cart?");
		    	String productName_1 = Tools.INPUT.nextLine();
		    	System.out.println("which store does this item belongs?");
		    	String storeName = Tools.INPUT.nextLine();
		    	System.out.println("How many do you want?");
		        int quantity = Integer.parseInt(Tools.INPUT.nextLine());
		    	List<AddToCartResponse> atcr = ClusterClient.MULTI().read(ClusterUI.CL().getRootAddress().getIP(),
		    		 ClusterUI.CL().getRootAddress().getPort(), new AddToCartRequest(productName_1, storeName, quantity ,userName),
		    		 AddToCartResponse.class); 
		    	for(AddToCartResponse entry: atcr) 
		    	{
		    		if(entry.IsSuccessed()) {
		    			System.out.println("Added to cart successfully!");
		    		}else {
		    			System.out.println("Failed to add to cart");
		    		}
		    		break;
		    	}
		    	break;
		    	
		    case ShoppingMenuOptions.UPATE_QUANTITY:
		    	System.out.println("Upate quantity for which item?");
		    	String productName_2 = Tools.INPUT.nextLine();
		    	System.out.println("What's the new quantity?");
		    	int newQuantity = Integer.parseInt(Tools.INPUT.nextLine());
		    	List<UpdateQuantityResponse> uqr  = ClusterClient.MULTI().read(ClusterUI.CL().getRootAddress().getIP(),
		    		 ClusterUI.CL().getRootAddress().getPort(), new UpdateQuantityRequest(userName, productName_2, newQuantity),
		    		 UpdateQuantityResponse.class);
		    	for(UpdateQuantityResponse entry : uqr) 
		    	{
		    		if(entry.isSucceeded()) {
		    			System.out.println("Updated quantity sucessfully!");
		    		}else {
		    			System.out.println("Failed to update quantity");
		    		}
		    		break;
		    	}
		    	break;
		    	
		    case ShoppingMenuOptions.CHECK_CART:
		    	List<GetCartResponse> gcr = ClusterClient.MULTI().read(ClusterUI.CL().getRootAddress().getIP(),
		    		 ClusterUI.CL().getRootAddress().getPort(), new GetCartRequest(userName), 
		    		 GetCartResponse.class);
		    	for(GetCartResponse entry : gcr)
		    	{
		    		entry.getCart().displayCart();
		    		break;
		    	}
		    	break;
		    	
		    case ShoppingMenuOptions.CHECK_OUT:
		    	List<PayResponse> pr = ClusterClient.MULTI().read(ClusterUI.CL().getRootAddress().getIP(),
		    		 ClusterUI.CL().getRootAddress().getPort(), new PayRequest(userName),
		    		 PayResponse.class);
		    	for(PayResponse entry : pr) 
		    	{
		    		System.out.println("Checking out status:" + entry.isSucceeded());
		    		break;
		    	}
		    	break;
		    case ShoppingMenuOptions.QUIT:
		    	 break;
		}
	}

}
