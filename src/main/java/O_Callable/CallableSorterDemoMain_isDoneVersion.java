package O_Callable;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableSorterDemoMain_isDoneVersion {
	public static void main(String[] args) {
		int arraySize=1_000_000;
		
		byte[] b = new byte[arraySize];
		new Random().nextBytes(b); // between -127 and 127

		Callable<byte[]> c=new SorterCallable(b);
		ExecutorService exec=Executors.newCachedThreadPool();
		Future<byte[]> result = exec.submit(c);
			
		// check every half second, if the work is done
		while (!result.isDone()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		try {
			// isDone is true, so this is not blocking anymore
			byte[] myB = result.get();  

			for (int i=0;i<myB.length;i++)
				System.out.println(i+". ="+myB[i]);
				
			System.out.println("\nisDone()="+result.isDone());
			System.out.println("isCanceled()="+result.isCancelled());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
