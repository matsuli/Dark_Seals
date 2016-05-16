import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;


public class world {
	
	
	ArrayList <hitDetObj> objects = new ArrayList <hitDetObj>();
	
	public void initialize () {
		
		addhitDetObject(objects, 20, 40, 100, 100);
		addhitDetObject(objects, 320, 100, 100, 100);

		
	}
	
	public void HitDetect () {
			
		
		SimpleGameEngine.player.hit=false;
		SimpleGameEngine.player.hitRight=false;
		SimpleGameEngine.player.hitLeft=false;
		SimpleGameEngine.player.hitUp=false;
		SimpleGameEngine.player.hitDown=false;
		
		
		for (Iterator<hitDetObj> it = this.objects.iterator(); it.hasNext(); ) {
			hitDetObj o = it.next();
			o.hitdetect();
			
			
			}
			
		}
				
	
	public void drawWorld (Graphics g) {
		g.drawLine(70, 40, 70, 140);		
		g.setColor(Color.blue);
		g.drawRect(20, 40, 100, 100);
		g.drawRect(320, 100, 100, 100);
	}
	
	public void addhitDetObject(ArrayList<hitDetObj> objects, int ox, int oy, int ow, int oh){
		hitDetObj r = new hitDetObj (ox, oy, ow, oh);
		objects.add(r);	
		
		}
	
}


