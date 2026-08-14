package com.greatfree.cluster.ecommerce.v3.client;

import java.util.Date;
import java.util.List;

import org.greatfree.util.Time;

import com.greatfree.cluster.ecommerce.v3.message.LatestOrdersRequest;
import com.greatfree.cluster.ecommerce.v3.message.LatestOrdersResponse;

import edu.greatfree.framework.cluster.multicast.client.ClusterClient;

final class ClusterChecker implements Runnable {
    
	private String userName;
    private String storeName;
    private boolean isFirstTime;
    private Date latestOrderTime;
    
    public ClusterChecker(String userName, String storeName) {
        this.userName = userName;
        this.storeName = storeName;
        this.isFirstTime = true;
        this.latestOrderTime = Time.INIT_TIME;
    }

    public void run() {
        try {
            List<LatestOrdersResponse> lcrs = null;
            
            if (this.isFirstTime) {
                lcrs = ClusterClient.MULTI().read(
                    ClusterUI.CL().getRootAddress().getIP(),
                    ClusterUI.CL().getRootAddress().getPort(),
                    new LatestOrdersRequest(this.userName, this.storeName, Time.INIT_TIME),
                    LatestOrdersResponse.class
                );
                this.isFirstTime = false;
            } else {
                lcrs = ClusterClient.MULTI().read(
                    ClusterUI.CL().getRootAddress().getIP(),
                    ClusterUI.CL().getRootAddress().getPort(),
                    new LatestOrdersRequest(this.userName, this.storeName, this.latestOrderTime),
                    LatestOrdersResponse.class
                );
            }
            
            for (LatestOrdersResponse entry : lcrs) {
                if (entry.getOrders() != null) {
                    if (this.latestOrderTime.before(entry.getOrders().get(0).getTime())){
                        this.latestOrderTime = entry.getOrders().get(0).getTime();
                    }
                    
                    System.out.println("latestChatTime = " + String.valueOf(this.latestOrderTime));
                    
                    for (int i = entry.getOrders().size() - 1; i >= 0; i--) {
                        System.out.println(entry.getOrders().get(i));
                    }
                }
            }
            
        } catch (ClassNotFoundException | org.greatfree.exceptions.RemoteReadException | 
                 java.io.IOException | org.greatfree.exceptions.NullClassConversionException e) {
            e.printStackTrace();
        }
    }
}