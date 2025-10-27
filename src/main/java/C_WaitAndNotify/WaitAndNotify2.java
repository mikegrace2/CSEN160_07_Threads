package C_WaitAndNotify;

import java.util.Random;

class Demo2 {
	private volatile int numbers = 0;
    private Random random = new Random();
    private Object obj=new Object();

	public synchronized void producer() {
			try {
					numbers = numbers + 1;
					System.out.println(Thread.currentThread().getName() + ": ("+numbers+") producer produced 1 ...");
					System.out.println(Thread.currentThread().getName() + ": ("+numbers+") producer notifes consumer!");
					notify(); // notify the waiting thread, if any
			} catch (Exception e) {
				System.out.println(e.getClass());
			}
			this.randomSleep();
	}

	public synchronized void consumer() {
		while (true) {
			try {
				System.out.println(Thread.currentThread().getName() + ": ("+numbers+") consumer is waiting ...");

					if (this.numbers ==0)
						wait();
					System.out.println(Thread.currentThread().getName() + ": ("+numbers+") consumer consumes 1");
					numbers = numbers - 1;
				
				wait();
			} catch (Exception e) {
				System.out.println(e.getClass());
			}
			//this.randomSleep();
		}
	}
	
	public void randomSleep() {
        try {
    		int minSleep = 1000; // 1 second
            int maxSleep = 5000; // 5 seconds
            int randomSleepDuration = random.nextInt(maxSleep - minSleep + 1) + minSleep;
            System.err.println("Random sleep "+randomSleepDuration);        	
			Thread.sleep(randomSleepDuration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class WaitAndNotify2 {
	public static void main(String[] args) {
		Demo2 demo2 = new Demo2();

		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				demo2.producer();
			}
		});
		thread1.setName("Producer");

		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				demo2.consumer();
			}
		});
		thread2.setName("Consumer");

		thread2.start();
		while(true) {
			thread1.start();
		}
	}
}
