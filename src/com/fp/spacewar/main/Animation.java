package com.fp.spacewar.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
	
	ArrayList<BufferedImage>anim;
	BufferedImage animImage;
	int index;

	/**
	 * @param animationImage passing image yanbg akan dianimasikan
	 */
	public Animation(ArrayList<BufferedImage>animationImage) {
		this.anim=animationImage;
		index=0;
		animImage= animationImage.get(index);
	}

	/**Dijalankan dirunnable pada class game
	 * mengupdate index gambar dan melooping setiap kali fungsi tick dipanggil
	 */
	public void tick() {
			index++;
			animImage= anim.get(index % anim.size());
	}
	
	/**
	 * @param g graphic passing
	 * @param posX posisi koordinat x untuk menggambar animasi
	 * @param posY posisi koordinat y untuk menggambar animasi
	 */
	public void drawAnimation(Graphics g,double posX,double posY) {
		
		g.drawImage(animImage, (int) posX, (int) posY, null);
	}



}
