package com.greatfree.cluster.ecommerce.child;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import org.greatfree.exceptions.RemoteReadException;

import com.greatfree.cluster.ecommerce.child.app.CartRegistry;
import com.greatfree.cluster.ecommerce.child.app.StoreRegistry;
import com.greatfree.cluster.ecommerce.message.AddToCartRequest;
import com.greatfree.cluster.ecommerce.message.AddToCartResponse;
import com.greatfree.cluster.ecommerce.message.AppID;
import com.greatfree.cluster.ecommerce.message.CreateStoreRequest;
import com.greatfree.cluster.ecommerce.message.CreateStoreResponse;
import com.greatfree.cluster.ecommerce.message.GetAllProductsRequest;
import com.greatfree.cluster.ecommerce.message.GetAllProductsResponse;
import com.greatfree.cluster.ecommerce.message.GetCartRequest;
import com.greatfree.cluster.ecommerce.message.GetCartResponse;
import com.greatfree.cluster.ecommerce.message.GetStoreRequest;
import com.greatfree.cluster.ecommerce.message.GetStoreResponse;
import com.greatfree.cluster.ecommerce.message.PayRequest;
import com.greatfree.cluster.ecommerce.message.PayResponse;
import com.greatfree.cluster.ecommerce.message.PutOnSaleNotification;
import com.greatfree.cluster.ecommerce.message.RemoveFromSaleNotification;
import com.greatfree.cluster.ecommerce.message.UpdateQuantityRequest;
import com.greatfree.cluster.ecommerce.message.UpdateQuantityResponse;
import com.greatfree.cluster.ecommerce.message.UpdateStockQuantityNotification;

import edu.greatfree.cluster.child.ChildTask;
import edu.greatfree.cluster.message.ClusterNotification;
import edu.greatfree.cluster.message.ClusterRequest;
import edu.greatfree.cluster.message.ClusterResponse;
import edu.greatfree.cluster.message.InterChildrenNotification;
import edu.greatfree.cluster.message.InterChildrenRequest;
import edu.greatfree.cluster.message.IntercastNotification;
import edu.greatfree.cluster.message.IntercastRequest;
import edu.greatfree.framework.cluster.multicast.message.ClusterAppID;
import edu.greatfree.multicast.message.MulticastResponse;

final class MarketChildTask extends ChildTask{
	
	private final static Logger log = Logger.getLogger("edu.greatfree.cluster.ecommerce.child");

	@Override
	public void processNotification(ClusterNotification notification) 
	{
		switch(notification.getAppID())
		{
		   case AppID.PUT_ON_SALE_NOTIFICATION:
			   log.info("PUT_ON_SALE_NOTIFICATION received @" + Calendar.getInstance().getTime());
			   PutOnSaleNotification posn = (PutOnSaleNotification) notification;
			   StoreRegistry.SR().getStore(posn.getStoreName()).addProduct(posn.getProduct());
               break;
               
		   case AppID.REMOVE_FROM_SALE_NOTIFICATION:
			   log.info("REMOVE_FROM_SALE_NOTIFICATION received @" + Calendar.getInstance().getTime());
			   RemoveFromSaleNotification rfsn = (RemoveFromSaleNotification) notification;
			   StoreRegistry.SR().getStore(rfsn.getStoreName()).removeProduct(rfsn.getProductName());
			   break;
			   
		   case AppID.UPDATE_STOCK_QUANTITY_NOTIFICATION:
			   log.info("UPDATE_STOCK_QUANTITY_NOTIFICATION received @" + Calendar.getInstance().getTime());
			   UpdateStockQuantityNotification usqn = (UpdateStockQuantityNotification) notification;
			   StoreRegistry.SR().getStore(usqn.getStoreName()).updateStockQuantity(usqn.getProductName(),usqn.getNewStockQuantity());			      
			   break;
			   
		   case ClusterAppID.SHUTDOWN_ROOT_NOTIFICATION:
			   log.info("SHUTDOWN_ROOT_NOTIFICATION received @" +Calendar.getInstance().getTime());
			   try 
			   {
				StartChild.CLUSTER().stop();
			   } 
			   catch (ClassNotFoundException | IOException | InterruptedException | RemoteReadException e) 
			   {
				e.printStackTrace();
			   }
			   break;	   
		}
	}
	@Override
	public MulticastResponse processRequest(ClusterRequest request)
	{
		switch(request.getAppID()) 
		{
		    case AppID.GET_ALL_PRODUCTS_REQUEST:
		    	log.info("GET_ALL_PRODUCTS_REQUEST @" + Calendar.getInstance().getTime());
		    	GetAllProductsRequest gapr = (GetAllProductsRequest) request;
		    	return new GetAllProductsResponse(StoreRegistry.SR().getAllProducts(),gapr.getCollaboratorKey());
		    	
		    case AppID.CREATE_STORE_REQUEST:
		    	log.info("CREATE_STORE_REQUEST @" + Calendar.getInstance().getTime());
		    	CreateStoreRequest csr = (CreateStoreRequest) request;
		    	StoreRegistry.SR().addStore(csr.getUserName(), csr.getStoreName());
		    	return new CreateStoreResponse(true, csr.getCollaboratorKey());
		    	
		    case AppID.GET_STORE_REQUEST:
		    	log.info("GET_STORE_REQUEST @" + Calendar.getInstance().getTime());
		    	GetStoreRequest getsr = (GetStoreRequest) request;
		    	return new GetStoreResponse(StoreRegistry.SR().getStore(getsr.getStoreName()),getsr.getCollaboratorKey());
		    	
		    case AppID.ADD_TO_CART_REQUEST:
		    	log.info("ADD_TO_CART_REQUEST received @" + Calendar.getInstance().getTime());
		    	AddToCartRequest atcr = (AddToCartRequest) request;
		    	CartRegistry.CR().getCart(atcr.getUserName()).addItem(StoreRegistry.SR().getStore(atcr.getStoreName()).getProductByName(atcr.getProductName()),atcr.getQuantity());
		    	return new AddToCartResponse(true, atcr.getCollaboratorKey());
		    	
		    case AppID.GET_CART_REQUEST:
		    	log.info("GET_CART_REQUEST received @" + Calendar.getInstance().getTime());
		    	GetCartRequest gcr = (GetCartRequest) request;
		    	return new GetCartResponse(CartRegistry.CR().getCart(gcr.getUserName()),gcr.getCollaboratorKey());
		    	 	
		    case AppID.UPDATE_QUANTITY_REQUEST:
		    	log.info("UPDATE_QUANTITY_REQUEST received @" + Calendar.getInstance().getTime());
		    	UpdateQuantityRequest uqr = (UpdateQuantityRequest) request;
		    	CartRegistry.CR().getCart(uqr.getuserName()).updateQuantity(uqr.getProductName(), uqr.getQuantity());
		    	return new UpdateQuantityResponse(true, uqr.getCollaboratorKey());
		    	
		    case AppID.PAY_REQUEST:
		    	log.info("PAY_REQUEST received @" + Calendar.getInstance().getTime());
		    	PayRequest pr = (PayRequest) request;
		    	CartRegistry.CR().getCart(pr.getUserName()).checkout();
		    	return new PayResponse(true, pr.getCollaboratorKey());
		}
      return null;
	}

	@Override
	public InterChildrenNotification prepareNotification(IntercastNotification paramIntercastNotification) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InterChildrenRequest prepareRequest(IntercastRequest paramIntercastRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processNotification(InterChildrenNotification paramInterChildrenNotification) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processNotification(InterChildrenNotification paramInterChildrenNotification, List<String> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MulticastResponse> processRequest(InterChildrenRequest paramInterChildrenRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MulticastResponse> processRequest(InterChildrenRequest paramInterChildrenRequest,
			List<String> paramList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processResponse(ClusterResponse paramClusterResponse) {
		// TODO Auto-generated method stub
		
	}

}
