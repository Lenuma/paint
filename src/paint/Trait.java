package paint;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.*;

public class Trait {
	
	private Vector<Point> points;
	private Color couleur;
	private int taille = 1;
	
	
		
	public Trait() {
		points = new Vector <Point>();
	}
	
	public void setPoint(Point p) {
		points.addElement(p);
	}
	
	public Vector<Point> getVectPoints() {
		return points;
	}
	
	public void setTaille(int t) {
		taille = t;
	}
	
	public void setColor(Color c) {
		couleur = c;
	}
	
	public Color getColor() {
		return couleur;
	}
	
	public int getTaille() {
		return taille;
	}
	
	
	public void dessiner(Graphics g) {
		for (int i = 0; i < points.size() - 1; i++) {
			
			int x1 = (int)points.elementAt(i).getX();
			int y1 = (int)points.elementAt(i).getY();
			int x2 = (int)points.elementAt(i+1).getX();
			int y2 = (int)points.elementAt(i+1).getY();
			
			g.drawLine(x1,y1,x2,y2);
		}
	}
	

}
