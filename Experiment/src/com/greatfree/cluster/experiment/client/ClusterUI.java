package com.greatfree.cluster.experiment.client;

import java.io.IOException;

import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.util.IPAddress;
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
	
	public void printMenu() {
		System.out.println("\n==========Menu Head==========");
		System.out.println("\t1)Login in");
		System.out.println("\t2)Regsirtry an account");
		System.out.println("\n==========Menu Tail==========");

	}
	
	public void execute() {
		
	}
}
