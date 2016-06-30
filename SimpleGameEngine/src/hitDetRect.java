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
		//(ocornerX-targetX)pow2+(cornerY-centerY)pow2=radius*radius =Cirkelns ekvation, där targetX är det centerX för vilket rektangelns hörn är punkt på cirkeln
		double i=(radius*radius)-((cornerY-centerY)*(cornerY-centerY));	
		double targetX=(Math.sqrt(i)*(-1)-cornerX)*(-1);
	//	System.out.println(i);	
	//	System.out.println(targetX);		
		return targetX;	
		
	}
	public boolean hitdetect ( Ellipse2D hitDetCircle, int radius, Ellipse2D prevHitDetCircle){
		
		hit=false;
		if (hitDetCircle.intersects(rect)) {
		hit=true;
		
			if(ox >=(prevHitDetCircle.getCenterX())) {
				hitRight=true;	
				
				hitCorrectionRight= (hitDetCircle.getCenterX() + radius)-ox;
			}
			if(ox+ow <=(prevHitDetCircle.getCenterX())){
				hitLeft=true;	
				
				
				if(oy<=hitDetCircle.getCenterY() && oy+ow>=hitDetCircle.getCenterY()){		//om player är på "yttre sidan" av rektangeln
				hitCorrectionLeft= ((ox+ow)-(hitDetCircle.getCenterX()- radius));}
				else{
					if(!(oy<=hitDetCircle.getCenterY())){
					double i=getCornerTangentX(radius, hitDetCircle.getCenterY(), ox+ow, oy);	//denna är det centerX för player där cirkeln träffar hörnet (hörnet är punkt på cirkel)
					hitCorrectionLeft=i-hitDetCircle.getCenterX();
					}
					if(!(oy+ow>=hitDetCircle.getCenterY())){
						double i=getCornerTangentX(radius, hitDetCircle.getCenterY(),ox+ow, oy+oh);	//denna är det centerX för player där cirkeln träffar hörnet (hörnet är punkt på cirkel)
						hitCorrectionLeft=i-hitDetCircle.getCenterX();
						}
				}
			}
			if(oy >=(prevHitDetCircle.getCenterY()) ){
				hitDown=true;
				hitCorrectionDown= ((hitDetCircle.getCenterY() + radius)-oy);
			}
			if(oy+oh <=(prevHitDetCircle.getCenterY())){
				hitUp=true;	
				hitCorrectionUp= ((oy+oh)-(hitDetCircle.getCenterY()- radius));
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
	g.drawRect(ox, oy, ow, oh);
	if(textureImg2!=null){
		g.drawImage(textureImg, ox, oy, ow, oh, null);}
	}
			
}
	
	

	
	

	

