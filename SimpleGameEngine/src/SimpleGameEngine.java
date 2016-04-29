import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;

//test

public class SimpleGameEngine extends JFrame {
	
	//basic variables
	static int windowWidth = 500;
	static int windowHeight = 500;
	boolean isRunning = true;
	long fps = 60;
	Insets insets;
	//used for input
	InputHandler input;
	//Buffering
	BufferedImage backBuffer = new BufferedImage (windowWidth,windowHeight,BufferedImage.TYPE_INT_RGB);
	
	
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
			draw ();
			
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
		playerMovement ();
	}
	
	//draw everything
	void draw () {
		Graphics g = getGraphics ();
		Graphics bbg = backBuffer.getGraphics ();
		//background
		bbg.setColor(Color.WHITE);
		bbg.fillRect(0, 0, windowWidth, windowHeight);
		
		//draw a circle for orientation
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.BLACK);
		g2d.drawOval(200, 200, 150, 150);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		
		//player
		bbg.setColor(Color.RED);
		bbg.fillRect(playerX,playerY,20,20);
		
		//should be last in the method
		g.drawImage(backBuffer, insets.left, insets.top, this);
	}
	
	void playerMovement () {
		if (input.isKeyDown(KeyEvent.VK_D)) {
			playerX += 5;
		}
		if (input.isKeyDown(KeyEvent.VK_A)) {
			playerX -= 5;
		}
		if (input.isKeyDown(KeyEvent.VK_S)) {
			playerY += 5;
		}
		if (input.isKeyDown(KeyEvent.VK_W)) {
			playerY -= 5;
		}
	}
}
