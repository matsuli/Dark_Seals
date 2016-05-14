
public class hitDetObj {
	
	public hitDetObj () {
		
	}
	
	public void object (int ox, int oy, int ow, int oh) {
		//kan ej röra sig åt höger
				if (ox <= (SimpleGameEngine.playerX + SimpleGameEngine.player.radius) && (SimpleGameEngine.playerX + SimpleGameEngine.player.radius) <= (ox + ow/2) && oy <=(SimpleGameEngine.playerY + SimpleGameEngine.player.radius) && (SimpleGameEngine.playerY + SimpleGameEngine.player.radius) <= (oy + oh)) {
					SimpleGameEngine.player.right = -5;
				}
				//kan ej röra sig åt vänster
				if ((ox + ow/2) <= (SimpleGameEngine.playerX + SimpleGameEngine.player.radius) && (SimpleGameEngine.playerX + SimpleGameEngine.player.radius) <= (ox + ow) && oy <=(SimpleGameEngine.playerY+ SimpleGameEngine.player.radius) && (SimpleGameEngine.playerY + SimpleGameEngine.player.radius) <= (oy + oh)) {
					SimpleGameEngine.player.left = -5;
				}
				//kan ej röra sig nedåt
				if (ox <= (SimpleGameEngine.playerX + SimpleGameEngine.player.radius) && (SimpleGameEngine.playerX + SimpleGameEngine.player.radius) <= (ox + ow) && oy <=(SimpleGameEngine.playerY + SimpleGameEngine.player.radius) && (SimpleGameEngine.playerY + SimpleGameEngine.player.radius) <= (oy + oh/2)) {
					SimpleGameEngine.player.down = -5;
				}
				//kan ej röra sig uppåt
				if (ox <= (SimpleGameEngine.playerX + SimpleGameEngine.player.radius) && (SimpleGameEngine.playerX + SimpleGameEngine.player.radius) <= (ox + ow) && (oy + oh/2) <=(SimpleGameEngine.playerY + SimpleGameEngine.player.radius) && (SimpleGameEngine.playerY + SimpleGameEngine.player.radius) <= (oy + oh)) {
					SimpleGameEngine.player.up = -5;
				}
	}
}
