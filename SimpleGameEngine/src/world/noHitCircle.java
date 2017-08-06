package world;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class noHitCircle extends noHitObj {

	double r;
	Ellipse2D circle;
	double centerX;
	double centerY;
	double ow;
	double oh;

	public noHitCircle(double ox, double oy, double ow, double oh) {
		this.ox = ox;
		this.oy = oy;
		this.ow = ow;
		this.oh = oh;
		r = ow / 2;
		ox = ox - (r);
		oy = oy - (r);
		circle = new Ellipse2D.Double(ox, oy, ow, oh);
		centerX = circle.getCenterX();
		centerY = circle.getCenterY();
	}

	public void draw(Graphics2D g) {

		g.fill(circle);
		if (textureImg != null) {
			g.drawImage(textureImg, (int) ox, (int) oy, (int) ow, (int) oh, null);
		}
	}
	public void move(double ox, double oy, double ow, double oh) {
		this.ox = ox;
		this.oy = oy;
		this.ow = ow;
		this.oh = oh;
		r = ow / 2;
		ox = ox - (r);
		oy = oy - (r);
		circle.setFrame(ox, oy, ow, oh);
		centerX = circle.getCenterX();
		centerY = circle.getCenterY();
		}

}
