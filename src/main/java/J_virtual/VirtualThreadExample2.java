package J_virtual;

import java.util.ArrayList;
import java.util.List;

public class VirtualThreadExample2 {
	public static void main(String[] args) {
		long start1, start2, start3, end1, end2, end3;
		int vThreadCount = 10000;
		
		{ // Virtual Threads
			// Create a lot of Threads
			List<Thread> vThreads = new ArrayList<>();

			start1 = System.currentTimeMillis();
			for (int i = 0; i < vThreadCount; i++) {
				int vThreadIndex = i;
				Thread vThread = Thread.ofVirtual().start(() -> {
					int result = 1;
					for (int j = 0; j < 10; j++) {
						result *= (j + 1);
					}
					System.out.println("Result[" + vThreadIndex + "]: " + result);
				});

				vThreads.add(vThread);
			}

			// Join them all
			for (Thread currentThread : vThreads) {
				try {
					currentThread.join();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			end1 = System.currentTimeMillis();
		}
		
		{ // Platform Threads ----------------------------------------------------------------------------
			// Create a lot of Threas
			List<Thread> vThreads = new ArrayList<>();

			start2 = System.currentTimeMillis();
			for (int i = 0; i < vThreadCount; i++) {
				int vThreadIndex = i;
				Thread vThread = Thread.ofPlatform().start(() -> {
					int result = 1;
					for (int j = 0; j < 10; j++) {
						result *= (j + 1);
					}
					System.out.println("Result[" + vThreadIndex + "]: " + result);
				});

				vThreads.add(vThread);
			}

			// Join them all
			for (Thread currentThread : vThreads) {
				try {
					currentThread.join();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			end2 = System.currentTimeMillis();
		}
		
		{ // Platform Threads The standard way ------------------------------------------------------
			// Create a lot of Threads
			List<Thread> vThreads = new ArrayList<>();

			start3 = System.currentTimeMillis();
			for (int i = 0; i < vThreadCount; i++) {
				int vThreadIndex = i;
				
				Thread vThread = new Thread(new Runnable() {
					@Override
					public void run() {
						int result = 1;
						for (int j = 0; j < 10; j++) {
							result *= (j + 1);
						}
						System.out.println("Result[" + vThreadIndex + "]: " + result);
					}
				});
				
				vThread.start();

				vThreads.add(vThread);
			}

			// Join them all
			for (Thread currentThread : vThreads) {
				try {
					currentThread.join();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			end3 = System.currentTimeMillis();
		}		
		
		// Done
		System.out.println("Virtual Threads:          Done time in ms=" + (end1 - start1));		
		System.out.println("Platform Threads:         Done time in ms=" + (end2 - start2));
		System.out.println("Platform Threads classic: Done time in ms=" + (end3 - start3));
	}
}