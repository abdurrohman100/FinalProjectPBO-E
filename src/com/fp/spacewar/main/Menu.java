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
		g.drawString("Judul Asu", Game.w/2-150, 100);
		Font optMenu = new Font("SanSerif", Font.BOLD,46);
		g.drawString("Play", playButton.x+35, playButton.y+55);
		g2d.draw(playButton);
		g.drawString("Hall", hofButton.x+35, hofButton.y+55);
		g2d.draw(hofButton);
		g.drawString("Help", helpButton.x+35, helpButton.y+55);
		g2d.draw(helpButton);
		g.drawString("Quit", quitButton.x+35, quitButton.y+55);
		g2d.draw(quitButton);
	}
}
