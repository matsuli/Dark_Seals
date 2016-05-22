	import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
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
	    	
	    double a = Math.toDegrees(Math.atan2(ox - ox2, oy - oy2)); 	
	    
	    double a2 = Math.toDegrees(Math.atan2(ox - hitDetCircle.getCenterX(), oy - hitDetCircle.getCenterY())); 
	    
	    double a3 = Math.toDegrees(Math.asin(radius/(Math.sqrt((ox-hitDetCircle.getCenterX())*(ox-hitDetCircle.getCenterX()) + (oy-hitDetCircle.getCenterY())*(oy-hitDetCircle.getCenterY())))));
	   // System.out.println(a);
	 //   System.out.println(a2);
	   // System.out.println(a3);   
	//        if  (){
	//        	hit=false;	
	           
	  //      }
	    
	  if(a-a2<a3& a-a2>-a3 ){
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



