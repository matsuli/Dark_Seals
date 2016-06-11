import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class menuBox  {

String text;	
Rectangle2D rect;
int ox;
int oy;
int width;
int height;
	
	public menuBox(int ox, int oy, int width, int height, String text) {
		
		this.text=text;
		this.ox=ox;
		this.oy=oy;
		this.width=width;
		this.height=height;
		rect= new Rectangle2D.Double();
	}

public boolean selected(float mouseX, float mouseY){
	
	if(rect.contains(mouseX,  mouseY)){
		return true;
	}
	else {	
		return false;
	}
}
			
	
public void draw(Graphics2D g, float mouseX, float mouseY){
	
	
	rect.setRect(ox-1-width/2, oy-1, width+2, height+2);
	
	if(rect.contains(mouseX,  mouseY)){
		g.setColor(Color.red);	
	}
	else {
		g.setColor(Color.black);}
		g.draw(rect);
		g.drawString(text, ox-width/2, oy+(3*height/4));	
	}

}
