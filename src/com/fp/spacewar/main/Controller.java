package com.fp.spacewar.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller {
	private LinkedList<Bullet> b = new LinkedList<Bullet>();
	Game game;
	Bullet tempBullet;
	
	public Controller(Game game){
		this.game=game;

	}
	public void tick() {
		for(int i=0;i<b.size();i++) {
			tempBullet= b.get(i);
			if(tempBullet.getX()>1280)
				removeBullet(tempBullet);
			tempBullet.tick();
		}
	}
	public void render(Graphics g) {
		for(int i=0;i<b.size();i++) {
			tempBullet= b.get(i);
			tempBullet.render(g);
		}
	}
	public void addBullet(Bullet block) {
		b.add(block);
	}
	public void removeBullet(Bullet block) {
		b.remove(block);
	}
}
