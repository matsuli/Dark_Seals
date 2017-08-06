package world;

import engine.SimpleGameEngine;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Interaction extends noHitObj {

	Rectangle2D rect;
	double ow;
	double oh;
	boolean interactionPossible;

	public Interaction(double ox, double oy, double ow2, double oh2) {
		this.ox = ox;
		this.oy = oy;
		this.ow = ow2;
		this.oh = oh2;
		rect = new Rectangle2D.Double(ox, oy, ow2, oh2);
	}

	public boolean interact(hitDetCircle hitDetCircle) {
		hitDetCircle = SimpleGameEngine.player.hitDetCircle;
		if (hitDetCircle.circle.intersects(rect)) {
			interactionPossible = true;
		} else {
			interactionPossible = false;
		}
		return interactionPossible;
	}

	public void draw(Graphics2D g) {
		g.draw(rect);
		if (textureImg2 != null) {
			g.drawImage(textureImg2, (int) ox, (int) oy, (int) ow, (int) oh, null);
		}
		if (interact(SimpleGameEngine.player.hitDetCircle) && interactSymbol != null) {
			g.drawImage(interactSymbol,
					SimpleGameEngine.windowWidth / 2 - interactSymbol.getWidth() / 2 - SimpleGameEngine.px,
					SimpleGameEngine.windowHeight - interactSymbol.getHeight() - 10 - SimpleGameEngine.py,
					interactSymbol.getWidth(), interactSymbol.getHeight() - 10, null);
		}
	}
}
