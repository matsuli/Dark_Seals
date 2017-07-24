package editor;

import java.awt.event.KeyEvent;

import engine.SimpleGameEngine;

public class levelEditor {					//Tool for creating and editing levels (worlds)

boolean hitdet=true;			//Do the created objects have hitdetection or not
boolean foreground=false;		//Are the objects in the foreground
public int p1;
public int p2;
public String mode="R"; //this determines what the object created is
						// R=rect, L= line, Circle
	public void update(){
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_R)) {
			mode="R";
		}
		else if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_L)) {
			mode="L";
		}
		else if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_C)) {
			mode="C";
		}
		if (mode=="R"){
			addingRect();
		}
	
		else if (mode=="L"){
			addingLine();
		}
		
		else if (mode=="C"){
			addingCircle();
		}
	
	}

	public void addingRect(){
		
		
		
		
	}
	
	public void addingLine(){
		
		
		
		
	}
	
	public void addingCircle(){
		
		
		
		
	}
	
	
	
}
