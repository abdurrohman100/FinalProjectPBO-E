package com.fp.spacewar.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.fp.spacewar.main.entity.EntityA;
import com.fp.spacewar.main.entity.EntityB;

public class EntityController {

	private LinkedList<EntityA> entityAList = new LinkedList<EntityA>();
	private LinkedList<EntityB> entityBList = new LinkedList<EntityB>();
	Game game;
	boolean sudahkeluar;
	
	Texture tex;
	EntityA tempEntityA;
	EntityB tempEntityB;
	
	int aggresivePoint;
	
	Random random = new Random();
	
	public EntityController() {
		// TODO Auto-generated constructor stub
	}
	
	public EntityController(Texture tex,Game game){
		this.tex=tex;
		
		this.game=game;
		createEnemy(4);
	}
	
	public void createEnemy(int enemy_count) { //spawn
		
//			System.out.println("game tiuem"+game.gameTime);
		for(int i=0;i<enemy_count;i++) {
			addEntity(new Army(random.nextInt(1600 - 1250 + 1) + 1250, random.nextInt(600), tex,game,this));
		}
		
	}
	public void tick() {
		compAggresivePoint();
		
		if(game.gameTime%10 == 0)
			sudahkeluar = true;
		if((game.gameTime-1) % 10 == 0 && game.gameTime != 0 && sudahkeluar == true)  {
				createEnemy(totalSpawnPer10sec());
				sudahkeluar = false;
		}
		
		
		
		//A Class
		for(int i=0;i<entityAList.size();i++) {
			tempEntityA= entityAList.get(i);
			tempEntityA.tick();
		}
		//B Class
		for(int i=0;i<entityBList.size();i++) {
			tempEntityB= entityBList.get(i);
			tempEntityB.tick();
		}
		

	}
	public void render(Graphics g) {
		//A class
		for(int i=0;i<entityAList.size();i++) {
			tempEntityA= entityAList.get(i);
			tempEntityA.render(g);
		}
		//B class
		for(int i=0;i<entityBList.size();i++) {
			tempEntityB= entityBList.get(i);
			tempEntityB.render(g);
		}

	}
	//A class
	public void addEntity(EntityA block) {
		entityAList.add(block);
	}
	public void removeEntity(EntityA block) {
		entityAList.remove(block);
	}
	//B class
	public void addEntity(EntityB block) {
		entityBList.add(block);
	}
	public void removeEntity(EntityB block) {
		entityBList.remove(block);
	}
	public LinkedList<EntityA> getEntityAList() {
		return entityAList;
	}
	public LinkedList<EntityB> getEntityBList() {
		return entityBList;
	}
	
	private int totalSpawnPer10sec() {
		return aggresivePoint*8;
	}
	
	public void compAggresivePoint() {
		  if(game.gameTime <= 120) {
		   this.aggresivePoint = 1;
		  }else if(game.gameTime <= 220) {
		   this.aggresivePoint = 2;
		  }else if(game.gameTime <= 320) {
		   this.aggresivePoint = 3;
		  }else if(game.gameTime <= 420) {
		   this.aggresivePoint = 4;
		  }else if(game.gameTime <= 520) {
		   this.aggresivePoint = 5;
		  }else {
		   this.aggresivePoint = 6;
		  }
	 }
	
}
