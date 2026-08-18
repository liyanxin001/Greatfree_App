package com.greatfree.cluster.ecommerce.v3.client;

import java.io.IOException;
import java.util.Map;

import org.greatfree.util.Tools;

import com.greatfree.cluster.ecommerce.data.Product;
import com.greatfree.cluster.ecommerce.v2.client.StoreMenuOptions;
import com.greatfree.cluster.ecommerce.v3.message.ProductRegistryNotification;
import com.greatfree.cluster.ecommerce.v3.message.PutOnSaleNotification;
import com.greatfree.cluster.ecommerce.v3.message.RemoveFromSaleNotification;
import com.greatfree.cluster.ecommerce.v3.message.RemoveProductRegistryNotification;
import com.greatfree.cluster.ecommerce.v3.message.UpdateStockQuantityNotification;


import edu.greatfree.framework.cluster.multicast.client.ClusterClient;

final class StoreUI 

{
	
	public static void printMenu(String storeName)
	{
		 System.out.println("\n========== Menu Head ===========");
	     System.out.println("\t1) Add product to your store:" + storeName);
	     System.out.println("\t2) Remove product from your store " + storeName);
	     System.out.println("\t3) Update stock quantity");
	     System.out.println("\t0) Quit");
	     System.out.println("========== Menu Tail ===========\n");
	     System.out.println("Input an option:");
	}
	
	public static void execute(String storeName, String userName, int option, Map<Integer, Product> products) throws IOException, InterruptedException 
	
	{
		switch(option)
		{
		    case StoreMenuOptions.PUT_ON_SALE:
		    	System.out.println("What product do you want to add to store?");
		    	String productName_1 = Tools.INPUT.nextLine();
		    	System.out.println("How many do you want to add?");
		    	int quantity = Integer.parseInt(Tools.INPUT.nextLine());
		    	System.out.println("What's the price?");
		    	double price = Double.parseDouble(Tools.INPUT.nextLine());
		    	
		    	Product product = new Product(productName_1,  quantity, price, storeName);
		    	
		    	ClusterClient.MULTI().syncNotify(ClusterUI.CL().getRootAddress().getIP(), ClusterUI.
			    	  CL().getRootAddress().getPort(), new ProductRegistryNotification(product.getKey(), 
			    		   product.getProductName()));
		    	ClusterClient.MULTI().syncNotify(ClusterUI.CL().getRootAddress().getIP(), ClusterUI.
		    		  CL().getRootAddress().getPort(), new PutOnSaleNotification(product
		    				  ));
		    	break;
		    	
		
		    case StoreMenuOptions.REMOVE_FROM_SALE:
		    	System.out.println("Which product do you want to Remove?");
		    	int number = Integer.parseInt(Tools.INPUT.nextLine());
		    	
		    	ClusterClient.MULTI().syncNotify(ClusterUI.CL().getRootAddress().getIP(), ClusterUI.
		    		  CL().getRootAddress().getPort(), new RemoveProductRegistryNotification( products.get(number).getKey()
		    		  ));
		    	ClusterClient.MULTI().syncNotify(ClusterUI.CL().getRootAddress().getIP(), ClusterUI.
			    		  CL().getRootAddress().getPort(), new RemoveFromSaleNotification( products.get(number).getKey(), 
			    		   storeName));
		    	break;

		    	
		    case StoreMenuOptions.UPDATE_STOCK_QUANTITY:
		    	System.out.println("Update stock for which product?");
		    	String productName_3 =Tools.INPUT.nextLine();
		    	System.out.println("What's the new stock quantity?");
		    	int newStockQuantity = Tools.INPUT.nextInt();
		    	Tools.INPUT.nextLine();
		    	
		    	ClusterClient.MULTI().syncNotify(ClusterUI.CL().getRootAddress().getIP(), ClusterUI.
		    		  CL().getRootAddress().getPort(), new UpdateStockQuantityNotification(newStockQuantity, 
		    		  storeName, productName_3));
		    	break;
		    	
		    case StoreMenuOptions.QUIT:
		    	break;
		}
	}
     
   

}
