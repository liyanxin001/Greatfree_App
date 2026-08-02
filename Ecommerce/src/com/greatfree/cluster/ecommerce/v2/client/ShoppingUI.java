package com.greatfree.cluster.ecommerce.v2.client;

import java.io.IOException;
import java.util.List;

import org.greatfree.exceptions.NullClassConversionException;
import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.util.Tools;


import com.greatfree.cluster.ecommerce.v2.message.AddToCartNotification;

import com.greatfree.cluster.ecommerce.v2.message.WithdrawFromStoreResponse;
import com.greatfree.cluster.ecommerce.v2.message.GetCartRequest;
import com.greatfree.cluster.ecommerce.v2.message.GetCartResponse;
import com.greatfree.cluster.ecommerce.v2.message.PayRequest;
import com.greatfree.cluster.ecommerce.v2.message.PayResponse;

import com.greatfree.cluster.ecommerce.v2.message.RemoveFromCartNotification;

import com.greatfree.cluster.ecommerce.v2.message.WithdrawFromStoreRequest;

import edu.greatfree.framework.cluster.multicast.client.ClusterClient;

final class ShoppingUI {
	
	
	public static void printMenu() 
	{
		 System.out.println("\n========== Menu Head ===========");
	     System.out.println("\t1) Add item to your cart");
	     System.out.println("\t2) Remove item from your cart");
	     System.out.println("\t3) Check your cart");
	     System.out.println("\t4) Check out");
	     System.out.println("\t0) Quit");
	     System.out.println("========== Menu Tail ===========\n");
	     System.out.println("Input an option:");			
	}
	
	public static void execute(String userName, int option) throws ClassNotFoundException, RemoteReadException, IOException, NullClassConversionException, InterruptedException
	{
		switch(option)
		{
		    case ShoppingMenuOptions.ADD_TO_CART:
		    	System.out.println("Which item do you want to add to your cart?");
		    	String productName_1 = Tools.INPUT.nextLine();
		    	System.out.println("Which store does this item belongs?");
		    	String storeName_1 = Tools.INPUT.nextLine();
		    	System.out.println("How many do you want?");
		        int quantity = Integer.parseInt(Tools.INPUT.nextLine());
		       
		    	List<WithdrawFromStoreResponse> wfsr = ClusterClient.MULTI().read(ClusterUI.CL().getRootAddress().getIP(),
		    		 ClusterUI.CL().getRootAddress().getPort(), new WithdrawFromStoreRequest(quantity, storeName_1, productName_1),
		    		 WithdrawFromStoreResponse.class); 
		    	for(WithdrawFromStoreResponse entry: wfsr) 
		    	{
		    		if(entry.getItem() != null) {
		    			ClusterClient.MULTI().syncNotify(ClusterUI.CL().getRootAddress().getIP(), ClusterUI.
		  		    		  CL().getRootAddress().getPort(), new AddToCartNotification(entry.getItem(), storeName_1, productName_1));
		    			System.out.println("Added to cart successfully");

		    		}else {
		    			System.out.println("Failed to add to cart");
		    		}
		    		break;
		    	}
		    	break;
		    	
		    case ShoppingMenuOptions.REMOVE_FROM_CART:
		    	System.out.println("Which item do you want to remove?");
		    	String productName_2 = Tools.INPUT.nextLine();
		    	System.out.println("Which store does this item belongs?");
		    	String storeName_2 = Tools.INPUT.nextLine();
		    	ClusterClient.MULTI().syncNotify(ClusterUI.CL().getRootAddress().getIP(), ClusterUI.CL().getRootAddress().getPort(), new RemoveFromCartNotification(userName, storeName_2, productName_2));
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
