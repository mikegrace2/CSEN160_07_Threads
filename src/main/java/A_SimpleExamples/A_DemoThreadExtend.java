package A_SimpleExamples;

public class A_DemoThreadExtend extends Thread{
	@Override
	public void run() {
		for (int i=0;i<10;i++) {
			System.out.println("Thread ID("+Thread.currentThread().getId()+") "+i);
		}
		
//		try {
//			Thread.sleep(10);// Sleep for 10 ms
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

	public static void main(String[] args) {
		new A_DemoThreadExtend().start(); // run Thread 1
		new A_DemoThreadExtend().start(); // run Thread 2
	}
}