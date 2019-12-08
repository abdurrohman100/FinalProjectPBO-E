package com.fp.spacewar.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy extends GameObject{
	
	private int healtPoint, attackPoint;
	protected Game game;
	public Enemy(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void shootPlayer() {
		
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

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setX(double x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setY(double y) {
		// TODO Auto-generated method stub
		
	}
	

	
	
}
