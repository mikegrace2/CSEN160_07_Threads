package J_virtual;

import java.util.ArrayList;
import java.util.List;

public class VirtualThread_Speed implements Runnable{
	public static int counter=0;

	public static synchronized void counterPlusPlus() {
		VirtualThread_Speed.counter++;
	}
	
	@Override
	public void run() {
			//System.out.println("Started!");
			for (int i=0;i<10000;i++) {
				VirtualThread_Speed.counterPlusPlus();
				double a=100;
				double b=200;
				double c=a/b;
			} 
	}
	
	public static void main(String[] args) {
		{
			List<Thread> myThreads = new ArrayList<Thread>();

			long start = System.currentTimeMillis();
			for (int i = 0; i < 1000000; i++) {
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

			System.out.println("\n\nVirtual Counter=" + VirtualThread_Speed.counter + " Time ms=" + (end - start));
		}
		
		{
			List<Thread> myThreads = new ArrayList<Thread>();

			long start = System.currentTimeMillis();
			for (int i = 0; i < 1000000; i++) {
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

			System.out.println("\n\nPlatform Counter=" + VirtualThread_Speed.counter + " Time ms=" + (end - start));
		}		
	}
}
