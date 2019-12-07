package com.fp.spacewar.main.entity;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.fp.spacewar.main.Enemy;
import com.fp.spacewar.main.Texture;

public class EnemyController{
	private LinkedList<Enemy> enemyList = new LinkedList<Enemy>();
	private int gameTime;
	private Enemy tempEnemy;
	private static Random random = new Random();  
	Texture tex;
	public EnemyController(Texture tex){
		this.tex=tex;
        addEnemy(new Enemy(1300 ,random.nextDouble(),tex,game,this));
        
	}
	public void tick() {
		for(int i=0;i<enemyList.size();i++) {
			tempEnemy= enemyList.get(i);
			if(tempEnemy.getX()<0)
				//removeEnemy(tempEnemy);
				tempEnemy.setX(1290);
			tempEnemy.tick();
		}
	}
	public void render(Graphics g) {
		System.out.println(enemyList.size());
		for(int i=0;i<enemyList.size();i++) {
			tempEnemy= enemyList.get(i);
			tempEnemy.render(g);
		}
	}
	public void addEnemy(Enemy block) {
		enemyList.add(block);
	}
	public void removeEnemy(Enemy block) {
		enemyList.remove(block);
	}
	public int getGameTime() {
		return gameTime;
	}

	public void setGameTime(int gameTime) {
		this.gameTime = gameTime;
	}

	public static int getRandomInt(int min, int max){

		return random.nextInt(max - min + 1) + min;
	 }
	public void spawn() {
		// TODO Auto-generated method stub
		this.addEnemy(new Enemy((double)800,(double)getRandomInt(5, 595),tex));
		
	}


	
}
