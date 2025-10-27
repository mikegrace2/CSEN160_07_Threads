package F_threadProblems;

/**
 * This program is used to show the starvation problem.
 * 
 * @author w3spoint
 */
public class D_Starvation implements Runnable {
	private final Object resource;
	private final String message;
	private final boolean fair;

	public D_Starvation(Object resource, String message, boolean fair) {
		this.resource = resource;
		this.message = message;
		this.fair = fair;
	}

	public void run() {
		synchronized (this) {
			for (;;) {
				synchronized (resource) {
					synchronized (System.out) {
						System.out.print(message);
						System.out.flush();
					}
					try {
						(fair ? resource : this).wait(100);
					} catch (InterruptedException ignored) {}
				}
			}
		}
	}

	public static void main(String[] args) {
		boolean fair = false;
		if (args != null && args.length >= 1 && args[0].equals("fair")) {
			fair = true;
		}

		// get the number of available CPUs, do twice as much threads.
		final int cpus = Runtime.getRuntime().availableProcessors();
		System.out.println("" + cpus + " available CPUs found");
		final int runners = cpus * 2;
		System.out.println("starting " + runners + " runners");

		final Object resource = new Object();

		Thread[] myThreadsArr=new Thread[runners];
		
		// create sample runners and start them
		for (int i = 0; i < runners; i++) {
			myThreadsArr[i]=new Thread(new D_Starvation(resource, "-"+String.valueOf(i), fair));
			myThreadsArr[i].start();
		}
		
		// Wait that they all are done
		long start=System.currentTimeMillis();
		for (int i = 0; i < runners; i++) {
			try {
				myThreadsArr[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long end=System.currentTimeMillis();
		System.out.println("time="+(end-start)+" ALL done :) ");
	}
}
