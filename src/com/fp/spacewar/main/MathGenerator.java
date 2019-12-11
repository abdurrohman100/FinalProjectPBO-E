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
	
	public MathGenerator(int aggresivePoint,Game game) {
		this.difficulty = aggresivePoint;
		answer = new ArrayList<String>();
		findAllOperand();
		findOperatorAndResult();
		printString();
	}
	
	private static int randomGenerator(int min, int max) {
		Random random= new Random();  
		return random.nextInt(max - min + 1) + min;
	}
	
//	private static int randomGenerator(double min, double max) {
//		Random random= new Random();  
//		random.nextDouble();
//		random.ne
//		return random.nextInt(max - min + 1) + min;
//	}
	
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
	
	public void printString() {
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
		
		System.out.println(question);
		for(int i=0;i<4;i++) {
			System.out.println(answer.get(i));
			
		}
		System.out.println(trueOption +" "+ resultInt +" "+tempOperator);
//		System.out.println(this.operand1 +" "+this.operator+" "+this.operand2+" = "+this.result);
	}
	
	public void render(Graphics g) {
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
		Font questionFont = new Font("SanSerif", Font.ITALIC, 54);
		g.setFont(questionFont);
		g.setColor(Color.WHITE);
		g.drawString(question, 600, 100);

		for(int i=0;i<4;i++) {
			Graphics2D g2d =(Graphics2D) g;
			g2d.draw(new Rectangle(50+i*300, 150,300,70));
			g.drawString("("+(i+1)+")"+ "." + answer.get(i), 50+i*300, 200);
		}
		//System.out.println(trueOption +" "+ resultInt +" "+tempOperator);

	}
	public boolean cekTrue(int submited) {
		if(trueOption==submited) {
			return true;
		}
		else {
			return false;
		}
	}
}
