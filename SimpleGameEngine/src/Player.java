import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;


public class Player extends Actor {
	int radius = 10;
	//used in movement
	int right = 5;
	int left = 5;
	int down = 5;
	int up = 5;
	boolean hit;
	boolean hitLeft;
	boolean hitRight;
	boolean hitUp;
	boolean hitDown;
	Ellipse2D hitDetCircle;
	int hitCorrectionLeft;
	int hitCorrectionRight;
	int hitCorrectionUp;
	int hitCorrectionDown;
	boolean hitCorrected;
	
	Player(){
		
		location.x=SimpleGameEngine.playerX;
		location.y=SimpleGameEngine.playerY;
		hitDetCircle = new Ellipse2D.Double(location.x-radius, location.y-radius, radius*2, radius*2);		//Används för att hitdetecta player. Blir "translated" i hitdetect() med objekten
	}																										//location-radius innebär att location e cirkelns mitt
	
	public void drawPlayer (Graphics2D g, int x, int y, int d) {		//ritar player med x, y, (dvs. playerX, playerY) i mitten. (OBS! Filloval ritar som vänster övre hörn.)
			  g.setColor(Color.RED);
			  x = x-(d/2);
			  y = y-(d/2);
			  g.fillOval(x,y,d,d);
	}
	
	public void Control (world space) {
		hitDetCircle.setFrame(location.x-radius-SimpleGameEngine.px, location.y-radius-SimpleGameEngine.py, radius*2, radius*2);
		
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_D)) {
						
			SimpleGameEngine.px -= right;
			space.HitDetect();
			if(hitRight==true){
				SimpleGameEngine.px += hitCorrectionRight;
				
			}
		}
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_A)) {
			
			
				SimpleGameEngine.px += left;
				space.HitDetect();
			if(hitLeft==true){
				SimpleGameEngine.px -= hitCorrectionLeft;
				
			}
		}
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_S)) {
	
			
				SimpleGameEngine.py -= down;
				space.HitDetect();
			 if(hitDown==true){
				SimpleGameEngine.py += hitCorrectionDown;
				
			}
		}

		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_W)) {
	
			
				SimpleGameEngine.py += up;
				space.HitDetect();
			 if(hitUp==true){
				SimpleGameEngine.py -= hitCorrectionUp;
				
			}
		}
		
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_ESCAPE)) {
			System.exit(0);
		}
		
	}
	
}
		
	
	

	
