import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;


public class hitDetCircle extends hitDetObj {

	int r;
	Ellipse2D circle;
	double centerX;
	double centerY;
	int ow;
	int oh;
	
	public hitDetCircle(int ox, int oy, int ow, int oh) {
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

	public boolean hitdetect(Ellipse2D hitDetCircle, int radius, Ellipse2D unused){
	
		double px=hitDetCircle.getCenterX();
		double py=hitDetCircle.getCenterY();
		hit=false;
		
		if ((centerX - px) * (centerX - px) + (centerY - py) * (centerY - py) <= (radius + r) * (radius + r)){
			hit=true;
			
			if(centerX>px){
				hitRight=true;		
				
				int x1=(int) (centerX-px);
				int y=(int) (centerY-py);
				int i2= (int) Math.sqrt(((radius + r) * (radius + r)));
				int x2=(int)(Math.sqrt(i2*i2-y*y));
				hitCorrectionRight=x2-x1;
				
			}
			
			if(centerX<px){
				hitLeft=true;	
				
				int x1=(int) (-centerX+px);
				int y=(int) (centerY-py);
				int i2= (int) Math.sqrt(((radius + r) * (radius + r)));
				int x2=(int)(Math.sqrt(i2*i2-y*y));
				hitCorrectionLeft=x2-x1;
				
			}
			
			if(centerY>py){
				hitDown=true;
			
				int y1=(int) (centerY-py);
				int x=(int) (centerX-px);
				int i2= (int) Math.sqrt(((radius + r) * (radius + r)));
				int y2=(int)(Math.sqrt(i2*i2-x*x));
				hitCorrectionDown=y2-y1;
			}
			if(centerY<py){
				hitUp=true;	
				
				int y1=(int) (-centerY+py);
				int x=(int) (centerX-px);
				int i2= (int) Math.sqrt(((radius + r) * (radius + r)));
				int y2=(int)(Math.sqrt(i2*i2-x*x));
				hitCorrectionUp=y2-y1;
			}
		}
		if(hitDetCircle==SimpleGameEngine.player.hitDetCircle){ 
			if(hit==true){
				SimpleGameEngine.player.hit=true;
				hit=false;
			}
			if(hitRight==true){
				SimpleGameEngine.player.hitRight=true;
				SimpleGameEngine.player.hitCorrectionRight=hitCorrectionRight;
				hitRight=false;
			}
			if(hitDown==true){
				SimpleGameEngine.player.hitDown=true;
				hitDown=false;
				SimpleGameEngine.player.hitCorrectionDown=hitCorrectionDown;
			}
			if(hitUp==true){
				SimpleGameEngine.player.hitUp=true;
				hitUp=false;
				SimpleGameEngine.player.hitCorrectionUp=hitCorrectionUp;
			}
			if(hitLeft==true){
				SimpleGameEngine.player.hitLeft=true;
				hitLeft=false;	
				SimpleGameEngine.player.hitCorrectionLeft=hitCorrectionLeft;
			}
		}
		return hit;
	}
	
	public void draw(Graphics2D g){
		g.fill(circle);
	}
}
