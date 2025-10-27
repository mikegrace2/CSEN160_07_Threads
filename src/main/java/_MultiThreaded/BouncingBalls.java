package _MultiThreaded;

import java.awt.Color;

import javax.swing.JFrame;

@SuppressWarnings("serial")
class BouncingBalls extends JFrame {
	protected Ball ball;
	
	public BouncingBalls(Ball ball) {
		this.setResizable(false);
		this.setSize(400, 400);
		
		this.ball=ball;
		this.getContentPane().add(this.ball);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		Ball ball1 = new Ball(Color.BLUE);
		Ball ball2 = new Ball(Color.RED);
		Ball ball3 = new Ball(Color.GREEN);

		ball1.add(ball2);
		ball2.add(ball3);
		new BouncingBalls(ball1);

		Thread x = new Thread(ball1);
		Thread y = new Thread(ball2);
		Thread z = new Thread(ball3);

		x.start();
		y.start();
		z.start();
	}
}
