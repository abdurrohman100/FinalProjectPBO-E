package com.fp.spacewar.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import com.fp.spacewar.main.Game.GameState;
public class ScoreManager {
	private ArrayList<Score> scores;
	Game game;
	// The name of the file where the highscores will be saved
    private static final String HIGHSCORE_FILE = "scores.dat";
    private int activeScore;
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;
    
    public ScoreManager(Game game) {
    	 scores = new ArrayList<Score>();
    	 this.game=game;
    	 this.activeScore=game.getTotalScore();
    	 
    }
    //getTop10
    public ArrayList<Score> getScores() {
        loadScoreFile();
        sort();
        return scores;
    }
    //adding score
    public void addScore(String name, int score) {
    	System.out.println(name+" "+score);
        loadScoreFile();
        scores.add(new Score(name, score));
        updateScoreFile();
    }
    //sorting arraylist
    private void sort() {
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(scores, comparator);
    }
    //adding score
    public void removesScore() {
        loadScoreFile();
        scores.removeAll(scores);
        updateScoreFile();
    }
    //load file
    public void loadScoreFile() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            scores = (ArrayList<Score>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("[Laad] FNF Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[Laad] IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("[Laad] CNF Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Laad] IO Error: " + e.getMessage());
            }
        }
        
    }
    public void updateScoreFile() {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
            outputStream.writeObject(scores);
        } catch (FileNotFoundException e) {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("[Update] IO Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }
    }
    //get highest score
    public int getCurrentHS() {
    	if(scores.isEmpty()) {
    		return 0;
    	}else {
    		return scores.get(0).getScore();
    	}
    }
    public int getLastTen() {
    	int scoresSize =scores.size();
    	if(scoresSize>10)scoresSize=10;
    	if(scores.isEmpty()) {
    		return 0;
    	}else {
    		return scores.get(scoresSize-1).getScore();
    	}
    }
    public boolean isTopTen(int score) {
    	if(scores.size()<10) {
    		return true;
    	}else {
    		if(score>this.getLastTen()) {
    			return true;
    		}
    	}
		return false;
    }
    public void render(Graphics g) {
	    	Font title = new Font("SanSerif", Font.BOLD,36);
			g.setFont(title);
    		g.setColor(Color.BLUE);	
        	g.drawRect(0, 0, 200, 50);
        	String print = ""+ this.activeScore;
        	g.drawString(print, 5, 40);
    }
    public void renderHOF(Graphics g) {
    	
    	loadScoreFile();
    	//removesScore();
    	sort();
    	//g.drawRect(0, 0, 1280, 720);
		Graphics2D g2d =(Graphics2D)g;
		Font title = new Font("SanSerif", Font.BOLD,66);
		g.setFont(title);
		g.setColor(Color.WHITE	);
		g.drawString("Hall of Shame", Game.w/2-150, 100);
		Font back = new Font("SanSerif", Font.BOLD,20);
		g.setFont(back);
		g2d.draw(new Rectangle(20,50,60,30));
		g.drawString("Back", 25, 70);
		Font optMenu = new Font("SanSerif", Font.BOLD,46);
		g.setFont(optMenu);
		int jumlahdisplay;
		if(scores.size()<10)jumlahdisplay=scores.size();
		else jumlahdisplay=10;
		for(int i=0;i<jumlahdisplay;i++) {
			String cetak="";
			if(scores.size()!=0) {
				Score temp= scores.get(i);
				cetak=i+1 + " " + temp.getName()+" ";
				if(temp!=null) {
					cetak= cetak +""+ temp.getScore();
				}
			}
			
			
			//g2d.draw(new Rectangle(Game.w/2-100,150+i*50,200,50));
			g.drawString(cetak, Game.w/2-100, 150+i*50+55);
		}
    }

    public void tick() {
    	activeScore=game.getTotalScore();
    }

	
	
}
