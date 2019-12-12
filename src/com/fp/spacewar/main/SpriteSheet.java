package com.fp.spacewar.main;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteSheet {
	private BufferedImage image;
	/**
	 * @param image Passing berupa BufferedImage
	 */
	public SpriteSheet(BufferedImage image) {
		this.image=image;
	}
	/**
	 * Grab single image
	 * @param x Posisi awal x
	 * @param y Posisi awal Y
	 * @param w lebar gambar
	 * @param h tinggi gambar
	 * @return
	 */
	public BufferedImage grabImage (int x, int y, int w,int h) {
		BufferedImage img = image.getSubimage(x, y, w, h);
		return img;
	}
	/**
	 * Grab semua gambar object dan masukan dalam arraylist
	 * @param x Posisi awal x
	 * @param y Posisi awal Y
	 * @param w lebar gambar
	 * @param h tinggi gambar
	 * @param jumlah jumlah gambar pada array
	 * @return array of Buffred image
	 */
	public ArrayList<BufferedImage> grabSprite (int x, int y, int w,int h,int jumlah) {
		ArrayList<BufferedImage> img = new ArrayList<BufferedImage>();
		for(int i=0;i<jumlah;i++) {
			img.add(grabImage(x+(i*w),y,w,h));
		}
		return img;
	}
}


