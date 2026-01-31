package edu.greatfree.cluster.ecommerce.client;

import java.io.IOException;
import java.util.List;

import org.greatfree.exceptions.NullClassConversionException;
import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.util.IPAddress;
import org.greatfree.util.Tools;

import edu.greatfree.cluster.ecommerce.data.Product;
import edu.greatfree.cluster.ecommerce.message.CreateStoreRequest;
import edu.greatfree.cluster.ecommerce.message.CreateStoreResponse;
import edu.greatfree.cluster.ecommerce.message.GetAllProductsRequest;
import edu.greatfree.cluster.ecommerce.message.GetAllProductsResponse;
import edu.greatfree.cluster.ecommerce.message.GetStoreRequest;
import edu.greatfree.cluster.ecommerce.message.GetStoreResponse;
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
		System.out.println(HomeMenu.CREATE_STORE + storeName);
		System.out.println(HomeMenu.GO_TO_STORE + storeName);
		System.out.println(HomeMenu.START_SHOPPING);
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
		    	 
		     case HomeMenuOptions.GO_TO_STORE:
		    	 int storeOption = StoreMenuOptions.NO_OPTION;
		    	 List<GetStoreResponse> gsr = ClusterClient.MULTI().read(this.rootAddress.getIP(),
		    	      this.rootAddress.getPort(), new GetStoreRequest(storeName),
		    	      GetStoreResponse.class);

		    	 while (storeOption != StoreMenuOptions.QUIT)
		    	 {
			    	 for(GetStoreResponse entry: gsr)
			    	 {
			    		  entry.getStore().displayStore();
			    		  break;
			    	 }
			    	 StoreUI.printMenu(storeName);
			    	 storeOption = Tools.INPUT.nextInt();
			    	 System.out.println("Your choice: option");
			    	 try 
			    	 {
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
		    	 int shopOption = ShoppingMenuOptions.NO_OPTION;
		    	 List<GetAllProductsResponse> gapr = ClusterClient.MULTI().read(this.rootAddress.getIP(),
		    		  this.rootAddress.getPort(), new GetAllProductsRequest(userName),
		    		  GetAllProductsResponse.class);
		    	 while(shopOption != ShoppingMenuOptions.QUIT)
		    	 {
		    		 for(GetAllProductsResponse entry: gapr) 
		    		 {
		    			 List<Product> allProducts = entry.getAllProducts();
			    		 for(Product product: allProducts) {
			    			 System.out.println(product.toString());	 
			    		 }
		    			 break;
		    		 }
		    		 ShoppingUI.printMenu();
		    		 shopOption = Tools.INPUT.nextInt();
		    		 Tools.INPUT.nextLine();
		    		 try 
		    		 {
			    		 ShoppingUI.execute(userName, shopOption);	 
		    		 }
		    		 catch(NumberFormatException e)
		    		 {
		    			 shopOption = ShoppingMenuOptions.NO_OPTION;
		    			 System.out.println("Wrong Option");
		    		 }
		    	 }
		    	 break;
		}
		
		
		
	}
	
	
	
}
