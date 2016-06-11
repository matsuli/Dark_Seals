import java.awt.Component;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class InputHandler implements KeyListener {
	
	boolean [] keys = new boolean [256];
	
	
	public InputHandler (Component c) {
		c.addKeyListener(this);
	}
	
	public boolean isKeyDown (int keyCode) {
		if (keyCode > 0 && keyCode < 256) {
			return keys [keyCode];
		}
		return false;
	}
	
	public void keyPressed (KeyEvent e) {
		if (e.getKeyCode() > 0 && e.getKeyCode() < 256) {
			keys [e.getKeyCode()] = true;
		}
	}
	
	public void keyReleased (KeyEvent e) {
		if (e.getKeyCode() > 0 && e.getKeyCode() < 256) {
			keys [e.getKeyCode()] = false;
		}
	}
	
	public void keyTyped (KeyEvent e) {
		
	}
		
	
		
	public void handleInput(){
		
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_ESCAPE)) {
			System.exit(0);
		}
		//shoot
		if (SimpleGameEngine.mouse.buttonDown(1)) {
			
				if(SimpleGameEngine.play){
					SimpleGameEngine.player.shoot (SimpleGameEngine.player,SimpleGameEngine.mouseX,SimpleGameEngine.mouseY);}
			
				else if(SimpleGameEngine.currentMenu!=null){
					
					String menuString=SimpleGameEngine.menuHandler.getCurrentMenu().returnString;
					
						if(menuString!=null){
							SimpleGameEngine.menuHandler.menuClicked(menuString);
						}
						
				}				
			}
		
		}
			
	}
	

