import java.awt.Color;
import java.awt.Graphics;

public class world {
	
	hitDetObj r = new hitDetObj (20, 40, 100, 100);
	
	public void drawWorld (Graphics g) {
		g.setColor(Color.blue);
		g.drawRect(20, 40, 100, 100);
	}
	
}
