package world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.Serializable;

public class noHitObj implements Serializable {
	public String textureName;
	public BufferedImage textureImg; // den bild som ritas
	public Image textureImg2; // den bild vars bakgrund görs transparent i world
	public BufferedImage interactSymbol; // den bild som ritas
	public Image intSymImg; // den bild vars bakgrund görs transparent i world
	int ox;
	int oy;

	public void draw(Graphics2D g) {

	}

}
