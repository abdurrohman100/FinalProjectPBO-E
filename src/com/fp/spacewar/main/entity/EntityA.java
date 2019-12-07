package com.fp.spacewar.main.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityA {
	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();
	public double getX();
	public double getY();
	public void setX(double x);
	public void setY(double y);

}
