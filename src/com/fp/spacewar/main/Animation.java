package com.fp.spacewar.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
	
	ArrayList<BufferedImage>anim;
	BufferedImage animImage;
	int index;
	Game game;
	
	public Animation(ArrayList<BufferedImage>anim,Game game) {
		this.anim=anim;
		index=0;
		animImage= anim.get(index);
		this.game=game;
	}

	public void tick() {
			//index=(int)game.pewaktu.getTime();
			index++;
			animImage= anim.get(index%anim.size());
	}
	public void drawAnimation(Graphics g,double x,double y) {
		
		g.drawImage(animImage, (int) x, (int) y, null);
	}



}
