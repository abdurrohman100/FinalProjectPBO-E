package com.fp.spacewar.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.fp.spacewar.main.Game.GameState;
import com.fp.spacewar.main.entity.EntityA;

public class Player extends GameObject{
	public int healthPoint;
	private double velX=0;
	private double velY=0;
	private Texture tex;
	private int score;

	public Player(double x,double y,Texture tex) {
		super(x,y);
		this.healthPoint = 10;
		this.tex=tex;
		this.score=0;
		
		
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void tick() {
		x+=velX;
		y+=velY;

		if(x>=1280-40)x=1280-40;
		if(x<=0)x=0;
		if(y<=0)y=0;
		if(y>=720-40)y=720-40;
		if(this.healthPoint<=0) {
			System.out.println("Mati");
			Game.currentGameState=GameState.IN_GAMEOVER;
		}
		
	}
	public void render(Graphics g) {
    	Font title = new Font("SanSerif", Font.BOLD,36);
		g.setFont(title);
		g.setColor(Color.BLUE);	
    	g.drawRect(0, 50, 200, 50);
    	String print = ""+ this.healthPoint;
    	g.drawString(print, 5, 90);
		g.drawImage(tex.player, (int)x,(int)y,null);
	}
	
	public void reduceHP(int damage) {
		this.healthPoint-=damage;
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
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,50,50);
	}
	


}
