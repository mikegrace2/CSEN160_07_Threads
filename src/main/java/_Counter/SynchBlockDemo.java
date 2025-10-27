package _Counter;

class PrintCounter {
	public void printCount() {
		try {
			for (int i = 5; i > 0; i--) {
				System.out.println("Counter: " + i);
			}
		} catch (Exception e) {
			System.out.println("Thread  interrupted.");
		}
	}
}

///////////////////////////////////////////////////////////////////////
// Multi-threading without synchronization
class ThreadOne extends Thread {
	private Thread t;
	private String threadName;
	PrintCounter counter;

	ThreadOne(String name, PrintCounter pc) {
		threadName = name;
		counter = pc;
	}

	public void run() {
		counter.printCount();
		System.out.println("Thread " + threadName + " exiting.");
	}

	public void start() {
		System.out.println("Starting " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
}
///////////////////////////////////////////////////////////////////////////////////
// Multi-threading with synchronization

class ThreadTwo extends Thread {
	private Thread t;
	private String threadName;
	PrintCounter counter;

	ThreadTwo(String name, PrintCounter pc) {
		threadName = name;
		counter = pc;
	}

	public void run() {
		// Note the synchronized block where you keep the shared resources
		synchronized (counter) {
			counter.printCount();
		}
		System.out.println("Thread " + threadName + " exiting.");
	}

	public void start() {
		System.out.println("Starting " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
}

class TestThread1 {
	public static void driver() {
		PrintCounter counter1 = new PrintCounter();

		ThreadOne t1 = new ThreadOne("Thread - 1 ", counter1);
		ThreadOne t2 = new ThreadOne("Thread - 2 ", counter1);

		t1.start();
		t2.start();

		// wait for threads to end
		try {
			t1.join();
			t2.join();
		} catch (Exception e) {
			System.out.println("Interrupted");
		}
	}
}

class TestThread2 {
	public static void driver() {
		PrintCounter counter1 = new PrintCounter();

		ThreadTwo t1 = new ThreadTwo("Thread - 10 ", counter1);
		ThreadTwo t2 = new ThreadTwo("Thread - 12 ", counter1);

		t1.start();
		t2.start();

		// wait for threads to end
		try {
			t1.join();
			t2.join();
		} catch (Exception e) {
			System.out.println("Interrupted");
		}
	}
}

public class SynchBlockDemo {
	public static void main(String[] args) {
		TestThread1.driver();
		TestThread2.driver();

	}
}