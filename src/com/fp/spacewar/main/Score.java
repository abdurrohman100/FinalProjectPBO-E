package com.fp.spacewar.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

public class Score implements Serializable{
    private int score;
    private String nama="Tanpa Nama";
    
    public Score(int score) {
    	this.score=score;
    }
    public int getScore() {
        return score;
    }

    public String getName() {
        return nama;
    }

    public Score(String nama, int score) {
        this.score = score;
        this.nama = nama;
    }

	
}
