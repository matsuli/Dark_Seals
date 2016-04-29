import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Player  {
	int radius = 20;
	
	public void drawPlayer (Graphics bbg) {
		
				bbg.setColor(Color.RED);
				bbg.fillOval(SimpleGameEngine.playerX,SimpleGameEngine.playerY,radius,radius);
	}
	
	public void Movement () {
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_D)) {
			SimpleGameEngine.px += 5;
		}
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_A)) {
			SimpleGameEngine.px -= 5;
		}
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_S)) {
			SimpleGameEngine.py += 5;
		}
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_W)) {
			SimpleGameEngine.py -= 5;
		}
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_ESCAPE)) {
			System.exit(0);
		}
	
	}
	
}