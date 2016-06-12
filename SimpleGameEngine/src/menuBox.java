import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class menuBox  {

String text;	
Rectangle2D rect;
int ox;
int oy;
int width;
int height;
boolean header;
int fontSizeMultiple=1;

	
	public menuBox(int ox, int oy, int width, int height, String text) {		
		this.ox=ox;
		this.oy=oy;
		this.width=width;
		this.height=height;
		
		String split[]= text.split(" ", 3);	//splittar stringen i flera (3) strings "runt" mellanrum
		
		if(split[0].equals("HEADER")){	//Gör ändringar om stringen har HEADER först, dvs. är rubrik.
		this.header=true;
		this.text=split[2];
		this.fontSizeMultiple=Integer.parseInt(split[1]);	////multiplicerar fontstorlek med andra splittade biten, siffran i rubrik, som int
		BufferedImage unused = new BufferedImage (SimpleGameEngine.windowWidth, SimpleGameEngine.windowHeight,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = unused.createGraphics();		
		Font currentFont = g.getFont();
		Font newFont = currentFont.deriveFont(currentFont.getSize()*fontSizeMultiple * 1F);
		g.setFont(newFont);
		FontMetrics metrics = g.getFontMetrics();
		this.width = metrics.stringWidth(this.text);		
		}
		
		else{
		this.text=text;}
		rect= new Rectangle2D.Double();
	}

public boolean selected(float mouseX, float mouseY){
	
	if(rect.contains(mouseX + SimpleGameEngine.px,  mouseY + SimpleGameEngine.py)){
		return true;
	}
	else {	
		return false;
	}
}
			
	
public void draw(Graphics2D g, float mouseX, float mouseY){
	
	
	rect.setRect(ox-1-width/2, oy-1, width+2, height+2);
	
	if(selected(mouseX,  mouseY) && header!=true){
		g.setColor(Color.red);	
	}
	else {
		g.setColor(Color.black);}
	
	if(header){			//Ändrar font om rubrik	
		Font currentFont = g.getFont();
		Font newFont = currentFont.deriveFont(currentFont.getSize()*fontSizeMultiple * 1F);
		g.setFont(newFont);
		g.drawString(text, ox-width/2, oy+(3*height/4));
	}
	else{
		g.draw(rect);
		g.drawString(text, ox-width/2, oy+(3*height/4));
		}
	
	if(header){									//ändrar font tillbaks om rubrik
			Font currentFont = g.getFont();
			Font newFont = currentFont.deriveFont(currentFont.getSize()/fontSizeMultiple * 1F);
			g.setFont(newFont);
		}
	}


}
