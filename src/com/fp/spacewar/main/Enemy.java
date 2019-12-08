package com.fp.spacewar.main;

public class Enemy extends GameObject{
	
	private int healtPoint, attackPoint;
	protected Game game;
	public Enemy(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
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
	

	
	
}
