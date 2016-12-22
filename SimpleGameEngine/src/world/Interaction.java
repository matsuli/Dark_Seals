package world;

import engine.SimpleGameEngine;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Interaction extends noHitObj {

	Rectangle2D rect;
	int ow;
	int oh;
	boolean interactionPossible;

	public Interaction(int ox, int oy, int ow, int oh) {
		this.ox = ox;
		this.oy = oy;
		this.ow = ow;
		this.oh = oh;
		rect = new Rectangle2D.Double(ox, oy, ow, oh);
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
		g.drawRect(ox, oy, ow, oh);
		if (textureImg2 != null) {
			g.drawImage(textureImg2, ox, oy, ow, oh, null);
		}
		if (interact(SimpleGameEngine.player.hitDetCircle) && interactSymbol != null) {
			g.drawImage(interactSymbol,
					SimpleGameEngine.windowWidth / 2 - interactSymbol.getWidth() / 2 - SimpleGameEngine.px,
					SimpleGameEngine.windowHeight - interactSymbol.getHeight() - 10 - SimpleGameEngine.py,
					interactSymbol.getWidth(), interactSymbol.getHeight() - 10, null);
		}
	}
}
