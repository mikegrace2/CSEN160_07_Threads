package A_SimpleExamples;

public class E_SimpleCounter_Synchronized{
	private static int count=0;
	
	// Can only be access by one Thread at a time
	public synchronized static void countPlusPlus() {
		count++;
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i=0;i<=10000;i++) {
					countPlusPlus();
				}		
			}			
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i=0;i<=10000;i++) {
					countPlusPlus();
				}		
			}			
		});		
		
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
