package world;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
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
