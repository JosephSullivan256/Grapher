package com.youknowjoejoe.gmail.grapher;

public class SignificantIntercept {
	private double x;
	private boolean positiveToNegative;
	
	public SignificantIntercept(double x, boolean positiveToNegative){
		this.x = x;
		this.positiveToNegative = positiveToNegative;
	}

	public double getX() {
		return x;
	}

	public boolean isPositiveToNegative() {
		return positiveToNegative;
	}
	
	@Override
	public String toString(){
		return "at x=" + x + ", the graph " + (positiveToNegative ? "changes from + to -" : "changes from - to +");
	}
}
