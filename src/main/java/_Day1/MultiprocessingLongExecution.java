package _Day1;

public class MultiprocessingLongExecution {

	public static void main(String[] args) {
		try {
            System.out.println("Start");
			Thread.sleep(1000);
			System.out.println("Done");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// Sleep for 10 ms
	}

}
