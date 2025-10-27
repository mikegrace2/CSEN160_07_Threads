package _Daemon;
class DaemonThread_UserThreadTest {
	public static void Main() throws InterruptedException {
		Runnable r = new Runnable() {
			public void run() {
				for (int time = 10; time > 0; --time) {
					System.out.println("Time #" + time);
					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		/*
		 * Daemon threads are typically used to perform services for user threads.
		 * 
		 * Threads created by a user thread are user threads. JVM doesn't terminate
		 * unless all the user threads terminate.
		 */
		Thread t = new Thread(r);
		t.setDaemon(false); // set this to "false" and see what happens
		t.start();
		System.out.println("Main thread-waiting...");
		Thread.sleep(4);
		System.out.println("Main thread exited.");
	}
}

public class ThreadDemo2 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DaemonThread_UserThreadTest.Main();
		} catch (InterruptedException ie) {
		}
	}
}