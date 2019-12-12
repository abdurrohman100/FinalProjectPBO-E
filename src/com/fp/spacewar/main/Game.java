package com.fp.spacewar.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import com.fp.spacewar.main.entity.EntityA;

public class Game extends Canvas implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int w =1280;
	public static final int h =700;
	public final static String title = "Space Impact";
	private boolean running= false, tidakJawab;
	private boolean scoredSubmitted= false;
	private Thread thread,waktu;
	public TimerSendiri pewaktu;
	public long gameTime;
	private int totalScore;
	private BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet=null;
	private Player player;
	private Texture tex;
	private boolean isShooting=false;
	private Background background1;
	private Background background2;
	private EntityController entityController;
	private Help help = new Help();
	private Menu myMenu;
	private ScoreManager myScoreManager;
	public static GameState currentGameState;
	public LinkedList<EntityA> entityAList;
	private long timeSoal;
	private boolean adaSoal= false;
	public static enum GameState{
		IN_MENU,
		IN_PLAY,
		IN_GAMEOVER,
		IN_HOF,
		IN_HELP

	}

	private Sound bgmMenu = new Sound("mainmenu.wav");
	private Sound bgmHOF = new Sound("HallofFame.wav");
	private Sound bgmPlay = new Sound("ingame.wav");
	private Sound bgmGameOver = new Sound("gameovverwav.wav");
	private Sound shoot = new Sound("shoot.wav");
	private MathGenerator mg;

	
	public Game() {
		this.setPreferredSize(new Dimension (w,h));
		this.setMaximumSize(new Dimension (w,h));
		this.setMinimumSize(new Dimension (w,h));
		
	}
	
	public void init() {
		requestFocus();
		BufferedImageLoader loader= new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("res/asset6.png");
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		currentGameState=GameState.IN_MENU;
		scoredSubmitted=false;
		running=true;
		adaSoal=false;
		addKeyListener(new KeyInput(this));
		addMouseListener(new MouseAction(this));
		tex= new Texture(this);
		player = new Player(100,360,tex,this);
		background1 = new Background();
		background2 = new Background(background1.getWidth(),0,"res/bg-Recovered.png");
		myMenu = new Menu();
		myScoreManager = new ScoreManager(this);
		entityController = new EntityController(tex,this);
		bgmPlay.setVolume(0.21F);
		bgmGameOver.setVolume(1F);
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
		final double ammountOfTick = 90.0;
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
			
			if((pewaktu.getTime() % 15) == 0 && pewaktu.getTime() !=  0&& currentGameState==GameState.IN_PLAY) {
				System.out.println();
				mg = new MathGenerator(entityController.getAggresivePoint(), this);
				adaSoal=true;
				timeSoal = pewaktu.getTime();
				tidakJawab = false;
			}
			if(timeSoal + 5 == pewaktu.getTime() && tidakJawab == false) {
				adaSoal = false;
				player.healthPoint-=2*entityController.getAggresivePoint();
				tidakJawab = true;
			}

		
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
		if(currentGameState==GameState.IN_PLAY) {
			bgmHOF.stop();
			bgmMenu.stop();
			bgmGameOver.stop();
			if(!bgmPlay.isRunning()) {
				
				bgmPlay.loop();
			}
			background1.drawScrollingBG(g);
			background2.drawScrollingBG(g);
			entityController.render(g);
			player.render(g);
			myScoreManager.render(g);
			if(adaSoal) {
				
				mg.render(g);
				
			}
			
		}if(currentGameState==GameState.IN_HOF) {
			bgmGameOver.stop();
			bgmPlay.stop();
			bgmMenu.stop();
			if(!bgmHOF.isRunning()) {
				bgmHOF.loop();
			}
			myScoreManager.renderHOF(g);
		}if(currentGameState==GameState.IN_MENU) {
			bgmPlay.stop();
			bgmHOF.stop();
			bgmGameOver.stop();
			if(!bgmMenu.isRunning()) {
				bgmMenu.loop();
			}
			myMenu.render(g);
		}if(currentGameState==GameState.IN_GAMEOVER) {
			bgmPlay.stop();
			bgmHOF.stop();
			bgmMenu.stop();
			bgmGameOver.play();
			drawGameOver(g);
		}if(currentGameState==GameState.IN_HELP) {
			help.render(g);
			
			
		}
		
		
		

		///////////////////////////////
		g.dispose();
		bs.show();
	}
	private void tick() {
		// TODO Auto-generated method stub
		
		if(currentGameState==GameState.IN_PLAY) {
			player.tick();
			entityController.tick();
			totalScore=(int) (player.getScore()+pewaktu.getTime());
			myScoreManager.tick();
		}
	
	}
	public void keyPressed(KeyEvent e) {
		int k= e.getKeyCode();
		if(currentGameState==GameState.IN_PLAY) {
			if(k==KeyEvent.VK_RIGHT) {
				player.setVelX(4);
			}else if(k==KeyEvent.VK_LEFT) {
				player.setVelX(-4);
			}else if(k==KeyEvent.VK_DOWN) {
				player.setVelY(2.5);
			}else if(k==KeyEvent.VK_UP) {
				player.setVelY(-2.5);
			}else if(k==KeyEvent.VK_SPACE && !isShooting) {
				
//				//lastShooting=
				entityController.addBullet(new Bullet(player.getX()+60, player.getY()+25, tex,this));	
				shoot.play();
				shoot.click.setFramePosition(0);
				isShooting=true;
			}else if(k==KeyEvent.VK_ENTER) {
				mg = new MathGenerator(entityController.getAggresivePoint(), this);
				adaSoal=true;
			}
			if(adaSoal) {
				int submitedAnswer=0;
				if(k==KeyEvent.VK_1) {
					submitedAnswer=1;
					adaSoal=false;
				}else if(k==KeyEvent.VK_2) {

						submitedAnswer=2;
						adaSoal=false;

				}else if(k==KeyEvent.VK_3) {
	
						submitedAnswer=3;
						adaSoal=false;
				
				}else if(k==KeyEvent.VK_4) {

						submitedAnswer=4;
						adaSoal=false;
					
					
				}
				mg.cekTrue(submitedAnswer);
				submitedAnswer=0;
			}
		}
		if(currentGameState==GameState.IN_GAMEOVER) {
			if(k==KeyEvent.VK_SPACE) {
				if(!scoredSubmitted) {
					myScoreManager.addScore(JOptionPane.showInputDialog("Input Your Name"), totalScore);
					scoredSubmitted=true;
				}	
			}else if(k==KeyEvent.VK_ESCAPE) {
				currentGameState=GameState.IN_MENU;
				init();
			}
			
		}

		
		
	}
	public void keyReleased(KeyEvent e) {
		int k= e.getKeyCode();
		if(currentGameState==GameState.IN_PLAY) {
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
		//System.out.println(player.getX() +" "+ player.getY());

		
		
	}
 
	public Player getPlayer() {
		return this.player;
	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public void drawGameOver(Graphics g) {
		Font title = new Font("SanSerif", Font.BOLD,66);
		g.setFont(title);
		g.setColor(Color.WHITE);
		g.drawString("GameOver", Game.w/2-150, 100);
		Font word = new Font("SanSerif", Font.BOLD,46);
		g.setFont(word);
		String stringScore = " "+totalScore;
		g.drawString("Your score is"+stringScore , Game.w/2-150, 200);
		if(myScoreManager.isTopTen(totalScore)) {
			if(totalScore>=myScoreManager.getCurrentHS()) {
				g.drawString("You got a new HighScore", Game.w/2-150, 250);
				
			}
			else {
				g.drawString("You rank in top 10", Game.w/2-150, 250);
			}
			
			if(!scoredSubmitted) {
				g.drawString("Please press space to input your name", Game.w/2-150, 300);
			}	
		}
		g.drawString("Please Esc to return to main menu", Game.w/2-150, 400);
		
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public long getGameTime() {
		return gameTime;
	}

	public void setGameTime(long gameTime) {
		this.gameTime = gameTime;
	}

	public TimerSendiri getPewaktu() {
		return pewaktu;
	}

	public void setPewaktu(TimerSendiri pewaktu) {
		this.pewaktu = pewaktu;
	}

	public EntityController getEntityController() {
		return entityController;
	}
	
	
}