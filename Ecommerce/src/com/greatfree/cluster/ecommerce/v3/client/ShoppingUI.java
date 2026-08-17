package com.greatfree.cluster.ecommerce.v3.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.greatfree.exceptions.NullClassConversionException;
import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.util.Tools;

import com.greatfree.cluster.ecommerce.data.Product;
import com.greatfree.cluster.ecommerce.v2.client.ShoppingMenuOptions;


import com.greatfree.cluster.ecommerce.v3.message.AddToCartResponse;
import com.greatfree.cluster.ecommerce.v3.message.GetCartRequest;
import com.greatfree.cluster.ecommerce.v3.message.GetCartResponse;
import com.greatfree.cluster.ecommerce.v2.message.PayRequest;
import com.greatfree.cluster.ecommerce.v3.message.PayResponse;
import com.greatfree.cluster.ecommerce.v3.message.PlaceOrderNotification;
import com.greatfree.cluster.ecommerce.v3.message.RemoveFromCartNotification;
import com.greatfree.cluster.ecommerce.v3.data.Order;
import com.greatfree.cluster.ecommerce.v3.message.AddToCartRequest;

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
	
	public static void execute(String userName, String storeName, int option, Map<Integer, Product> products) throws ClassNotFoundException, RemoteReadException, IOException, NullClassConversionException, InterruptedException
	{
		switch(option)
		{
		    case ShoppingMenuOptions.ADD_TO_CART:
		 
		    	System.out.println("Which product?(enter the number)");
		    	int number_1 = Integer.parseInt(Tools.INPUT.nextLine());
		    	System.out.println("How many?");
		        int quantity = Integer.parseInt(Tools.INPUT.nextLine());
		        
		        if(products.get(number_1).getStoreName().equals(storeName)) {
		        	System.out.println("You can't purchase products from your own store.");
		        	break;
		        }		       
		    	List<AddToCartResponse> wfsr = ClusterClient.MULTI().read(ClusterUI.CL().getRootAddress().getIP(),
		    		 ClusterUI.CL().getRootAddress().getPort(), new AddToCartRequest(products.get(number_1),userName, quantity),
		    		 AddToCartResponse.class); 
		    	
		    	for(AddToCartResponse entry: wfsr) 
		    	{
		    		if(entry.isSucceeded()) {
		    			System.out.println("Added to cart successfully.");

		    		}else {
		    			System.out.println("Invaild amount.");
		    		}
		    		break;
		    	}
		    	break;
		    	
		    case ShoppingMenuOptions.REMOVE_FROM_CART:
		    	System.out.println("Which item?(enter the number)");
		    	int number_2 = Integer.parseInt(Tools.INPUT.nextLine());
		    	ClusterClient.MULTI().syncNotify(ClusterUI.CL().getRootAddress().getIP(), 
		    			ClusterUI.CL().getRootAddress().getPort(), new RemoveFromCartNotification(userName,  
		    			products.get(number_2).getKey()));
		    	break;
		    	
		    case ShoppingMenuOptions.CHECK_CART:
		    	List<GetCartResponse> gcr = ClusterClient.MULTI().read(ClusterUI.CL().getRootAddress().getIP(),
		    		 ClusterUI.CL().getRootAddress().getPort(), new GetCartRequest(userName), 
		    		 GetCartResponse.class);
		    	for(GetCartResponse entry : gcr)
		    	{
		    		if(!(entry.getCart() == null)) 
		    		{
		    			entry.getCart().displayCart();
		    		}
		    		else 
		    		{
		    			System.out.println("Your cart is empty.");
		    		}
		    		break;
		    	}
		    	break;
		    	
		    case ShoppingMenuOptions.CHECK_OUT:
		    	List<PayResponse> pr = ClusterClient.MULTI().read(ClusterUI.CL().getRootAddress().getIP(),
		    		 ClusterUI.CL().getRootAddress().getPort(), new PayRequest(userName),
		    		 PayResponse.class);
		    	System.out.println("Debugging: Response received is" + pr.size());
		    	List<Order> orders = new ArrayList<>();
		    	for(PayResponse entry : pr) 
		    	{
		    		System.out.println("Debugging: Orders size is " + entry.getOrders().size());
		    		if(!(entry.getOrders() == null))
		    		{
		    			orders =entry.getOrders();
		    			for(Order order : orders)
				    	{
				    		ClusterClient.MULTI().syncNotify(ClusterUI.CL().getRootAddress().getIP(), 
					    			ClusterUI.CL().getRootAddress().getPort(), new PlaceOrderNotification(  
					    			order));
				    	}
		    			break;
		    		}
		    		else 
		    		{
		    			System.out.println("Your cart is empty.");
		    		}
		    		break;
		    	}
		    	
		    	break;
		    case ShoppingMenuOptions.QUIT:
		    	 break;
		}
	}

}
