package J_virtual;

public class A_VirtualThread{	
	public static void main(String[] args) {
		
		Runnable runnable = () -> { 
			for (int i=0;i<10;i++) {
				System.out.println("Index="+i);
			} 
		};

        Thread myThread = Thread.startVirtualThread(runnable);

		// Wait to join
		try {
			myThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Done");
		
	}
}
