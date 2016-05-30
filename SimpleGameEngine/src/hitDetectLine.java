	import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
	import java.util.Collections;
	import java.util.List;
	
	
public class hitDetectLine extends hitDetObj {

	
	int ox2;
	int oy2;
	Line2D line;
	int k;
	double k2;
	
	public hitDetectLine(int x, int y, int x2, int y2) {
		if(x<=x2){		
		this.ox=x;
		this.ox2=x2;}
		else{
		this.ox=x2;
		this.ox2=x;
		}		
		this.oy=y;
		this.oy2=y2;
		line = new Line2D.Double(ox, oy, ox2, oy2);
		k = (oy2-oy);
		
		k2=(oy2-oy)/(ox2-ox);
		
	}
	
	public double getLineY(Double x){
		double i = (x-ox)*k2*oy;
		return i;
	}
	public double getLineX(Double y){
		double i;
		i=y/(k2*oy)+ox;
		return i;
	}
	
	


	    public boolean hitdetect(Ellipse2D hitDetCircle, int radius) {
	    	hit=false;
	    	Point2D centerP = new Point2D.Double(hitDetCircle.getCenterX(), hitDetCircle.getCenterY());

//	if((Math.sqrt((ox-hitDetCircle.getCenterX())*(ox-hitDetCircle.getCenterX()) + (oy-hitDetCircle.getCenterY())*(oy-hitDetCircle.getCenterY()))<radius)){
//		hit=true;	
//	}
	    
	 if(line.ptSegDist(centerP)<radius){
	  	hit=true;
	  	
	  	int i =line.relativeCCW(centerP);
	  	System.out.println(i);
	  	
	  	if(i==1 && k>0){
	  		hitDown=true;
	  		hitLeft=true;
	  		
	  	double i2=getLineX(centerP.getY());	
	  	
	  		
	  	}
	  	else if( i==-1 && k>0){
	  		hitUp=true;
	  		hitRight=true;
	  	}
	  	else if( i==-1 && k<0){
	  		hitUp=true;
	  		hitLeft=true;
	  	}
	  	else if(i==1 && k<0){
	  		hitDown=true;
	  		hitRight=true;
	  	}
	  	
	  	
	  	
	   }
			
	
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



