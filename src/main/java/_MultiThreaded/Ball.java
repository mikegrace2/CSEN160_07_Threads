package _MultiThreaded;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Ball extends JPanel implements Runnable {
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

	public void run() { // move
		while (true) {
			try {
				Thread.sleep(20);
			}
			catch (InterruptedException exception) {
				System.err.println(exception.toString());
			}

			if (xUp == true)
				this.x += this.xDx;
			else
				this.x -= this.xDx;

			if (yUp == true)
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
