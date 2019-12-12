package com.fp.spacewar.main;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle playButton;
	public Rectangle quitButton;
	public Rectangle hofButton;
	public Rectangle helpButton;
	private Background background1= new Background(0,0,"res/mainmenu.png");
	public Menu(){


	}
	public void render(Graphics g) {
		background1.render(g);
		playButton = new Rectangle(Game.w/2-100,200,200,70);
		hofButton = new Rectangle(Game.w/2-100,300,200,70);
		helpButton = new Rectangle(Game.w/2-100,400,200,70);
		quitButton = new Rectangle(Game.w/2-100,500,200,70);
		Graphics2D g2d =(Graphics2D)g;
		Font title = new Font("SanSerif", Font.BOLD,66);
		g.setFont(title);
		g.setColor(Color.WHITE);
		g.drawString("Space Impact", Game.w/2-200, 100);
		Font optMenu = new Font("SanSerif", Font.BOLD,46);
		g.setFont(optMenu);
		g.drawString("Play", playButton.x+50, playButton.y+55);
		g2d.draw(playButton);
		g.drawString("Hall", hofButton.x+50, hofButton.y+55);
		g2d.draw(hofButton);
		g.drawString("Help", helpButton.x+50, helpButton.y+55);
		g2d.draw(helpButton);
		g.drawString("Quit", quitButton.x+50, quitButton.y+55);
		g2d.draw(quitButton);
	}
}
