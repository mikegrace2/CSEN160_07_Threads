package G_memory;

import java.awt.Button;

import javax.swing.JPanel;

public class A_SimpleThread implements Runnable{
	public static int counter=0; 
	public static DataObject data_object_static=new DataObject(Integer.valueOf(303), "John", "Doe", Math.PI+56);
	public DataObject data_object_member=new DataObject(Integer.valueOf(909), "Michael", "Schimpf", Math.PI);

	public static synchronized void counterPlusPlus() {
		A_SimpleThread.counter++;
	}
	
	@Override
	public void run() {
		JPanel myJPanel=new JPanel();
		
		for (int i=0;i<10;i++) {
			myJPanel.add(new Button("ClickMe"+i));
			A_SimpleThread.counterPlusPlus();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(
				Thread.currentThread().getName()+" Counter     =" + A_SimpleThread.counter+"\n"+
				Thread.currentThread().getName()+" data static =" + A_SimpleThread.data_object_static+"\n"+
				Thread.currentThread().getName()+" data member =" + this.data_object_member+"\n");
	}
	
	public static void main(String[] args) {
		A_SimpleThread mySimpleThread1=new A_SimpleThread();
		Thread myThread1= new Thread(mySimpleThread1);
		myThread1.start();

		A_SimpleThread mySimpleThread2=new A_SimpleThread();
		Thread myThread2= new Thread(mySimpleThread2);
		myThread2.start();

		A_SimpleThread mySimpleThread3=new A_SimpleThread();
		Thread myThread3= new Thread(mySimpleThread3);
		myThread3.start();

		try {
			myThread1.join();
			myThread2.join();
			myThread3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}