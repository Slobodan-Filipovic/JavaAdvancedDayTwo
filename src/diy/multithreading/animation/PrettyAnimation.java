package diy.multithreading.animation;

import java.awt.Graphics;

import javax.swing.JComponent;

public class PrettyAnimation extends JComponent{
	private SelectionSorter sorter;
	
	public PrettyAnimation() {
		int[] values = ArrayUtil.randomIntArray(30, 300);
		sorter = new SelectionSorter(values, this);
	}
	
	public void paintComponent(Graphics g) {
		sorter.draw(g);
	}
	
	public void startAnimation() {
		class AnimationRunnable implements Runnable{

			@Override
			public void run() {
				try {
					sorter.sort();
				}
				catch(InterruptedException e){
					System.out.println(e.toString());
				}
				
			}
			
		}
		Runnable r = new AnimationRunnable();
		Thread t = new Thread(r);
		t.start();
		
	}

}
