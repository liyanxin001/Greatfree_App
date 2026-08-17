package com.greatfree.cluster.ecommerce.v3.child;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

import java.util.logging.Logger;

import org.greatfree.exceptions.RemoteReadException;

import com.greatfree.cluster.ecommerce.v3.child.app.CartRepository;
import com.greatfree.cluster.ecommerce.v3.child.app.ProductRepository;

import com.greatfree.cluster.ecommerce.v1.message.AppID;
import com.greatfree.cluster.ecommerce.v3.message.AddToCartRequest;
import com.greatfree.cluster.ecommerce.v3.message.AddToCartResponse;
import com.greatfree.cluster.ecommerce.v3.message.CreateStoreRequest;
import com.greatfree.cluster.ecommerce.v3.message.CreateStoreResponse;
import com.greatfree.cluster.ecommerce.v3.message.GetCartRequest;
import com.greatfree.cluster.ecommerce.v3.message.GetCartResponse;
import com.greatfree.cluster.ecommerce.v3.message.GetProductsRequest;
import com.greatfree.cluster.ecommerce.v3.message.GetProductsResponse;
import com.greatfree.cluster.ecommerce.v3.message.GetStoreRequest;
import com.greatfree.cluster.ecommerce.v3.message.GetStoreResponse;
import com.greatfree.cluster.ecommerce.v3.message.InterAddToCartRequest;
import com.greatfree.cluster.ecommerce.v3.message.InterGetProductsRequest;
import com.greatfree.cluster.ecommerce.v3.message.InterPutOnSaleNotification;
import com.greatfree.cluster.ecommerce.v3.message.InterRemoveFromCartNotification;
import com.greatfree.cluster.ecommerce.v3.message.InterRemoveFromSaleNotification;
import com.greatfree.cluster.ecommerce.v3.message.InterUpdateStockQuantityNotification;
import com.greatfree.cluster.ecommerce.v3.message.LatestOrdersRequest;
import com.greatfree.cluster.ecommerce.v3.message.LatestOrdersResponse;
import com.greatfree.cluster.ecommerce.v2.message.PayRequest;
import com.greatfree.cluster.ecommerce.v3.message.PayResponse;
import com.greatfree.cluster.ecommerce.v3.message.PlaceOrderNotification;
import com.greatfree.cluster.ecommerce.v3.message.RemoveFromCartNotification;
import com.greatfree.cluster.ecommerce.v3.message.RemoveFromSaleNotification;
import com.greatfree.cluster.ecommerce.v3.message.ProductRegistryNotification;
import com.greatfree.cluster.ecommerce.v3.message.PutOnSaleNotification;
import com.greatfree.cluster.ecommerce.v3.message.RemoveProductRegistryNotification;
import com.greatfree.cluster.ecommerce.v3.message.SearchForProductKeysRequest;
import com.greatfree.cluster.ecommerce.v3.message.SearchForProductKeysResponse;

import com.greatfree.cluster.ecommerce.v3.message.TRAppID;
import com.greatfree.cluster.ecommerce.v3.message.UpdateStockQuantityNotification;

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

public class MarketChildTask  extends ChildTask{
	
	private final static Logger log = Logger.getLogger("edu.greatfree.cluster.ecommerce.child");

	@Override
	public void processNotification(ClusterNotification notification) {
		switch(notification.getAppID()) 
		{    
		    case TRAppID.PRODUCT_REGISTRY_NOTIFICATION:
		    	log.info("PRODUCT_REGISTRY_NOTIFICATION received @" + Calendar.getInstance().getTime());
				ProductRegistryNotification prn = (ProductRegistryNotification) notification;
				ProductRepository.PR().registerProduct(prn.getProductKey(), prn.getProductName());
	            break;
	            
		    case TRAppID.REMOVE_PRODUCT_REGISTRY_NOTIFICATION:
		    	log.info("REMOVE_PRODUCT_REGISTRY_NOTIFICATION received @" + Calendar.getInstance().getTime());
				RemoveProductRegistryNotification rprn = (RemoveProductRegistryNotification) notification;
				ProductRepository.PR().UnregistryProducts(rprn.getProductKey());
				break;
				
		    case TRAppID.PLACE_ORDER_NOTIFICATION:
		    	log.info("PLACE_ORDER_NOTIFICATION received @" + Calendar.getInstance().getTime());
		    	PlaceOrderNotification pon = (PlaceOrderNotification) notification;
		    	ProductRepository.PR().addOrder(pon.getOrder().getProduct().getStoreName(), pon.getOrder());
		    	break;
			
		    case ClusterAppID.SHUTDOWN_CHILDREN_NOTIFICATION:
				log.info("SHUTDOWN_CHILDREN_NOTIFICATION received @" +Calendar.getInstance().getTime());
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
		    	log.info("CREATE_STORE_REQUEST received @" + Calendar.getInstance().getTime());
		    	CreateStoreRequest csr = (CreateStoreRequest) request;
		    	return new CreateStoreResponse(ProductRepository.PR().registerStore(csr.getUserName(), csr.getStoreName()), csr.getCollaboratorKey());
		    	
		    case AppID.GET_STORE_REQUEST:	
		    	log.info("GET_STORE_REQUEST received @" + Calendar.getInstance().getTime());
		    	GetStoreRequest gsr = (GetStoreRequest) request;
		    	return new GetStoreResponse(ProductRepository.PR().isOwner(gsr.getUserName(), gsr.getStoreName()), ProductRepository.PR().getStore(gsr.getUserName(), gsr.getStoreName()), gsr.getCollaboratorKey());
		    
		    case TRAppID.SEARCH_FOR_PRODUCTS_KEYS_REQUEST:
		    	log.info("SEARCH_FOR_PRODUCTS_KEYS_REQUEST received @" + Calendar.getInstance().getTime());
		        SearchForProductKeysRequest sfpr = (SearchForProductKeysRequest) request;
		        return new SearchForProductKeysResponse(ProductRepository.PR().searchProducts(sfpr.getKeyword()), sfpr.getCollaboratorKey());
		    	
		    case AppID.GET_CART_REQUEST:
		    	log.info("GET_CART_REQUEST received @" + Calendar.getInstance().getTime());
		    	GetCartRequest gcr = (GetCartRequest) request;
		    	return new GetCartResponse(CartRepository.CR().getCart(gcr.getUserName()), gcr.getCollaboratorKey());
		    	
		    case AppID.PAY_REQUEST:
		    	log.info("PAY_REQUESTreceived @" + Calendar.getInstance().getTime());
		    	PayRequest pr = (PayRequest) request;
		    	System.out.println("Debugging: cart size before checkout is" + CartRepository.CR().getCart(pr.getUserName()).getItems().values().size());
		    	return new PayResponse(CartRepository.CR().checkOut(pr.getUserName()), pr.getCollaboratorKey());

		    	
		    case TRAppID.LATEST_ORDERS_REQUEST:
		    	log.info("LATEST_ORDER_REQUEST @" + Calendar.getInstance().getTime());
		    	LatestOrdersRequest lor = (LatestOrdersRequest) request;
		    	return new LatestOrdersResponse(ProductRepository.PR().getOrders(lor.getUserName(), lor.getStoreName(), lor.getTimeStamp()), lor.getCollaboratorKey());
		    	
		}
		return null;
	}

	@Override
	public InterChildrenNotification prepareNotification(IntercastNotification notification) {
		switch(notification.getAppID()) 
		{
		
		    case AppID.PUT_ON_SALE_NOTIFICATION:
		    	log.info("PUT_ON_SALE_NOTIFICATION received @" + Calendar.getInstance().getTime());
		    	PutOnSaleNotification posn = (PutOnSaleNotification) notification;
		    	ProductRepository.PR().registerToStore(posn.getProduct().getStoreName(), posn.getProduct().getKey());
		    	return new InterPutOnSaleNotification(posn, ProductRepository.PR().isRegistered(posn.getKey()));
		  
		    case AppID.REMOVE_FROM_CART_NOTIFICATION:
		    	log.info("REMOVE_FORM_CART_NOTIFICTAION received @" + Calendar.getInstance().getTime());
		    	RemoveFromCartNotification rfcn  = (RemoveFromCartNotification) notification;
		    	CartRepository.CR().getCart(rfcn.getUserName()).removeItem(rfcn.getKey());
		    	return new InterRemoveFromCartNotification(rfcn, CartRepository.CR().getCart(rfcn.getUserName()).getItemQuantity(rfcn.getProductKey()));
		    	
		    case AppID.REMOVE_FROM_SALE_NOTIFICATION:
		    	log.info("REMOVE_FROM_SALE_NOTIFICATION received @" + Calendar.getInstance().getTime());
		    	RemoveFromSaleNotification rfsn = (RemoveFromSaleNotification) notification;
		    	ProductRepository.PR().removeRegistryFromStore(rfsn.getStoreName(), rfsn.getProductKey());
		    	return new InterRemoveFromSaleNotification(rfsn, !ProductRepository.PR().isRegistered(rfsn.getProductKey()));
		    	
		    case AppID.UPDATE_STOCK_QUANTITY_NOTIFICATION:
		    	log.info("UPDATE_STOCK_QUANTITY_NOTIFICATION @" + Calendar.getInstance().getTime());
		    	UpdateStockQuantityNotification usqn = (UpdateStockQuantityNotification) notification;
		    	return new InterUpdateStockQuantityNotification(usqn, ProductRepository.PR().isRegistered(usqn.getKey()));
		}
		return null;
	}

	@Override
	public InterChildrenRequest prepareRequest(IntercastRequest request) {
		switch(request.getAppID()) 
		{
		      case TRAppID.GET_PRODUCTS_REQUEST:
		    	  log.info("GET_PRODUCTS_KEYS_REQUEST @" + Calendar.getInstance().getTime());
		    	  GetProductsRequest gpr = (GetProductsRequest) request;
		    	  return new InterGetProductsRequest(gpr);
		      
		      case TRAppID.ADD_TO_CART_REQUEST:
		    	  log.info("ADD_TO_CART_REQUEST @" + Calendar.getInstance().getTime());
		    	  AddToCartRequest atcr  = (AddToCartRequest) request;
		          return new InterAddToCartRequest(atcr, ProductRepository.PR().packItem(atcr.getProduct().getKey(), atcr.getQuantity()));
		
		}
		return null;
	}

	@Override
	public void processNotification(InterChildrenNotification notification) {
		switch(notification.getAppID()) 
		{
		      case AppID.PUT_ON_SALE_NOTIFICATION:
		    	  log.info("PUT_ON_SLAE_NOTIFICATION @" + Calendar.getInstance().getTime());
		    	  InterPutOnSaleNotification iposn = (InterPutOnSaleNotification) notification;
		    	  PutOnSaleNotification posn = (PutOnSaleNotification) iposn.getNotification();
		          ProductRepository.PR().addProduct(posn.getProduct());
		          break;
		          
		      case AppID.REMOVE_FROM_SALE_NOTIFICATION:
		    	  log.info("REMOVE_FROM_SALE_NOTIFICATION @" + Calendar.getInstance().getTime());
		    	  InterRemoveFromSaleNotification irfs = (InterRemoveFromSaleNotification) notification;
		          RemoveFromSaleNotification rfsn = (RemoveFromSaleNotification) irfs.getNotification();
		          ProductRepository.PR().removeProduct(rfsn.getProductKey());
		          break;
		          
		      case AppID.UPDATE_STOCK_QUANTITY_NOTIFICATION:
		    	  log.info("UPDATESTOCK_QUANTITY_NOTIFICATION @" + Calendar.getInstance().getTime());
		    	  InterUpdateStockQuantityNotification iusqn = (InterUpdateStockQuantityNotification) notification;
		    	  UpdateStockQuantityNotification usqn = (UpdateStockQuantityNotification) iusqn.getNotification();
		    	  ProductRepository.PR().getProduct(usqn.getProductKey()).setStockQuantity(usqn.getNewStockQuantity());
		    	  break;
		}	
	}
	

	@Override
	public void processNotification(InterChildrenNotification paramInterChildrenNotification, List<String> paramList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MulticastResponse> processRequest(InterChildrenRequest request)
	{
		List<MulticastResponse> responses;
		switch(request.getAppID()) 
		{
		      case TRAppID.ADD_TO_CART_REQUEST:
		    	  log.info("ADD_TO_CART_REQUEST @" + Calendar.getInstance().getTime());
		    	  InterAddToCartRequest iacr = (InterAddToCartRequest) request;
		    	  responses = new ArrayList<MulticastResponse>();
		    	  AddToCartRequest atr = (AddToCartRequest) iacr.getRequest();
		    	  responses.add(new AddToCartResponse(CartRepository.CR().getOrCreateCart(atr.getUserName()).addItem(iacr.getItem()), request.getCollaboratorKey()));
		    	  return responses;
	    	  		
		}
		return null;		
	}

	@Override
	public List<MulticastResponse> processRequest(InterChildrenRequest request,
			List<String> paramList) 
	{
		List<MulticastResponse> responses;
		switch(request.getAppID()) 
		{   	  
	          case TRAppID.GET_PRODUCTS_REQUEST:
	        	   log.info("GET_PRODUCTS_REQUEST @" + Calendar.getInstance().getTime());
		    	    InterGetProductsRequest igpr = (InterGetProductsRequest) request;
		    	    responses = new ArrayList<MulticastResponse>();
		    	    GetProductsRequest gpr = (GetProductsRequest) igpr.getRequest();
		    	    responses.add(new GetProductsResponse(ProductRepository.PR().getMatchingProducts(gpr.getProductKeys()), request.getCollaboratorKey())); 
		    	    return responses;
		  }
		    	
		return null;		
	}

	@Override
	public void processResponse(ClusterResponse paramClusterResponse) {
		// TODO Auto-generated method stub
		
	}

}
