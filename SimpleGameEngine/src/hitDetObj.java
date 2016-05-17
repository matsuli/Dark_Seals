import java.util.ArrayList;

public class hitDetObj {
	
	int ox;
	int oy;
	int ow;
	int oh;
	boolean hit;
	boolean hitLeft;
	boolean hitRight;
	boolean hitUp;
	boolean hitDown;
	
	
	public hitDetObj (int ox, int oy, int ow, int oh) {
		this.ox=ox;
		this.oy=oy;
		this.ow=ow;
		this.oh=oh;
	}
	
	public void hitdetect (){
		
		
		
	if (ox <=(SimpleGameEngine.playerX + SimpleGameEngine.player.radius-SimpleGameEngine.px) && (SimpleGameEngine.playerX - SimpleGameEngine.player.radius-SimpleGameEngine.px) <= (ox + ow) && oy <=(SimpleGameEngine.playerY + SimpleGameEngine.player.radius-SimpleGameEngine.py) && (SimpleGameEngine.playerY - SimpleGameEngine.player.radius-SimpleGameEngine.py) <= (oy + oh)) {
			hit=true;
	
	
		if(ox >=(SimpleGameEngine.playerX - SimpleGameEngine.player.radius-SimpleGameEngine.px) && oy != (SimpleGameEngine.playerY + SimpleGameEngine.player.radius-SimpleGameEngine.py) && (oy+oh)!=SimpleGameEngine.playerY - SimpleGameEngine.player.radius-SimpleGameEngine.py) {
		hitRight=true;			
	}
		if(ox+ow <=(SimpleGameEngine.playerX + SimpleGameEngine.player.radius-SimpleGameEngine.px) && oy != (SimpleGameEngine.playerY + SimpleGameEngine.player.radius-SimpleGameEngine.py) && (oy+oh)!=SimpleGameEngine.playerY - SimpleGameEngine.player.radius-SimpleGameEngine.py){
		hitLeft=true;	
				
	}
		if(oy >=(SimpleGameEngine.playerY - SimpleGameEngine.player.radius-SimpleGameEngine.py) && ox !=(SimpleGameEngine.playerX + SimpleGameEngine.player.radius-SimpleGameEngine.px) && SimpleGameEngine.playerX - SimpleGameEngine.player.radius-SimpleGameEngine.px != (ox + ow) ){
		hitDown=true;
		
		}
		if(oy+oh <=(SimpleGameEngine.playerY + SimpleGameEngine.player.radius-SimpleGameEngine.py)&& ox !=(SimpleGameEngine.playerX + SimpleGameEngine.player.radius-SimpleGameEngine.px) && SimpleGameEngine.playerX - SimpleGameEngine.player.radius-SimpleGameEngine.px != (ox + ow)){
		hitUp=true;	
		}	
	}
	
	if(hit==true){
		SimpleGameEngine.player.hit=true;
		hit=false;
		}
	
	if(hitRight==true){
		SimpleGameEngine.player.hitRight=true;
		hitRight=false;
		}
	if(hitDown==true){
		SimpleGameEngine.player.hitDown=true;
		hitDown=false;
		}
	if(hitUp==true){
		SimpleGameEngine.player.hitUp=true;
		hitUp=false;
		}
	if(hitLeft==true){
		SimpleGameEngine.player.hitLeft=true;
		hitLeft=false;	
		}
	}
	
	
	
		
		}
	
	

	
	

	

