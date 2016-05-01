import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Player extends Actor {
	int radius = 20;
	//used in movement
	int right = 5;
	int left = 5;
	int down = 5;
	int up = 5;
	
	public void drawPlayer (Graphics g) {
		
				g.setColor(Color.RED);
				g.fillOval(SimpleGameEngine.playerX,SimpleGameEngine.playerY,radius,radius);
	}
	
	public void Movement () {
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_D)) {
			SimpleGameEngine.px -= right;
		}
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_A)) {
			SimpleGameEngine.px += left;
		}
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_S)) {
			SimpleGameEngine.py -= down;
		}
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_W)) {
			SimpleGameEngine.py += up;
		}
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_ESCAPE)) {
			System.exit(0);
		}
	
	}
	
}