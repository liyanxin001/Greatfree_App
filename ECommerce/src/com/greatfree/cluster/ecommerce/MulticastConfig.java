package edu.greatfree.cluster.ecommerce;

import org.greatfree.util.Tools;

public final class MulticastConfig {
	
	public final static String REGISTRY_IP = "192.168.1.25";
	public final static int REGISTRY_PORT = 8941;
	
	public final static String ROOT_NAME = "Root";
    public final static String ROOT_KEY = Tools.getNodeKey(ROOT_NAME);
	public final static int ROOT_PORT = 8000;
	public final static int CHILD_PORT = 8001;

	
	

}
