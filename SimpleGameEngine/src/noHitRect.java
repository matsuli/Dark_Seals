import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class noHitRect extends noHitObj {
	
	Rectangle2D rect;
	int ow;
	int oh;
	
	public noHitRect( int ox, int oy, int ow, int oh) {
		this.ox=ox;
		this.oy=oy;
		this.ow=ow;
		this.oh=oh;
		rect = new Rectangle2D.Double(ox, oy, ow, oh);
	}
	public void draw(Graphics2D g){
	g.drawRect(ox, oy, ow, oh);
	if(textureImg2!=null){
	g.drawImage(textureImg2, ox, oy, ow, oh, null);}
	}

}
