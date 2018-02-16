package diy.multithreading.animation;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JComponent;

/**
 * Selection sort
 * 
 * @author Slobodan
 *
 */
public class SelectionSorter {

	private int[] arr;

	// for displaying
	private int markedPosition = -1;
	private int done = -1;
	private JComponent component;
	

	// basically does what the name says, its kinda intuitive but if you forget
	// google it.
	private Lock sortStateLock;

	private static final int DELAY = 100;

	/**
	 * Constructor
	 * 
	 * @param anArray
	 * @param aComponent
	 */
	public SelectionSorter(int[] anArray, JComponent component) {
		arr = anArray;
		sortStateLock = new ReentrantLock();
		this.component = component;
	}

	public void sort() throws InterruptedException {
		for (int i = 0; i < arr.length - 1; i++) {
			int minPos = minimumPosition(i);
			sortStateLock.lock();
			try {
				ArrayUtil.swap(arr, minPos, i);
				done = i;				
			}
			finally {
				sortStateLock.unlock();
			}
			pause(2);
		}
	}

	/**
	 * Finds the smallest element in a tail range of the array.
	 * @param from the first position in a to compare
	 * @return the position of the smallest element in the range arr[from] to arr[arr.length - 1]
	 * @throws InterruptedException
	 */
	private int minimumPosition(int from) throws InterruptedException {
		int minPos = from;
		for (int i = from + 1; i < arr.length; i++) {
			sortStateLock.lock();
			try {
				if (arr[i] < arr[minPos]) {
					minPos = i;
				}
				// For displaying
				markedPosition = i;
			} finally {
				sortStateLock.unlock();
			}
			pause(2);
		}
		return minPos;
	}
	
	/**
	 * Updates the drawing of the algorithm (selection sort)
	 * @param g
	 */
	public void draw(Graphics g) {
		sortStateLock.lock();		 
		try {
			int deltaX = component.getWidth() / arr.length;
			for(int i = 0; i < arr.length; i++) {
				if( i == markedPosition) {
					g.setColor(Color.RED);
				}
				else if( i <= done) {
					g.setColor(Color.BLUE);
				}
				else {
					g.setColor(Color.WHITE);
				}
				g.drawLine(i * deltaX, 0, i * deltaX, arr[i]);
			}
		}
		finally {
			sortStateLock.unlock();
		}
	}

	/**
	 * a method that helps with animation by pausing stuff. Aside from the obvious
	 * look at it like a black box
	 * 
	 * @param steps
	 * @throws InterruptedException
	 */
	public void pause(int steps) throws InterruptedException {
		component.repaint();
		Thread.sleep(steps * DELAY);
	}
}
