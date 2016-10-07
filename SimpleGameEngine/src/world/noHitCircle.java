package world;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;


public class noHitCircle extends noHitObj {

	int r;
	Ellipse2D circle;
	double centerX;
	double centerY;
	int ow;
	int oh;
	public noHitCircle(int ox, int oy, int ow, int oh) {
		this.ox=ox;
		this.oy=oy;
		this.ow=ow;
		this.oh=oh;
		r=ow/2;
		ox = ox-(r/2);
		oy = oy-(r/2);		
		circle = new Ellipse2D.Double(ox, oy, ow, oh);
		centerX=circle.getCenterX();
		centerY=circle.getCenterY();
	}
	
	public void draw(Graphics2D g){		
		
		g.fill(circle);
		if(textureImg!=null){
			g.drawImage(textureImg, ox, oy, ow, oh, null);
		}
	}


}
