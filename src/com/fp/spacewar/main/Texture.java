package com.fp.spacewar.main;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Texture {

		public ArrayList<BufferedImage> player,bullet,enemy,boss;
		private SpriteSheet ss;
		/**
		 * @param game Untuk mendapatkan spritesheet
		 */
		public Texture(Game game) {
			ss = new SpriteSheet(game.getSpriteSheet());
			getTextureAll();
			
		}
		/**
		 * Ambil semua gambar untuk etaip object dan jadikan sebuah arraylist of image
		 */
		private void getTextureAll() {
			player=ss.grabSprite(0, 0, 50, 50, 20);
			enemy=ss.grabSprite(0, 50, 40, 40, 3);
			bullet=ss.grabSprite(0, 122,10,3,1);
			boss=ss.grabSprite(0, 125+32, 250, 250, 4);
		}
		
}
