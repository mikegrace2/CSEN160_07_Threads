package K_executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {
	public static Object objLock=new Object();
	
	public static void main(String[] args) {
		Runnable r1 = () -> {
			synchronized (CachedThreadPool.objLock) {
				System.out.println("1.1 =" + Thread.currentThread().getName());
				System.out.println("1.2 =" + Thread.currentThread().getName());
			}
		};
		
		Runnable r2 = () -> {
			synchronized (CachedThreadPool.objLock) {
				System.out.println("\t\t\t\t2.1 =" + Thread.currentThread().getName());
				System.out.println("\t\t\t\t2.2 =" + Thread.currentThread().getName());
			}
		};
		
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(r1);
		executor.execute(r2);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		executor.execute(r1);
		executor.execute(r2);

		executor.shutdown();
	}
}