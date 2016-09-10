package engine;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class hitDetRect extends hitDetObj {
	

	Rectangle2D rect;
	int ow;
	int oh;
	
	public hitDetRect(int ox, int oy, int ow, int oh) {
		this.ox=ox;
		this.oy=oy;
		this.ow=ow;
		this.oh=oh;
		rect = new Rectangle2D.Double(ox, oy, ow, oh);
	}
	
	public double getCornerTangentX(int radius, double centerY,double cornerX, double cornerY){
		//(cornerX-targetX)pow2+(cornerY-centerY)pow2=radius*radius =Cirkelns ekvation, d�r targetX �r det centerX f�r vilket rektangelns h�rn �r punkt p� cirkeln
		double i=(radius*radius)-((cornerY-centerY)*(cornerY-centerY));	//i=(cornerX-targetX)pow2
		double targetX;
		if(cornerX==ox){	//v�nster sida av rekt, cornerX-targetX>0
		 targetX=(-Math.sqrt(i)+cornerX);	
		}
		else{			//eller h�ger sida av rekt, cornerX-targetX<0
		 targetX=(Math.sqrt(i)+cornerX);}		
		return targetX;	
		
	}
	public double getCornerTangentY(int radius, double centerX,double cornerX, double cornerY){
		//(cornerY-targetY)pow2+(cornerX-centerX)pow2=radius*radius =Cirkelns ekvation, d�r targetX �r det centerX f�r vilket rektangelns h�rn �r punkt p� cirkeln
		double i=(radius*radius)-((cornerX-centerX)*(cornerX-centerX));	//i=(cornerY-targetY)pow2
		double targetY;
		if(cornerY==oy){	//ovanf�r rekt, cornerY-targetY>0
		 targetY=(-Math.sqrt(i)+cornerY);	
	//		System.out.println(Math.sqrt(i));	
		}
		else{			//eller under rekt, cornerY-targetY<0
		 targetY=(Math.sqrt(i)+cornerY);
		 }		
		return targetY;	
		
	}
	public boolean hitdetect ( Ellipse2D hitDetCircle, int radius, Ellipse2D prevHitDetCircle){
		
		hit=false;
		if (hitDetCircle.intersects(rect)) {
		hit=true;
		
			if(ox >=(prevHitDetCircle.getCenterX())) {
				hitRight=true;	
				if(oy<=hitDetCircle.getCenterY() && oy+oh>=hitDetCircle.getCenterY()){
				hitCorrectionRight= (hitDetCircle.getCenterX() + radius)-ox;}
				else{
					if(!(oy<=hitDetCircle.getCenterY())){
					double i=getCornerTangentX(radius, hitDetCircle.getCenterY(), ox, oy);	//denna �r det centerX f�r player d�r cirkeln tr�ffar h�rnet (h�rnet �r punkt p� cirkel)
					hitCorrectionRight=hitDetCircle.getCenterX()-i;
					}
					if(!(oy+oh>=hitDetCircle.getCenterY())){
						double i=getCornerTangentX(radius, hitDetCircle.getCenterY(),ox, oy+oh);	//denna �r det centerX f�r player d�r cirkeln tr�ffar h�rnet (h�rnet �r punkt p� cirkel)
						hitCorrectionRight=hitDetCircle.getCenterX()-i;
						}
				}
			}
			if(ox+ow <=(prevHitDetCircle.getCenterX())){
				hitLeft=true;	
				
				if(oy<=hitDetCircle.getCenterY() && oy+oh>=hitDetCircle.getCenterY()){		//om player �r p� "yttre sidan" av rektangeln
				hitCorrectionLeft= ((ox+ow)-(hitDetCircle.getCenterX()- radius));}
				else{
					if(!(oy<=hitDetCircle.getCenterY())){
					double i=getCornerTangentX(radius, hitDetCircle.getCenterY(), ox+ow, oy);	//denna �r det centerX f�r player d�r cirkeln tr�ffar h�rnet (h�rnet �r punkt p� cirkel)
					hitCorrectionLeft=i-hitDetCircle.getCenterX();
					}
					if(!(oy+oh>=hitDetCircle.getCenterY())){
						double i=getCornerTangentX(radius, hitDetCircle.getCenterY(),ox+ow, oy+oh);	//denna �r det centerX f�r player d�r cirkeln tr�ffar h�rnet (h�rnet �r punkt p� cirkel)
						hitCorrectionLeft=i-hitDetCircle.getCenterX();
						}
				}
			}
			if(oy >=(prevHitDetCircle.getCenterY()) ){
				hitDown=true;
				
				if(ox<=hitDetCircle.getCenterX() && ox+ow>=hitDetCircle.getCenterX()){		//om player �r p� "yttre sidan" av rektangeln
					hitCorrectionDown= ((hitDetCircle.getCenterY() + radius)-oy);}
				else{
					if(!(ox<=hitDetCircle.getCenterX())){
					double i=getCornerTangentY(radius, hitDetCircle.getCenterX(), ox, oy);	//denna �r det centerX f�r player d�r cirkeln tr�ffar h�rnet (h�rnet �r punkt p� cirkel)
					hitCorrectionDown=-i+hitDetCircle.getCenterY();
					}
					if(!(ox+ow>=hitDetCircle.getCenterX())){
						double i=getCornerTangentY(radius, hitDetCircle.getCenterX(),ox+ow, oy);	//denna �r det centerX f�r player d�r cirkeln tr�ffar h�rnet (h�rnet �r punkt p� cirkel)
						hitCorrectionDown=-i+hitDetCircle.getCenterY();
						}
				}
				
			}
			if(oy+oh <=(prevHitDetCircle.getCenterY())){
				hitUp=true;	
				if(ox<=hitDetCircle.getCenterX() && ox+ow>=hitDetCircle.getCenterX()){		//om player �r p� "yttre sidan" av rektangeln
					hitCorrectionUp= ((oy+oh)-(hitDetCircle.getCenterY()- radius));}
				else{
					if(!(ox<=hitDetCircle.getCenterX())){
					double i=getCornerTangentY(radius, hitDetCircle.getCenterX(), ox, oy+oh);	//denna �r det centerX f�r player d�r cirkeln tr�ffar h�rnet (h�rnet �r punkt p� cirkel)
					hitCorrectionUp=i-hitDetCircle.getCenterY();
					}
					if(!(ox+ow>=hitDetCircle.getCenterX())){
						double i=getCornerTangentY(radius, hitDetCircle.getCenterX(),ox+ow, oy+oh);	//denna �r det centerX f�r player d�r cirkeln tr�ffar h�rnet (h�rnet �r punkt p� cirkel)
						hitCorrectionUp=i-hitDetCircle.getCenterY();
						}
				}
				
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
	//g.fill(rect);	
	if(textureImg2!=null){
		g.drawImage(textureImg, ox, oy, ow, oh, null);}
	}
			
}
	
	

	
	

	

