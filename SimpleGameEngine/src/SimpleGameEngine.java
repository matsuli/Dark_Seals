import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JFrame;

public class SimpleGameEngine extends JFrame {
	
	//basic variables
	static int windowWidth = 500;
	static int windowHeight = 500;
	boolean isRunning = true;
	long fps = 60;
	public static Insets insets;
	//used for input
	public static InputHandler input;
	public static mouseInput mouse;
	public static ActorHandler ActorHandler;
	 //Buffering
	BufferedImage backBuffer = new BufferedImage (windowWidth,windowHeight,BufferedImage.TYPE_INT_RGB);
	Graphics2D bbg = backBuffer.createGraphics();
	//saving/loading
	public static savingSystem savingSystem;
	//menuHandler
	public static menuHandler menuHandler;
	//the main menu	
	public static menu mainMenu;
	
	//new player
	static Player player;
	//Player variables
	static int playerX = windowWidth/2;
	static int playerY = windowHeight/2;
	//
	static int px = 0;
	static int py = 0;
	//mouse coordinates
	static float mouseX;
	static float mouseY;
	
	static boolean play;
	
	static String currentMenu;
	
	static String currentWorld;
	
	world space = new world ();
	
	//this is the game
	public static void main (String [] args) throws IOException {
		SimpleGameEngine game = new SimpleGameEngine ();
		game.run ();
		System.exit(0);
	}
	
	//starts game, loops game
	public void run () throws IOException {
		initialize ();
		
		//game loop
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
		setTitle ("SimpleGameEngine");	//name on window
		setSize(windowWidth, windowHeight);	//window size
		setResizable (true);
		setDefaultCloseOperation (EXIT_ON_CLOSE);
		setVisible (true);
		insets = getInsets ();
		setSize (insets.left+windowWidth+insets.right, insets.top+windowHeight+insets.bottom);
		space.initialize();
		input = new InputHandler (this);
		mouse = new mouseInput ();
		addMouseListener( mouse );
		addMouseMotionListener( mouse );
		savingSystem= new savingSystem();
		menuHandler = new menuHandler();
		mainMenu= new menu("Main","New Game", "Load Game", "Settings", "Quit");
		currentMenu=mainMenu.thisMenu;
		menuHandler.menus.add(mainMenu);
		player = new Player ();
		
	}
	
	//check for input, move things, etc.
	void update () {
		
		if(play){
		player.Control(space);}
		mouse.poll();
		mouseX = mouse.getPosition().x-px-insets.left;
		mouseY = mouse.getPosition().y-py-insets.top;
		input.handleInput();
		paint(bbg);		
		//System.out.print(currentMenu);
	}
	
	//draw everything
	public void paint (Graphics g) {
		if(play){
		paintGame(g);}
		else{
		paintMenu(g);
		}
	}
	
	public void paintGame(Graphics g){
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
		
		
		offgc.translate(px, py);	//flyttar omvärlden i förhållande till player, (px,py) blir nya (0,0)
		offgc.setColor(Color.BLACK);
		//world
		space.drawWorld (offgc);
		
		offgc.translate(-px, -py);	//sätter kordinatsystemet tillbaks till det vanliga
		
		//player
		player.drawPlayer(offgc,playerX, playerY, player.radius*2 );
		//stamina
		offgc.drawRect(5, 5, (int) (player.stamina*10 / 4), 10);
		
		offgc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		//should be last in the method
		g.drawImage(offscreen, insets.left, insets.top, this);
	}
	
	public void paintMenu(Graphics g){
		BufferedImage offscreen = null;
		offscreen = new BufferedImage (SimpleGameEngine.windowWidth, SimpleGameEngine.windowHeight,BufferedImage.TYPE_INT_RGB);
		Graphics2D offgc = offscreen.createGraphics();
		
		offgc.setColor(Color.WHITE);
		offgc.fillRect(0, 0, windowWidth, windowHeight);
		offgc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		offgc.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		offgc.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		offgc.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		
		menuHandler.updateCurrentMenu(offgc);
		System.out.println(currentMenu);
		offgc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		g.drawImage(offscreen, SimpleGameEngine.insets.left, SimpleGameEngine.insets.top, this);
	}
	//TESTAR FRÅN NY DATA
	//to do
	//texture funktion
	//save function
	//interaction
	//settings
	//stamina?
	//sneak?
	//npcs, enemies
	//AI
}

