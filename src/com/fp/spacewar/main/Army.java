package com.fp.spacewar.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import com.fp.spacewar.main.entity.EntityA;

public class Army extends Enemy {

	private Texture tex;
	private EntityController entityController;
	private Animation anim;
	Random random = new Random();
	
	public Army(double x, double y,Texture tex,Game game,EntityController entityController, int aggresivePoint) {
		super(x,y);
		this.game=game;
		this.tex=tex;
		this.entityController=entityController;
		this.setAttackPoint(aggresivePoint+1);
		anim=new Animation(tex.enemy,game);
	}
	
	public void tick() {
		
		x-=2;
		if(x<-100) {
			x=1300;
			y=random.nextInt(600);
		}
		anim.tick();
//		if(Physics.Collision(this, game.entityAList)) {
//			System.out.println("Duar");
//			entityController.removeEntity(this);
//		}
		
		
	
	}
	public void render(Graphics g) {
		//g.drawImage(tex.enemy.get(0), (int)x,(int)y,null);
		anim.drawAnimation(g, x, y);
	}
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,40,40);
	}
	
}