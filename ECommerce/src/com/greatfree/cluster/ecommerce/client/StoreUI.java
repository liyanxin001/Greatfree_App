package edu.greatfree.cluster.ecommerce.client;

import java.io.IOException;

import org.greatfree.util.Tools;

import edu.greatfree.cluster.ecommerce.data.Product;
import edu.greatfree.cluster.ecommerce.message.PutOnSaleNotification;
import edu.greatfree.cluster.ecommerce.message.RemoveFromSaleNotification;
import edu.greatfree.cluster.ecommerce.message.UpdateStockQuantityNotification;
import edu.greatfree.framework.cluster.group.client.MenuOptions;
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
	
	public static void execute(String storeName, String userName, int option) throws IOException, InterruptedException 
	
	{
		switch(option)
		{
		    case StoreMenuOptions.PUT_ON_SALE:
		    	System.out.println("What product do you want to add to store?");
		    	String productName_1 = Tools.INPUT.nextLine();
		    	System.out.println("How many do you want to add?");
		    	int quantity = Tools.INPUT.nextInt();
		    	System.out.println("What's the price?");
		    	double price = Tools.INPUT.nextDouble();
		    	Tools.INPUT.nextLine(); 
		    	ClusterClient.MULTI().syncNotify(ClusterUI.CL().getRootAddress().getIP(), ClusterUI.
		    		  CL().getRootAddress().getPort(), new PutOnSaleNotification(new Product(productName_1, 
		    		  quantity, price, storeName), storeName));
		    	break;
		    	
		    case StoreMenuOptions.REMOVE_FROM_SALE:
		    	System.out.println("Which product do you want to Remove?");
		    	String productName_2 = Tools.INPUT.nextLine();
		    	ClusterClient.MULTI().syncNotify(ClusterUI.CL().getRootAddress().getIP(), ClusterUI.
		    		  CL().getRootAddress().getPort(), new RemoveFromSaleNotification(productName_2, storeName));
		    	break;
		    	
		    case StoreMenuOptions.UPDATE_STOCK_QUANTITY:
		    	System.out.println("Update stock for which product?");
		    	String productName_3 =Tools.INPUT.nextLine();
		    	System.out.println("What's the new stock quantity?");
		    	int newStockQuantity = Tools.INPUT.nextInt();
		    	Tools.INPUT.nextLine();
		    	ClusterClient.MULTI().syncNotify(ClusterUI.CL().getRootAddress().getIP(), ClusterUI.
		    		  CL().getRootAddress().getPort(), new UpdateStockQuantityNotification(productName_3, storeName, newStockQuantity));
		    	break;
		    	
		    case MenuOptions.QUIT:
		    	break;
		}
		
		
		
	}
     
   

}
