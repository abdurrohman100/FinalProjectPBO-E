package com.fp.spacewar.main;

import java.util.LinkedList;

import com.fp.spacewar.main.entity.*;

public class Physics {
	public static boolean Collision(EntityA entityA, LinkedList<EntityB> entityBList) {
		for(int a=0;a<entityBList.size();a++) {
			if(entityA.getBounds().intersects(entityBList.get(a).getBounds())) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean Collision(EntityB entityB, LinkedList<EntityA> entityAList) {
		for(int a=0;a<entityAList.size();a++) {
			if(entityB.getBounds().intersects(entityAList.get(a).getBounds())) {
				return true;
			}
		}
		return false;
	}

}
