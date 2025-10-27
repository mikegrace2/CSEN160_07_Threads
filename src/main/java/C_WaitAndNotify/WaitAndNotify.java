package C_WaitAndNotify;

class Demo {
	volatile boolean part1done = false;

	public synchronized void part1() {
		System.out.println(Thread.currentThread().getName()+": Part1 is executed ...");
		part1done = true;
		System.out.println(Thread.currentThread().getName()+": Part1 about to surrender lock"); 
		notify(); // notify the waiting thread, if any
	}

	public synchronized void part2() {
		while (!part1done) {
			try {
				System.out.println(Thread.currentThread().getName()+": Part2 waiting ...");
				wait(); // until part1 is done
				System.out.println(Thread.currentThread().getName()+": Part2 running again");
			} catch (Exception e) {
				System.out.println(e.getClass());
			}
		}
		System.out.println(Thread.currentThread().getName()+": Part2 done!");
	}
}

public class WaitAndNotify {
	public static void main(String[] args) {
		Demo obj = new Demo();

		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				obj.part1();
			}
		});
		thread1.setName("Thread1");

		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				obj.part2();
			}
		});
		thread2.setName("Thread2");

		thread2.start();
		thread1.start();
	}
}
