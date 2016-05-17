import java.awt.Color;
import java.awt.Graphics;

public class hitDetCircle extends hitDetObj {

	int r;
	public hitDetCircle(int ox, int oy, int ow, int oh) {
		super(ox, oy, ow, oh);
		r=ow/2;
		ox = ox-(r/2);
		oy = oy-(r/2);		
	}

	public void hitdetect(){
		
		
	}
	
	public void draw(Graphics g){		

		  g.fillOval(ox,oy,r,r);
	}
	
	
}
