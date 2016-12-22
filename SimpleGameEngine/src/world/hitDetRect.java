package world;

import engine.SimpleGameEngine;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class hitDetRect extends hitDetObj {

	Rectangle2D rect;
	int ow;
	int oh;

	public hitDetRect(int ox, int oy, int ow, int oh) {
		this.ox = ox;
		this.oy = oy;
		this.ow = ow;
		this.oh = oh;
		rect = new Rectangle2D.Double(ox, oy, ow, oh);
	}

	public double getCornerTangentX(int radius, double centerY, double cornerX, double cornerY) {
		// (cornerX-targetX)pow2+(cornerY-centerY)pow2=radius*radius =Cirkelns
		// ekvation, där targetX är det centerX för vilket rektangelns hörn är
		// punkt på cirkeln
		double i = (radius * radius) - ((cornerY - centerY) * (cornerY - centerY)); // i=(cornerX-targetX)pow2
		double targetX;
		if (cornerX == ox) { // vänster sida av rekt, cornerX-targetX>0
			targetX = (-Math.sqrt(i) + cornerX);
		} else { // eller höger sida av rekt, cornerX-targetX<0
			targetX = (Math.sqrt(i) + cornerX);
		}
		return targetX;

	}

	public double getCornerTangentY(int radius, double centerX, double cornerX, double cornerY) {
		// (cornerY-targetY)pow2+(cornerX-centerX)pow2=radius*radius =Cirkelns
		// ekvation, där targetX är det centerX för vilket rektangelns hörn är
		// punkt på cirkeln
		double i = (radius * radius) - ((cornerX - centerX) * (cornerX - centerX)); // i=(cornerY-targetY)pow2
		double targetY;
		if (cornerY == oy) { // ovanför rekt, cornerY-targetY>0
			targetY = (-Math.sqrt(i) + cornerY);
			// System.out.println(Math.sqrt(i));
		} else { // eller under rekt, cornerY-targetY<0
			targetY = (Math.sqrt(i) + cornerY);
		}
		return targetY;

	}

	public boolean hitdetect(hitDetCircle hitDetCircle, int radius, hitDetCircle prevHitDetCircle) {

		hit = false;
		if (hitDetCircle.circle.intersects(rect)) {
			hit = true;

			if (ox >= (prevHitDetCircle.circle.getCenterX())) {
				hitRight = true;
				if (oy <= hitDetCircle.circle.getCenterY() && oy + oh >= hitDetCircle.circle.getCenterY()) {
					hitCorrectionRight = (hitDetCircle.circle.getCenterX() + radius) - ox;
				} else {
					if (!(oy <= hitDetCircle.circle.getCenterY())) {
						double i = getCornerTangentX(radius, hitDetCircle.circle.getCenterY(), ox, oy); // denna
																										// är
																										// det
																										// centerX
																										// för
																										// player
																										// där
																										// cirkeln
																										// träffar
																										// hörnet
																										// (hörnet
																										// är
																										// punkt
																										// på
																										// cirkel)
						hitCorrectionRight = hitDetCircle.circle.getCenterX() - i;
					}
					if (!(oy + oh >= hitDetCircle.circle.getCenterY())) {
						double i = getCornerTangentX(radius, hitDetCircle.circle.getCenterY(), ox, oy + oh); // denna
																												// är
																												// det
																												// centerX
																												// för
																												// player
																												// där
																												// cirkeln
																												// träffar
																												// hörnet
																												// (hörnet
																												// är
																												// punkt
																												// på
																												// cirkel)
						hitCorrectionRight = hitDetCircle.circle.getCenterX() - i;
					}
				}
			}
			if (ox + ow <= (prevHitDetCircle.circle.getCenterX())) {
				hitLeft = true;

				if (oy <= hitDetCircle.circle.getCenterY() && oy + oh >= hitDetCircle.circle.getCenterY()) { // om
																												// player
																												// är
																												// på
																												// "yttre
																												// sidan"
																												// av
																												// rektangeln
					hitCorrectionLeft = ((ox + ow) - (hitDetCircle.circle.getCenterX() - radius));
				} else {
					if (!(oy <= hitDetCircle.circle.getCenterY())) {
						double i = getCornerTangentX(radius, hitDetCircle.circle.getCenterY(), ox + ow, oy); // denna
																												// är
																												// det
																												// centerX
																												// för
																												// player
																												// där
																												// cirkeln
																												// träffar
																												// hörnet
																												// (hörnet
																												// är
																												// punkt
																												// på
																												// cirkel)
						hitCorrectionLeft = i - hitDetCircle.circle.getCenterX();
					}
					if (!(oy + oh >= hitDetCircle.circle.getCenterY())) {
						double i = getCornerTangentX(radius, hitDetCircle.circle.getCenterY(), ox + ow, oy + oh); // denna
																													// är
																													// det
																													// centerX
																													// för
																													// player
																													// där
																													// cirkeln
																													// träffar
																													// hörnet
																													// (hörnet
																													// är
																													// punkt
																													// på
																													// cirkel)
						hitCorrectionLeft = i - hitDetCircle.circle.getCenterX();
					}
				}
			}
			if (oy >= (prevHitDetCircle.circle.getCenterY())) {
				hitDown = true;

				if (ox <= hitDetCircle.circle.getCenterX() && ox + ow >= hitDetCircle.circle.getCenterX()) { // om
																												// player
																												// är
																												// på
																												// "yttre
																												// sidan"
																												// av
																												// rektangeln
					hitCorrectionDown = ((hitDetCircle.circle.getCenterY() + radius) - oy);
				} else {
					if (!(ox <= hitDetCircle.circle.getCenterX())) {
						double i = getCornerTangentY(radius, hitDetCircle.circle.getCenterX(), ox, oy); // denna
																										// är
																										// det
																										// centerX
																										// för
																										// player
																										// där
																										// cirkeln
																										// träffar
																										// hörnet
																										// (hörnet
																										// är
																										// punkt
																										// på
																										// cirkel)
						hitCorrectionDown = -i + hitDetCircle.circle.getCenterY();
					}
					if (!(ox + ow >= hitDetCircle.circle.getCenterX())) {
						double i = getCornerTangentY(radius, hitDetCircle.circle.getCenterX(), ox + ow, oy); // denna
																												// är
																												// det
																												// centerX
																												// för
																												// player
																												// där
																												// cirkeln
																												// träffar
																												// hörnet
																												// (hörnet
																												// är
																												// punkt
																												// på
																												// cirkel)
						hitCorrectionDown = -i + hitDetCircle.circle.getCenterY();
					}
				}

			}
			if (oy + oh <= (prevHitDetCircle.circle.getCenterY())) {
				hitUp = true;
				if (ox <= hitDetCircle.circle.getCenterX() && ox + ow >= hitDetCircle.circle.getCenterX()) { // om
																												// player
																												// är
																												// på
																												// "yttre
																												// sidan"
																												// av
																												// rektangeln
					hitCorrectionUp = ((oy + oh) - (hitDetCircle.circle.getCenterY() - radius));
				} else {
					if (!(ox <= hitDetCircle.circle.getCenterX())) {
						double i = getCornerTangentY(radius, hitDetCircle.circle.getCenterX(), ox, oy + oh); // denna
																												// är
																												// det
																												// centerX
																												// för
																												// player
																												// där
																												// cirkeln
																												// träffar
																												// hörnet
																												// (hörnet
																												// är
																												// punkt
																												// på
																												// cirkel)
						hitCorrectionUp = i - hitDetCircle.circle.getCenterY();
					}
					if (!(ox + ow >= hitDetCircle.circle.getCenterX())) {
						double i = getCornerTangentY(radius, hitDetCircle.circle.getCenterX(), ox + ow, oy + oh); // denna
																													// är
																													// det
																													// centerX
																													// för
																													// player
																													// där
																													// cirkeln
																													// träffar
																													// hörnet
																													// (hörnet
																													// är
																													// punkt
																													// på
																													// cirkel)
						hitCorrectionUp = i - hitDetCircle.circle.getCenterY();
					}
				}

			}
		}
		if (hitDetCircle == SimpleGameEngine.player.hitDetCircle) {
			if (hit == true) {
				SimpleGameEngine.player.hit = true;
				hit = false;
			}
			if (hitRight == true) {
				SimpleGameEngine.player.hitRight = true;
				SimpleGameEngine.player.hitCorrectionRight = hitCorrectionRight;
				hitRight = false;
			}
			if (hitDown == true) {
				SimpleGameEngine.player.hitDown = true;
				hitDown = false;
				SimpleGameEngine.player.hitCorrectionDown = hitCorrectionDown;
			}
			if (hitUp == true) {
				SimpleGameEngine.player.hitUp = true;
				hitUp = false;
				SimpleGameEngine.player.hitCorrectionUp = hitCorrectionUp;
			}
			if (hitLeft == true) {
				SimpleGameEngine.player.hitLeft = true;
				hitLeft = false;
				SimpleGameEngine.player.hitCorrectionLeft = hitCorrectionLeft;
			}
		}
		return hit;
	}

	public void draw(Graphics2D g) {
		// g.fill(rect);
		if (textureImg != null) {
			g.drawImage(textureImg, (int) ox, (int) oy, (int) ow, (int) oh, null);
		}
	}

}
