package _Day1;
import java.util.Scanner;

class SomeAction extends Thread {
	private volatile boolean running = true;

	public void run() {
		while (running) {
			System.out.println("Hello");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}

	public void stopRunning() {
		running = false;
	}
}

public class UseVolatile_Demo {
	public static void main(String[] args) {
		SomeAction action1 = new SomeAction();
		action1.start();
		System.out.println("Hit Enter to stop");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		action1.stopRunning();
	}
}