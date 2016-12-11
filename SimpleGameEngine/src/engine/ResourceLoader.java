package engine;

import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ResourceLoader {
	
	public Map <String, BufferedImage> resources = new HashMap<String, BufferedImage>();
	
	public void addResource (String name, String type, String source){
		if (type=="image"){
			BufferedImage img;
			try{
				img = ImageIO.read(new File(source));
				resources.put(name,img);
			} catch (IOException e) {
				System.out.println("Failed to load image");
			}
		}
	}
	
}
