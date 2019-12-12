package com.fp.spacewar.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import com.fp.spacewar.main.Game.GameState;
public class Player extends GameObject{
	public int healthPoint;
	private double velX=0;
	private double velY=0;

	private int score=0;
	private Animation anim;
	Game game;

	public Player(double x,double y,Texture tex,Game game) {
		super(x,y);
		this.healthPoint = 100;
		this.score=0;
		this.game=game;
		anim= new Animation(tex.player);
		
		
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
		anim.tick();
		if(x>=1280-50)x=1280-50;
		if(x<=0)x=0;
		if(y<=0)y=0;
		if(y>=720-50)y=720-50;
		if(this.healthPoint<=0) {
			System.out.println("Mati");
			Game.currentGameState=GameState.IN_GAMEOVER;
		}
		
	}
	public void render(Graphics g) {
    	Font title = new Font("SanSerif", Font.BOLD,36);
		g.setFont(title);
		g.setColor(Color.BLUE);	
    	String printLive = "Life: "+ this.healthPoint;
    	g.drawRect(0, 50, 200, 50);
    	g.drawString(printLive, 5, 90);
    	String printLevel = "Level: "+ game.getEntityController().getAggresivePoint() ;
    	g.drawRect(0, 100, 200, 50);
    	g.drawString(printLevel, 5, 140);
		//g.drawImage(tex.player.get(0), (int)x,(int)y,null);
    	anim.drawAnimation(g, x, y);
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
