package H_counter;

public class B_SimpleCounter_NotSynchronized  implements Runnable{
	private volatile static int count=0;
	
	@Override
	public void run() {
		for (int i=0;i<=10000;i++) {
			count++;
		}		
	}	
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new B_SimpleCounter_NotSynchronized());
		Thread t2 = new Thread(new B_SimpleCounter_NotSynchronized());		
		
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
