package world;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class hitDetTriangle extends hitDetObj {
int ox2;
int oy2;
int ox3;
int oy3;
hitDetectLine line1;
hitDetectLine line2;
hitDetectLine line3;

	public hitDetTriangle(int ox, int oy, int ox2, int oy2, int ox3,int oy3) {
		this.ox=ox;
		this.oy=oy;
		this.ox2=ox2;
		this.oy2=oy2;
		this.ox3=ox3;
		this.oy3=oy3;
		line1= new hitDetectLine(ox, oy, ox2, oy2);
		line2= new hitDetectLine(ox2, oy2, ox3, oy3);
		line3= new hitDetectLine(ox3, oy3, ox, oy);
	}
	public boolean hitdetect(Ellipse2D hitDetCircle, int radius, Ellipse2D prevHitDetCircle){
		
		line1.hitdetect(hitDetCircle, radius, prevHitDetCircle);
		line2.hitdetect(hitDetCircle, radius, prevHitDetCircle);
		line3.hitdetect(hitDetCircle, radius, prevHitDetCircle);
		if(line1.hit==true || line2.hit==true || line3.hit==true){
		hit=true;			
		}
		else{
		hit=false;	
		}

		return hit;
	}
	public void draw(Graphics2D g){
    	
	    line1.draw(g);
	    line2.draw(g);
	    line3.draw(g);
	    	}

}
