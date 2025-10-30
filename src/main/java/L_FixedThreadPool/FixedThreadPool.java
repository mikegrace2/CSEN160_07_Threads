package L_FixedThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {

	public static void main(String[] args) {
		int numberOfThreads=2;
		int numberOfTasks=4;
		
		System.out.println("FixedThreadPool.main(): Start ...");
		
		ExecutorService exec= Executors.newFixedThreadPool(numberOfThreads);
		
		for (int i=0;i<numberOfTasks;i++) {
			exec.execute(new TaskA());
		}
		
		exec.shutdown();
		
		System.out.println("FixedThreadPool.main(): ... end!");
	}
}