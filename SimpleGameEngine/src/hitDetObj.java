import java.util.ArrayList;

public class hitDetObj {
	
	int ox;
	int oy;
	int ow;
	int oh;
	
	
	
	public hitDetObj (int ox, int oy, int ow, int oh) {
		this.ox=ox;
		this.oy=oy;
		this.ow=ow;
		this.oh=oh;
	}
	
	public void hitdetect (){
		
	if (ox <=(SimpleGameEngine.playerX + SimpleGameEngine.player.radius-SimpleGameEngine.px) && (SimpleGameEngine.playerX - SimpleGameEngine.player.radius-SimpleGameEngine.px) <= (ox + ow) && oy <=(SimpleGameEngine.playerY + SimpleGameEngine.player.radius-SimpleGameEngine.py) && (SimpleGameEngine.playerY - SimpleGameEngine.player.radius-SimpleGameEngine.py) <= (oy + oh)) {
			SimpleGameEngine.player.hit=true;
	
	
	if(ox >=(SimpleGameEngine.playerX - SimpleGameEngine.player.radius-SimpleGameEngine.px)){
		SimpleGameEngine.player.hitRight=true;			
	}
	if(ox+ow <=(SimpleGameEngine.playerX + SimpleGameEngine.player.radius-SimpleGameEngine.px)){
		SimpleGameEngine.player.hitLeft=true;	
	}
	if(oy >=(SimpleGameEngine.playerY - SimpleGameEngine.player.radius-SimpleGameEngine.py)){
		SimpleGameEngine.player.hitDown=true;	
	}
	if(oy+oh <=(SimpleGameEngine.playerY + SimpleGameEngine.player.radius-SimpleGameEngine.py)){
		SimpleGameEngine.player.hitUp=true;	
	}	
		
		}
	}
	

	}
	

	

