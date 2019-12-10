package com.fp.spacewar.main;

import java.util.Random;
import java.util.Stack;

public class Soal {
	
	int operatorCount;
	int operandCount;
	Random random = new Random();
	Stack<Integer> operand;
	Stack<Character> operator;
	String soal;
	
	public void soal() {
		this.operatorCount= 1;
		this.operandCount= 2;
		
	}
	public void makeSoal() {
		this.operatorCount= random.nextInt(3)+1;
		this.operandCount= operatorCount+1;
		for(int i=0;i<operatorCount;i++) {
			Random operatorRand = new Random();
			operatorRand.nextInt(3);
			switch (operatorRand.nextInt()) {
			case 0:
				operator.add('+');
				break;
			case 1:
				operator.add('x');
				break;
			case 2:
				operator.add('-');
				break;
			default:
				break;
			}
		}
	}
	
	

}
