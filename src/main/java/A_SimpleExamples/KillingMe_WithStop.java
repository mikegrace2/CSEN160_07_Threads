package A_SimpleExamples;

public class KillingMe_WithStop extends Thread{
	@Override
	public void run() {
		while ( !Thread.currentThread().isInterrupted() ) {
			System.out.println("Still alive!");
			
			try {
				Thread.sleep(10);// Sleep for 10 ms
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // It breaks out, then you need call interrupt() again
				System.out.println("\nBreak in sleep()\n");
			}	
		}
		
		System.out.println("\nThis is the end!");
		System.out.println("This is the end!");
		System.out.println("This is the end!\n");
	}

	public static void main(String[] args) {
		Thread killMe = new Thread(new KillingMe_WithStop());
		killMe.start();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		killMe.stop();
	}
}