package com.fp.spacewar.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import com.fp.spacewar.main.entity.EntityA;

public class Bullet extends GameObject implements EntityA{


	private Texture textureBullet;
	/**
	 * @param posX posisi X bullet
	 * @param posY posisi Y bullet
	 * @param tex texture gambar untuk bullet
	 */
	public Bullet (double posX, double posY,Texture tex) {
		super(posX,posY);
		this.textureBullet=tex;
	}
	/**
	 *Update perilaku Bullet
	 */
	public void tick() {
		x+=8;
	}
	/**
	 *Gambar objek
	 */
	public void render(Graphics g) {
		g.drawImage(textureBullet.bullet.get(0), (int)x,(int)y,null);
	}
	/**
	 *Dapatkan boundaries dari objek dalam bentuk Rectangle
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,10,3);
	}

	public double getX() {
		return x;
	}public double getY() {
		return y;
	}public void setX(double x) {
		this.x=x;
	}public void setY(double y) {
		this.y=y;
	}
	
}
