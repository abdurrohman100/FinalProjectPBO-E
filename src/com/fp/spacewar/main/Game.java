package com.fp.spacewar.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	public static final int w =1280;
	public static final int h =720;
	public final static String title = "Space Impact";
	private boolean running= false;
	private Thread thread;
	private BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet=null;
	private BufferedImage background=null;
	private Player p;
	private Controller c;
	private boolean isShooting=false;
	public Game() {
		
	}
	private BufferedImage player;
	
	public void init() {
		requestFocus();
		BufferedImageLoader loader= new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("SpriteSheet.png");
			background = loader.loadImage("Background.png");
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		addKeyListener(new KeyInput(this));
		
		p = new Player(100,360,this);
		c = new Controller(this);
		
		//player = ss.grabImage(1, 1, 50, 50);
	}
	
	private synchronized void start() {
		if(running) return;
		running= true;
		thread= new Thread(this);
		thread.start();
	}
	private synchronized void stop() {
		if(!running) return;
		running= false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(1);
	}
	public void run() {
		// TODO Auto-generated method stub
		init();
		long lastTime = System.nanoTime();
		final double ammountOfTick = 60.0;
		double ns=1000000000/ammountOfTick;
		double delta=0;
		int updates=0;
		int frames=0;
		long timer=System.currentTimeMillis();
		while(running) {
			long now=System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			if(delta>=1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			//System.out.println("WOrking");
			if(System.currentTimeMillis()-timer>1000) {
				timer+=1000;
				//System.out.println(updates + "Ticks, FPS"+ frames);
				updates=0;
				frames=0;
			}
			
		}
		stop();
	}
	private void render() {
		// TODO Auto-generated method stub
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(4);
			return;
		}
		Graphics g =bs.getDrawGraphics();
		///////////////////////////////
		g.drawImage(image, 0,0,getWidth(),getHeight(),this);
		g.drawImage(background, 0, 0, null);
		p.render(g);
		c.render(g);
		
		///////////////////////////////
		g.dispose();
		bs.show();
	}
	private void tick() {
		// TODO Auto-generated method stub
		p.tick();
		c.tick();
	
	}
	public void keyPressed(KeyEvent e) {
		int k= e.getKeyCode();
		System.out.println(p.getX() +" "+ p.getY());
		if(k==KeyEvent.VK_RIGHT) {
			p.setVelX(6);
		}else if(k==KeyEvent.VK_LEFT) {
			p.setVelX(-6);
		}else if(k==KeyEvent.VK_DOWN) {
			p.setVelY(3);
		}else if(k==KeyEvent.VK_UP) {
			p.setVelY(-3);
		}else if(k==KeyEvent.VK_SPACE && !isShooting) {
			isShooting=true;
			c.addBullet(new Bullet(p.getX()+40, p.getY(), this));		
		}
		
	}
	public void keyReleased(KeyEvent e) {
		int k= e.getKeyCode();

		System.out.println(p.getX() +" "+ p.getY());
		if(k==KeyEvent.VK_RIGHT) {
			p.setVelX(0);
		}else if(k==KeyEvent.VK_LEFT) {
			p.setVelX(0);
		}else if(k==KeyEvent.VK_DOWN) {
			p.setVelY(0);
		}else if(k==KeyEvent.VK_UP) {
			p.setVelY(0);
		}else if(k==KeyEvent.VK_SPACE) {
			isShooting=false;
		}
		
		
	}
	public static void main(String args[]) {
		Game game = new Game();
		game.setPreferredSize(new Dimension (w,h));
		game.setMaximumSize(new Dimension (w,h));
		game.setMinimumSize(new Dimension (w,h));
		JFrame frame = new JFrame(title);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		game.start();
	}
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

}
