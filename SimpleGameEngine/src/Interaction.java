import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Interaction extends noHitObj {
	
	Rectangle2D rect;
	int ow;
	int oh;
	boolean interactionPossible;
	//Image interactSymbol = Toolkit.getDefaultToolkit().createImage("images/engage.png");
	
	public Interaction(int ox, int oy, int ow, int oh) {
		this.ox=ox;
		this.oy=oy;
		this.ow=ow;
		this.oh=oh;
		rect = new Rectangle2D.Double(ox, oy, ow, oh);
	}
	
	public boolean interact (Ellipse2D hitDetCircle, int radius, Ellipse2D unused) {
		hitDetCircle = SimpleGameEngine.player.hitDetCircle;
		if (hitDetCircle.intersects(rect)) {
			interactionPossible = true;
		} else {
			interactionPossible = false;
		}
		return interactionPossible;
	}
	
	public void draw(Graphics2D g){
		g.drawRect(ox, oy, ow, oh);
		if(textureImg2!=null){
			g.drawImage(textureImg2, ox, oy, ow, oh, null);
		}
		if (interactionPossible) {
		//	g.drawImage(interactSymbol, SimpleGameEngine.windowWidth/2 - interactSymbol.getWidth(null)/2, SimpleGameEngine.windowHeight - interactSymbol.getHeight(null) - 10, interactSymbol.getWidth(null), SimpleGameEngine.windowHeight - 10, null);
		}
	}
}
