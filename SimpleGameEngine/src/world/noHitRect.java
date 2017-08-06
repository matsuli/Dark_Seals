package world;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class noHitRect extends noHitObj {

	Rectangle2D rect;
	double ow;
	double oh;

	public noHitRect(double ox, double oy, double ow2, double oh2) {
		this.ox = ox;
		this.oy = oy;
		this.ow = ow2;
		this.oh = oh2;
		rect = new Rectangle2D.Double(ox, oy, ow2, oh2);
	}

	public void draw(Graphics2D g) {
		g.draw(rect);
		if (textureImg != null) {
			g.drawImage(textureImg, (int) ox, (int)oy, (int) ow, (int) oh, null);}
		}
		public void move(double oxNew, double oyNew, double ow2, double oh2) {
		this.ox = oxNew;
		this.oy = oyNew;
		this.ow = ow2;
		this.oh = oh2;	
		rect.setRect(oxNew, oyNew, ow2, oh2);
	}

}
