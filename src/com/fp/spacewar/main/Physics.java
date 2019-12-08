package com.fp.spacewar.main;

import java.util.LinkedList;

import com.fp.spacewar.main.entity.*;

public class Physics {
	public static boolean Collision(GameObject entityA, LinkedList<GameObject> entityBList) {
		for(int a=0;a<entityBList.size();a++) {
			if(entityA.getBounds().intersects(entityBList.get(a).getBounds())) {
				return true;
			}
		}
		return false;
	}
	

}

