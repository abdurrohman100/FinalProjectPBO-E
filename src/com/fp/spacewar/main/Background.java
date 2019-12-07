package com.fp.spacewar.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
	import java.awt.Graphics;
	import java.awt.image.BufferedImage;
	import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
	 
	public class Background {
	    private BufferedImage image;
	 
	    private double x;
	    private double y;
	    public Background() {
	        this(0,0);
	    }
	 
	    public Background(double x, double y) {
	        this.x = x;
	        this.y = y;
	        
	        // Try to open the image file background.png
	        BufferedImageLoader loader= new BufferedImageLoader();
			try {
				image = loader.loadImage("background_1.png");
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	    }
	 
	    /**
	     * Method that draws the image onto the Graphics object passed
	     * @param window
	     */
	    public void draw(Graphics window) {
	 
	        // Draw the image onto the Graphics reference
	        window.drawImage(image, (int)getX(), (int)getY(), image.getWidth(), image.getHeight(), null);
	 
	        // Move the x position left for next time
	        this.x -= 5;
	        
	 
	        // Check to see if the image has gone off stage left
	        if (this.x <= -1 * image.getWidth()) {
	 
	            // If it has, line it back up so that its left edge is
	            // lined up to the right side of the other background image
	            this.x = this.x + image.getWidth() * 2;
	        }
	 
	    }
	 
	    public void setX(double x) {
	        this.x = x;
	    }
	    public double getX() {
	        return this.x;
	    }
	    public double getY() {
	        return this.y;
	    }
	 
}
