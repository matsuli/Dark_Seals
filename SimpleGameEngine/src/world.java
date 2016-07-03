import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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
	
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();	//bullets arraylist
	
	boolean write =false;
//	boolean write =true;
	
	public void initialize () {
	
	if(write){	
	objects.clear();		//Adda hitdetect objects här
	addhitDetCircle(objects, 100, 100, 50, 50, null);
	addhitDetCircle(objects, 320, 300, 50, 50, null);		
	addhitDetLine(objects, 374, 250, 500, 400);
	addhitDetLine(objects, 360, 400, 0, 125);
	addhitDetTriangle(objects, -80, 0, 0, 125, -40, 200);
	saveWorld("world1");	
	objects.clear();	
	addhitDetCircle(objects, 225, 100, 50, 50, null);		
	addhitDetLine(objects, 100, 250, 125, 450);
	addhitDetLine(objects, 50, 500, 0, 0);
	addhitDetTriangle(objects, 500, 330, 470, 490, 300, 200);				
	saveWorld("world2");	
	objects.clear();
	addhitDetRect(objects, 100, 100, 100, 100, "images/chicken.gif");
	addNoHitRect(noHitObjects, 250, 250, 150, 150,"images/chicken.gif");
	addInteractionArea (noHitObjects, 200,100,100,100, null, null);
	saveWorld("chicken");																//skapar en fil med valfritt namn och sparar arraylisten objects där, 
	}																//object till den har addats ovan OBS! VArje gång saveworld runnar overwritas hela den tidigare arraylist-filen helt!
	else{
	//	loadWorld("world1");	
	//	loadWorld("world2");
		loadWorld("chicken"); //laddar arraylisten ur en fil
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
		g.setColor(Color.green);	
		for (Iterator<noHitObj> it = this.noHitObjects.iterator(); it.hasNext(); ) {
			noHitObj o = it.next();
			o.draw(g);
		}
		this.Bullet(g);
		
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
	
	  }	
	
}


	



