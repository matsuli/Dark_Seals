package engine;

import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ResourceLoader {

	public Map<String, BufferedImage> resources = new HashMap<String, BufferedImage>();

	public void addResource(String name, String type, String source) {
		if (type == "image") {
			BufferedImage img;
			Image img2;
			BufferedImage imgFinal;
			try {
				img = ImageIO.read(new File(source));
				img2 = addTransparency(img, Color.white);
				imgFinal = imageToBufferedImage(img2);
				resources.put(name, imgFinal);
			} catch (IOException e) {
				System.out.println("Failed to load image");
			}
		}
	}

	public Image addTransparency(BufferedImage image, Color color) {
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

		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bufferedImage.createGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();

		return bufferedImage;
	}

}
