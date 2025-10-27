package F_threadProblems;

class TestThread extends Thread {
	   public void run() {
	      String threadName = Thread.currentThread().getName();
	      System.out.println(threadName + " Started");
	      
	      synchronized(TestThread.class) { // lock
	         // doing some useful work
	         try {
	            Thread.sleep(2000); // 2 sec
	         } catch (InterruptedException ie){}
	      }
	      
	      System.out.println(threadName + " End");
	   }
	}

	public class E_Starvation {
	   public static void main(String[] args) {
	      System.out.println("Start of Main thread");
	      TestThread mt[] = new TestThread[10];
	       for (int i=0; i<mt.length; i++) {
	          mt[i] = new TestThread(); // create thread
	          mt[i].start();
	       }
	       System.out.println("End of Main thread");
	   }
	}
