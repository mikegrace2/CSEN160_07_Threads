package N_SingleThreadExecutor;

import java.util.concurrent.TimeUnit;

public class TaskA implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+
				" ---------------------- START  ----------------------------- ");
		for (int i = 0; i < 1000_000; i++) {
			double a = 100;
			double b = 200;
			double c = a / b;
		}
		
		try {
			TimeUnit.MILLISECONDS.sleep((long)Math.random() * 1000);
			System.out.println(Thread.currentThread().getName()+"... doing something ...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName()+
				"======================= END ================================");
	}
}