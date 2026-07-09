package com.greatfree.cluster.ecommerce.v2.client;

import java.io.IOException;
import java.util.List;

import org.greatfree.exceptions.NullClassConversionException;
import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.util.IPAddress;
import org.greatfree.util.Tools;


import com.greatfree.cluster.ecommerce.v2.data.Product;
import com.greatfree.cluster.ecommerce.v2.message.CreateStoreRequest;
import com.greatfree.cluster.ecommerce.v2.message.CreateStoreResponse;

import com.greatfree.cluster.ecommerce.v2.message.GetStoreRequest;
import com.greatfree.cluster.ecommerce.v2.message.GetStoreResponse;
import com.greatfree.cluster.ecommerce.v2.message.SearchForProductsRequest;
import com.greatfree.cluster.ecommerce.v2.message.SearchForProductsResponse;

import edu.greatfree.framework.cluster.multicast.client.ClusterClient;

final class ClusterUI {
	
	private IPAddress rootAddress;
	
    private ClusterUI() {
    	
    }
	private static ClusterUI instance = new ClusterUI(); 
	
	public static ClusterUI CL()
	{
		if(instance == null) 
		{
			instance = new ClusterUI();
			return instance;
		}
		else
		{
			return instance;	
		}
	
	}
	
	public void init() throws ClassNotFoundException, RemoteReadException, IOException {
		this.rootAddress = ClusterClient.MULTI().getAddress("192.168.1.25", 8941, "Root");
	}
	
	public IPAddress getRootAddress() {
		return this.rootAddress;
	}
	
	public void printMenu(String storeName) {
		System.out.println(HomeMenu.MENU_HEAD);
		System.out.println(HomeMenu.SEARCH_FOR_PRODUCTS);
		System.out.println(HomeMenu.CREATE_STORE + storeName);
		System.out.println(HomeMenu.GO_TO_STORE + storeName);
		System.out.println(HomeMenu.QUIT);
		System.out.println(HomeMenu.MENU_TAIL);
		System.out.println(HomeMenu.INPUT_PROMPT);
	}
	
	public void execute(String userName, String storeName, int option) throws ClassNotFoundException, RemoteReadException, IOException, NullClassConversionException, InterruptedException 
	{
		switch(option)		
		{
		     case HomeMenuOptions.CREATE_STORE:
		    	 List<CreateStoreResponse> csr = ClusterClient.MULTI().read(this.rootAddress.getIP(), 
		    		  this.rootAddress.getPort(), new CreateStoreRequest(userName, storeName), 
		    		  CreateStoreResponse.class);
		    	 for(CreateStoreResponse entry : csr)
		    	 {
		    		 System.out.println("Creating Store status:" + entry.isSucceeded());
		    		 break; 
		    	 }
		    	 break;
		    	 
		     case HomeMenuOptions.GO_TO_STORE:
		    	 int storeOption = StoreMenuOptions.NO_OPTION;
		    	 String optionStr;
		    	 
		    	 while (storeOption != StoreMenuOptions.QUIT)
		    	 {
		    		 List<GetStoreResponse> gsr = ClusterClient.MULTI().read(this.rootAddress.getIP(),
				    	      this.rootAddress.getPort(), new GetStoreRequest(storeName),
				    	      GetStoreResponse.class);
			    	 for(GetStoreResponse entry: gsr)
			    	 {
			    		  entry.getStore().displayStore();
			    		  break;
			    	 }
			    	 StoreUI.printMenu(storeName);
			    	 optionStr = Tools.INPUT.nextLine();
			    	 try 
			    	 {
			    		 storeOption = Integer.parseInt(optionStr);
				    	 System.out.println("Your choice:" + option);
						 StoreUI.execute(storeName, userName, storeOption);
					 } 
			    	 catch (NumberFormatException e) 
			    	 {
						storeOption = StoreMenuOptions.NO_OPTION;
						System.out.println("Wrong Option");
					 }
		    	 }
		    	 break;
		    	 
		     case HomeMenuOptions.START_SHOPPING:
		    	 System.out.println("\n==========SEARCH BAR==========");
		    	 System.out.println("Enter the keyword:");
		    	 String keyword = Tools.INPUT.nextLine();
		    	 int shoppingOption = ShoppingMenuOptions.NO_OPTION;
		    	
		    	 
		    	 while(shoppingOption != ShoppingMenuOptions.QUIT) 
		    	 {
		    		 List<SearchForProductsResponse> sfpr = ClusterClient.MULTI().read(this.rootAddress.getIP(),
				    	      this.rootAddress.getPort(), new SearchForProductsRequest(userName, keyword),
				    	      SearchForProductsResponse.class);
		    		 System.out.println("\n==========SEARCH RESULTS==========");
		    		 for(SearchForProductsResponse entry: sfpr) 
		    		 {
		    			 List<Product> Products = entry.getProducts();		    			
			    		 for(Product product: Products) {
			    			 System.out.println(product.toString());	 
			    		 }
		    		 }
		    		 ShoppingUI.printMenu();
		    		 try 
		    		 {
			    		 shoppingOption = Integer.parseInt(Tools.INPUT.nextLine());
			    		 System.out.println("Your choice:" + option);
			    		 ShoppingUI.execute(userName, shoppingOption);	 
		    		 }
		    		 catch(NumberFormatException e)
		    		 {
		    			 shoppingOption = ShoppingMenuOptions.NO_OPTION;
		    			 System.out.println("Wrong Option");
		    		 }
		    	 }		    	 
		    	 break;
		}
		
		
		
	}
	
	
	
}
