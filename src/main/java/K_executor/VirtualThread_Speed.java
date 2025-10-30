package K_executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VirtualThread_Speed implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 1000_000; i++) {
			double a = 100;
			double b = 200;
			double c = a / b;
		}
	}

	public static void main(String[] args) {
		int numberOfThreads = 1000000;

		// Virtual Threads Virtual Threads Virtual Threads Virtual Threads Virtual
		{ // Virtual Threads Virtual Threads Virtual Threads Virtual Threads Virtual
			List<Thread> myThreads = new ArrayList<Thread>();

			long start = System.currentTimeMillis();
			for (int i = 0; i < numberOfThreads; i++) {
				myThreads.add(Thread.ofVirtual().start(new VirtualThread_Speed()));
			}

			try {
				for (Thread aThread : myThreads) {
					aThread.join();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long end = System.currentTimeMillis();

			System.out.println("Virtual Threads      Time ms=" + (end - start));
		}

		// Platform Threas Platform Threas Platform Threas Platform Threas Platform
		{ // Platform Threas Platform Threas Platform Threas Platform Threas Platform
			List<Thread> myThreads = new ArrayList<Thread>();

			long start = System.currentTimeMillis();
			for (int i = 0; i < numberOfThreads; i++) {
				myThreads.add(Thread.ofPlatform().start(new VirtualThread_Speed()));
			}

			try {
				for (Thread aThread : myThreads) {
					aThread.join();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long end = System.currentTimeMillis();

			System.out.println("Platform Threads     Time ms=" + (end - start));
		}

		// Executor Threads Executor Threads Executor Threads Executor Threads Executor
		{ // Executor Threads Executor Threads Executor Threads Executor Threads Executor
			Runnable temp = () -> {
				for (int i = 0; i < 1000_000; i++) {
					double a = 100;
					double b = 200;
					double c = a / b;
				}
			};			
			
			// Threads Executor Threads Executor Threads
			List<Thread> myThreads = new ArrayList<Thread>();

			ExecutorService executor = Executors.newCachedThreadPool();

			long start = System.currentTimeMillis();
			for (int i = 0; i < numberOfThreads; i++) {
				executor.execute(temp);
			}

			executor.shutdown();
			try { // waits until all threads are done!
				while (!executor.awaitTermination(24L, TimeUnit.HOURS)) {} 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long end = System.currentTimeMillis();

			System.out.println("Executor Thread Pool Time ms=" + (end - start));
		}
	}
}
