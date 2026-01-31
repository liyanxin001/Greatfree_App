package edu.greatfree.cluster.ecommerce.child.app;

import java.util.HashMap;
import java.util.Map;

import java.util.concurrent.ConcurrentHashMap;

import edu.greatfree.cluster.ecommerce.data.Cart;

public class CartRegistry {
  
    private static CartRegistry instance;
    
    private final Map<String, Cart> carts = new ConcurrentHashMap<>();
    private final Map<String, String> userToCartMap = new ConcurrentHashMap<>();
    
//    private CartRegistry() {
//        // Private constructor for singleton
//        startCleanupTask();
//    }
    
    public static CartRegistry CR() {
        if (instance == null) {
        	
            instance = new CartRegistry();
            return instance;
        }
        return instance;
    }
    
    
    // Get cart by ID (only if exists on THIS node)
    public Cart getCart(String ClientID) {
        return carts.get(ClientID);
    }
    
    // Check if cart exists on this node
    public boolean hasCart(String ClientID) {
        return carts.containsKey(ClientID);
    }
    
    // Get all carts on this node (for monitoring)
    public Map<String, Cart> getAllLocalCarts() {
        return new HashMap<>(carts);
    }


	public Map<String, String> getClientToCartMap() {
		return userToCartMap;
	}
}    
    // Remove cart (when checkout completes or session ends)

    
    // Cleanup old carts
//    private void startCleanupTask() {
//        Timer timer = new Timer(true); // Daemon thread
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                cleanupInactiveCarts();
//            }
//        }, 0, 5 * 60 * 1000); // Every 5 minutes
//    }
//    
//    private void cleanupInactiveCarts() {
//        long cutoff = System.currentTimeMillis() - (30 * 60 * 1000); // 30 minutes
//        carts.entrySet().removeIf(entry -> {
//            Cart cart = entry.getValue();
//            if (cart.getLastAccessed() < cutoff) {
//                clientToCartMap.remove(cart.getClientId());
//                System.out.println("Cleaned up inactive local cart: " + cart.getCartId());
//                return true;
//            }
//            return false;
//        });
//    }
//}
