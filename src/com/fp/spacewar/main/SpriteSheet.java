package com.fp.spacewar.main;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteSheet {
	private BufferedImage image;
	public SpriteSheet(BufferedImage image) {
		this.image=image;
	}
	public BufferedImage grabImage (int x, int y, int w,int h) {
		BufferedImage img = image.getSubimage(x, y, w, h);
		return img;
	}
	public ArrayList<BufferedImage> grabSprite (int x, int y, int w,int h,int jumlah) {
		ArrayList<BufferedImage> img = new ArrayList<BufferedImage>();
		for(int i=0;i<jumlah;i++) {
			img.add(grabImage(x+(i*w),y,w,h));
		}
		return img;
	}
}


