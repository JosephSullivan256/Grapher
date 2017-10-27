package com.youknowjoejoe.gmail.grapher;

import java.util.List;

public class GraphInfo {
	private List<SignificantIntercept> intercepts;
	
	public GraphInfo(List<SignificantIntercept> intercepts){
		this.intercepts = intercepts;
	}

	public List<SignificantIntercept> getIntercepts() {
		return intercepts;
	}
	
	@Override
	public String toString(){
		String txt = "Graph Info:\n";
		for(SignificantIntercept si: intercepts){
			txt+=si.toString() + "\n";
		}
		return txt;
	}
}
