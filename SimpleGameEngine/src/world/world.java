package world;

import engine.SimpleGameEngine;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import objects.BasicTree;
import objects.enemy;
import objects.Bullet;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class world {
	
	ArrayList <hitDetObj> objects = new ArrayList <hitDetObj>();
	ArrayList <noHitObj> noHitObjects = new ArrayList <noHitObj>();
	ArrayList <noHitObj> foregroundStuff = new ArrayList <noHitObj>();
	
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();	//bullets arraylist
	
	public ArrayList<enemy> enemies = new ArrayList<enemy>();	
	
	public boolean write =false;
//	boolean write =true;
	
	public void initialize () {
	
	if(write){	
	objects.clear();
	noHitObjects.clear();		//Adda hitdetect objects h�r
	addhitDetCircle(objects, 100, 100, 50, 50, null);
	addhitDetCircle(objects, 320, 300, 50, 50, null);		
	addhitDetLine(objects, 374, 250, 500, 400);
	addhitDetLine(objects, 360, 400, 0, 125);
	addhitDetTriangle(objects, -80, 0, 0, 125, -40, 200);
	saveWorld("world1");	
	objects.clear();
	noHitObjects.clear();	
	addhitDetCircle(objects, 225, 100, 50, 50, null);		
	addhitDetLine(objects, 100, 250, 125, 450);
	addhitDetLine(objects, 50, 500, 0, 0);
	addhitDetTriangle(objects, 500, 330, 470, 490, 300, 200);				
	saveWorld("world2");	
	objects.clear();
	noHitObjects.clear();
	addhitDetRect(objects, 100, 100, 100, 100, "images/chicken.gif");
	addNoHitRect(noHitObjects, 250, 250, 150, 150,"images/chicken.gif");
	addInteractionArea (noHitObjects, 200,100,100,100, null, null);
	saveWorld("chicken");
	objects.clear();
	noHitObjects.clear();
	addTree (noHitObjects, objects, foregroundStuff, 30, 200, 200, 60, 80, 200, 170, 60, 60, "images/tree_trunk.png", "images/tree_crown.png");
	saveWorld("tree");
	objects.clear();
	noHitObjects.clear();
	
	}									
	else{
	//	loadWorld("world1");	
	//	loadWorld("world2");
	//	loadWorld("chicken");
		loadWorld("tree"); //laddar arraylisten ur en fil
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
		
		if(enemies.size()<2){
			 addEnemy(Math.random()*1000, Math.random()*1000); 	//addar enemies. N�r vi b�rjar med levels ska vi v�l inte anv�nda random utan ha dem att spawna p� specifika st�llen?
		 }
		
		 for (Iterator<enemy> it = enemies.iterator(); it.hasNext(); ) {
			    enemy e = it.next(); 
			    e.update();
			    e.drawEnemy(g);// uppdaterar enemies position och detection av player, drawar den
			    
				 if(e.ReloadTimer==120){
					 e.shoot(e, e.targetActor.location.x, e.targetActor.location.y);
					 e.ReloadTimer=0;
				 }
			    if (e.toRemove)
			      it.remove();			//removar enemyn om toRemove=true
			  }
		
		g.setColor(Color.blue);
		for (Iterator<hitDetObj> it = this.objects.iterator(); it.hasNext(); ) {
			hitDetObj o = it.next();
			o.draw(g);
		}
		g.setColor(Color.green);	
		for (Iterator<noHitObj> it = this.noHitObjects.iterator(); it.hasNext(); ) {
			noHitObj o = it.next();
			o.draw(g);
		}
		
		this.Bullet(g);
		
	}
	
public void drawForeground (Graphics2D g) {
		
		
		g.setColor(Color.black);
		for (Iterator<noHitObj> it = this.foregroundStuff.iterator(); it.hasNext(); ) {
			noHitObj o = it.next();
			o.draw(g);
		}
	}
	
	public void addhitDetRect(ArrayList<hitDetObj> objects, int ox, int oy, int ow, int oh, String texture){
		
		hitDetRect r = new hitDetRect (ox, oy, ow, oh);
		r.texture = texture;
		objects.add(r);	
	}
	public void addhitDetCircle(ArrayList<hitDetObj> objects, int ox, int oy, int ow, int oh, String texture){
		
			hitDetCircle c = new hitDetCircle (ox, oy, ow, oh);
			objects.add(c);	
			c.texture = texture;
		}
	public void addhitDetTriangle(ArrayList<hitDetObj> objects, int ox, int oy, int ox2, int oy2, int ox3,int oy3){
		
		hitDetTriangle t = new hitDetTriangle (ox, oy, ox2, oy2, ox3, oy3);
		objects.add(t);	
	}
	public void addhitDetLine(ArrayList<hitDetObj> objects, int ox, int oy, int ox2, int oy2){
		
		hitDetectLine l = new hitDetectLine (ox, oy, ox2, oy2);
		objects.add(l);	
	}
	public void addNoHitRect(ArrayList<noHitObj> noHitObjects, int ox, int oy, int ow, int oh, String texture){
	
		noHitRect r = new  noHitRect(ox, oy, ow, oh);
		r.texture = texture;
		noHitObjects.add(r);	
	}
	
	public void addNoHitCircle(ArrayList<noHitObj> noHitObjects, int ox, int oy, int ow, int oh, String texture){
		
		noHitCircle c = new  noHitCircle(ox, oy, ow, oh);
		c.texture = texture;
		noHitObjects.add(c);	
	}
	
	public void addInteractionArea (ArrayList<noHitObj> noHitObjects, int ox, int oy, int ow, int oh, String type, String texture) {
		Interaction I = new Interaction (ox,oy,ow,oh);
		I.texture = texture;
		noHitObjects.add(I);
	}
	
	public void addTree (ArrayList<noHitObj> noHitObjects, ArrayList<hitDetObj> objects, ArrayList<noHitObj> foregroundStuff, int treeRadius, int tx, int ty, int tw, int th, int topX, int topY, int topW, int topH, String trunk, String crown) {
		//hitbox		
		BasicTree t= new BasicTree(noHitObjects, objects,foregroundStuff, treeRadius,tx, ty, tw, th, topX, topY, topW,topH, trunk, crown);
		
	}

	public void saveWorld(String world){
		try {
			new File(world).mkdirs();
			
			FileOutputStream fos = new FileOutputStream(world+"/hitDet");
			ObjectOutputStream	oos = new ObjectOutputStream(fos);
			oos.writeObject(objects);
			oos.close();
		} catch(Exception ex) {
		    ex.printStackTrace();
		}	
		try {
			
		
			FileOutputStream fos = new FileOutputStream(world+"/noHitObj");
			ObjectOutputStream	oos = new ObjectOutputStream(fos);
			oos.writeObject(noHitObjects);
			oos.close();
		} catch(Exception ex) {
		    ex.printStackTrace();
		}
		
	try {
		FileOutputStream fos = new FileOutputStream(world+"/foreGround");
		ObjectOutputStream	oos = new ObjectOutputStream(fos);
		oos.writeObject(foregroundStuff);
		oos.close();
	} catch(Exception ex) {
	    ex.printStackTrace();
	}	
	}

	
	public void loadWorld(String world){
		try {
		FileInputStream fis = new FileInputStream(world+"/hitDet");
		ObjectInputStream ois = new ObjectInputStream(fis);
		objects = (ArrayList <hitDetObj>)ois.readObject();
		ois.close();
		SimpleGameEngine.currentWorld=world;
		} catch(Exception ex) {
			    ex.printStackTrace();
			}	
		
		
		for (Iterator<hitDetObj> it = this.objects.iterator(); it.hasNext(); ) {
		hitDetObj o = it.next();
		BufferedImage img2;
		if (o.texture == null) {
		} else {
			try {
			    img2 = ImageIO.read(new File(o.texture));
				o.textureImg2 = o.addTransparency(img2, Color.white);
				o.textureImg = o.imageToBufferedImage(o.textureImg2);
			} catch (IOException e) {
			}
		}
	  }
		try {
			FileInputStream fis = new FileInputStream(world+"/noHitObj");
			ObjectInputStream ois = new ObjectInputStream(fis);
			noHitObjects = (ArrayList <noHitObj>)ois.readObject();
			ois.close();
			
			} catch(Exception ex) {
				    ex.printStackTrace();
				}	
		
		for (Iterator<noHitObj> it = this.noHitObjects.iterator(); it.hasNext(); ) {
			noHitObj o = it.next();
			BufferedImage img2;
			//Image img2 = null;
			if (o.texture == null) {
			} else {
				try {
				    img2 = ImageIO.read(new File(o.texture));
					o.textureImg2 = o.addTransparency(img2, Color.white);
					o.textureImg = o.imageToBufferedImage(o.textureImg2);
				} catch (IOException e) {
				}
				//img2 = Toolkit.getDefaultToolkit().createImage(o.texture);
				//o.textureImg2=img2;
				//try {
				//	img2 = new ImageIcon (new URL(o.texture)).getImage();
				//	o.textureImg2=img2;
				//} catch (MalformedURLException e) {
				//	e.printStackTrace();
				//}
			}
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File("images/engage.png"));
			} catch (IOException e) {
			}
			o.intSymImg = o.addTransparency(img, Color.white);
			o.interactSymbol = o.imageToBufferedImage(o.intSymImg);
		}
		try {
		FileInputStream fis = new FileInputStream(world+"/foreGround");
		ObjectInputStream ois = new ObjectInputStream(fis);
		foregroundStuff = (ArrayList <noHitObj>)ois.readObject();
		ois.close();
		SimpleGameEngine.currentWorld=world;
		} catch(Exception ex) {
			    ex.printStackTrace();
			}	
		
		
		for (Iterator<noHitObj> it = this.foregroundStuff.iterator(); it.hasNext(); ) {
		noHitObj o = it.next();
		BufferedImage img2;
		if (o.texture == null) {
		} else {
			try {
			    img2 = ImageIO.read(new File(o.texture));
				o.textureImg2 = o.addTransparency(img2, Color.white);
				o.textureImg = o.imageToBufferedImage(o.textureImg2);
			} catch (IOException e) {
			}
		}
	  }
	  }	
	 private void addEnemy(double d, double e) {				// addenemy metoden. skapar en ny enemy vid positionen som ins�tts i metoden
			{			
					enemy Enemy  = new enemy(d, e);
					enemies.add(Enemy);
				}
	 }
	
}


	


