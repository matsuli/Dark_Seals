
public class hitDetObj {
	
	public hitDetObj () {
		
	}
	
	public void object (int ox, int oy, int ow, int oh) {
		//kan ej r�ra sig �t h�ger
		if (ox <= (SimpleGameEngine.playerX + SimpleGameEngine.player.radius-SimpleGameEngine.px) && (SimpleGameEngine.playerX + SimpleGameEngine.player.radius-SimpleGameEngine.px) <= (ox + ow/2) && oy <=(SimpleGameEngine.playerY + SimpleGameEngine.player.radius-SimpleGameEngine.py) && (SimpleGameEngine.playerY + SimpleGameEngine.player.radius-SimpleGameEngine.py) <= (oy + oh)) {
			SimpleGameEngine.px += (SimpleGameEngine.playerX + SimpleGameEngine.player.radius-SimpleGameEngine.px)-ox;
		}
		//kan ej r�ra sig �t v�nster
		if ((ox + ow/2) <= (SimpleGameEngine.playerX - SimpleGameEngine.player.radius/2-SimpleGameEngine.px) && (SimpleGameEngine.playerX - SimpleGameEngine.player.radius/2-SimpleGameEngine.px) <= (ox + ow) && oy <=(SimpleGameEngine.playerY+ SimpleGameEngine.player.radius-SimpleGameEngine.py) && (SimpleGameEngine.playerY + SimpleGameEngine.player.radius-SimpleGameEngine.py) <= (oy + oh)) {
			SimpleGameEngine.px += (SimpleGameEngine.playerX-SimpleGameEngine.px)-(ox+ow);
		}
		//kan ej r�ra sig ned�t
		if (ox <= (SimpleGameEngine.playerX + SimpleGameEngine.player.radius) && (SimpleGameEngine.playerX + SimpleGameEngine.player.radius) <= (ox + ow) && oy <=(SimpleGameEngine.playerY + SimpleGameEngine.player.radius) && (SimpleGameEngine.playerY + SimpleGameEngine.player.radius) <= (oy + oh/2)) {
			SimpleGameEngine.player.down = -5;
		}
		//kan ej r�ra sig upp�t
		if (ox <= (SimpleGameEngine.playerX + SimpleGameEngine.player.radius) && (SimpleGameEngine.playerX + SimpleGameEngine.player.radius) <= (ox + ow) && (oy + oh/2) <=(SimpleGameEngine.playerY + SimpleGameEngine.player.radius) && (SimpleGameEngine.playerY + SimpleGameEngine.player.radius) <= (oy + oh)) {
			SimpleGameEngine.player.up = -5;
		}
	}
}
