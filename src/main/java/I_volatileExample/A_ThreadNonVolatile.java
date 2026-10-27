package I_volatileExample;

class A_ThreadNonVolatile extends Thread {
	boolean keepRunning = true;

	public void run() {
        int counter=0;
		while (keepRunning) {
			System.out.print("A");
            counter++;
		} // endless loop, doing nothing

		System.out.println("Thread terminated. counter="+counter); // never get here
	}

	public static void main(String[] args) throws InterruptedException {
		A_ThreadNonVolatile t = new A_ThreadNonVolatile();
		t.start();
		
		Thread.sleep(1000);
		
		t.keepRunning = false;
		System.out.println("keepRunning set to false.");
	}
}
