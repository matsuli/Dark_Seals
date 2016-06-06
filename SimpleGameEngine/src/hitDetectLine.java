	import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

	
	
public class hitDetectLine extends hitDetObj {

	double ox;
	double oy;
	double ox2;
	double oy2;
	Line2D line;
	double k;
	double k2;
	double c;
	double b;
	double a;
	double lineLenght;
	
	public hitDetectLine(double x, double y, double x2, double y2) {
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
		lineLenght=Math.sqrt((ox-ox2)*(ox-ox2) + (oy-oy2)*(oy-oy2));
		k = (oy2-oy);
		k2=(oy2-oy)/(ox2-ox);
		c=getLineY(0.0);
		b=-1;
		a=k2;
		
	}
	
	public double getLineY(Double x){
		double i = (x-ox)*k2+oy;
		return i;
	}
	public double getLineX(Double y){
		double i;
		i=(y-oy)/k2+ox;
		return i;
	}
	
	public double getTangentX(Double y, Double x, int r){
		double i; //=(c+ax+by)*(c+ax+by)
		i=(a*a+b*b)*(r*r);
		double i2;
		i2=(Math.sqrt(i));
		double i3;
		i3=(i2-c-b*y)/a;
		double i4;
		i4=(-i2-c-b*y)/a;
		if(Math.abs(x-i3)<Math.abs(x-i4)){
		return i3;	
		}
		else{
		return i4;		
		}
	}
	
	public double getTangentY(Double x, Double y, int r){
		double i; //=(c+ax+by)*(c+ax+by)
		i=(a*a+b*b)*(r*r);
		double i2;
		i2=(Math.sqrt(i));
		double i3;
		i3=(i2-c-a*x)/b;
		double i4;
		i4=(-i2-c-a*x)/b;
		if(Math.abs(y-i3)<Math.abs(y-i4)){
		return i3;	
		}
		else{
		return i4;		
		}
	}
	
	


	    public boolean hitdetect(Ellipse2D hitDetCircle, int radius) {
	    	hit=false;
	    	Point2D centerP = new Point2D.Double(hitDetCircle.getCenterX(), hitDetCircle.getCenterY());

//	if((Math.sqrt((ox-hitDetCircle.getCenterX())*(ox-hitDetCircle.getCenterX()) + (oy-hitDetCircle.getCenterY())*(oy-hitDetCircle.getCenterY()))<radius)){
//		hit=true;	
//	}		
	    	double px=centerP.getX();
	    	double py=centerP.getY();
	    	double lx=getTangentX(py, px, radius);	
		  	double ly=getTangentY(px, py, radius);	
	    
	 if(line.ptSegDist(centerP)<radius){
	  	hit=true;	
	  		
	  	int i =line.relativeCCW(centerP);
	 	System.out.println(i);
	  	
	  	if(i==1 && k>0){
	  		hitDown=true;
	  		hitLeft=true;
	  		
	  		hitCorrectionLeft=(int) (lx-px);
	  		hitCorrectionDown=(int) (py-ly);
	  		
	  	}
	  	else if( i==-1 && k>0){
	  		hitUp=true;
	  		hitRight=true;
	  		hitCorrectionRight=(int) (px-lx);
	  		hitCorrectionUp=(int) (ly-py);
	  	}
	  	else if( i==-1 && k<0){
	  		hitUp=true;
	  		hitLeft=true;
	  		hitCorrectionUp=(int) (ly-py);
	  		hitCorrectionLeft=(int) (lx-px);
	  		
	  	}
	  	else if(i==1 && k<0){
	  		hitDown=true;
	  		hitRight=true;
	  		hitCorrectionDown=(int) (py-ly);
	  		hitCorrectionRight=(int) (px-lx);
	  	}
	  	
	  	if((centerP.distance(ox, oy)<radius || centerP.distance(ox2, oy2)<radius) && (centerP.distance(ox, oy)>lineLenght || centerP.distance(ox2, oy2)>lineLenght)){
	  		double centerX;
	  		double centerY;
	  		if(centerP.distance(ox, oy)<radius ){
	  			 centerX=ox;
	  			 centerY=oy;
	  		}
	  		else{
	  			centerX=ox2;
	  			centerY=oy2;
	  		}
	  		
	  		
			 if(centerX>px){
				 hitRight=true;		
				 
				int x1=(int) (centerX-px);
				int y=(int) (centerY-py);
				int i2= (int) Math.sqrt(((radius) * (radius)));
				int x2=(int)(Math.sqrt(i2*i2-y*y));
				hitCorrectionRight=x2-x1;
				
			 }
			 if(centerX<px){
				 hitLeft=true;	
				 
				int x1=(int) (-centerX+px);
				int y=(int) (centerY-py);
				int i2= (int) Math.sqrt(((radius) * (radius)));
				int x2=(int)(Math.sqrt(i2*i2-y*y));
				hitCorrectionLeft=x2-x1;
				
			 }
			 if(centerY>py){
				 hitDown=true;
					int y1=(int) (centerY-py);
					int x=(int) (centerX-px);
					int i2= (int) Math.sqrt(((radius) * (radius)));
					int y2=(int)(Math.sqrt(i2*i2-x*x));
					hitCorrectionDown=y2-y1;
			 }
			 if(centerY<py){
				 hitUp=true;	
					int y1=(int) (-centerY+py);
					int x=(int) (centerX-px);
					int i2= (int) Math.sqrt(((radius ) * (radius)));
					int y2=(int)(Math.sqrt(i2*i2-x*x));
					hitCorrectionUp=y2-y1;
			 }	
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


	    public void draw(Graphics2D g){
	    	
	    g.draw(line);
	    
	    	}
	    }



