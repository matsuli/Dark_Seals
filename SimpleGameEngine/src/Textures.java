import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Textures {
	
	public void loadTexture () {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("images/chicken.gif"));
		} catch (IOException e) {
		}
	}
	
}
