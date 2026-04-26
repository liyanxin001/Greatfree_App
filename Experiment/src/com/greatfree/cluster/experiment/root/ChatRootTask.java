package com.greatfree.cluster.experiment.root;

import java.util.Calendar;
import java.util.logging.Logger;

import edu.greatfree.cluster.message.ChildRequest;
import edu.greatfree.cluster.message.ClusterNotification;
import edu.greatfree.cluster.message.ClusterRequest;
import edu.greatfree.cluster.message.ClusterResponse;
import edu.greatfree.cluster.message.RootResponse;
import edu.greatfree.cluster.root.RootTask;
import edu.greatfree.cluster.root.UnaryRoot;

/*    */ final class ChatRootTask
/*    */   extends RootTask
/*    */ {
/* 27 */   private static final Logger log = Logger.getLogger("com.greatfree.cluster.experiment.root");
/*    */ 
/*    */ 
/*    */   
/*    */   public void processNotification(ClusterNotification notification) {
/* 32 */     switch (notification.getAppID()) {
/*    */       
/*    */       case 80019:
/* 35 */         log.info("SHUTDOWN_ROOT_NOTIFICATION received @" + String.valueOf(Calendar.getInstance().getTime()));
/*    */         
/*    */         try {
/* 38 */           UnaryRoot.CLUSTER().stop();
/*    */         }
/* 40 */         catch (ClassNotFoundException|java.io.IOException|InterruptedException|org.greatfree.exceptions.RemoteReadException e) {
/*    */           
/* 42 */           e.printStackTrace();
/*    */         } 
/*    */         break;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 52 */   public ClusterResponse processRequest(ClusterRequest request) { return null; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public RootResponse processChildRequest(ChildRequest request) { return null; }
/*    */ }
