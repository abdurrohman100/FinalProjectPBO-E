package com.fp.spacewar.main;

import java.awt.Rectangle;

import com.fp.spacewar.main.entity.EntityA;

public abstract class GameObject implements EntityA{
	public double x,y;
	public GameObject(double x,double y) {
		this.x=x;
		this.y=y;
	}
	public Rectangle getBounds(int width, int height) {
		return new Rectangle((int)x,(int)y,width,height);
	}	

}
