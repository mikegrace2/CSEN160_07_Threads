package I_volatileExample;

class B_ThreadVolatile extends Thread {
	volatile boolean keepRunning = true;

	public void run() {
        int counter=0;
		while (keepRunning) {
            System.out.print("B");
            counter++;
        } // endless loop, doing nothing

		System.out.println("Thread terminated. ="+counter); // never get here
	}

	public static void main(String[] args) throws InterruptedException {
		B_ThreadVolatile t = new B_ThreadVolatile();
		t.start();
		
		Thread.sleep(1000);
		
		t.keepRunning = false;
		System.out.println("keepRunning set to false.");
	}
}
