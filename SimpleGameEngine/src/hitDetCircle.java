import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class hitDetCircle extends hitDetObj {

	int r;
	Ellipse2D circle;
	
	public hitDetCircle(int ox, int oy, int ow, int oh) {
		super(ox, oy, ow, oh);
		r=ow/2;
		ox = ox-(r/2);
		oy = oy-(r/2);		
		circle = new Ellipse2D.Double(ox, oy, ow, oh);
	}

	public void hitdetect(){
		
		
	}
	
	public void draw(Graphics2D g){		
		
		  g.fill(circle);
		
	}
	
	
}
