package com.fp.spacewar.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Boss extends Enemy{

	private Texture tex;
	private EntityController entityController;
	private Animation anim;
	Random random = new Random();
	
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
		
		if(x >=1260) {
			x -= 3;
		}else if(x<900) {
			x+=2;
		}
		else if(y>650) {
			y-=2;
		}else if(y<100) {
			y+=2;
		}
			else {
			int tempTransformation = random.nextInt(4);
			if(tempTransformation == 1) {
				x -= 2;
			}else if(tempTransformation ==2) {
				x += 2;
			}else if(tempTransformation ==3) {
				y -= 2;
			}else if(tempTransformation ==4) {
				y += 2;
			}else {
				x -= 1;
			}
			
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
