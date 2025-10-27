package _SingleThreaded;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class Ball extends JPanel {
	private Color color;
	private boolean xUp, yUp, bouncing;
	private int x, y, xDx, yDy;
	private final int MAX_X = 400, MAX_Y = 400;

	public Ball(Color color) {
		this.xUp = false;
		this.yUp = false;
		this.xDx = 1;
		this.yDy = 1;
		this.bouncing = true;

		this.setOpaque(false);
		this.setPreferredSize(new Dimension(MAX_X, MAX_Y));
		this.color=color;
	}

	public void move() { // move
		while (true) {
			// Let's sleep anyway
			try {
				Thread.sleep(20);
			}
			catch (InterruptedException exception) {
				System.err.println(exception.toString());
			}

			if (this.xUp == true)
				this.x += this.xDx;
			else
				this.x -= this.xDx;

			if (this.yUp == true)
				this.y += this.yDy;
			else
				this.y -= this.yDy;

			if (this.y <= 0) {
				this.yUp = true;
				this.yDy = (int) (Math.random() * 5 + 2);
			}
			else if (this.y >= MAX_Y - 30) {
				this.yDy = (int) (Math.random() * 5 + 2);
				this.yUp = false;
			}

			if (this.x <= 0) {
				this.xUp = true;
				this.xDx = (int) (Math.random() * 5 + 2);
			}
			else if (this.x >= MAX_X - 30) {
				this.xUp = false;
				this.xDx = (int) (Math.random() * 5 + 2);
			}

			repaint();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (bouncing) {
			g.setColor(this.color);
			g.fillOval(this.x, this.y, 30, 30);
		}
	}
}

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
		Ball ball1 = new Ball(Color.BLUE); // create one ball

		BouncingBalls myBouncingBalls=new BouncingBalls(ball1);
		myBouncingBalls.ball.move();
	}
}
