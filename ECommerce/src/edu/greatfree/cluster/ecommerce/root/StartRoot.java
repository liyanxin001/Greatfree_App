package edu.greatfree.cluster.ecommerce.root;

 import edu.greatfree.cluster.ClusterProfile;
 import edu.greatfree.cluster.root.RootTask;
 import edu.greatfree.cluster.root.UnaryRoot;
 import edu.greatfree.exceptions.NoChildrenAvailableException;
 import edu.greatfree.exceptions.TaskAlreadyExistedException;
 import java.io.IOException;
 import org.greatfree.exceptions.RemoteReadException;
 import org.greatfree.util.TerminateSignal;
 
 
 
 
 
 
 final class StartRoot
 {
   public static void main(String[] args) throws ClassNotFoundException, IOException, TaskAlreadyExistedException, RemoteReadException, NoChildrenAvailableException {
     System.out.println("Root starting up ...");
     UnaryRoot.CLUSTER().start(ClusterProfile.getLightRootSpec("Root", 8000, "192.168.101.17", 8941), new RootTask[] { new ChatRootTask() });
     System.out.println("Root started ...");
     TerminateSignal.SIGNAL().waitTermination();
   }
 }