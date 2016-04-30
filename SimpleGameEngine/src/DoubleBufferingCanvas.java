import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

//http://www.ecst.csuchico.edu/~amk/classes/csciOOP/double-buffering.html

public class DoubleBufferingCanvas extends Canvas {
	
	public void update (Graphics g) {
		
		Graphics2D offgc;
		BufferedImage offscreen = null;
		

		// create the offscreen buffer and associated Graphics
		offscreen = new BufferedImage (SimpleGameEngine.windowWidth, SimpleGameEngine.windowHeight,BufferedImage.TYPE_INT_RGB);
		offgc = offscreen.createGraphics();
	
		//background
		offgc.setColor(Color.WHITE);
		offgc.fillRect(0, 0, SimpleGameEngine.windowWidth, SimpleGameEngine.windowHeight);
		offgc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
		//draw a circle for orientation
		offgc.setColor(Color.BLACK);
		offgc.drawOval(200-SimpleGameEngine.px, 200-SimpleGameEngine.py, 150, 150);
		
		
		//player
		SimpleGameEngine.player.drawPlayer(offgc);


		offgc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		//should be last in the method
		
		// do normal redraw
		paint(offgc);
		
		// transfer offscreen to window

		g.drawImage(offscreen, 0, 0, null);
		
 	}
	}

