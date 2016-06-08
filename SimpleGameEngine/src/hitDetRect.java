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
	
	public boolean hitdetect ( Ellipse2D hitDetCircle, int radius, Ellipse2D unused){
		
		hit=false;
		if (hitDetCircle.intersects(rect)) {
		hit=true;
		
			if(ox >=(hitDetCircle.getCenterX())) {
				hitRight=true;	
				
				hitCorrectionRight=(int) (hitDetCircle.getCenterX() + radius)-ox;
			}
			if(ox+ow <=(hitDetCircle.getCenterX())){
				hitLeft=true;	
				hitCorrectionLeft=(int) ((ox+ow)-(hitDetCircle.getCenterX()- radius));
			}
			if(oy >=(hitDetCircle.getCenterY()) ){
				hitDown=true;
				hitCorrectionDown=(int) ((hitDetCircle.getCenterY() + radius)-oy);
			}
			if(oy+oh <=(hitDetCircle.getCenterY())){
				hitUp=true;	
				hitCorrectionUp=(int) ((oy+oh)-(hitDetCircle.getCenterY()- radius));
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
	g.drawImage(textureImg, ox, oy, ow, oh, null);
	}
			
}
	
	

	
	

	

