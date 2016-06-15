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
	int speed = 5;
	double stamina = 100;
	boolean sprint, sneak;
	boolean hit;
	boolean hitLeft;
	boolean hitRight;
	boolean hitUp;
	boolean hitDown;
	Ellipse2D hitDetCircle;
	Ellipse2D prevHitDetCircle;
	int hitCorrectionLeft;
	int hitCorrectionRight;
	int hitCorrectionUp;
	int hitCorrectionDown;
	boolean hitCorrected;
	
	Player(){
		location.x=SimpleGameEngine.playerX;
		location.y=SimpleGameEngine.playerY;
		shooterLocation.setLocation(location.x - SimpleGameEngine.px,location.y - SimpleGameEngine.py);
		hitDetCircle = new Ellipse2D.Double(location.x-radius, location.y-radius, radius*2, radius*2);	//Används för att hitdetecta player. Blir "translated" i hitdetect() med objekten
		prevHitDetCircle = new Ellipse2D.Double(location.x-radius, location.y-radius, radius*2, radius*2);
	}																										//location-radius innebär att location e cirkelns mitt
	
	public void drawPlayer (Graphics2D g, int x, int y, int d) {		//ritar player med x, y, (dvs. playerX, playerY) i mitten. (OBS! Filloval ritar som vänster övre hörn.)
			  g.setColor(Color.RED);
			  x = x-(d/2);
			  y = y-(d/2);
			  g.fillOval(x,y,d,d);
	}
	
	public void Control (world space) {
		hitDetCircle.setFrame(location.x-radius-SimpleGameEngine.px, location.y-radius-SimpleGameEngine.py, radius*2, radius*2);
		prevHitDetCircle.setFrame(location.x-radius-SimpleGameEngine.px, location.y-radius-SimpleGameEngine.py, radius*2, radius*2);
		
		right = speed;
		left = speed;
		down = speed;
		up = speed;
		
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
		
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_SHIFT)) {
			sprint = true;
		} else {sprint = false;}
		if (sprint == true) {
			if (stamina >= 2/60) {
				stamina =stamina-(2.0/5);
			} else if(stamina >= 0) {
				stamina =stamina-(1.0/5);
			}
			if (speed < 10 && stamina>0) {
				speed ++;
			}
			if (stamina <= 0 && speed > 5) {
				speed --;
			}
		}
		if (sprint == false) {
			if (speed > 5) {
				speed --;
			}
		}
		if (stamina <= 100 && sprint==false) {
			stamina = stamina + 1;
		}
		

		
		hitDetCircle.setFrame(location.x-radius-SimpleGameEngine.px, location.y-radius-SimpleGameEngine.py, radius*2, radius*2);
		shooterLocation.setLocation(location.x - SimpleGameEngine.px,location.y - SimpleGameEngine.py);
	}
	
}
		
	
	

	
