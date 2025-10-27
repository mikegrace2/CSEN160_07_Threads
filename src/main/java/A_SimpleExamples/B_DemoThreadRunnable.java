package A_SimpleExamples;

public class B_DemoThreadRunnable implements Runnable {
	@Override
	public void run() {
		for (int i=0;i<10;i++) {
			System.out.println("Thread ID("+Thread.currentThread().getId()+") "+i);
		}
		
		try {
			Thread.sleep(10);// Sleep for 10 ms
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Thread t1=new Thread(new B_DemoThreadRunnable());
		Thread t2=new Thread(new B_DemoThreadRunnable());
		t1.start();
		t2.start();
	}
}
