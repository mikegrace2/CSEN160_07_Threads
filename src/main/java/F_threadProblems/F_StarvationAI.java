package F_threadProblems;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;

/**
 * Demonstrates starvation caused by strict priority scheduling. A single
 * worker always takes the highest-priority task. While high-priority work keeps
 * arriving, the low-priority task waits in the queue and cannot get service.
 */
public class F_StarvationAI {
	private static final AtomicLong nextSequence = new AtomicLong();

	private static final class PriorityTask implements Runnable, Comparable<PriorityTask> {
		private final int priority;
		private final Runnable action;
		private final long sequence = nextSequence.getAndIncrement();

		private PriorityTask(int priority, Runnable action) {
			this.priority = priority;
			this.action = action;
		}

		@Override
		public void run() {
			action.run();
		}

		@Override
		public int compareTo(PriorityTask other) {
			int byPriority = Integer.compare(other.priority, priority);
			return byPriority != 0 ? byPriority : Long.compare(sequence, other.sequence);
		}
	}

	public static void main(String[] args) throws InterruptedException, InvocationTargetException {
		ThreadPoolExecutor scheduler = new ThreadPoolExecutor(
				1, 1, 0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<>());
		CountDownLatch workerReady = new CountDownLatch(1);
		CountDownLatch startWork = new CountDownLatch(1);
		AtomicBoolean continueHighPriorityWork = new AtomicBoolean(true);
		AtomicBoolean animate = new AtomicBoolean(true);
		AtomicInteger highPriorityBallX = new AtomicInteger(20);
		AtomicInteger lowPriorityBallX = new AtomicInteger(20);
		AtomicLong highPriorityRuns = new AtomicLong();
		JLabel status = new JLabel("Starting...");
		JPanel scene = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLACK);
				g.drawString("High priority", 10, 48);
				g.setColor(new Color(45, 120, 220));
				g.fillOval(highPriorityBallX.get(), 55, 36, 36);
				g.setColor(Color.BLACK);
				g.drawString("Low priority", 10, 128);
				g.setColor(new Color(220, 70, 70));
				g.fillOval(lowPriorityBallX.get(), 135, 36, 36);
			}
		};
		scene.setPreferredSize(new Dimension(500, 180));
		scene.setBackground(Color.WHITE);
		JFrame window = new JFrame("Thread Starvation: Low Priority Animation");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.add(status, BorderLayout.NORTH);
		window.add(scene, BorderLayout.CENTER);
		window.pack();
		window.setLocationRelativeTo(null);
		SwingUtilities.invokeAndWait(() -> window.setVisible(true));

		long startTime = System.currentTimeMillis();
		Timer displayTimer = new Timer(100, event -> {
			long elapsedSeconds = (System.currentTimeMillis() - startTime) / 1_000;
			String phase = elapsedSeconds < 3
					? "STARVED: high-priority work keeps winning"
					: animate.get() ? "Moving: worker is available" : "Finished";
			status.setText(phase + " | high-priority tasks: " + highPriorityRuns.get());
			scene.repaint();
		});
		displayTimer.start();

		// Hold the only worker until both tasks have been placed in the priority queue.
		scheduler.execute(new PriorityTask(10, () -> {
			workerReady.countDown();
			try {
				startWork.await();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}));
		workerReady.await();

		Runnable[] lowPriorityAnimationTick = new Runnable[1];
		lowPriorityAnimationTick[0] = () -> {
			lowPriorityBallX.updateAndGet(x -> x >= 450 ? 20 : x + 4);
			SwingUtilities.invokeLater(scene::repaint);
			if (animate.get()) {
				try {
					Thread.sleep(35);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					return;
				}
				try {
					scheduler.execute(new PriorityTask(1, lowPriorityAnimationTick[0]));
				} catch (RejectedExecutionException ignored) {
					// Shutdown won the race with this final animation reschedule.
				}
			}
		};
		scheduler.execute(new PriorityTask(1, lowPriorityAnimationTick[0]));

		Runnable[] highPriorityJob = new Runnable[1];
		highPriorityJob[0] = () -> {
			highPriorityRuns.incrementAndGet();
			highPriorityBallX.updateAndGet(x -> x >= 450 ? 20 : x + 4);
			SwingUtilities.invokeLater(scene::repaint);
			if (continueHighPriorityWork.get()) {
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					return;
				}
				try {
					scheduler.execute(new PriorityTask(10, highPriorityJob[0]));
				} catch (RejectedExecutionException ignored) {
					// Shutdown won the race with this final high-priority reschedule.
				}
			}
		};
		scheduler.execute(new PriorityTask(10, highPriorityJob[0]));

		System.out.println("Both balls start together: blue is high priority, red is low priority.");
		System.out.println("The blue ball moves while the red ball is starved.");
		startWork.countDown();
		Thread.sleep(3_000);

		System.out.println("Stopping high-priority work; the starved animation can now run.");
		continueHighPriorityWork.set(false);
		Thread.sleep(3_000);
		animate.set(false);
		// Let any animation task that already read animate=true finish its
		// short delay and enqueue its final tick before shutting down the pool.
		Thread.sleep(100);
		scheduler.shutdown();
		if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
			scheduler.shutdownNow();
		}
		System.out.println("Animation finished. Close the window when you are done.");
	}
}
