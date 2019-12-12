package com.fp.spacewar.main;
import java.io.Serializable;

public class Score implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
