import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Graphics2D;


public class world {
	
	ArrayList <hitDetObj> objects = new ArrayList <hitDetObj>();
	
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();	//bullets arraylist
	
	public void initialize () {
		
		addhitDetObject(objects, 20, 40, 100, 100, "rect");			//Adda hitdetect objects här
		addhitDetObject(objects, 320, 100, 100, 100, "rect");
		addhitDetObject(objects, 320, 300, 50, 50, "circle");		//cirkelns hitdetection funkar inte ännu
		
	}
	
	public void HitDetect (Graphics2D g) {
		
		SimpleGameEngine.player.hit=false;
		SimpleGameEngine.player.hitRight=false;
		SimpleGameEngine.player.hitLeft=false;
		SimpleGameEngine.player.hitUp=false;
		SimpleGameEngine.player.hitDown=false;
		
		
		for (Iterator<hitDetObj> it = this.objects.iterator(); it.hasNext(); ) {
			hitDetObj o = it.next();
			o.hitdetect();
			o.draw(g);
		}
			
	}
	
	public void Bullet (Graphics2D g) {
		for (int i = bullets.size()-1; i >= 0; i--) {
		    Bullet bullet = bullets.get(i);
		    bullet.update();
		    g.setColor(Color.black);;
		    g.fillOval(bullet.location.x, bullet.location.y, bullet.radius*2, bullet.radius*2);
	  }
	}
	
	public void drawWorld (Graphics2D g) {
		
		g.setColor(Color.blue);
		this.HitDetect (g);
		this.Bullet(g);
		
	}
	
	public void addhitDetObject(ArrayList<hitDetObj> objects, int ox, int oy, int ow, int oh, String type){
		
		if(type=="rect"){
			hitDetRect r = new hitDetRect (ox, oy, ow, oh);
			objects.add(r);	
		}
		if(type=="circle"){
			hitDetCircle c = new hitDetCircle (ox, oy, ow, oh);
			objects.add(c);	
		}
	}
	
}


