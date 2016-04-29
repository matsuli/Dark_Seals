import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;

public class SimpleGameEngine extends JFrame {
	
	//basic variables
	static int windowWidth = 500;
	static int windowHeight = 500;
	boolean isRunning = true;
	long fps = 60;
	Insets insets;
	//used for input
	public static InputHandler input;
	//Buffering
	BufferedImage backBuffer = new BufferedImage (windowWidth,windowHeight,BufferedImage.TYPE_INT_RGB);

	Graphics2D bbg = (Graphics2D)backBuffer.getGraphics ();
	
	
	//new player
	Player player = new Player ();
	//Player variables
	static int playerX = windowWidth/2;
	static int playerY = windowHeight/2;
	
	
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
		setResizable (false);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		setVisible (true);
		insets = getInsets ();
		setSize (insets.left+windowWidth+insets.right, insets.top+windowHeight+insets.bottom);
		
		input = new InputHandler (this);
	}
	
	//check for input, move things, etc.
	void update () {
		player.Movement ();
		paint(bbg);
	}
	
	//draw everything
	public void paint (Graphics g) {
		
		//background
		bbg.setColor(Color.WHITE);
		bbg.fillRect(0, 0, windowWidth, windowHeight);
		bbg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//draw a circle for orientation
		bbg.setColor(Color.BLACK);
		bbg.drawOval(200, 200, 150, 150);
		
		
		//player
		player.drawPlayer(bbg);


		bbg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		//should be last in the method
		g.drawImage(backBuffer, insets.left, insets.top, this);
	}
	

	}

