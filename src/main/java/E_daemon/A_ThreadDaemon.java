package E_daemon;

class A_ThreadDaemon extends Thread {
	String first;
	String second;
	int sleepTime;

	public A_ThreadDaemon(String first, String second, int sleepTime) {
		this.first = first;
		this.second = second;
		this.sleepTime = sleepTime;
		// A Daemon thread is a background thread that is subordinate to the thread that creates it.
		// When the thread that creates it dies, the daemon thread dies.
		setDaemon(true); // TRUE TRUE TRUE TRUE TRUE TRUE TRUE TRUE
	}

	@Override
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
		A_ThreadDaemon t1 = new A_ThreadDaemon("AAAA", "BBBB", 1000);
		A_ThreadDaemon t2 = new A_ThreadDaemon("CCCC", "DDDD", 1100);
		A_ThreadDaemon t3 = new A_ThreadDaemon("EEEE", "FFFF", 1200);

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
