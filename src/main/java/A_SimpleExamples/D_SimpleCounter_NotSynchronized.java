package A_SimpleExamples;

public class D_SimpleCounter_NotSynchronized{
	private static int count=0;
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i=0;i<=10000;i++) {
					count++;
				}		
			}			
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i=0;i<=10000;i++) {
					count++;
				}		
			}			
		});		
		
		System.out.println("BEFORE: SimpleCounter.count="+count);
		t1.start(); // returns immediately
		t2.start(); // returns immediately
		
		try {
			t1.join(); // wait for t1 to finish
			t2.join(); // wait for t2 to finish
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("AFTER:  SimpleCounter.count="+count);
	}
}
