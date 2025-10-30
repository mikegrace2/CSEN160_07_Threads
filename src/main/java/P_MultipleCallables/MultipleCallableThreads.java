package P_MultipleCallables;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultipleCallableThreads {
	public static void main(String[] args) {
		int numberOfThreads=8;
		int numberOfTasks=8;
		
		System.out.println("FixedThreadPool.main(): Start ...");
		
		ExecutorService exec= Executors.newFixedThreadPool(numberOfThreads);
		
		List<Callable<StringBuffer>> myCallables=new ArrayList<>();
		
		for (int i=0;i<numberOfTasks;i++) {
			StringBuffer myBuf=new StringBuffer();
			myBuf.append("Start:("+i+")");
			myCallables.add(new CallableTask(myBuf));
		}
		
		List<Future<StringBuffer>> myFutures;
		try {
			myFutures = exec.invokeAll(myCallables);
			
			for (Future<StringBuffer> fut : myFutures) {
				try {
					StringBuffer buffi=fut.get(); 
					System.out.println("Is done: " + buffi);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		exec.shutdown();
		
		System.out.println("FixedThreadPool.main(): ... end!");
	}
}