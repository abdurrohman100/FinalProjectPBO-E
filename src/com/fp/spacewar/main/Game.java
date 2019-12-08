package com.fp.spacewar.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Timer;

import javax.swing.JFrame;

//import com.fp.spacewar.main.entity.Controller;
//import com.fp.spacewar.main.entity.EnemyController;
import com.fp.spacewar.main.entity.EntityA;
import com.fp.spacewar.main.entity.EntityB;

public class Game extends Canvas implements Runnable {
	public static final int w =1280;
	public static final int h =720;
	public final static String title = "Space Impact";
	private boolean running= false;
	private Thread thread,waktu;
	public TimerSendiri pewaktu;
	public long gameTime;
	
	private BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet=null;
	private Player player;
	private Texture tex;
	//private Controller bulletController;
	private boolean isShooting=false;
	private Background background1;
	private Background background2;
	//private EnemyController enemyController;
	private EntityController entityController;

	public LinkedList<EntityA> entityAList;
	public LinkedList<EntityB> entityBList;
	
	public Game() {
		this.setPreferredSize(new Dimension (w,h));
		this.setMaximumSize(new Dimension (w,h));
		this.setMinimumSize(new Dimension (w,h));
		
	}

	public void init() {
		requestFocus();
		BufferedImageLoader loader= new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("SpriteSheet.png");
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		addKeyListener(new KeyInput(this));
		//myTimer = new javax.swing.Timer(timerDelay, arg1)
		tex= new Texture(this);
		player = new Player(100,360,tex);
		//bulletController = new Controller(this);
		background1 = new Background();
		background2 = new Background(2001,0);
		//enemyController = new EnemyController(tex);
		entityController = new EntityController(tex,this);
		entityAList = entityController.getEntityAList();
		entityBList = entityController.getEntityBList();
	
	}
	
	public synchronized void start() {
		if(running) return;
		running= true;
		thread= new Thread(this);
		thread.start();
		
		waktu = new Thread(pewaktu = new TimerSendiri());
		waktu.start();
		
	}
	public synchronized void stop() {
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
			gameTime = pewaktu.getTime(); 
//			System.out.println("wakltu skregadsasdsa"+ gameTime);
			long now=System.nanoTime();
//			System.out.println("Score "+ player.getScore());
			
			delta+=(now-lastTime)/ns;
			lastTime=now;
			if(delta>=1) {
				tick();
				updates++;
				delta--;
			}
//			
		
			render();
			frames++;
			//System.out.println("WOrking");
			if(System.currentTimeMillis()-timer>1000) {
				timer+=1000;
				System.out.println(updates + "Ticks, FPS"+ frames);
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
		background1.draw(g);
		background2.draw(g);
		//enemyController.render(g);
		entityController.render(g);
		player.render(g);
		//bulletController.render(g);
		
		///////////////////////////////
		g.dispose();
		bs.show();
	}
	private void tick() {
		// TODO Auto-generated method stub
		player.tick();
		//enemyController.tick();
		//bulletController.tick();
		entityController.tick();
	
	}
	public void keyPressed(KeyEvent e) {
		int k= e.getKeyCode();
		//System.out.println(player.getX() +" "+ player.getY());
		if(k==KeyEvent.VK_RIGHT) {
			player.setVelX(6);
		}else if(k==KeyEvent.VK_LEFT) {
			player.setVelX(-6);
		}else if(k==KeyEvent.VK_DOWN) {
			player.setVelY(3);
		}else if(k==KeyEvent.VK_UP) {
			player.setVelY(-3);
		}else if(k==KeyEvent.VK_SPACE && !isShooting) {
			isShooting=true;
			entityController.addEntity(new Bullet(player.getX(), player.getY(), tex,this));		
		}
		
	}
	public void keyReleased(KeyEvent e) {
		int k= e.getKeyCode();

		//System.out.println(player.getX() +" "+ player.getY());
		if(k==KeyEvent.VK_RIGHT) {
			player.setVelX(0);
		}else if(k==KeyEvent.VK_LEFT) {
			player.setVelX(0);
		}else if(k==KeyEvent.VK_DOWN) {
			player.setVelY(0);
		}else if(k==KeyEvent.VK_UP) {
			player.setVelY(0);
		}else if(k==KeyEvent.VK_SPACE) {
			isShooting=false;
		}
		
		
	}
 


	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

}