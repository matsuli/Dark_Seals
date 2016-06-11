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
ArrayList <menuBox> menuBoxes = new ArrayList <menuBox>();
String returnString;
	
	

	public menu(String...strings) {
		this.length=strings.length;
		stringPosX=SimpleGameEngine.windowWidth/2;
		stringHeight=SimpleGameEngine.windowHeight/(length+2);
		stringPosY=0;
		int i2=0;
		
		for (int i = length+1; i >= 0; i--) {
			if(i==length+1 || i==0){
			stringPosY=stringPosY+stringHeight;	
			}
			
			else{
			String thisString = strings[i2];	
			menuBox box=new menuBox(stringPosX, stringPosY, thisString);	
			menuBoxes.add(box);
			stringPosY=stringPosY+stringHeight;	
			i2++;
			}
			
		}
	}
	
	public void update(Graphics2D g){
		
		
		SimpleGameEngine.mouse.poll();
		float	mouseX = SimpleGameEngine.mouse.getPosition().x-SimpleGameEngine.px-SimpleGameEngine.insets.left;
		float	mouseY = SimpleGameEngine.mouse.getPosition().y-SimpleGameEngine.py-SimpleGameEngine.insets.top;
		returnString=null;
		
		for(Iterator<menuBox> it = this.menuBoxes.iterator(); it.hasNext();) {
		menuBox o = it.next();	
		
		o.draw(g, mouseX, mouseY);
		
		if(o.selected(mouseX, mouseY)){
			returnString=o.text;	
		}
		
		}
		
		System.out.println(returnString);
		
	}
	
	

}
