package world;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class noHitObj implements Serializable {
	public String textureName;
	public transient BufferedImage textureImg; // den bild som ritas
	public transient Image textureImg2; // den bild vars bakgrund görs transparent i world
	public transient BufferedImage interactSymbol; // den bild som ritas
	public transient Image intSymImg; // den bild vars bakgrund görs transparent i world
	double ox;
	double oy;

	public void draw(Graphics2D g) {

	}

}
