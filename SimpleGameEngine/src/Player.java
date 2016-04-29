import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Player  {
	
	public void drawPlayer (Graphics bbg) {
		
				bbg.setColor(Color.RED);
				bbg.fillRect(SimpleGameEngine.playerX,SimpleGameEngine.playerY,20,20);
	}
	
	public void Movement () {
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_D)) {
			SimpleGameEngine.playerX += 5;
		}
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_A)) {
			SimpleGameEngine.playerX -= 5;
		}
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_S)) {
			SimpleGameEngine.playerY += 5;
		}
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_W)) {
			SimpleGameEngine.playerY -= 5;
		}
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_ESCAPE)) {
			System.exit(0);
		}
	
	}
	
}