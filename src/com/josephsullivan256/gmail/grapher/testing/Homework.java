package com.josephsullivan256.gmail.grapher.testing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.josephsullivan256.gmail.grapher.Grapher;
import com.josephsullivan256.gmail.grapher.functions.DDX;
import com.josephsullivan256.gmail.grapher.functions.Function;
import com.josephsullivan256.gmail.grapher.functions.RationalFunction;
import com.josephsullivan256.gmail.grapher.math.Vec2;
import com.josephsullivan256.gmail.grapher.math.Vec2i;

public class Homework {
	public static void main(String[] args) throws IOException {
		String problem = "15";
		
		//Function f1 = (x)->(6*Math.sin(x)+1.0/Math.tan(x));
		Function f1 = new RationalFunction((x)->(x*x*x+5*x*x+1),(x)->(x*x*x*x+x*x*x-x*x+2)); //(x+4)(x-3)^2/(x^4)(x-1)
		//Function f1 = (x)->1+(new RationalFunction((x1)->1,(x1)->x1)).evaluate(x)+(new RationalFunction((x1)->8,(x1)->x1*x1)).evaluate(x)+(new RationalFunction((x1)->1,(x1)->x1*x1*x1)).evaluate(x);
		Function f2 = new DDX(f1,0.00001);
		Function f3 = new DDX(f2,0.00001);
		double xScale = 1;
		double yScale = 5;
		Grapher g = new Grapher(
					new Vec2i(800,800), //dimensions in pixels
					new Vec2(-10,10), //x range
					new Vec2(-80,25), //y range
					xScale, //x scale
					yScale, //y scale
					64000 //x frequency
				);
		BufferedImage img1 = new BufferedImage(800,800,BufferedImage.TYPE_INT_RGB);
		BufferedImage img2 = new BufferedImage(800,800,BufferedImage.TYPE_INT_RGB);
		BufferedImage img3 = new BufferedImage(800,800,BufferedImage.TYPE_INT_RGB);
		String info = "";
		info+="X Scale: " + xScale + "\n";
		info+="Y Scale: " + yScale + "\n\n";
		Graphics2D g2d1 = img1.createGraphics();
		Graphics2D g2d2 = img2.createGraphics();
		Graphics2D g2d3 = img3.createGraphics();
		
		g2d1.setColor(Color.white);
		g2d1.fillRect(0, 0, 800, 800);
		g2d2.setColor(Color.white);
		g2d2.fillRect(0, 0, 800, 800);
		g2d3.setColor(Color.white);
		g2d3.fillRect(0, 0, 800, 800);
		
		g.drawAxis(g2d1);
		g.drawAxis(g2d2);
		g.drawAxis(g2d3);
		
		g2d1.setColor(Color.red);
		info += "Original Function " + g.drawFunction(g2d1, f1);
		
		g2d2.setColor(Color.red);
		info += "\nFirst Derivative " + g.drawFunction(g2d2, f2);
		
		g2d3.setColor(Color.red);
		info += "\nSecond Derivative " + g.drawFunction(g2d3, f3);
		
		g2d1.dispose();
		g2d2.dispose();
		g2d3.dispose();
		
		String path = "problem"+problem;
		File f = new File(path);
		f.mkdir();
		ImageIO.write(img1, "png", new File(path+"/graph1.png"));
		ImageIO.write(img2, "png", new File(path+"/graph2.png"));
		ImageIO.write(img3, "png", new File(path+"/graph3.png"));
		
		try(BufferedWriter out = new BufferedWriter(new FileWriter(path+"/info.txt"))){
			out.write(info);
		}
	}
}
