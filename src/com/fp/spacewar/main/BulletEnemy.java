package com.fp.spacewar.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public class BulletEnemy extends GameObject{

	private Texture tex;
	
	/**
	 * @param posX posisi X
	 * @param posY posisi Y
	 * @param tex texture untuk bullet
	 */
	public BulletEnemy (double posX, double posY,Texture tex) {
		super(posX,posY);
		this.tex=tex;
	}
	/**
	 *Update perilaku bullet
	 */
	public void tick() {
		x-=3;
	}
	public void render(Graphics g) {
		g.drawImage(tex.bullet.get(0), (int)x,(int)y,null);
	}
	
	//Getter and Setter
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
