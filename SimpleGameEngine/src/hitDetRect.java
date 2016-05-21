import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

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
	
	public void hitdetect (){
				
		if (SimpleGameEngine.player.hitDetCircle.intersects(rect)) {
		hit=true;
		
			if(ox >=(SimpleGameEngine.playerX - SimpleGameEngine.player.radius-SimpleGameEngine.px)) {
				hitRight=true;	
				
				hitCorrectionRight=(SimpleGameEngine.playerX + SimpleGameEngine.player.radius-SimpleGameEngine.px)-ox;
			}
			if(ox+ow <=(SimpleGameEngine.playerX + SimpleGameEngine.player.radius-SimpleGameEngine.px) && oy != (SimpleGameEngine.playerY + SimpleGameEngine.player.radius-SimpleGameEngine.py) && (oy+oh)!=SimpleGameEngine.playerY - SimpleGameEngine.player.radius-SimpleGameEngine.py){
				hitLeft=true;	
				hitCorrectionLeft=(ox+ow)-(SimpleGameEngine.playerX - SimpleGameEngine.player.radius-SimpleGameEngine.px);
			}
			if(oy >=(SimpleGameEngine.playerY - SimpleGameEngine.player.radius-SimpleGameEngine.py) && ox !=(SimpleGameEngine.playerX + SimpleGameEngine.player.radius-SimpleGameEngine.px) && SimpleGameEngine.playerX - SimpleGameEngine.player.radius-SimpleGameEngine.px != (ox + ow) ){
				hitDown=true;
				hitCorrectionDown=(SimpleGameEngine.playerY + SimpleGameEngine.player.radius-SimpleGameEngine.py)-oy;
			}
			if(oy+oh <=(SimpleGameEngine.playerY + SimpleGameEngine.player.radius-SimpleGameEngine.py)&& ox !=(SimpleGameEngine.playerX + SimpleGameEngine.player.radius-SimpleGameEngine.px) && SimpleGameEngine.playerX - SimpleGameEngine.player.radius-SimpleGameEngine.px != (ox + ow)){
				hitUp=true;	
				hitCorrectionUp=(oy+oh)-(SimpleGameEngine.playerY - SimpleGameEngine.player.radius-SimpleGameEngine.py);
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
	}

	public void draw(Graphics2D g){
		g.drawRect(ox, oy, ow, oh);
	}
			
}
	
	

	
	

	

