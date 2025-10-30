package _MultiThreaded;

import java.awt.Color;

import javax.swing.JFrame;

@SuppressWarnings("serial")
class BouncingBalls_DifferentPriorities extends JFrame {
	protected Ball ball;
	
	public BouncingBalls_DifferentPriorities(Ball ball) {
		this.setResizable(false);
		this.setSize(400, 400);
		
		this.ball=ball;
		this.getContentPane().add(this.ball);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		Ball ball1 = new Ball(Color.BLUE);
		Ball ball2 = new Ball(Color.RED);

		ball1.add(ball2);
		new BouncingBalls_DifferentPriorities(ball1);

		Thread x = new Thread(ball1);
		//x.setPriority(Thread.NORM_PRIORITY + 3); // Blue
		x.setPriority(Thread.MIN_PRIORITY); // Blue
		Thread y = new Thread(ball2);
		//y.setPriority(Thread.NORM_PRIORITY - 3); // Red
        y.setPriority(Thread.MAX_PRIORITY); // Red

		x.start();
		y.start();
	}
}
