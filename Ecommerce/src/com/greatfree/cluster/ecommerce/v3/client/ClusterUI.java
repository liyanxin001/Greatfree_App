package com.greatfree.cluster.ecommerce.v3.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.greatfree.concurrency.Scheduler;
import org.greatfree.exceptions.NullClassConversionException;
import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.util.IPAddress;
import org.greatfree.util.Tools;

import com.greatfree.cluster.ecommerce.data.Product;
import com.greatfree.cluster.ecommerce.v2.client.HomeMenu;
import com.greatfree.cluster.ecommerce.v2.client.HomeMenuOptions;
import com.greatfree.cluster.ecommerce.v2.client.ShoppingMenuOptions;
import com.greatfree.cluster.ecommerce.v2.client.StoreMenuOptions;
import com.greatfree.cluster.ecommerce.v3.message.CreateStoreRequest;
import com.greatfree.cluster.ecommerce.v3.message.CreateStoreResponse;
import com.greatfree.cluster.ecommerce.v3.message.GetProductsRequest;
import com.greatfree.cluster.ecommerce.v3.message.GetProductsResponse;
import com.greatfree.cluster.ecommerce.v3.message.GetStoreRequest;
import com.greatfree.cluster.ecommerce.v3.message.GetStoreResponse;
import com.greatfree.cluster.ecommerce.v3.message.SearchForProductKeysRequest;
import com.greatfree.cluster.ecommerce.v3.message.SearchForProductKeysResponse;

import edu.greatfree.framework.cluster.multicast.client.ClusterClient;



final class ClusterUI {
	
	private IPAddress rootAddress;
	private final String randomSource;
	
    private ClusterUI() 
    {
    	this.randomSource = Tools.generateUniqueKey();
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
		System.out.println(HomeMenu.CREATE_STORE + storeName);
		System.out.println(HomeMenu.GO_TO_STORE + storeName);
		System.out.println(HomeMenu.SEARCH_FOR_PRODUCTS);
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
		    		 if(entry.isSucceeded())
		    		 {
		    			 System.out.println("Creating store status: true");
		    		 }
		    		 else
		    		 {
		    			 System.out.println("Action failed. Used name of store /You already created a store.");
		    		 }	 
		    		 break; 
		    	 }
		    	 break;
		    	 
		     case HomeMenuOptions.GO_TO_STORE:
		    	 int storeOption = StoreMenuOptions.NO_OPTION;
		    	 String optionStr;
		    	 boolean isOwner = false;
		    	 while (storeOption != StoreMenuOptions.QUIT)
		    	 {
		    		 List<String> productKeys = new ArrayList<String>();
		    		 Map<Integer, Product> products = new HashMap<>();
		    		 List<GetStoreResponse> gsr = ClusterClient.MULTI().read(this.rootAddress.getIP(),
				    	      this.rootAddress.getPort(), new GetStoreRequest(userName, storeName),
				    	      GetStoreResponse.class);	 
	                 for(GetStoreResponse entry: gsr)
	                 {
	                	 if (!entry.isOwner()) 
	                	 {
	                         System.out.println("You are not the owner of this store.");
	                         isOwner = false;
	                         break; 
	                     } 
	                	 else 
	                     {
	                         isOwner = true;
	                         if (entry.getProductKeys() != null) 
	                         {
	                             productKeys.addAll(entry.getProductKeys());
	                         }
	                     }
	                 }	  
	                 if (!isOwner) {
	                     break; 
	                 }
	                 if(productKeys.size() > 0) 
	                 {
	                	 List<GetProductsResponse> gpr = ClusterClient.MULTI().read(this.rootAddress.getIP(),
					    	      this.rootAddress.getPort(), new GetProductsRequest(storeName, productKeys),
					    	      GetProductsResponse.class);
		                 for(GetProductsResponse entry: gpr) 
		                 {
		                	 products = entry.getProducts();
		                	 break;
		                 }
		                 System.out.println("\n=== Your Store ===");
		                 for (Map.Entry<Integer, Product> entry : products.entrySet()) 
	                	 {
	                		 System.out.println(entry.getKey() + ". " + entry.getValue().toString());
	                	 }                	 
	                 }
	                 else 
	                 {
	                	 System.out.println("Your store is empty.");
	                 }     
			    	 StoreUI.printMenu(storeName);
			    	 optionStr = Tools.INPUT.nextLine();
			    	 try 
			    	 {
			    		 storeOption = Integer.parseInt(optionStr);
				    	 System.out.println("Your choice:" + storeOption);
						 StoreUI.execute(storeName, userName, storeOption, products);
					 } 
			    	 catch (NumberFormatException e) 
			    	 {
						storeOption = StoreMenuOptions.NO_OPTION;
						System.out.println("Wrong Option");
					 }
		    	 }
		    	
		    	 break;
		    	 
		     case HomeMenuOptions.SEARCH_FOR_PRODUCTS:
		    	 
		    	 int shoppingOption = ShoppingMenuOptions.NO_OPTION; 
		    	 while(shoppingOption != ShoppingMenuOptions.QUIT) 
		    	 {
		    		 List<String> productKeys = new ArrayList<String>();
		    		 Map<Integer, Product> products = new LinkedHashMap<>();
		    		 boolean hasResults = false;
		    		 
		    		 while(!hasResults) 
		    		 {
		    			 System.out.println("\n==========SEARCH BAR==========");
				    	 System.out.println("Enter the keyword (or type 'quit' to exit):");
				    	 String keyword = Tools.INPUT.nextLine().trim();
				    	 
				    	 if (keyword.equalsIgnoreCase("quit")) 
				    	 {
				                shoppingOption = ShoppingMenuOptions.QUIT;
				                break;
				         }
				    	 List<SearchForProductKeysResponse> sfpr = ClusterClient.MULTI().read(this.rootAddress.getIP(),
					    	      this.rootAddress.getPort(), new SearchForProductKeysRequest(randomSource, keyword),
					    	      SearchForProductKeysResponse.class);
				    	 for(SearchForProductKeysResponse entry: sfpr) 
			    		 {
			    			 if(entry.getProductKeys()!=null) 
			    			 {
			    				 productKeys.addAll(entry.getProductKeys());
			    			 }	
			    		 }
			    		 if(productKeys.size() > 0) 
			    		 {
			    			 List<GetProductsResponse> gpr = ClusterClient.MULTI().read(this.rootAddress.getIP(),
						    	      this.rootAddress.getPort(), new GetProductsRequest(randomSource, productKeys),
						    	      GetProductsResponse.class);
			    			 
			    			 int currentKey = 1;
			    			 for (GetProductsResponse entry : gpr) {
			    			     for (Product product : entry.getProducts().values()) {
			    			         products.put(currentKey++, product);
			    			     }
			    			 }
			    			 hasResults = true;
			             } 
			    		 else 
			    		 {
			                 System.out.println("No results found. Please try a different keyword.");
			             }		    	 		    	 		    	 
		    		 }
		    		 if (shoppingOption == ShoppingMenuOptions.QUIT) 
		    		 {
		    	            break;
		    	     }
		    		 System.out.println("\n==========SEARCH RESULTS==========");
	    			 for (Map.Entry<Integer, Product> entry : products.entrySet()) {
	    				    System.out.println(entry.getKey() + ". " + entry.getValue().toString());
	    			 }	
		    		 	  
		    		 ShoppingUI.printMenu();
		    		 try 
		    		 {
			    		 shoppingOption = Integer.parseInt(Tools.INPUT.nextLine());
			    		 System.out.println("Your choice:" + option);
			    		 ShoppingUI.execute(userName, shoppingOption, products);	 
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
