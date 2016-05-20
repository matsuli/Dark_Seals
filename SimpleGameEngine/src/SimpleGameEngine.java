import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class SimpleGameEngine extends JFrame {
	
	//basic variables
	static int windowWidth = 500;
	static int windowHeight = 500;
	boolean isRunning = true;
	long fps = 60;
	Insets insets;
	//used for input
	public static InputHandler input;
	public static ActorHandler ActorHandler;
	 //Buffering
	BufferedImage backBuffer = new BufferedImage (windowWidth,windowHeight,BufferedImage.TYPE_INT_RGB);
	Graphics2D bbg = backBuffer.createGraphics();
	
	//new player
	static Player player;
	//Player variables
	static int playerX = windowWidth/2;
	static int playerY = windowHeight/2;
	//
	static int px = 0;
	static int py = 0;
	
	
	
	 world space = new world ();
	
	public static void main (String [] args) {
		SimpleGameEngine game = new SimpleGameEngine ();
		game.run ();
		System.exit(0);
	}
	
	//starts game, loops game
	public void run () {
		initialize ();
		
		while (isRunning) {
			long time = System.currentTimeMillis ();
			
			update ();
			repaint ();
			
			//delay for each frame - time it took for one frame
			time = (1000/fps)-(System.currentTimeMillis()-time);
			
			if (time > 0) {
				try {
					Thread.sleep(time);
				}
				catch (Exception e) {}
			}
		}
		setVisible (false);
	}
	
	
	//initial setup
	void initialize () {
		setTitle ("SimpleGameEngine");
		setSize(windowWidth, windowHeight);
		setResizable (true);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		setVisible (true);
		insets = getInsets ();
		setSize (insets.left+windowWidth+insets.right, insets.top+windowHeight+insets.bottom);
		space.initialize();
		input = new InputHandler (this);
		player= new Player ();
		
	}
	
	//check for input, move things, etc.
	void update () {
		
		player.Control(space);		
		paint(bbg);
	}
	
	//draw everything
	public void paint (Graphics g) {
		
		BufferedImage offscreen = null;
		offscreen = new BufferedImage (SimpleGameEngine.windowWidth, SimpleGameEngine.windowHeight,BufferedImage.TYPE_INT_RGB);
		Graphics2D offgc = offscreen.createGraphics();
		
		//background
		offgc.setColor(Color.WHITE);
		offgc.fillRect(0, 0, windowWidth, windowHeight);
		offgc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		offgc.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		offgc.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		offgc.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		
		
		offgc.translate(px, py);			//TRANSLATER med px, py, dvs. sätter px o py som kordinatsystemets 0,0 o gör att allt annat än player rör sig runt player
		//draw a circle for orientation		//OBS! Eter denna translate ska bara saker som inte rör sig ritas, annars hamnar deras position ur synk med var de ritas
		offgc.setColor(Color.BLACK);
		offgc.drawOval(200, 200, 150, 150);
		offgc.drawRect(50, 50, 150, 150);
		//world
		space.drawWorld (offgc);
	//	space.HitDetect();
	//	offgc.draw(new Ellipse2D.Double(320, 300, 50, 50));
		
		offgc.translate(-px, -py);			//sätter kordinatsystemet tillbaks till det vanliga, inte strictly necessary, men känns safer. NU kan positioner ändras igen.
		
		//player
		player.drawPlayer(offgc,playerX, playerY, player.radius*2 );
		
		offgc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		//should be last in the method
		g.drawImage(offscreen, insets.left, insets.top, this);
	}
	
	//to do
	//mouse input
	//bullets hitdetect
	//trianglar
	//texture funktion
	//save function
	//interaction
	//settings
	//stamina?
	//sneak?
	//npcs, enemies
	//AI
}

