import java.awt.Color;
import java.awt.Graphics;

public class world extends hitDetObj {
	
	hitDetObj r = new hitDetObj ();
	
	public void drawWorld (Graphics g) {
		g.drawLine(70, 40, 70, 140);
		object (20, 40, 100, 100);
		g.setColor(Color.blue);
		g.drawRect(20, 40, 100, 100);
	}
	
}
