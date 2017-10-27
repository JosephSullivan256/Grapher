package com.youknowjoejoe.gmail.grapher.functions;

public class RationalFunction implements Function{
	
	private Function P;
	private Function Q;
	
	public RationalFunction(Function P, Function Q){
		this.P = P;
		this.Q = Q;
	}
	
	@Override
	public double evaluate(double x) {
		double pv = P.evaluate(x);
		double qv = Q.evaluate(x);
		if(qv == 0) return Double.NaN;
		return pv/qv;
	}

}
