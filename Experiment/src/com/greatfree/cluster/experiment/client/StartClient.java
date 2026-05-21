package com.greatfree.cluster.experiment.client;

import java.io.IOException;


import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.util.Tools;



import edu.greatfree.framework.cluster.multicast.client.ClusterClient;

final class StartClient {

	public static void main(String[] args)throws ClassNotFoundException, RemoteReadException, IOException,InterruptedException
	{	
		System.out.println("\nAre You a student or teacher?");
		System.out.println("\t1)I'm a student");
		System.out.println("\t2)I'm a teacher");
		int firstOption = Integer.parseInt(Tools.INPUT.nextLine());
		
		
		
		
		ClusterClient.MULTI().init();
		
		switch(firstOption)
		{
		    case 1:
		    	
		}
	}
}