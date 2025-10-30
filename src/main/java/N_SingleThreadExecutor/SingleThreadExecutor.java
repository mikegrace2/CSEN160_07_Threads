package N_SingleThreadExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor {

	public static void main(String[] args) {
		int numberOfTasks=4;
		
		System.out.println("SingleThreadExecutor.main(): Start ...");
		
		ExecutorService exec= Executors.newSingleThreadExecutor();
		
		for (int i=0;i<numberOfTasks;i++) {
			exec.execute(new TaskA());
		}
		
		exec.shutdown();
		
		System.out.println("SingleThreadExecutor.main(): ... end!");
	}
}