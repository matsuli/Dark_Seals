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
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_R)) {	//press R to create a rectangle
			mode="R";
		}
		else if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_L)) {	//press L to create a line
			mode="L";
		}
		else if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_C)) {	//press C to create a circle
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
		int ow=0;
		int oh=0;
		int oxNew=0;
		int oyNew=0;
		if(SimpleGameEngine.mouse.buttonDownOnce(1) && create==false){ 	//press mouse1 to start creating a rectangle
			
			ox=(int) SimpleGameEngine.mouseX;		//OBS! Vet inte om detta kommer att funka. Måste jag 
			oy=(int) SimpleGameEngine.mouseY;		// kompensera för px o py?		
			create=true;		//this code runs once, ox and oy never change
								//they are used to calculate the width and height
								//oxNew and oyNew change, they are used for drawing and for the final rect
			
		}
		
		else if(SimpleGameEngine.mouse.buttonDownOnce(3) && create==true){	//press mouse2 to save the rectangle
			SimpleGameEngine.space.addhitDetRect(SimpleGameEngine.space.objects, oxNew, oyNew, ow, oh, "chicken");
			create=false;
			System.out.println(SimpleGameEngine.space.objects);
			System.out.println(SimpleGameEngine.space.noHitObjects);
			 SimpleGameEngine.savingSystem.saveWorld(SimpleGameEngine.currentWorld, SimpleGameEngine.space);
			}
		
		else if(create==true){
			if((int)SimpleGameEngine.mouseX>ox){		
				ow=(int)SimpleGameEngine.mouseX-ox;	//ow=width 
				oxNew=ox;							//oxNew=ox, since the cursor is on the right side 
			}										//of the rect starting point=ox
			else if((int)SimpleGameEngine.mouseX==ox){
				ow=1;
			}
			else{
				ow=ox-(int)SimpleGameEngine.mouseX;	//if the cursor goes left of the starting point
				oxNew=(int)SimpleGameEngine.mouseX;	//the width is changed and the cursor becomes oxNew
			}										//ox isnt changed since it is used in the later frames too
			
			
			if((int)SimpleGameEngine.mouseY>oy){
				oh=(int)SimpleGameEngine.mouseY-oy;		//oy and oyNew work with the same principle
				oyNew=oy;
			}
			else if((int)SimpleGameEngine.mouseY==oy){
				oh=1;
			}
			else{
				oh=oy-(int)SimpleGameEngine.mouseY;
				oyNew=(int)SimpleGameEngine.mouseY;
			}
			
			r.move(oxNew, oyNew, ow, oh);		//edits the rectangle
										
			r.draw(g);							//draws the rectangle
		}
		
		
	}
	
	public void addingLine(){
		
		
		
		
	}
	
	public void addingCircle(){
		
		
		
		
	}
	
	
	
	
}
