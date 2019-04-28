package paint;


import java.awt.Graphics;

public class Rectangle extends Trait{
	
	public Rectangle() {
		super();
	}
	
	public void dessiner(Graphics g) {
		
		int x1 = (int)this.getVectPoints().elementAt(0).getX();
		int y1 = (int)this.getVectPoints().elementAt(0).getY();
		int x2 = (int)this.getVectPoints().elementAt(this.getVectPoints().size() - 1).getX();
		int y2 = (int)this.getVectPoints().elementAt(this.getVectPoints().size() - 1).getY();
	
		g.drawLine(x1,y1, x2, y1);
		g.drawLine(x2,y1, x2, y2);
		g.drawLine(x2,y2, x1, y2);
		g.drawLine(x1,y1, x1, y2);
			
	}

}
