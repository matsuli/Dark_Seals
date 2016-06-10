import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class hitDetObj implements Serializable {

	int ox;
	int oy;
	boolean hit;
	boolean hitLeft;
	boolean hitRight;
	boolean hitUp;
	boolean hitDown;	
	int hitCorrectionLeft;
	int hitCorrectionRight;
	int hitCorrectionUp;
	int hitCorrectionDown;
	String texture;
	//BufferedImage textureImg;
	Image textureImg2;
	
	
	public boolean hitdetect(Ellipse2D hitDetCircle, int radius, Ellipse2D prevHitDetCircle){
		return hit;
		
	}
	
	public void draw(Graphics2D g){
		
	}
	
}
	
	

