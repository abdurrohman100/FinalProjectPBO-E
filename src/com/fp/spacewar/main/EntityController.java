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
	Texture tex;
	EntityA tempEntityA;
	EntityB tempEntityB;
	Random random = new Random();
	public EntityController(Texture tex,Game game){
		this.tex=tex;
		
		this.game=game;
		createEnemy(4);

	}
	public void createEnemy(int enemy_count) {
		for(int i=0;i<enemy_count;i++) {
			 addEntity(new Enemy(random.nextInt(1400), random.nextInt(600), tex,game,this));
		}
	}
	public void tick() {
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
	
}
