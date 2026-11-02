package F_threadProblems;

import java.util.concurrent.CountDownLatch;

public class A_DeadlockNoStatic extends Thread {
	private final Object Lock1 = new Object();
	private final Object Lock2 = new Object();
	private final CountDownLatch bothLocksHeld = new CountDownLatch(2);

	private class ThreadDemo1 extends Thread {
		public void run() {
			synchronized (Lock1) {
				System.out.println("Thread 1: Holding lock 1...");
				bothLocksHeld.countDown();
				awaitBothLocks();
				System.out.println("Thread 1: Waiting for lock 2...");

				synchronized (Lock2) {
					System.out.println("Thread 1: Holding lock 1 & 2...");
				}
			}
		}
	}

	private class ThreadDemo2 extends Thread {
		public void run() {
			synchronized (Lock2) {
				System.out.println("Thread 2: Holding lock 2...");
				bothLocksHeld.countDown();
				awaitBothLocks();
				System.out.println("Thread 2: Waiting for lock 1...");

				synchronized (Lock1) {
					System.out.println("Thread 2: Holding lock 1 & 2...");
				}
			}
		}
	}

	private void awaitBothLocks() {
		try {
			bothLocksHeld.await();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public static void main(String args[]) {
		A_DeadlockNoStatic example = new A_DeadlockNoStatic();
		ThreadDemo1 T1 = example.new ThreadDemo1();
		ThreadDemo2 T2 = example.new ThreadDemo2();
		T1.start();
		T2.start();
	}
}
