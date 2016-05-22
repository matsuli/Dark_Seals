	import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Arrays;
	import java.util.Collections;
	import java.util.List;
	
	
public class hitDetectLine extends hitDetObj {

	
	int ox2;
	int oy2;
	
	public hitDetectLine(int ox, int oy, int ox2, int oy2) {
		this.ox=ox;
		this.oy=oy;
		this.ox2=ox2;
		this.oy2=oy2;
	}
	


	    public boolean hitdetect(Ellipse2D hitDetCircle, int radius) {
	    	hit=false;
	    	
	    double vx=(ox2-ox);
	    double vy=(oy2-oy);	
	    
	   Point middle = new Point(ox+vx/2, oy+vy/2);
	   	    	
	    double a = Math.toDegrees(Math.atan2(ox - ox2, oy - oy2)); 	
	    
	    double a2 = Math.toDegrees(Math.atan2(ox - hitDetCircle.getCenterX(), oy - hitDetCircle.getCenterY())); 
	    
	    double a3 = Math.toDegrees(Math.asin(radius/(Math.sqrt((ox-hitDetCircle.getCenterX())*(ox-hitDetCircle.getCenterX()) + (oy-hitDetCircle.getCenterY())*(oy-hitDetCircle.getCenterY())))));
	 //  System.out.println(a);
	   
	if(a2>0){
		a2=a2-360;
	}
	//    System.out.println(a3);   
	   // System.out.println(a-a2);   
		
	  //  System.out.println (Math.sqrt(vx*vx+vy*vy));  
	if((Math.sqrt((ox-hitDetCircle.getCenterX())*(ox-hitDetCircle.getCenterX()) + (oy-hitDetCircle.getCenterY())*(oy-hitDetCircle.getCenterY()))<radius)){
		hit=true;	
	}
	    
	else if(a-a2<a3 && a-a2>-a3 && Math.sqrt((middle.x-hitDetCircle.getCenterX())*(middle.x-hitDetCircle.getCenterX()) + (middle.y-hitDetCircle.getCenterY())*(middle.y-hitDetCircle.getCenterY()))-radius<Math.sqrt((middle.x-ox)*(middle.x-ox) + (middle.y-oy)*(middle.y-oy))){
	  	hit=true;
	   }

	    	
	        if(hit==true){
				SimpleGameEngine.player.hit=true;
				hit=false;
				}
	        
			return hit;
	    }

	    static class Point {
	        double x, y;

	        public Point(double x, double y) { this.x = x; this.y = y; }

	        @Override
	        public String toString() {
	            return "Point [x=" + x + ", y=" + y + "]";
	        }
	    }
	    public void draw(Graphics2D g){
	    	
	    g.drawLine(ox, oy, ox2, oy2);
	    
	    	}
	    }



