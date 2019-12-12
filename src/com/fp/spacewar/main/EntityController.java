package com.fp.spacewar.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import com.fp.spacewar.main.entity.EntityA;

public class EntityController {

	private LinkedList<Army> armyList = new LinkedList<Army>();
	private LinkedList<Bullet> bulletList = new LinkedList<Bullet>();
	private LinkedList<BulletEnemy> bulletEnemyList = new LinkedList<BulletEnemy>();
	private Boss boss;
	private boolean bosMati = true;
	Game game;
	
	boolean sudahkeluar, flagBullet;
	
	Texture tex;
	Army tempEntityA;
	Bullet tempEntityB;
	
	private int aggresivePoint;
	
	Random random = new Random();
	
	public EntityController() {
		
	}
	
	public EntityController(Texture tex,Game game){
		this.tex=tex;
		this.game=game;
		createEnemy(4);
	}
	
	public void createEnemy(int enemy_count) { //spawn
//			System.out.println("game tiuem"+game.gameTime);
		for(int i=0;i<enemy_count;i++) {
			addArmy(new Army(random.nextInt(2000 - 1250 + 1) + 1250,random.nextInt(600),tex,game,this, aggresivePoint));
		}
	}
	
	public void createArmyBullet(Army army) {
		System.out.println("TEMBAK!");
		bulletEnemyList.add(new BulletEnemy(army.x, army.y, tex, game));
//		for(int i = 0;i<)
	}
	
	public void createBossBullet(Boss boss) {
		System.out.println("TEMBAK BOSS!");
		int a = 1;
		for(int i = 0; i<10 && bosMati == false ;i++) {
			bulletEnemyList.add(new BulletEnemy(boss.x, boss.y+(i*10*a), tex, game));
			bulletEnemyList.add(new BulletEnemy(boss.x, boss.y-(i*10*a), tex, game));
			a *= -1;
		}
		
//		for(int i = 0;i<)
	}
	
	public void tick() {
		compAggresivePoint();
		if(boss!=null) {
			boss.tick();
		}
		
		//spawn enemy
		if(game.gameTime%10 == 0)
			sudahkeluar = true;
		if((game.gameTime-1) % 10 == 0 && game.gameTime != 0 && sudahkeluar == true)  {
				createEnemy(totalSpawnPer10sec());
				sudahkeluar = false;
		}
		
		if(game.getTotalScore() % 300 == 0) {
			
		}
				
		//spawn enemy bullet
		if(armyList.size()>0 || boss != null) {
			if((game.gameTime)%(2*(7-aggresivePoint))==0) {
				flagBullet = true;
				
			}
			
			
			
			if(flagBullet && (game.gameTime % (2*(7-aggresivePoint)) ==1)) {
				System.out.println("tembakkk");
				flagBullet = false;
			
				//spawn bullet boss
				if(bosMati == false && boss.getHealtPoint() > 0) {
					if (flagBullet && game.getGameTime() % (15-aggresivePoint) == 0) {
						createBossBullet(boss);
					}
				}
				
				int rand22 = random.nextInt((armyList.size()+1)+0);
				
				rand22 = ((armyList.size()-rand22)*aggresivePoint);
				System.out.println("rand"+rand22);
				if(rand22>armyList.size())
					rand22 = armyList.size();
				System.out.println("rannds22  " + rand22);
				for(int i = armyList.size()-rand22-1;i>=0;i--) {
					createArmyBullet(armyList.get(i));
				}
				System.out.println("size byileira"+ bulletEnemyList.size());
			}
		}
		
		//spawn Boss every score 300
		if(game.getTotalScore() % 300 >= 0 && game.getTotalScore() % 300 <=9  && game.getTotalScore() != 0 && bosMati == true) {
			boss = new Boss(1280, 350, tex, game, this, aggresivePoint);
			bosMati = false;
		}
		
		
		
		if(bosMati == false) {
			collision(boss, bulletList);
			collisionObject(game.getPlayer(),boss);
		}
		//check player get hit by enemy army bullet
		collision(game.getPlayer(), bulletEnemyList);
		//check player crash into enemy army
		collisionObject(game.getPlayer(), armyList);
		//check army enemy killed by player bullet
		collision(armyList, bulletList);
		
//		//check enemy killed
//				Army armyIter = collision(armyList, bulletList);
//				if(armyIter!= null){
//					removeArmy(armyIter);
//				}
		
		//A Class
		for(int i=0;i<armyList.size();i++) {
			tempEntityA= armyList.get(i);
			tempEntityA.tick();
		}
		//B Class
		for(int i=0;i<bulletList.size();i++) {
			bulletList.get(i).tick();
//			tempEntityB.tick();
		}
		
		for(int i=0;i<bulletEnemyList.size();i++) {
			bulletEnemyList.get(i).tick();
		}
	}
	

	public void render(Graphics g) {
		//A class
		for(int i=0;i<armyList.size();i++) {
			tempEntityA= armyList.get(i);
			tempEntityA.render(g);
		}
		//B class
		for(int i=0;i<bulletList.size();i++) {
			tempEntityB= bulletList.get(i);
			tempEntityB.render(g);
		}
		
		for(int i=0;i<bulletEnemyList.size();i++) {
			bulletEnemyList.get(i).render(g);
		}
		if(bosMati == false &&boss.getHealtPoint()>0) {
			boss.render(g);			
		}
	}
	//A class
	public void addArmy(Army army) {
		armyList.add((Army) army);
	}
	public void removeArmy(Army army) {
		armyList.remove((Army)army);
	}
	//B class
	public void addBullet(Bullet block) {
		bulletList.add((Bullet)block);
	}
	public void removeEntity(Bullet block) {
		bulletList.remove((Bullet)block);
	}
	
	public void addBulletEnemy(BulletEnemy block) {
		bulletEnemyList.add((BulletEnemy)block);
	}
	
	//checkkilled enemy
	public void collision(LinkedList<Army> armyList2, LinkedList<Bullet> bulletList2) {
		for(int a=0;a<bulletList2.size();a++) {
			for(int b=0; b<armyList2.size();b++) {
				if((armyList2.get(b).getBounds()).intersects(bulletList2.get(a).getBounds())) {
					game.getPlayer().setScore(game.getPlayer().getScore()+2);
					armyList2.remove(b);
				}
			}
		}
	}
	
	//enemy bullet hit player
	public void collision(Player player, LinkedList<BulletEnemy> bulletList2) {
		for(int a=0;a<bulletList2.size();a++) {
//			System.out.println("nanana");
			if((player.getBounds()).intersects(bulletList2.get(a).getBounds())) {
				System.out.println("deavg HOP" + player.healthPoint);
				bulletList2.remove(a);
				player.reduceHP(2*aggresivePoint);
			}
		}
	}

	public void collision(Boss boss2, LinkedList<Bullet> bulletList2) {
		for(int a=0;a<bulletList2.size() && (bosMati == false);a++) {
			if((boss2.getBounds()).intersects(bulletList2.get(a).getBounds())) {
				game.getPlayer().setScore(game.getPlayer().getScore()+2);
				boss2.setHealtPoint(boss2.getHealtPoint()-25);
				bulletList2.remove(a);
				System.out.println("Boss HIt");
				System.out.println(boss.getHealtPoint());
				if(boss.getHealtPoint()<=0) {
					bosMati = true;	
					System.out.println("MATI KAU");
					if(bosMati == true)
						game.getPlayer().setScore(game.getPlayer().getScore() + 110);
				}
			}
		}
	}
	
	//enemy hit player
	public void collisionObject(Player player, LinkedList<Army> armylist2) {
		for(int a=0;a<armylist2.size();a++) {
//			System.out.println("nanana");
			if((player.getBounds()).intersects(armylist2.get(a).getBounds())) {
				armylist2.remove(a);
				player.reduceHP(3*aggresivePoint);
			}
		}
	}
	
	public void collisionObject(Player player, Boss boss2) {
		if(boss2 != null &&(player.getBounds()).intersects(boss2.getBounds())) {
			game.getPlayer().healthPoint = 0;
		}
		
	}
	
	
	
	private int totalSpawnPer10sec() {
		return aggresivePoint*8;
	}
	
	public void compAggresivePoint() {
		  if(game.gameTime <= 20) {
		   this.aggresivePoint = 1;
		  }else if(game.gameTime <= 30) {
		   this.aggresivePoint = 2;
		  }else if(game.gameTime <= 50) {
		   this.aggresivePoint = 3;
		  }else if(game.gameTime <= 70) {
		   this.aggresivePoint = 4;
		  }else if(game.gameTime <= 90) {
		   this.aggresivePoint = 5;
		  }else {
		   this.aggresivePoint = 6;
		  }
	 }
	
	public int getAggresivePoint() {
		return aggresivePoint;
	}
	
}
