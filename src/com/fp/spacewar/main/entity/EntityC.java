package com.fp.spacewar.main.entity;

import java.awt.Graphics;

public interface EntityC {
	public void tick();
	public void render(Graphics g);
	public double getX();
	public double getY();
	public void setX(double x);
	public void setY(double y);

}
