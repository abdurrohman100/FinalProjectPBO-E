package com.fp.spacewar.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
public class Army extends Enemy {


	private Animation anim;
	Random random = new Random();
	
	/**Constructor
	 * @param posX Posisi x spawn 
	 * @param posY Posisi x spawn 
	 * @param textureImagae texture image memuat gambar untuk animasi army;
	 * @param aggresivePoint merupakan variable tingkat kesulitan
	 */
	public Army(double posX, double posY,Texture textureImagae, int aggresivePoint) {
		super(posX,posY);
		this.setAttackPoint(aggresivePoint+1);
		anim=new Animation(textureImagae.enemy);
	}
	
	/**Fungsi untuk mengupdate perilaku army
	 *
	 */
	public void tick() {
		
		x-=2;
		if(x<-100) {
			x=1300;
			y=random.nextInt(600);
		}
		anim.tick();
	}
	
	/**Fungsi untuk merender animasi dari army
	 *
	 */
	public void render(Graphics g) {
		anim.drawAnimation(g, x, y);
	}
	
	/**Fungsi untuk mendapatkan batas dari object
	 *
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,40,40);
	}


	
}