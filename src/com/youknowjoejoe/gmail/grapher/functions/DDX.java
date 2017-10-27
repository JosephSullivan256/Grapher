package com.youknowjoejoe.gmail.grapher.functions;

public class DDX implements Function{
	
	private Function f;
	private double d;
	
	public DDX(Function f, double d){
		this.f = f;
		this.d = d;
	}
	
	@Override
	public double evaluate(double x) {
		double y2 = f.evaluate(x+d);
		double y1 = f.evaluate(x-d);
		if(!Double.isNaN(y2) && !Double.isNaN(y1)) return (y2-y1)/(2*d);
		return Double.NaN;
	}

}
