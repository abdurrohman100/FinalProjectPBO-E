package com.fp.spacewar.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Boss extends Enemy{

	private Animation anim;
	Random random = new Random();
	private int velX=0;
	private int velY=2;
	

	/**
	 * @param posX posisi X objek
	 * @param posY posisi Y objek
	 * @param tex  texture memuat gambar untuk animasi objek boss
	 * @param aggresivePoint merupakan faktor difficult dari bos
	 */
	public Boss(double posX, double posY,Texture tex, int aggresivePoint) {
		super(posX, posY);
		this.setAttackPoint(aggresivePoint+1);
		anim=new Animation(tex.boss);
		this.setHealtPoint(aggresivePoint*2000);
	}
	
	
	
	/**Fungsi untuk mengupdate perilaku boss
	 *
	 */
	public void tick() {
		x+=velX;
		y+=velY;
	
		if(x>1280-250) {
			velX=-2;
		}if(x<700){
			velX=+2;
		}if(y>450) {
			velY=-2;
		}if(y<50){	
			velY=+2;
		}
	}
	
	/**Fungsi untuk menggambar animasi dari Boss
	 * 
	 */
	public void render(Graphics g) {
		anim.drawAnimation(g, x, y);
    	Font title = new Font("SanSerif", Font.BOLD,36);
		g.setFont(title);
		g.setColor(Color.WHITE);
		String hpBos="BOSS : "+this.getHealtPoint();
		g.drawString(hpBos, 1000, 50);
	}
	/**Fungsi untuk mendapat boundaries persegi dari sprite Boss
	 *
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,250,250);
	}
	
	
	
}
