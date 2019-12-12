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
	        this(0,0,"res/bg-Recovered.png");
	    }
	 
	    public Background(double x, double y,String path) {
	        this.x = x;
	        this.y = y;
	        
	        // Try to open the image file background.png
	        BufferedImageLoader loader= new BufferedImageLoader();
			try {
				//image = loader.loadImage("res/bg.png");
				image = loader.loadImage(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    public void render(Graphics window) {
	   	 
	        // Draw the image onto the Graphics reference
	        window.drawImage(image, (int)getX(), (int)getY(), image.getWidth(), image.getHeight(), null);
	 
	    }
	    public void drawScrollingBG(Graphics window) {
	 
	        // Draw the image onto the Graphics reference
	        window.drawImage(image, (int)getX(), (int)getY(), image.getWidth(), image.getHeight(), null);
	 
	        // Move the x position left for next time
	        this.x -= 1;
	        
	 
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

		public double getWidth() {
			// TODO Auto-generated method stub
			return (double)this.image.getWidth();
		}
	 
}
