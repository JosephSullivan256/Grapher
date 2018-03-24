package com.josephsullivan256.gmail.grapher.testing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.josephsullivan256.gmail.grapher.Grapher;
import com.josephsullivan256.gmail.grapher.functions.DDX;
import com.josephsullivan256.gmail.grapher.functions.Function;
import com.josephsullivan256.gmail.grapher.functions.FunctionUtilities;
import com.josephsullivan256.gmail.grapher.functions.RationalFunction;
import com.josephsullivan256.gmail.grapher.functions.FunctionUtilities.Endpoint;
import com.josephsullivan256.gmail.grapher.math.Vec2;
import com.josephsullivan256.gmail.grapher.math.Vec2i;

public class Main {

	public static void main(String[] args) throws IOException {
		Function f1 = (x)->(Math.sin(x));
		
		Grapher g = new Grapher(
					new Vec2i(800,800), //dimensions in pixels
					new Vec2(-4,4), //x range
					new Vec2(-60,60), //y range
					1, //x scale
					5, //y scale
					64000 //x frequency
				);
		BufferedImage img = new BufferedImage(800,800,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = img.createGraphics();
		
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, 800, 800);
		
		g.drawAxis(g2d);
		
		g2d.setColor(Color.blue);
		g.drawFunction(g2d, f1);
		
		double a = 0;
		double b = Math.PI;
		int n = 100;
		Endpoint e = Endpoint.right;
		System.out.println("AUC~");
		double dx = (b-a)/((double)n);
		System.out.println("xi = " + (a + dx/2.0) + " + dx * i");
		System.out.println("dx = " + dx);
		System.out.println(FunctionUtilities.computeRiemannSum(f1, a, b, n, e));
		
		g2d.dispose();
		
		//display(img);
	}
	
	public static void display(BufferedImage img){
		JFrame frame = new JFrame();
		JLabel label = new JLabel(new ImageIcon(img));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(label);
		frame.pack();
		frame.setVisible(true);
	}

}
