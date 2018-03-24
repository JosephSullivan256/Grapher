package com.josephsullivan256.gmail.grapher.math;

public class Vec2i {
	private int x;
	private int y;
	
	public Vec2i(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Vec2i(Vec2i i, Vec2i f){
		this.x = f.getX()-i.getX();
		this.y = f.getY()-i.getY();
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Vec2i plus(Vec2i v){
		return new Vec2i(getX()+v.getX(),getY()+v.getY());
	}
	
	public Vec2i scaledBy(int f){
		return new Vec2i(f*getX(),f*getY());
	}
	
	public Vec2i minus(Vec2i v){
		return plus(v.scaledBy(-1));
	}
	
	public int dot(Vec2i v){
		return getX()*v.getX()+getY()*v.getY();
	}
	
	public int crossZ(Vec2i v){
		return getX()*v.getY()-getY()*v.getX();
	}
	
	public int magnitudeSquared(){
		return dot(this);
	}
	
	public double magnitude(){
		return (float) Math.sqrt(magnitudeSquared());
	}
	
	@Override
	public String toString(){
		return "<"+getX()+","+getY()+">";
	}
	
	public static boolean intersect(Vec2i a, Vec2i b, Vec2i c, Vec2i d){
		Vec2i ab = b.minus(a);
		Vec2i cd = d.minus(c);
		int abc = c.minus(a).crossZ(ab);
		int abd = d.minus(a).crossZ(ab);
		int cda = a.minus(c).crossZ(cd);
		int cdb = b.minus(c).crossZ(cd);
		return ((abc <= 0 && abd >= 0) || (abc >= 0 && abd <= 0)) && ((cda <= 0 && cdb >= 0) || (cda >= 0 && cdb <= 0));
	}
}