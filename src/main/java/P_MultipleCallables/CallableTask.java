package P_MultipleCallables;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CallableTask implements Callable<StringBuffer> {
	public StringBuffer returnString;
	
	public CallableTask(StringBuffer returnString) {
		this.returnString=returnString;
	}
	
	@Override
	public StringBuffer call() throws Exception {
		System.out.println(Thread.currentThread().getName()+" --- START  ---");

		double c=0;
		for (int i = 0; i < 1000_000; i++) {
			double a = 100;
			double b = 200;
			c = a / b;
			c = c + ((double)Math.random() * 1000);
			
		}
		
		this.returnString.append(" Result: "+c);
		
		try {
			TimeUnit.MILLISECONDS.sleep((long)Math.random() * 1000);
			System.out.println(Thread.currentThread().getName()+"... doing something ...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName()+" === END ===");
		return this.returnString;
	}
}