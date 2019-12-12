package com.fp.spacewar.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Boss extends Enemy{

	private Texture tex;
	private EntityController entityController;
	private Animation anim;
	Random random = new Random();
	private int velX=0;
	private int velY=2;
	
	public Boss(double x, double y,Texture tex,Game game,EntityController entityController, int aggresivePoint) {
		super(x, y);
		this.game=game;
		this.tex=tex;
		this.entityController=entityController;
		this.setAttackPoint(aggresivePoint+1);
		anim=new Animation(tex.boss,game);
		this.setHealtPoint(aggresivePoint*1000);
		System.out.println("HAI BOSS");
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void tick() {

		//System.out.println(x +" "+ y);
		x+=velX;
		y+=velY;
//		if(x>600) {
//			
			if(x>1280-250) {
				velX=-2;
			}if(x<700){
				velX=+2;
			}
//		}
			
			if(y>450) {
				velY=-2;
				System.out.println("Bounce up");
			}if(y<50){
				System.out.println("Bounce dwon");
				velY=+2;
			}
		
		
	}
	
	public void render(Graphics g) {
		//g.drawImage(tex.enemy.get(0), (int)x,(int)y,null);
		anim.drawAnimation(g, x, y);
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,250,250);
	}
	
	
	
}
