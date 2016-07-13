package engine;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class menu {

int length;	
int stringPosX;
int stringPosY;
int stringHeight;
int stringDistance;
ArrayList <menuBox> menuBoxes = new ArrayList <menuBox>();
String returnString;
BufferedImage unused;	
String thisMenu;
	

	public menu(String menuName, String...strings) {		//Det fina med den här koden e att man kan sätta in hur många strings som helst, så gör det automatiskt
		this.thisMenu=menuName;								//en menu av det. Stringen returnas alltid automatiskt till menuhandlers menuclicked metod då man trycker på den. 
		this.length=strings.length;							//dvs. det enda som krävs för att skapa ny menu e att createa den o adda till menuHandlers menus arraylist.
		stringPosX=SimpleGameEngine.windowWidth/2;			// Sen kan dess strings användas i menuclicked.
		stringDistance=SimpleGameEngine.windowHeight/(length+2);
		stringPosY=0;
		int i2=0;
		
		unused = new BufferedImage (SimpleGameEngine.windowWidth, SimpleGameEngine.windowHeight,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = unused.createGraphics();
		
		FontMetrics metrics = g.getFontMetrics();
		int stringHeight = metrics.getHeight();
		
		
		for (int i = length+1; i >= 0; i--) {
			if(i==length+1 || i==0){
			stringPosY=stringPosY+stringDistance;	
			}
			
			else{
			String thisString = strings[i2];
			int stringWidth = metrics.stringWidth(thisString);
			menuBox box=new menuBox(stringPosX, stringPosY, stringWidth, stringHeight, thisString);	
			menuBoxes.add(box);
			stringPosY=stringPosY+stringDistance;	
			i2++;
			}
			
		}
	}
	
	public void update(Graphics2D g){
		
		SimpleGameEngine.currentMenu=thisMenu;
		returnString=null;
		
		for(Iterator<menuBox> it = this.menuBoxes.iterator(); it.hasNext();) {
		menuBox o = it.next();	
		
		o.draw(g, SimpleGameEngine.mouseX, SimpleGameEngine.mouseY);
		
		if(o.selected( SimpleGameEngine.mouseX, SimpleGameEngine.mouseY)){
			returnString=o.text;	
		}
		
		}
			
	//	System.out.println(returnString);
		
	}
	public int getSelectedMenuBox(){		//returnar hur måntte menuboxen, uppifrån räknat som klickats
		menuBox returnBox=null;
		for(Iterator<menuBox> it = this.menuBoxes.iterator(); it.hasNext();) {
			menuBox o = it.next();	
			
			if(o.selected( SimpleGameEngine.mouseX, SimpleGameEngine.mouseY)){
				returnBox=o;	
			}
		
		
	}
		return menuBoxes.lastIndexOf(returnBox)+1;		//returnar -1 om ingen är selected
	
	}

}
