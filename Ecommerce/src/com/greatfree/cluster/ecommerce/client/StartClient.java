package com.greatfree.cluster.ecommerce.client;

import java.io.IOException;

import org.greatfree.exceptions.NullClassConversionException;
import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.util.Tools;

import edu.greatfree.framework.cluster.multicast.client.ClusterClient;

final class StartClient {

	public static void main(String[] args)throws ClassNotFoundException, RemoteReadException, IOException,InterruptedException
	{	
		System.out.println("Enter your user name:");
		String userName = Tools.INPUT.nextLine();
		System.out.println("Enter your store name:");
		String storeName = Tools.INPUT.nextLine();
		
		
		
		
		ClusterClient.MULTI().init();
		ClusterUI.CL().init();
		
		
		String optionStr;
		int option = HomeMenuOptions.NO_OPTION;
		while(option != HomeMenuOptions.QUIT)
		{
			ClusterUI.CL().printMenu(storeName);
			
			try 
			{
				optionStr = Tools.INPUT.nextLine();
				option = Integer.parseInt(optionStr);
				System.out.println("Your choice:" + option);
				ClusterUI.CL().execute(userName, storeName, option);
			} 
			catch (ClassNotFoundException | RemoteReadException | IOException | NullClassConversionException
					| InterruptedException e)
			{			
				option = HomeMenuOptions.NO_OPTION;
				System.out.println("Wrong option");
			}
		}
		ClusterClient.MULTI().dispose();
		
	}	
}
