package com.youknowjoejoe.gmail.grapher;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;

import com.youknowjoejoe.gmail.grapher.functions.Function;
import com.youknowjoejoe.gmail.grapher.math.Vec2;
import com.youknowjoejoe.gmail.grapher.math.Vec2i;

public class Grapher {
	
	private Vec2i dim;
	private Vec2 xRange, yRange;
	private double xScale, yScale, xFrequency;
	
	private double xDistance,yDistance;
	
	private static Stroke stroke = new BasicStroke(2.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
	private static Stroke thickStroke = new BasicStroke(4.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
	
	public Grapher(Vec2i dim, Vec2 xRange, Vec2 yRange, double xScale, double yScale, double xFrequency){
		this.dim = dim;
		this.xRange = xRange;
		this.yRange = yRange;
		this.xScale = xScale;
		this.yScale = yScale;
		this.xFrequency = xFrequency;
		
		this.xDistance = xRange.getY()-xRange.getX();
		this.yDistance = yRange.getY()-yRange.getX();
	}
	
	public void drawAxis(Graphics2D g2d){
		g2d.setColor(Color.black);
		g2d.setStroke(thickStroke);
		
		//origin in screen coords, top left and bottom right pts in model coords
		Vec2i pt = getP(new Vec2(0,0));
		Vec2 pt1 = getR(new Vec2i(0,0));
		Vec2 pt2 = getR(dim);
		
		//length of tick marks
		int length = (int)((dim.getX()+dim.getY())/(2.0*128.0));
		
		//draw axis
		g2d.setStroke(thickStroke);
		if(pt1.getY()>=0 && pt2.getY()<=0){
			g2d.drawLine(0, pt.getY(), dim.getX(), pt.getY());
			
			double start = Math.ceil(pt1.getX()/xScale)*xScale;
			double end = Math.floor(pt2.getX()/xScale)*xScale;
			for(double x = start; x <= end+xScale/2.0; x+=xScale){
				Vec2i mp = getP(new Vec2(x,0));
				g2d.drawLine(mp.getX(), mp.getY()-length, mp.getX(), mp.getY()+length);
			}
		}
		if(pt1.getX()<=0 && pt2.getX()>=0){
			g2d.drawLine(pt.getX(), 0, pt.getX(), dim.getY());
			
			double start = Math.ceil(pt2.getY()/yScale)*yScale;
			double end = Math.floor(pt1.getY()/yScale)*yScale;
			for(double y = start; y <= end+yScale/2.0; y+=yScale){
				Vec2i mp = getP(new Vec2(0,y));
				g2d.drawLine(mp.getX()-length, mp.getY(), mp.getX()+length, mp.getY());
			}
		}
		
	}
	
	public GraphInfo drawFunction(Graphics2D g2d, Function f){
		List<SignificantIntercept> intercepts = new ArrayList<SignificantIntercept>();
		
		g2d.setStroke(stroke);
		
		Vec2 ri = getR(f,-1);
		Vec2i pi = getP(ri);
		
		boolean positivei = ri.getY()>0;
		
		for(int i = 0; i < xFrequency; i++){
			Vec2 r = getR(f,i);
			Vec2i p = getP(r);
			boolean positive = r.getY()>0;
			
			boolean fakeNews = false;
			if(positive != positivei){
				SignificantIntercept intercept = findIntercept(positive,f,ri,r);
				if(intercept != null) intercepts.add(intercept);
				else fakeNews = true;
			}
			
			if(!fakeNews){
				g2d.drawLine(pi.getX(), pi.getY(), p.getX(), p.getY());
			}
			
			ri = r;
			pi = p;
			positivei = positive;
		}
		
		return new GraphInfo(intercepts);
	}
	
	private static final int iterations = 10;
	
	private SignificantIntercept findIntercept(boolean startsPositive, Function f, Vec2 r1, Vec2 r2){
		double x1 = r1.getX();
		double x2 = r2.getX();
		for(int i = 0; i < iterations; i++){
			double x = (x1+x2)/2.0;
			double y = Math.abs(f.evaluate(x));
			double y1 = Math.abs(f.evaluate(x1));
			double y2 = Math.abs(f.evaluate(x2));
			if(y-y1 > 0 && y-y2 > 0) return null;
			else if(y-y1 < y-y2){
				x1 = x;
			}
			else if(y-y2 < y-y1){
				x2 = x;
			}
		}
		return new SignificantIntercept((x1+x2)/2.0,!startsPositive);
	}
	
	private Vec2 getR(Function f, double i){
		double rx = (i/xFrequency)*xDistance+xRange.getX();
		double ry = f.evaluate(rx);
		
		return new Vec2(rx,ry);
	}
	
	private Vec2 getR(Vec2i p){
		double px = p.getX();
		double py = p.getY();
		
		double rx = xDistance*(px/((double)dim.getX()))+xRange.getX();
		double ry = yDistance*(1-py/((double)dim.getY()))+yRange.getX();
		
		return new Vec2(rx,ry);
	}
	
	private Vec2i getP(Vec2 r){
		
		int px = (int)((((r.getX()-xRange.getX())/xDistance) * dim.getX()));
		int py = (int)(((1-((r.getY()-yRange.getX())/yDistance))*dim.getY()));
		
		return new Vec2i(px,py);
	}
}
