package com.fp.spacewar.main;

import javax.swing.JFrame;

public class Main {
	public static void main(String args[]) {
		Game game = new Game();

		JFrame frame = new JFrame("FP");
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		game.start();
	}

}
