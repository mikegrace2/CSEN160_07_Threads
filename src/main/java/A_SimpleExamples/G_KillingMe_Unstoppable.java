package A_SimpleExamples;

public class G_KillingMe_Unstoppable extends Thread {
	@Override
	public void run() {
		while (true) {
			System.out.println("Still alive!");

			try {
				Thread.sleep(100); // Sleep for 10 ms
			} catch (InterruptedException e) {
				System.out.println("\nInterruptedException\n");
			} catch (ThreadDeath e) {
				System.out.println("\nThreadDeath\n");
				// Clean up resources and close files etc. 
				throw e;
			}
		}
	}

	public static void main(String[] args) {
		Thread killMe = new Thread(new G_KillingMe_Unstoppable());
		killMe.start();

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		killMe.stop();
	}
}