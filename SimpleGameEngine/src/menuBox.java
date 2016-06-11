import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class menuBox  {

String text;	
Rectangle2D rect;
int ox;
int oy;
	
	public menuBox(int ox, int oy, String text) {
	
	this.text=text;
	this.ox=ox;
	this.oy=oy;
	rect= new Rectangle2D.Double();
	}

public boolean selected(float mouseX, float mouseY){
	
	if(rect.contains(mouseX,  mouseY)){
		return true;
	}
	else{	
	return false;}
}
			
	
public void draw(Graphics2D g, float mouseX, float mouseY){
	
	FontMetrics metrics = g.getFontMetrics();
	int hgt = metrics.getHeight();
	int adv = metrics.stringWidth(text);
	
	rect.setRect(ox, oy, adv+2, hgt+2);
	
	if(rect.contains(mouseX,  mouseY)){
	g.setColor(Color.red);	
	}
	else{
	g.setColor(Color.black);}
	g.draw(rect);
	g.drawString(text, ox+2, oy+(3*hgt/4)+1);	
	}

}
