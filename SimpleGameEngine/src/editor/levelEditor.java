package editor;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import engine.SimpleGameEngine;
import world.noHitObj;
import world.noHitRect;

public class levelEditor {					//Tool for creating and editing levels (worlds)

int ox;			//Objects cordinates, width and height.
int oy;
int ow;
int oh;	
boolean create = false;	//is an object being created, should it be rendered?
noHitObj o;			//the object currently being created
boolean hitdet=true;			//Do the created objects have hitdetection or not
boolean foreground=false;		//Are the objects in the foreground
public int p1;
public int p2;
public String mode; //this determines what the object created is
						// R=rect, L= line, Circle
	public void update(Graphics2D g){
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
			noHitRect r = new noHitRect(0, 0, 1, 1); //Creates a placeholder rect, there for one frame
			addingRect(g, r);
		}
	
		else if (mode=="L"){
			addingLine();
		}
		
		else if (mode=="C"){
			addingCircle();
		}
				
	
	}

	public void addingRect(Graphics2D g, noHitRect r){
		int ow;
		int oh;
		int oxNew=0;
		int oyNew=0;
		if(SimpleGameEngine.mouse.buttonDownOnce(1) && create==false){
			ox=(int) SimpleGameEngine.mouseX;		//OBS! Vet inte om detta kommer att funka. M�ste jag 
			oy=(int) SimpleGameEngine.mouseY;		// kompensera f�r px o py?		
			create=true;		
			System.out.println("hoh");

		}
		
		else if(create==true){
			if((int)SimpleGameEngine.mouseX>ox){
				ow=(int)SimpleGameEngine.mouseX-ox;
				oxNew=ox;
			}
			else if((int)SimpleGameEngine.mouseX==ox){
				ow=1;
			}
			else{
				ow=ox-(int)SimpleGameEngine.mouseX;
				oxNew=(int)SimpleGameEngine.mouseX;
			}
			
			
			if((int)SimpleGameEngine.mouseY>oy){
				oh=(int)SimpleGameEngine.mouseY-oy;
				oyNew=oy;
			}
			else if((int)SimpleGameEngine.mouseY==oy){
				oh=1;
			}
			else{
				oh=oy-(int)SimpleGameEngine.mouseY;
				oyNew=(int)SimpleGameEngine.mouseY;
			}
			
			r.move(oxNew, oyNew, ow, oh);
										
			r.draw(g);
		}
		
		
	}
	
	public void addingLine(){
		
		
		
		
	}
	
	public void addingCircle(){
		
		
		
		
	}
	
	
	
	
}
