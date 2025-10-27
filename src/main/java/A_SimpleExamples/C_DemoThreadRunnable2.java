package A_SimpleExamples;

public class C_DemoThreadRunnable2{	
	public static void main(String[] args) {
		Thread t1=new Thread(new Runnable() {
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
		});
		t1.start();
		
		Thread t2=new Thread(new Runnable() {
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
		});
		t2.start();
	}
}
