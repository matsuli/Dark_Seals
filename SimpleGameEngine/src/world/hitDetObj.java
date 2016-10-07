package world;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class hitDetObj implements Serializable {
	public String texture;
	Image textureImg2;
	BufferedImage textureImg;
	int ox;
	int oy;
	boolean hit;
	boolean hitLeft;
	boolean hitRight;
	boolean hitUp;
	boolean hitDown;	
	double hitCorrectionLeft;
	double hitCorrectionRight;
	double hitCorrectionUp;
	double hitCorrectionDown;

	public Image addTransparency (BufferedImage image, Color color) {
		ImageFilter filter = new RGBImageFilter() {
			public int markerRGB = color.getRGB() | 0xFF000000;
			public final int filterRGB(int x, int y, int rgb) {
				if ((rgb | 0xFF000000) == markerRGB) {
    				// Mark the alpha bits as zero - transparent
    				return 0x00FFFFFF & rgb;
    			} else {
    				// nothing to do
    				return rgb;
    			}
			}
		};
		
		ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
			return Toolkit.getDefaultToolkit().createImage(ip);
	}
	
	public static BufferedImage imageToBufferedImage(Image image) {

    	BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    	Graphics2D g2 = bufferedImage.createGraphics();
    	g2.drawImage(image, 0, 0, null);
    	g2.dispose();

    	return bufferedImage;
    }
	
	public boolean hitdetect(Ellipse2D hitDetCircle, int radius, Ellipse2D prevHitDetCircle){
		return hit;
		
	}
	
	public void draw(Graphics2D g){
		
	}
	
}
	
	
