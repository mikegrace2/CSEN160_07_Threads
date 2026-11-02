package H_counter;

public class A_SimpleCounter_Synchronized implements Runnable{
	private static int count=0;
	
	// Can only be access by one Thread at a time
	public synchronized static void countPlusPlus() {
		count++;
	}

	public static void loop() {
		for (int i=0;i<1000000;i++) {
			countPlusPlus();
		}
	}
	
	@Override
	public void run() {
		loop();
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new A_SimpleCounter_Synchronized());
		Thread t2 = new Thread(new A_SimpleCounter_Synchronized());		
		
		System.out.println("BEFORE: SimpleCounter.count="+count);
		t1.start(); // asynchronous call it returns immediately
		t2.start(); // asynchronous call it returns immediately
		
		try {
			t1.join(); // wait for t1 to finish
			t2.join(); // wait for t2 to finish
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("AFTER:  SimpleCounter.count="+count);
	}
}
