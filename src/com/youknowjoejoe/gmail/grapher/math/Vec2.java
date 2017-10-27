package com.youknowjoejoe.gmail.grapher.math;

public class Vec2 {
	private double x;
	private double y;
	
	public Vec2(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public Vec2(Vec2 i, Vec2 f){
		this.x = f.getX()-i.getX();
		this.y = f.getY()-i.getY();
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public Vec2 plus(Vec2 v){
		return new Vec2(getX()+v.getX(),getY()+v.getY());
	}
	
	public Vec2 scaledBy(double f){
		return new Vec2(f*getX(),f*getY());
	}
	
	public Vec2 minus(Vec2 v){
		return plus(v.scaledBy(-1f));
	}
	
	public double dot(Vec2 v){
		return getX()*v.getX()+getY()*v.getY();
	}
	
	public double crossZ(Vec2 v){
		return getX()*v.getY()-getY()*v.getX();
	}
	
	public double magnitudeSquared(){
		return dot(this);
	}
	
	public double magnitude(){
		return (float) Math.sqrt(magnitudeSquared());
	}
	
	@Override
	public String toString(){
		return "<"+getX()+","+getY()+">";
	}
	
	public static boolean intersect(Vec2 a, Vec2 b, Vec2 c, Vec2 d){
		Vec2 ab = b.minus(a);
		Vec2 cd = d.minus(c);
		double abc = c.minus(a).crossZ(ab);
		double abd = d.minus(a).crossZ(ab);
		double cda = a.minus(c).crossZ(cd);
		double cdb = b.minus(c).crossZ(cd);
		return ((abc <= 0 && abd >= 0) || (abc >= 0 && abd <= 0)) && ((cda <= 0 && cdb >= 0) || (cda >= 0 && cdb <= 0));
	}
}