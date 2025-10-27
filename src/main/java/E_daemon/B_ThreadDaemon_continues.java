package E_daemon;

class B_ThreadDaemon_continues extends Thread {
	String first;
	String second;
	int sleepTime;

	public B_ThreadDaemon_continues(String first, String second, int sleepTime) {
		this.first = first;
		this.second = second;
		this.sleepTime = sleepTime;

		// A Daemon thread is a background thread that is subordinate
		// to the thread that creates it.
		// When the thread that creates it dies,
		// the daemon thread dies.

		setDaemon(false); // FALSE FALSE FALSE FALSE FALSE FALSE FALSE FALSE
	}

	public void run() {
		try {
			while (true) {
				System.out.println(first);
				sleep(sleepTime);
				System.out.println(second);
			}
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		B_ThreadDaemon_continues t1 = new B_ThreadDaemon_continues("AAAA", "BBBB", 1000);
		B_ThreadDaemon_continues t2 = new B_ThreadDaemon_continues("CCCC", "DDDD", 1100);
		B_ThreadDaemon_continues t3 = new B_ThreadDaemon_continues("EEEE", "FFFF", 1200);

		t1.start();
		t2.start();
		t3.start();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("\nDone\n");
		System.out.println("\nExiting main\n\n\n");
	}
}
