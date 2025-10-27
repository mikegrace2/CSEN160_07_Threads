package F_threadProblems;

class B_Starvation extends Thread {	
	public void run() {
		System.out.println("ThreadName="+Thread.currentThread().getName() + " start ...");
		
		double someVariable=0;
		for (int i=0;i<=1_000;i++) {
			someVariable=someVariable+1/0.5*1.5/4; // some calculation
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("ThreadName="+Thread.currentThread().getName() + "... done!");
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Thread1Prio10 started");
		B_Starvation thread1 = new B_Starvation();
		thread1.setName("Thread1Prio10");
		thread1.setPriority(10);
		
		System.out.println("Thread2Prio10 started");
		B_Starvation thread2 = new B_Starvation();
		thread2.setName("Thread2Prio10");
		thread2.setPriority(10);
		
		System.out.println("Thread3Prio10 started");
		B_Starvation thread3 = new B_Starvation();
		thread3.setName("Thread3Prio10");
		thread3.setPriority(10);
				
		System.out.println("Thread4Prio01 started");
		B_Starvation thread4 = new B_Starvation();
		thread4.setName("Thread4Prio01");
		thread4.setPriority(1);
		
		System.out.println();
		System.out.println();
		
		System.out.println("All started");
		thread4.start();
		thread3.start();
		thread2.start();
		thread1.start();
	
		System.out.println("All join\n");
		
		thread4.join();
		thread3.join();
		thread2.join();
		thread1.join();
		
		System.out.println("All threads are done!");
	}
}