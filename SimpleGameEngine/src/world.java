import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class world {
	
	ArrayList <hitDetObj> objects = new ArrayList <hitDetObj>();
	
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();	//bullets arraylist
	
	boolean write =false;
//	boolean write =true;
	
	public void initialize () {
	
	if(write){														//Adda hitdetect objects här
//		addhitDetCircle(objects, 100, 100, 50, 50);
//		addhitDetCircle(objects, 320, 300, 50, 50);		
//		addhitDetLine(objects, 374, 250, 500, 400);
//		addhitDetLine(objects, 360, 400, 0, 125);
//		addhitDetTriangle(objects, -80, 0, 0, 125, -40, 200);
//		saveWorld("world1");						
		
//		addhitDetCircle(objects, 225, 100, 50, 50);		
//		addhitDetLine(objects, 100, 250, 125, 450);
//		addhitDetLine(objects, 50, 500, 0, 0);
//		addhitDetTriangle(objects, 500, 330, 470, 490, 300, 200);				
//		saveWorld("world2");		
																	//skapar en fil med valfritt namn och sparar arraylisten objects där, 
	}																//object till den har addats ovan
	else{
	//	loadWorld("world1");	
		loadWorld("world2"); //laddar arraylisten ur en fil
	}
		
		
		

		
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
			o.hitdetect(SimpleGameEngine.player.hitDetCircle, SimpleGameEngine.player.radius, SimpleGameEngine.player.prevHitDetCircle);
			
		}
			
	}
	
	public void Bullet (Graphics2D g) {
		for (int i = bullets.size()-1; i >= 0; i--) {
		    Bullet bullet = bullets.get(i);
		    bullet.update();
		    g.setColor(Color.black);
		    bullet.draw(g);
		    for (int i2 = objects.size()-1;i2>=0;i2--) {
		    	hitDetObj o = objects.get(i2);
		
		   if (o.hitdetect(bullet.hitDetBullet, bullet.radius, bullet.prevHitDetBullet)) {
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
	
	public void addhitDetRect(ArrayList<hitDetObj> objects, int ox, int oy, int ow, int oh, String texture){
		BufferedImage img = null;
		
		if (texture == "none") {
		} else {
			try {
			    img = ImageIO.read(new File(texture));
			} catch (IOException e) {
			}
		}
		
		hitDetRect r = new hitDetRect (ox, oy, ow, oh);
		r.texture = img; ///THIS DOESNT WORK, r.texture är null även efter detta
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
public void addhitDetLine(ArrayList<hitDetObj> objects, int ox, int oy, int ox2, int oy2){
		
		hitDetectLine l = new hitDetectLine (ox, oy, ox2, oy2);
		objects.add(l);	
	}

	public void saveWorld(String world){
		try {
			FileOutputStream fos = new FileOutputStream(world);
			ObjectOutputStream	oos = new ObjectOutputStream(fos);
			oos.writeObject(objects);
			oos.close();
		} catch(Exception ex) {
		    ex.printStackTrace();
		}	
	}

	public void loadWorld(String world){
		try {
		FileInputStream fis = new FileInputStream(world);
		ObjectInputStream ois = new ObjectInputStream(fis);
		objects = (ArrayList <hitDetObj>)ois.readObject();
		ois.close();
		
		} catch(Exception ex) {
			    ex.printStackTrace();
			}	
		}
	}


	



