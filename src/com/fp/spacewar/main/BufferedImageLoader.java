package com.fp.spacewar.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	private BufferedImage image;
	
	/**
	 * @param path relative path dari file image yang ingin diload
	 * @return berupa image
	 * @throws IOException jika gagal meread file maka keluarkan IOException
	 */
	public BufferedImage loadImage(String path) throws IOException{
		image = ImageIO.read(getClass().getResource(path));
		return image;
	}
 
}
