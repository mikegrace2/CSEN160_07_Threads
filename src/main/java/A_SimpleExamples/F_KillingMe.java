package A_SimpleExamples;

public class F_KillingMe extends Thread{
	@Override
	public void run() {
		while ( !Thread.currentThread().isInterrupted() ) {
			System.out.println("Still alive!");
			
			try {
				Thread.sleep(10);// Sleep for 10 ms
			} catch (InterruptedException e) {
				System.out.println("\nBreak in sleep()\n");
				//Thread.currentThread().interrupt();
			}	
		}
		
		//System.out.println("\nThis is the end!");
		//System.out.println("This is the end!");
		//System.out.println("This is the end!\n");
	}

	public static void main(String[] args) {
		Thread killMe = new Thread(new F_KillingMe());
		killMe.start();
		killMe.stop();
		
		
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		//killMe.interrupt();
		System.out.println("\nAfter calling STOP()!\n");
	}
}