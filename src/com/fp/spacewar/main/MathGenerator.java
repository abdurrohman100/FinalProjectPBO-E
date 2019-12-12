package com.fp.spacewar.main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import com.fp.spacewar.main.Game;

public class MathGenerator{
	int tempOperator, operand1, operand2, trueOption,resultInt;
	double result;
	String operator,question ;
	ArrayList<String> answer;
	int difficulty=1;
	DecimalFormat df = new DecimalFormat("###.##");
	Game game;
	public MathGenerator(int aggresivePoint,Game game) {
		this.difficulty = aggresivePoint;
		answer = new ArrayList<String>();
		findAllOperand();
		findOperatorAndResult();
		init();
		this.game=game;
	}
	
	private static int randomGenerator(int min, int max) {
		Random random= new Random();  
		return random.nextInt(max - min + 1) + min;
	}

	private void findAllOperand() {
		operand1 = randomGenerator(2*(difficulty), 15*difficulty);
		operand2 = randomGenerator(2*(difficulty), 15*difficulty);
	}
	
	private void findOperatorAndResult() {
		this.tempOperator =  randomGenerator(1, 3);
		if(tempOperator == 1) {
			this.operator = "+";
			this.resultInt = operand1 + operand2;
		} else if(tempOperator == 2) {
			this.operator = "-";
			this.resultInt = operand1 - operand2;
		} else if(tempOperator == 3) {
			this.operator = "x";
			this.resultInt = operand1 * operand2;
		}			
	}
	
	public void init() {
		question = (Integer.toString(operand1).concat(" " +operator+ " "+operand2 + " ="));
		trueOption = randomGenerator(1, 4);
		
		for (int i = 0; i <4;i++) {
			if((i+1) == trueOption) {
				this.answer.add(Integer.toString(resultInt));
			}
			else {
				
				int tempResult = randomGenerator(this.resultInt-20, this.resultInt+20);
				if(tempResult == resultInt) {
					tempResult += 1;
				}
				this.answer.add(Integer.toString(tempResult));
			}		
		}

		for(int i=0;i<4;i++) {
			System.out.println(answer.get(i));
			
		}
	}
	
	public void render(Graphics g) {
		Font questionFont = new Font("SanSerif", Font.ITALIC, 54);
		g.setFont(questionFont);
		g.setColor(Color.WHITE);
		g.drawString(question, 550, 100);

		for(int i=0;i<4;i++) {
			g.drawString("("+(i+1)+")"+ "." + answer.get(i), 150+i*300, 200);
		}
	}
	public void cekTrue(int submited) {
		System.out.println("jawaban"+trueOption + " input"+submited);
		if(trueOption==submited&&submited!=0) {
			game.getPlayer().setScore(game.getPlayer().getScore()+20);
		}
		else if (trueOption!=submited&&submited!=0) {
			System.out.println("difficilty"+difficulty);
			game.getPlayer().healthPoint-= 2 * difficulty;
		}
	}
}
