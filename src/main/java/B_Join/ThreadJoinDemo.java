package B_Join;
import java.util.Date;

class RunnableJob implements Runnable {
	public void run() {
		Thread thread = Thread.currentThread();
		System.out.println("RunnableJob is being run by " + thread.getName() + " at " + new Date());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class ThreadJoinDemo {
	public static void main(String[] args) throws InterruptedException {
		RunnableJob runnableJob = new RunnableJob();

        long start = System.currentTimeMillis();
		Thread thread1 = new Thread(runnableJob, "T1");
		Thread thread2 = new Thread(runnableJob, "T2");
		Thread thread3 = new Thread(runnableJob, "T3");
		Thread thread4 = new Thread(runnableJob, "T4");

		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();

		Thread thread5 = new Thread(runnableJob, "T5");
		Thread thread6 = new Thread(runnableJob, "T6");
		Thread thread7 = new Thread(runnableJob, "T7");
		Thread thread8 = new Thread(runnableJob, "T8");

		thread5.start();
		thread6.start();
		thread7.start();
		thread8.start();

        thread2.join();
        thread1.join();
        thread4.join();
        thread3.join();
        thread5.join();
        thread6.join();
        thread7.join();
        thread8.join();

        long end = System.currentTimeMillis();
        System.out.println("Total time taken: " + (end - start) + " ms ");
	}
}