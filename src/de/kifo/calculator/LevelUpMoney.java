package de.kifo.calculator;

public class LevelUpMoney {
	
	private int a;
	private int b;
	private double c;
	private double d;
	private double e;
	
	int startCalculation(int wantedLevel, int currentLevel) {
		a = wantedLevel-1;
		b = currentLevel-1;
		
		c = ((a*(a+1)) /2) * 187.5;
		d = ((b*(b+1)) /2) * 187.5;
		e = c-d;
		e = Math.floor(e);
		
		return (int)e;
	}
}
