import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

//http://www.ecst.csuchico.edu/~amk/classes/csciOOP/double-buffering.html

public class DoubleBuffering extends Canvas {
	public void update(Graphics g) {
		Graphics2D offgc;
		Image offscreen = null;
		Dimension d = size();

		// create the offscreen buffer and associated Graphics
		offscreen = createImage(d.width, d.height);
		 offgc = (Graphics2D) offscreen.getGraphics();
		// clear the exposed area
		offgc.setColor(getBackground());
		offgc.fillRect(0, 0, d.width, d.height);
		offgc.setColor(getForeground());
		
		
		
		//background
		offgc.setColor(Color.WHITE);
		offgc.fillRect(0, 0, SimpleGameEngine.windowWidth, SimpleGameEngine.windowHeight);
		offgc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
		//draw a circle for orientation
		offgc.setColor(Color.BLACK);
		offgc.drawOval(200-SimpleGameEngine.px, 200-SimpleGameEngine.py, 150, 150);
		
		
		//player
		SimpleGameEngine.player.drawPlayer(SimpleGameEngine.bbg);


		offgc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		//should be last in the method
		
		// do normal redraw
		paint(offgc);
		// transfer offscreen to window
		g.drawImage(offscreen, 0, 0, this);
	    }
	}

