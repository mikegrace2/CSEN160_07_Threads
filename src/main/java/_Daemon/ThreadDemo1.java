package _Daemon;

import java.io.IOException;

class ThreadDemo1 extends Thread {
	String first;
	String second;
	int sleepTime;

	public ThreadDemo1(String first, String second, int sleepTime) {
		this.first = first;
		this.second = second;
		this.sleepTime = sleepTime;

		// A Daemon thread is a background thread that is subordinate
		// to the thread that creates it.
		// When the thread that creates it dies,
		// the daemon thread dies.

		setDaemon(true);
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
		ThreadDemo1 t1 = new ThreadDemo1("AAAA", "BBBB", 300);
		ThreadDemo1 t2 = new ThreadDemo1("CCCC", "DDDD", 200);
		ThreadDemo1 t3 = new ThreadDemo1("EEEE", "FFFF", 400);

		System.out.println("Press Enter when you are done");
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			System.in.read();
			System.out.println("Done");
		} catch (IOException e) {
		}
		
		System.out.println("Exiting main");
		return;
	}
}
