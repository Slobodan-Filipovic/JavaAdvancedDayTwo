package diy.multithreading.animation;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class SelectionSortDisplayer {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		final int FRAME_WIDTH = 300;
		final int FRAME_HEIGHT = 400;
		
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			
		final PrettyAnimation temp = new PrettyAnimation();
		frame.add(temp, BorderLayout.CENTER);
		
		frame.setVisible(true);
		temp.startAnimation();
	}

}
