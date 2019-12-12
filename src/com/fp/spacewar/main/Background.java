package com.fp.spacewar.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

	public class Background {
	    private BufferedImage image;
	    private double posX;
	    private double posY;
	    
	    /**Default Constructor
	     * 
	     */
	    public Background() {
	        this(0,0,"res/bg-Recovered.png");
	    }
	 
	    /**
	     * @param posX posisi X background
	     * @param posY posisi Y background
	     * @param path path file yang ingin di load (root src/)
	     */
	    public Background(double posX, double posY,String path) {
	        this.posX = posX;
	        this.posY = posY;
	        
	        // Try to open the image file
	        BufferedImageLoader loader= new BufferedImageLoader();
			try {
				image = loader.loadImage(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    /**Fungsi untuk merender background static
	     * @param window passing graphics
	     */
	    public void render(Graphics window) {
	        window.drawImage(image, (int)posX, (int)posY, image.getWidth(), image.getHeight(), null);
	 
	    }
	    /**Fungsi untuk merender background secara loop tiap n%2 * bacground width
	     * bisa dilakukan dengan bantuan 2 buah background
	     * @param window
	     */
	    public void drawScrollingBG(Graphics window) {
	 
	        // Render gambar image diposisi awal
	        window.drawImage(image, (int)posX, (int)posY, image.getWidth(), image.getHeight(), null);
	 
	        // Geser gambar ke kiri
	        this.posX -= 1;
	        
	 
	        // Cek apakah gambar sudah melewati batas kiri
	        if (this.posX <= -1 * image.getWidth()) {
	 
	            // Jika sudah melewati batas kiri
	            // Letakan background ini setelah background kedua
	            this.posX = this.posX + image.getWidth() * 2;
	        }
	    }
		/**
		 * @return panjang background
		 */
		public double getWidth() {
			return this.image.getWidth();
		}
	 
}
