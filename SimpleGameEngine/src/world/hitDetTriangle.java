package world;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class hitDetTriangle extends hitDetObj {
	double ox2;
	double oy2;
	double ox3;
	double oy3;
	hitDetectLine line1;
	hitDetectLine line2;
	hitDetectLine line3;

	public hitDetTriangle(double ox, double oy, double ox22, double oy22, double ox32, double oy32) {
		this.ox = ox;
		this.oy = oy;
		this.ox2 = ox22;
		this.oy2 = oy22;
		this.ox3 = ox32;
		this.oy3 = oy32;
		line1 = new hitDetectLine(ox, oy, ox22, oy22);
		line2 = new hitDetectLine(ox22, oy22, ox32, oy32);
		line3 = new hitDetectLine(ox32, oy32, ox, oy);
	}

	public boolean hitdetect(hitDetCircle hitDetCircle, int radius, hitDetCircle prevHitDetCircle) {

		line1.hitdetect(hitDetCircle, radius, prevHitDetCircle);
		line2.hitdetect(hitDetCircle, radius, prevHitDetCircle);
		line3.hitdetect(hitDetCircle, radius, prevHitDetCircle);
		if (line1.hit == true || line2.hit == true || line3.hit == true) {
			hit = true;
		} else {
			hit = false;
		}

		return hit;
	}

	public void draw(Graphics2D g) {

		line1.draw(g);
		line2.draw(g);
		line3.draw(g);
	}

}
