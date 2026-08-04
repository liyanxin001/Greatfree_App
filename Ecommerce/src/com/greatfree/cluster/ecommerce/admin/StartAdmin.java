package com.greatfree.cluster.ecommerce.admin;

/*    */ import edu.greatfree.framework.cluster.multicast.client.ClusterClient;
/*    */ import edu.greatfree.framework.cluster.multicast.message.ShutdownChildrenNotification;
/*    */ import edu.greatfree.framework.cluster.multicast.message.ShutdownRootNotification;
/*    */ import java.io.IOException;
/*    */ import org.greatfree.exceptions.RemoteReadException;
/*    */ import org.greatfree.framework.container.cs.multinode.message.ShutdownServerNotification;
/*    */ import org.greatfree.util.IPAddress;
/*    */ import org.greatfree.util.Tools;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class StartAdmin
/*    */ {
/*    */   private static IPAddress rootAddress;
/*    */   
/*    */   public static void main(String[] args) throws ClassNotFoundException, RemoteReadException, IOException, InterruptedException {
/* 32 */     ClusterClient.MULTI().init();
/* 33 */     int option = -1;
/*    */     
/* 35 */     rootAddress = ClusterClient.MULTI().getAddress("192.168.1.25", 8941, "Root");
/* 36 */     while (option != 0) {
/*    */       
/* 38 */       printMenu();
/* 39 */       String optionStr = Tools.INPUT.nextLine();
/*    */       
/*    */       try {
/* 42 */         option = Integer.parseInt(optionStr);
/* 43 */         System.out.println("Your choice is: " + option);
/* 44 */         execute(option);
/*    */       }
/* 46 */       catch (NumberFormatException e) {
/*    */         
/* 48 */         option = -1;
/* 49 */         System.out.println("Wrong option!");
/*    */       } 
/*    */     } 
/* 52 */     ClusterClient.MULTI().dispose();
/*    */   }
/*    */ 
/*    */   
/*    */   private static void printMenu() {
/* 57 */     System.out.println("\n========== Menu Head ===========");
/* 58 */     System.out.println("\t1) Shutdown children");
/* 59 */     System.out.println("\t2) Shutdown root");
/* 60 */     System.out.println("\t3) Stop registry server");
/* 61 */     System.out.println("\t0) Quit");
/* 62 */     System.out.println("========== Menu Tail ===========\n");
/* 63 */     System.out.println("Input an option:");
/*    */   }
/*    */ 
/*    */   
/*    */   private static void execute(int option) throws IOException, InterruptedException {
/* 68 */     switch (option) {
/*    */       
/*    */       case 1:
/* 71 */         ClusterClient.MULTI().syncNotify(rootAddress.getIP(), rootAddress.getPort(), new ShutdownChildrenNotification());
/*    */         break;
/*    */       
/*    */       case 2:
/* 75 */         ClusterClient.MULTI().syncNotify(rootAddress.getIP(), rootAddress.getPort(), new ShutdownRootNotification());
/*    */         break;
/*    */       
/*    */       case 3:
/* 79 */         ClusterClient.MULTI().syncNotify("192.168.1.25", 8941, new ShutdownServerNotification());
/*    */         break;
/*    */     } 
/*    */   }
/*    */ }
