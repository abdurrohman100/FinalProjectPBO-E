package com.fp.spacewar.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {
	private double x;
	private double y;
	private double velX=0;
	private double velY=0;
	
	private BufferedImage player;
	//init player
	public Player(double x,double y, Game game) {
		this.x=x;
		this.y=y;
		SpriteSheet ss =new SpriteSheet(game.getSpriteSheet());
		player = ss.grabImage(1, 1, 50, 50);
	}
	public void tick() {
		x+=velX;
		y+=velY;
		if(x>=1280-40)x=1280-40;
		if(x<=0)x=0;
		if(y<=0)y=0;
		if(y>=720-40)y=720-40;
		
	}
	public void render(Graphics g) {
		g.drawImage(player, (int)x,(int)y,null);
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getVelX() {
		return velX;
	}
	public double getVelY() {
		return velY;
	}
	public void setVelX(double velX) {
		this.velX = velX;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}


}
