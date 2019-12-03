package com.fp.spacewar.main;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage image;
	public SpriteSheet(BufferedImage image) {
		this.image=image;
	}
	public BufferedImage grabImage (int col, int row, int w,int h) {
		BufferedImage img = image.getSubimage((col*h)-h, (row*h)-h, w, h);
		return img;
	}
}


