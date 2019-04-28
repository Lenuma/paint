package paint;

import java.awt.Graphics;

public class Cercle extends Trait{
	
	public Cercle() {
		super();
	}
	
public void dessiner(Graphics g) {
	
		int minX = Math.min((int)this.getVectPoints().elementAt(0).getX(), (int)this.getVectPoints().elementAt(this.getVectPoints().size() - 1).getX());
		int minY = Math.min((int)this.getVectPoints().elementAt(0).getY(), (int)this.getVectPoints().elementAt(this.getVectPoints().size() - 1).getY());
		int maxX = Math.max((int)this.getVectPoints().elementAt(0).getX(), (int)this.getVectPoints().elementAt(this.getVectPoints().size() - 1).getX());
		int maxY = Math.max((int)this.getVectPoints().elementAt(0).getY(), (int)this.getVectPoints().elementAt(this.getVectPoints().size() - 1).getY());
		
		int width = maxX - minX;
		int height = maxY - minY;
		
		g.drawOval(minX,minY, width, height);
			
	}
}
