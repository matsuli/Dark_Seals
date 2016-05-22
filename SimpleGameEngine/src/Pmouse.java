import processing.core.*;

public class Pmouse extends PApplet {
	
	public void mouseLeft () {
		if (mousePressed == true && mouseButton == LEFT && SimpleGameEngine.player.canShoot == true) {
			SimpleGameEngine.player.shoot(SimpleGameEngine.player, mouseX,mouseY);
		}
	}
	
}
