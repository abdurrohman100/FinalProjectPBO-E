package com.fp.spacewar.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.fp.spacewar.main.entity.EntityA;

public class BulletEnemy extends GameObject{

	private double velX;
	private double velY;
	private Game game;
	private Texture tex;
	
	public BulletEnemy (double x, double y,Texture tex,Game game) {
		super(x,y);
		this.tex=tex;
		this.game=game;
		//enemyBulletAnim= new Animation(tex);
		
	}
	public void tick() {
		x-=3;
		

	}
	public void render(Graphics g) {
		g.drawImage(tex.bullet.get(0), (int)x,(int)y,null);
	}
	public double getX() {
		return x;
	}public double getY() {
		return y;
	}public void setX(double x) {
		this.x=x;
	}public void setY(double y) {
		this.y=y;
	}public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,10,3);
	}
	
}
