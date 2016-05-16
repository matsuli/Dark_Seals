import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Iterator;

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
	
	public void drawPlayer (Graphics2D g, int x, int y, int r) {		//ritar player med x, y, (dvs. playerX, playerY) i mitten. (OBS! Filloval ritar som vänster övre hörn.)
			  g.setColor(Color.RED);
			  x = x-(r/2);
			  y = y-(r/2);
			  g.fillOval(x,y,r,r);
			  System.out.println(hit);
			
	}
	
	public void Movement (world space) {
		
		space.HitDetect ();
				
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_D)) {
			
			if(hitRight==false){
			SimpleGameEngine.px -= right;}
			else{

			}
		}
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_A)) {
			
			if(hitLeft==false){
				SimpleGameEngine.px += left;}
			else{

			}
		}
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_S)) {
	
			if(hitDown==false){
				SimpleGameEngine.py -= down;}
			else{
	
		}
	}

		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_W)) {
	
			if(hitUp==false)
				SimpleGameEngine.py += up;
			else{
				
				

	}
}
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_ESCAPE)) {
			System.exit(0);
		}
		
	
	}


	
	 }
		
	
	

	
