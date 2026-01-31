package com.greatfree.cluster.ecommerce.child;

import java.io.IOException;
import java.util.logging.Logger;

import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.util.TerminateSignal;

import edu.greatfree.cluster.ClusterSpec;
import edu.greatfree.cluster.child.Child;
import edu.greatfree.cluster.child.ChildTask;
import edu.greatfree.exceptions.RootOfflineException;
import edu.greatfree.exceptions.TaskAlreadyExistedException;


 final class StartChild
 {
   private static final Logger log = Logger.getLogger("edu.greatfree.framework.cluster.mncs.child");
 
 
   
   private Child child;
 
 
   
   private static StartChild instance = new StartChild();
 
   
   public static StartChild CLUSTER() {
     if (instance == null) {
       
       instance = new StartChild();
       return instance;
     } 
 
     
     return instance;
   }
 
 
   
   public void stop() throws ClassNotFoundException, IOException, InterruptedException, RemoteReadException {
     TerminateSignal.SIGNAL().notifyAllTermination();
     this.child.stop();
   }
 
   
   public void start(ClusterSpec spec, ChildTask... tasks) throws ClassNotFoundException, IOException, RemoteReadException, TaskAlreadyExistedException, InterruptedException {
     this.child = new Child(spec, tasks);
     
     try {
       this.child.start();
     }
     catch (RootOfflineException e) {
       
       log.info("The root is offline!");
     } 
   }
 }
