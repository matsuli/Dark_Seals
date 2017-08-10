package editor;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import engine.SimpleGameEngine;
import world.hitDetectLine;
import world.noHitCircle;
import world.noHitObj;
import world.noHitRect;

public class levelEditor {					//Tool for creating and editing levels (worlds)

double ox;			//Objects cordinates, width and height.
double oy;
double ow;
double oh;	
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
			hitDetectLine l = new hitDetectLine(0,0, 1, 1); 	//Creates a placeholder line, there for one frame
			addingLine(g, l);
		}
		
		else if (mode=="C"){
			noHitCircle c = new noHitCircle(0, 0, 1, 1); //Creates a placeholder circle, there for one frame
			addingCircle(g, c);
		}
				
	
	}

	public void addingRect(Graphics2D g, noHitRect r){
		double ow=0;
		double oh=0;
		double oxNew=0;
		double oyNew=0;
		if(SimpleGameEngine.mouse.buttonDownOnce(1) && create==false){ 	//press mouse1 to start creating a rectangle
			
			ox=(int) SimpleGameEngine.mouseX;		//OBS! Vet inte om detta kommer att funka. Måste jag 
			oy=(int) SimpleGameEngine.mouseY;		// kompensera för px o py?		
			create=true;		//this code runs once, ox and oy never change (starting point)
								//they are used to calculate the width and height
								//oxNew and oyNew change, they are used for drawing and for the final rect
			
		}
		
		 else if(create==true){		//updates the variables when create==true
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
		if(SimpleGameEngine.mouse.buttonDownOnce(3) && create==true){	//press mouse2 to save the rectangle
			SimpleGameEngine.space.addhitDetRect(SimpleGameEngine.space.objects, oxNew, oyNew, ow, oh, null);
			create=false;
			System.out.println(oxNew);
			System.out.println(oyNew);
			System.out.println(ow);
			System.out.println(oh);
		//System.out.println(SimpleGameEngine.space.objects);
		//	System.out.println(SimpleGameEngine.space.noHitObjects);
			SimpleGameEngine.savingSystem.saveWorld(SimpleGameEngine.currentWorld, SimpleGameEngine.space);
			SimpleGameEngine.savingSystem.loadWorld(SimpleGameEngine.currentWorld, SimpleGameEngine.space);
		//	System.out.println(SimpleGameEngine.space.objects);
		//	System.out.println(SimpleGameEngine.space.noHitObjects);
			}
		
		
	}
	
	public void addingLine(Graphics2D g, hitDetectLine l){
		
		if(SimpleGameEngine.mouse.buttonDownOnce(1) && create==false){ 	//press mouse1 to start creating a line
			
			ox=(int) SimpleGameEngine.mouseX;		
			oy=(int) SimpleGameEngine.mouseY;		
			create=true;		//this code runs once, ox and oy never change (starting point for line)
								//oxNew and oyNew change, they are used for drawing and for the final line
			
		}
		
		else if(create==true){		//updates the variables when create==true
			
			
			
			l.move(ox, oy, SimpleGameEngine.mouseX, SimpleGameEngine.mouseY);		//edits the line									
			l.draw(g);							//draws the line
		}
	
		if(SimpleGameEngine.mouse.buttonDownOnce(3) && create==true){	//press mouse2 to save the rectangle
			SimpleGameEngine.space.addhitDetLine(SimpleGameEngine.space.objects, ox, oy, SimpleGameEngine.mouseX, SimpleGameEngine.mouseY);
			create=false;
			//System.out.println(SimpleGameEngine.space.objects);
			//	System.out.println(SimpleGameEngine.space.noHitObjects);
			SimpleGameEngine.savingSystem.saveWorld(SimpleGameEngine.currentWorld, SimpleGameEngine.space);
			SimpleGameEngine.savingSystem.loadWorld(SimpleGameEngine.currentWorld, SimpleGameEngine.space);
			//		System.out.println(SimpleGameEngine.space.objects);
			//	System.out.println(SimpleGameEngine.space.noHitObjects);
	}
		
		
}
	
	public void addingCircle(Graphics2D g, noHitCircle c){		
		double r=0;
		if(SimpleGameEngine.mouse.buttonDownOnce(1) && create==false){ 	//press mouse1 to start creating a rectangle
			
			ox=(int) SimpleGameEngine.mouseX;		
			oy=(int) SimpleGameEngine.mouseY;		
			create=true;		//this code runs once, ox and oy never change (starting point)
								//they are used to calculate the width and height
								
			
		}
		
		else if(create==true){		//updates the variables when create==true
			
			r = Math.sqrt((ox-SimpleGameEngine.mouseX)*(ox-SimpleGameEngine.mouseX) + (oy-SimpleGameEngine.mouseY)*(oy-SimpleGameEngine.mouseY));
			
			c.move(ox, oy, 2*r, 2*r);		//edits the circle
										
			c.draw(g);							//draws the circle
		}
		
		if(SimpleGameEngine.mouse.buttonDownOnce(3) && create==true){	//press mouse2 to save the circle
			SimpleGameEngine.space.addhitDetCircle(SimpleGameEngine.space.objects, ox, oy, 2*r, 2*r, null);
			create=false;
		//System.out.println(SimpleGameEngine.space.objects);
		//	System.out.println(SimpleGameEngine.space.noHitObjects);
			SimpleGameEngine.savingSystem.saveWorld(SimpleGameEngine.currentWorld, SimpleGameEngine.space);
			SimpleGameEngine.savingSystem.loadWorld(SimpleGameEngine.currentWorld, SimpleGameEngine.space);
		//	System.out.println(SimpleGameEngine.space.objects);
		//	System.out.println(SimpleGameEngine.space.noHitObjects);
			}
		
		
		
	}
	
	
	
	
}
