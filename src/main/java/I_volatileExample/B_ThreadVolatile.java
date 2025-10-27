package I_volatileExample;

class B_ThreadVolatile extends Thread {
	volatile boolean keepRunning = true;

	public void run() {
		while (keepRunning) {} // endless loop, doing nothing

		System.out.println("Thread terminated."); // never get here
	}

	public static void main(String[] args) throws InterruptedException {
		B_ThreadVolatile t = new B_ThreadVolatile();
		t.start();
		
		Thread.sleep(1000);
		
		t.keepRunning = false;
		System.out.println("keepRunning set to false.");
	}
}
