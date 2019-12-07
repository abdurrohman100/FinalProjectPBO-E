package com.fp.spacewar.main;

import java.awt.image.BufferedImage;

public class Texture {

		public BufferedImage player,bullet,enemy;
		private SpriteSheet ss;
		public Texture(Game game) {
			ss = new SpriteSheet(game.getSpriteSheet());
			getTexture();
			
		}
		private void getTexture() {
			player = ss.grabImage(1, 1, 50, 50);
			bullet=ss.grabImage(2, 1, 50,50);	
			enemy=ss.grabImage(3, 1, 50,50);	
		}
}
