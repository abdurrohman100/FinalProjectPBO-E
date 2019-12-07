package com.fp.spacewar.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.fp.spacewar.main.entity.EntityB;

public class Enemy extends GameObject implements EntityB {

	private Texture tex;
	private int healtPoint, attackPoint, aggresivePoint;
	private EntityController entityController;
	private Game game;
	Random random = new Random();
	public Enemy(double x, double y,Texture tex,Game game,EntityController entityController) {
		super(x,y);
		this.game=game;
		this.tex=tex;
		this.entityController=entityController;
		
	}
	
	public int getHealtPoint() {
		return healtPoint;
	}
	public void setHealtPoint(int healtPoint) {
		this.healtPoint = healtPoint;
	}
	public int getAttackPoint() {
		return attackPoint;
	}
	public void setAttackPoint(int attackPoint) {
		this.attackPoint = attackPoint;
	}
	public int getAggresivePoint() {
		return aggresivePoint;
	}
	public void setAggresivePoint(int aggresivePoint) {
		this.aggresivePoint = aggresivePoint;
	}
	public void tick() {
		x-=2;
		if(x<-0) {
			x=1300;
			y=random.nextInt(600);
		}
		if(Physics.Collision(this, game.entityAList)) {
			System.out.println("Duar");
			entityController.removeEntity(this);
		}
	
	}
	public void render(Graphics g) {
		g.drawImage(tex.enemy, (int)x,(int)y,null);
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

	protected int totalSpawnPer10sec() {
		return aggresivePoint*4;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,50,50);
	}
	
}