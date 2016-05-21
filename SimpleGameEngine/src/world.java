import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Graphics2D;


public class world {
	
	ArrayList <hitDetObj> objects = new ArrayList <hitDetObj>();
	
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();	//bullets arraylist
	
	public void initialize () {
		
		addhitDetRect(objects, 20, 40, 100, 100);			//Adda hitdetect objects här
		addhitDetRect(objects, 320, 100, 100, 100);
		addhitDetCircle(objects, 320, 300, 50, 50);		
		
	}
	
	public void HitDetect () {
		
		SimpleGameEngine.player.hit=false;
		SimpleGameEngine.player.hitRight=false;
		SimpleGameEngine.player.hitLeft=false;
		SimpleGameEngine.player.hitUp=false;
		SimpleGameEngine.player.hitDown=false;
		SimpleGameEngine.player.hitDetCircle.setFrame(SimpleGameEngine.player.location.x-SimpleGameEngine.player.radius-SimpleGameEngine.px, SimpleGameEngine.player.location.y-SimpleGameEngine.player.radius-SimpleGameEngine.py, SimpleGameEngine.player.radius*2, SimpleGameEngine.player.radius*2);
		
		
		for (Iterator<hitDetObj> it = this.objects.iterator(); it.hasNext(); ) {
			hitDetObj o = it.next();
			o.hitdetect(SimpleGameEngine.player.hitDetCircle, SimpleGameEngine.player.radius);
			
		}
			
	}
	
	public void Bullet (Graphics2D g) {
		for (int i = bullets.size()-1; i >= 0; i--) {
		    Bullet bullet = bullets.get(i);
		    bullet.update();
		    g.setColor(Color.black);
		    g.fillOval(bullet.location.x-bullet.radius, bullet.location.y-bullet.radius, bullet.radius*2, bullet.radius*2);
		    for (int i2 = objects.size()-1;i2>=0;i2--) {
		    	hitDetObj o = objects.get(i2);
		
		   if (o.hitdetect(bullet.hitDetBullet, bullet.radius)) {
		    		bullets.remove(bullet);
		   	}
		    }
		}
	}
	
	public void drawWorld (Graphics2D g) {
		
		g.setColor(Color.blue);
		for (Iterator<hitDetObj> it = this.objects.iterator(); it.hasNext(); ) {
			hitDetObj o = it.next();
			o.draw(g);
		}
		this.Bullet(g);
		
	}
	
	public void addhitDetRect(ArrayList<hitDetObj> objects, int ox, int oy, int ow, int oh){
		
		
			hitDetRect r = new hitDetRect (ox, oy, ow, oh);
			objects.add(r);	
		}
	public void addhitDetCircle(ArrayList<hitDetObj> objects, int ox, int oy, int ow, int oh){
		
			hitDetCircle c = new hitDetCircle (ox, oy, ow, oh);
			objects.add(c);	
		}
	public void addhitDetTriangle(ArrayList<hitDetObj> objects, int ox, int oy, int ox2, int oy2, int ox3,int oy3){
		
		hitDetTriangle t = new hitDetTriangle (ox, oy, ox2, oy2, ox3, oy3);
		objects.add(t);	
	}
	
	
	}
	



