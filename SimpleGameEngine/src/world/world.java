package world;

import engine.SimpleGameEngine;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import objects.BasicTree;
import objects.enemy;
import objects.Bullet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

public class world {
	
	public ArrayList <hitDetObj> objects = new ArrayList <hitDetObj>();
	public ArrayList <noHitObj> noHitObjects = new ArrayList <noHitObj>();
	public ArrayList <noHitObj> foregroundStuff = new ArrayList <noHitObj>();	
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();	//bullets arraylist	
	public ArrayList<enemy> enemies = new ArrayList<enemy>();	
	
	public boolean write =false;
//	boolean write =true;
	
	public void initialize () {
		
		if(write){	
			/*	objects.clear();
			noHitObjects.clear();		//Adda hitdetect objects här
			addhitDetCircle(objects, 100, 100, 50, 50, null);
			addhitDetCircle(objects, 320, 300, 50, 50, null);		
			addhitDetLine(objects, 374, 250, 500, 400);
			addhitDetLine(objects, 360, 400, 0, 125);
			addhitDetTriangle(objects, -80, 0, 0, 125, -40, 200);
			SimpleGameEngine.savingSystem.saveWorld("world1", this);	
			objects.clear();
			noHitObjects.clear();	
			addhitDetCircle(objects, 225, 100, 50, 50, null);		
			addhitDetLine(objects, 100, 250, 125, 450);
			addhitDetLine(objects, 50, 500, 0, 0);
			addhitDetTriangle(objects, 500, 330, 470, 490, 300, 200);				
			SimpleGameEngine.savingSystem.saveWorld("world2", this);		
			objects.clear();
			noHitObjects.clear();
			objects.clear();
			noHitObjects.clear();
			 addTree (noHitObjects, objects, foregroundStuff, 30, 200, 200, 60, 80, 200, 170, 60, 60, "images/tree_trunk.png", "images/tree_crown.png");
			SimpleGameEngine.savingSystem.saveWorld("tree", this);	
			noHitObjects.clear();
			foregroundStuff.clear();
			addMultipleTrees(noHitObjects, objects, foregroundStuff, 30, 60, 80, 60, 60, "images/tree_trunk.png", "images/tree_crown.png",-500,-500,1500,1500);
			SimpleGameEngine.savingSystem.saveWorld("multipleTrees", this);
			objects.clear();
			noHitObjects.clear();
			foregroundStuff.clear();
		*/	
		}									
		else {
		//	loadWorld("world1");	
		//	loadWorld("world2");
		
		//	SimpleGameEngine.savingSystem.loadWorld("tree", this); //laddar arraylisten ur en fil
		//	SimpleGameEngine.savingSystem.loadWorld("multipleTrees", this);
		}
		
	}
	
	public void HitDetect () {
		
		SimpleGameEngine.player.hit=false;
		SimpleGameEngine.player.hitRight=false;
		SimpleGameEngine.player.hitLeft=false;
		SimpleGameEngine.player.hitUp=false;
		SimpleGameEngine.player.hitDown=false;
		SimpleGameEngine.player.hitDetCircle.circle.setFrame(SimpleGameEngine.player.location.x-SimpleGameEngine.player.radius-SimpleGameEngine.px, SimpleGameEngine.player.location.y-SimpleGameEngine.player.radius-SimpleGameEngine.py, SimpleGameEngine.player.radius*2, SimpleGameEngine.player.radius*2);
		
		
		for (Iterator<hitDetObj> it = this.objects.iterator(); it.hasNext(); ) {
			hitDetObj o = it.next();
			o.hitdetect(SimpleGameEngine.player.hitDetCircle, (int) SimpleGameEngine.player.radius, SimpleGameEngine.player.prevHitDetCircle);
			
		}
		for (Iterator<enemy> it = this.enemies.iterator(); it.hasNext(); ) {
			enemy e = it.next();
			e.hitDetCircle.hitdetect(SimpleGameEngine.player.hitDetCircle, (int) SimpleGameEngine.player.radius, SimpleGameEngine.player.prevHitDetCircle);
			//funkar inte...
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
			 addEnemy(Math.random()*1000, Math.random()*1000); 	//addar enemies. När vi börjar med levels ska vi väl inte använda random utan ha dem att spawna på specifika ställen?
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
		r.textureName = texture;
		objects.add(r);	
	}
	public void addhitDetCircle(ArrayList<hitDetObj> objects, int ox, int oy, int ow, int oh, String texture){
		
			hitDetCircle c = new hitDetCircle (ox, oy, ow, oh);
			objects.add(c);	
			c.textureName = texture;
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
		r.textureName = texture;
		noHitObjects.add(r);	
	}
	
	public void addNoHitCircle(ArrayList<noHitObj> noHitObjects, int ox, int oy, int ow, int oh, String texture){
		
		noHitCircle c = new  noHitCircle(ox, oy, ow, oh);
		c.textureName = texture;
		noHitObjects.add(c);	
	}
	
	public void addInteractionArea (ArrayList<noHitObj> noHitObjects, int ox, int oy, int ow, int oh, String type, String texture) {
		Interaction I = new Interaction (ox,oy,ow,oh);
		I.textureName = texture;
		noHitObjects.add(I);
	}
	
	public void addTree (ArrayList<noHitObj> nho, ArrayList<hitDetObj> obj, ArrayList<noHitObj> foregroundStuff, int treeRadius, int tx, int ty, int tw, int th, int topX, int topY, int topW, int topH, String trunk, String crown) {
		//hitbox		
		BasicTree t= new BasicTree(nho, obj,foregroundStuff, treeRadius,tx, ty, tw, th, topX, topY, topW,topH, trunk, crown);
		
	}
	
	public void addMultipleTrees (ArrayList<noHitObj> nho, ArrayList<hitDetObj> obj, ArrayList<noHitObj> fgs, int treeRadius, int tw, int th, int topW, int topH, String trunk, String crown, int ax, int ay, int aw, int ah){
		for (int i = ax; i < aw; i += treeRadius*3) {
			for (int j = ay; j < ah; j += treeRadius*3) {
				int dx = ThreadLocalRandom.current().nextInt(0, 30 + 1);
				int dy = ThreadLocalRandom.current().nextInt(0, 30 + 1);
				int PosOrNeg1 = ThreadLocalRandom.current().nextInt(0, 1 + 1);
				if (PosOrNeg1 == 0){
					dx = -dx;
				}
				int PosOrNeg2 = ThreadLocalRandom.current().nextInt(0, 1 + 1);
				if (PosOrNeg2 == 0){
					dy = -dy;
				}
				
				addTree(nho,obj,fgs,treeRadius,i+dx,j+dy,60, 100,i+dx,j+dy-30,60, 60,trunk,crown);
			}
		}
	}

	 private void addEnemy(double d, double e) {				// addenemy metoden. skapar en ny enemy vid positionen som insätts i metoden
			{			
					enemy Enemy  = new enemy(d, e);
					enemies.add(Enemy);
				}
	 }
	
}


	



