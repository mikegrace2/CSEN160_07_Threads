package A_SimpleExamples;

import java.util.Iterator;
import java.util.Set;

public class WhoAmI {
	public static void main(String[] args) {
		System.out.println("This Thread: This Thread: This Thread: This Thread:");
		System.out.println("ToString:      "+Thread.currentThread());
		System.out.println("Name:          "+Thread.currentThread().getName());
		System.out.println("Id:            "+Thread.currentThread().getId());
		System.out.println("State:         "+Thread.currentThread().getState());
		System.out.println("Priority:      "+Thread.currentThread().getPriority());

		System.out.println("isAlive?       "+Thread.currentThread().isAlive());
		System.out.println("isDaemon?      "+Thread.currentThread().isDaemon());
		System.out.println("isInterrupted? "+Thread.currentThread().isInterrupted());
		System.out.println("ThreadGroupd:  "+Thread.currentThread().getThreadGroup());		
		System.out.println("MIN_PRIORITY:  "+Thread.MIN_PRIORITY);
		System.out.println("NORM_PRIORITY: "+Thread.NORM_PRIORITY);
		System.out.println("MAX_PRIORITY:  "+Thread.MAX_PRIORITY);
		System.out.println("activeCount:   "+Thread.activeCount());

		System.out.println("\nOther Threads: Other Threads: Other Threads: Other Threads:");
		
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		
		Iterator<Thread> myIt=threadSet.iterator();
		
		while(myIt.hasNext()) {
			Thread thread=myIt.next();
			System.out.println("toString:      "+thread);
			System.out.println("Name:          "+thread.getName());
			System.out.println("Id:            "+thread.getId());
			System.out.println("State:         "+thread.getState());
			System.out.println("Priority:      "+thread.getPriority());
			System.out.println("isAlive?       "+thread.isAlive());
			System.out.println("isDaemon?      "+thread.isDaemon());
			System.out.println("isInterrupted? "+thread.isInterrupted());
			System.out.println("\n");
		}
	}
}