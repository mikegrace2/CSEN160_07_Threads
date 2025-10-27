package F_threadProblems;

class C_Starvation extends Thread {
	static int count = 1;
	
	public static synchronized void countPlusPlus() {
		C_Starvation.count++;
		try {
			System.out.println("Thread sleep for 10 seconds!");
			Thread.sleep(10_000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		System.out.println(count + " Thread executed!");
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Thread1 start prio 7\n");
		C_Starvation thread1 = new C_Starvation();
		thread1.setPriority(7);
		
		System.out.println("Thread2 start prio 6\n");
		C_Starvation thread2 = new C_Starvation();
		thread2.setPriority(6);
		
		System.out.println("Thread3 start prio 5\n");
		C_Starvation thread3 = new C_Starvation();
		thread3.setPriority(5);
		
		System.out.println("Thread4 start prio 4\n");
		C_Starvation thread4 = new C_Starvation();
		thread4.setPriority(4);
		
		System.out.println("Thread5 start prio 3\n");
		C_Starvation thread5 = new C_Starvation();
		thread5.setPriority(3);

		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();

		thread1.join();
		thread2.join();
		thread3.join();
		thread4.join();
		thread5.join();
		
		System.out.println("All threads are done!");
	}
}