import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class object implements Serializable {
	String texture;
	BufferedImage textureImg;
	boolean hitDet;
	public void draw(Graphics2D g) {
		
		
	}
	public boolean hitdetect(Ellipse2D hitDetBullet, int radius, Ellipse2D prevHitDetBullet){
		return false;
		
	}

	

}
