package com.greatfree.cluster.ecommerce.v2.child;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import org.greatfree.exceptions.RemoteReadException;

import com.greatfree.cluster.ecommerce.v2.app.CartRegistry;
import com.greatfree.cluster.ecommerce.v2.app.StoreRegistry;
import com.greatfree.cluster.ecommerce.v2.message.AddStockQuantityNotification;
import com.greatfree.cluster.ecommerce.v2.message.AddToCartNotification;

import com.greatfree.cluster.ecommerce.v2.message.AppID;
import com.greatfree.cluster.ecommerce.v2.message.CartRegistryRequest;
import com.greatfree.cluster.ecommerce.v2.message.CartRegistryResponse;
import com.greatfree.cluster.ecommerce.v2.message.CreateStoreRequest;
import com.greatfree.cluster.ecommerce.v2.message.CreateStoreResponse;
import com.greatfree.cluster.ecommerce.v2.message.GetStoreRequest;
import com.greatfree.cluster.ecommerce.v2.message.GetStoreResponse;
import com.greatfree.cluster.ecommerce.v2.message.PayRequest;
import com.greatfree.cluster.ecommerce.v2.message.PayResponse;
import com.greatfree.cluster.ecommerce.v2.message.PutOnSaleNotification;
import com.greatfree.cluster.ecommerce.v2.message.RemoveFromCartRequest;
import com.greatfree.cluster.ecommerce.v2.message.RemoveFromCartResponse;
import com.greatfree.cluster.ecommerce.v2.message.RemoveFromSaleNotification;
import com.greatfree.cluster.ecommerce.v2.message.UpdateStockQuantityNotification;
import com.greatfree.cluster.ecommerce.v2.message.WithdrawFromStoreRequest;
import com.greatfree.cluster.ecommerce.v2.message.WithdrawFromStoreResponse;

import edu.greatfree.cluster.child.ChildTask;
import edu.greatfree.cluster.child.UnaryChild;
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
	public void processNotification(ClusterNotification notification) {
		switch(notification.getAppID()) 
		{
		    case AppID.ADD_STOCK_QUANTITY_NOTIFICATION:
			    log.info("ADD_TO_STORE_NOTIFICATION received @" + Calendar.getInstance().getTime());
			    AddStockQuantityNotification asqn = (AddStockQuantityNotification) notification;
			    StoreRegistry.SR().getStore(asqn.getStoreName()).addProductQuantity(asqn.getProductName(), asqn.getQuantity());
			    break;
			    
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
		    	StoreRegistry.SR().getStore(usqn.getStoreName()).getProductByName(usqn.getProductName()).setStockQuantity(usqn.getNewStockQuantity());
		    	break;
		    	
		    case AppID.ADD_TO_CART_NOTIFICATION:
		    	log.info("ADD_TO_CART_NOTIFICATION received @" + Calendar.getInstance().getTime());
		        AddToCartNotification atcn = (AddToCartNotification) notification;
		        CartRegistry.CR().getCart(atcn.getUserName()).addItem(atcn.getProductName(), atcn.getItem());
		        break;
		    
		    case ClusterAppID.SHUTDOWN_ROOT_NOTIFICATION:
				log.info("SHUTDOWN_ROOT_NOTIFICATION received @" +Calendar.getInstance().getTime());
				try 
				{
			    UnaryChild.CLUSTER().stop();
				} 
				catch (ClassNotFoundException | IOException | InterruptedException | RemoteReadException e) 
				{
			    e.printStackTrace();
				}
				break;
		}
		
	}

	@Override
	public MulticastResponse processRequest(ClusterRequest request) {
		switch(request.getAppID())
		{
		    case AppID.CREATE_STORE_REQUEST:
		    	log.info("CREATE_STORE_REQUEST @" + Calendar.getInstance().getTime());
		    	CreateStoreRequest csr = (CreateStoreRequest) request;
		    	return new CreateStoreResponse(StoreRegistry.SR().addStore(csr.getOwner(), csr.getStoreName()), csr.getCollaboratorKey());
		    	
		    case AppID.GET_STORE_REQUEST:	
		    	log.info("GET_STORE_REQUEST @" + Calendar.getInstance().getTime());
		    	GetStoreRequest getsr = (GetStoreRequest) request;
		    	return new GetStoreResponse(StoreRegistry.SR().getStore(getsr.getStoreName()),getsr.getCollaboratorKey());
		    	
		    case AppID.CART_REGISTRY_REQUEST:
		    	log.info("CART_REGISTRY_REQUEST @" + Calendar.getInstance().getTime());
		    	CartRegistryRequest crr = (CartRegistryRequest) request;
		    	return new CartRegistryResponse(CartRegistry.CR().createCart(crr.getUserName()), crr.getCollaboratorKey());
		    		    	
		    case AppID.WITHDRAW_FROM_STORE_REQUEST:
		    	log.info("WITHDRAW_FROM_STORE @" + Calendar.getInstance().getTime());
		    	WithdrawFromStoreRequest wfsr =  (WithdrawFromStoreRequest) request;
		    	return new WithdrawFromStoreResponse(StoreRegistry.SR().getStore(wfsr.getStoreName()).withdarwProduct(wfsr.getProductName(), wfsr.getQuantity()), wfsr.getCollaboratorKey());
		    
		    case AppID.SEARCH_FOR_PRODUCTS_REQUEST:
		    	log.info("SEARCH_FOR_PRODUCTS_REQUEST @" + Calendar.getInstance().getTime());
		        
		    	
		    case AppID.REMOVE_FROM_CART_REQUEST:
		    	log.info("REMOVE_FROM_CART_REQUEST @" + Calendar.getInstance().getTime());
		    	RemoveFromCartRequest rfcr = (RemoveFromCartRequest) request;
		    	return new RemoveFromCartResponse(CartRegistry.CR().getCart(rfcr.getUserName()).removeItem(rfcr.getProductName()), rfcr.getCollaboratorKey());
		    	
		    case AppID.PAY_REQUEST:
		    	log.info("PAY_REQUEST @" + Calendar.getInstance().getTime());
		    	PayRequest pr = (PayRequest) request;
		    	return new PayResponse(CartRegistry.CR().getCart(pr.getUserName()).checkout(), pr.getCollaboratorKey());
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

	public static Logger getLog() {
		return log;
	}

}
