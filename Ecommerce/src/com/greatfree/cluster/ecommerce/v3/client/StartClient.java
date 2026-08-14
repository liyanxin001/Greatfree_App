package com.greatfree.cluster.ecommerce.v3.client;

import java.io.IOException;

import org.greatfree.concurrency.Scheduler;
import org.greatfree.exceptions.NullClassConversionException;
import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.util.Tools;

import com.greatfree.cluster.ecommerce.v2.client.HomeMenuOptions;

import edu.greatfree.framework.cluster.multicast.client.ClusterClient;
import edu.greatfree.mncs.client.ClientConfig;


final class StartClient {

	public static void main(String[] args)throws ClassNotFoundException, RemoteReadException, IOException,InterruptedException
	{	
		System.out.println("Enter your username:");
		String userName = Tools.INPUT.nextLine();
		System.out.println("Enter the name of your store:");
		String storeName = Tools.INPUT.nextLine();
		
		
		ClusterClient.MULTI().init();
		ClusterUI.CL().init();
		Scheduler.PERIOD().init(ClientConfig.SCHEDULER_POOL_SIZE, ClientConfig.SCHEDULER_KEEP_ALIVE_TIME);
		Scheduler.PERIOD().submit(new ClusterChecker(userName, storeName), ClientConfig.CHAT_POLLING_DELAY, ClientConfig.CHAT_POLLING_PERIOD);
		
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
				System.out.println("WrongBI option");
			}
		}
		Scheduler.PERIOD().shutdown(ClientConfig.SCHEDULER_SHUTDOWN_TIMEOUT);
		ClusterClient.MULTI().dispose();
		
	}	
}
