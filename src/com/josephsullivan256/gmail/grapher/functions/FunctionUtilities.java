package com.josephsullivan256.gmail.grapher.functions;

public class FunctionUtilities {
	
	public enum Endpoint {
		left(0),midpoint(0.5),right(1);
		
		double scale;
		Endpoint(double c){
			this.scale = c;
		}
	}
	
	public static double computeRiemannSum(Function f, double a, double b, int n, Endpoint e){
		double dx = (b-a)/((double) n);
		double x1 = a + dx*e.scale;
		
		double sum = 0;
		for(double i = 0; i < n; i++){
			sum+=f.evaluate(x1+dx*i)*dx;
		}
		
		return sum;
	}
}
