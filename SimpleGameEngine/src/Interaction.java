import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Interaction extends noHitObj {
	
	Rectangle2D rect;
	int ow;
	int oh;
	boolean interactionPossible;
	
	public Interaction(int ox, int oy, int ow, int oh) {
		this.ox=ox;
		this.oy=oy;
		this.ow=ow;
		this.oh=oh;
		rect = new Rectangle2D.Double(ox, oy, ow, oh);
	}
	
	public boolean interact (Ellipse2D hitDetCircle, int radius, Ellipse2D unused) {
		hitDetCircle = SimpleGameEngine.player.hitDetCircle;
		interactionPossible = false;
		if (hitDetCircle.intersects(rect)) {
			interactionPossible = true;
		}
		
		return interactionPossible;
	}
}
