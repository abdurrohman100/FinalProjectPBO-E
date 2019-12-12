package com.fp.spacewar.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Help {
	private Background background1= new Background(0,0,"res/HowTo.png");
	public Help() {
		
	}
	public void render(Graphics g) {
		Font back = new Font("SanSerif", Font.BOLD,20);
		g.setFont(back);
		g.setColor(Color.WHITE);
		Graphics2D g2d = (Graphics2D) g;
		background1.render(g);
		g2d.draw(new Rectangle(20,50,60,30));
		g.drawString("Back", 25, 70);
		
	}
	
}
