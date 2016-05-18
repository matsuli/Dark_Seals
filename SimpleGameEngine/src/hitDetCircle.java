import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;


public class hitDetCircle extends hitDetObj {

	int r;
	Ellipse2D circle;
	double centerX;
	double centerY;
	
	public hitDetCircle(int ox, int oy, int ow, int oh) {
		super(ox, oy, ow, oh);
		r=ow/2;
		ox = ox-(r/2);
		oy = oy-(r/2);		
		circle = new Ellipse2D.Double(ox, oy, ow, oh);
		centerX=circle.getCenterX();
		centerY=circle.getCenterY();
	}

	public void hitdetect(){
	
		int px=SimpleGameEngine.player.location.x-SimpleGameEngine.px;
		int py=SimpleGameEngine.player.location.y-SimpleGameEngine.py;
		
		
		 if ((centerX - px) * (centerX - px) + (centerY - py) * (centerY - py) <
		          (SimpleGameEngine.player.radius + r) * (SimpleGameEngine.player.radius + r)){
			 System.out.println("hit");
		 	}
		 }
		
	
	
	public void draw(Graphics2D g){		
		
		  g.fill(circle);
		
	}
	
	
}
