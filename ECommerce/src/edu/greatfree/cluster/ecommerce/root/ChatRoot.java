package edu.greatfree.cluster.ecommerce.root;


 
 import edu.greatfree.cluster.ClusterSpec;
 import edu.greatfree.cluster.root.Root;
 import edu.greatfree.cluster.root.RootTask;
 import edu.greatfree.exceptions.NoChildrenAvailableException;
 import edu.greatfree.exceptions.TaskAlreadyExistedException;
 import java.io.IOException;
 import org.greatfree.exceptions.RemoteReadException;
 import org.greatfree.util.TerminateSignal;

 final class ChatRoot
 {
   private Root root;
   private static ChatRoot instance = new ChatRoot();
 
   
   public static ChatRoot CLUSTER() {
     if (instance == null) {
       
       instance = new ChatRoot();
       return instance;
     } 
 
     
     return instance;
   }
 
 
   
   public void stop() throws ClassNotFoundException, IOException, InterruptedException, RemoteReadException {
     TerminateSignal.SIGNAL().notifyAllTermination();
     this.root.stop();
   }
 
   
   public void start(ClusterSpec spec, RootTask... tasks) throws IOException, TaskAlreadyExistedException, ClassNotFoundException, RemoteReadException, NoChildrenAvailableException {
     this.root = new Root(spec, tasks);
     this.root.start();
   }
 }