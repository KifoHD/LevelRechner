package de.kifo.calculator;

public class LevelUpXp {
	

		private int a;
		private int b;
		private int c;
		private int d;
		public int e;
		
		int startCalculation(int wantedLevel, int currentLevel) {
			a = wantedLevel;
			b = currentLevel;
			
			a = a-1;
			b = b-1;
			
			c = ((a*(a+1)) /2) * 1000;
			d = ((b*(b+1)) /2) * 1000;
			
			e = c-d;
			
			return e;
		}
}
