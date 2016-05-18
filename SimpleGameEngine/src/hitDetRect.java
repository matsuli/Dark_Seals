import java.awt.Graphics2D;

public class hitDetRect extends hitDetObj {
	
	public hitDetRect(int ox, int oy, int ow, int oh) {
		super(ox, oy, ow, oh);
		
	}
	
	public void hitdetect (){
			
		if (ox <=(SimpleGameEngine.playerX + SimpleGameEngine.player.radius-SimpleGameEngine.px) && (SimpleGameEngine.playerX - SimpleGameEngine.player.radius-SimpleGameEngine.px) <= (ox + ow) && oy <=(SimpleGameEngine.playerY + SimpleGameEngine.player.radius-SimpleGameEngine.py) && (SimpleGameEngine.playerY - SimpleGameEngine.player.radius-SimpleGameEngine.py) <= (oy + oh)) {
		hit=true;

			if(ox >=(SimpleGameEngine.playerX - SimpleGameEngine.player.radius-SimpleGameEngine.px) && oy != (SimpleGameEngine.playerY + SimpleGameEngine.player.radius-SimpleGameEngine.py) && (oy+oh)!=SimpleGameEngine.playerY - SimpleGameEngine.player.radius-SimpleGameEngine.py) {
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
	
	

	
	

	

