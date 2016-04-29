import java.awt.Color;
import java.awt.Graphics;

public class Player  {
	
	public void drawPlayer (Graphics bbg) {
		
				bbg.setColor(Color.RED);
				bbg.fillRect(SimpleGameEngine.playerX,SimpleGameEngine.playerY,20,20);
	}
	
}