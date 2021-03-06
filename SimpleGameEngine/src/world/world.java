package world;

import engine.SimpleGameEngine;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import objects.BasicTree;
import objects.enemy;
import objects.Bullet;

import java.awt.Graphics2D;
import java.util.concurrent.ThreadLocalRandom;

public class world {

	public ArrayList<hitDetObj> objects = new ArrayList<hitDetObj>();
	public ArrayList<noHitObj> noHitObjects = new ArrayList<noHitObj>();
	public ArrayList<noHitObj> foregroundStuff = new ArrayList<noHitObj>();
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>(); // bullets
																		// arraylist
	public ArrayList<enemy> enemies = new ArrayList<enemy>();

	public boolean write = false;
	// boolean write =true;
	
	public void HitDetect() {

		SimpleGameEngine.player.hit = false;
		SimpleGameEngine.player.hitRight = false;
		SimpleGameEngine.player.hitLeft = false;
		SimpleGameEngine.player.hitUp = false;
		SimpleGameEngine.player.hitDown = false;
		SimpleGameEngine.player.hitDetCircle.circle.setFrame(
				SimpleGameEngine.player.location.x - SimpleGameEngine.player.radius - SimpleGameEngine.px,
				SimpleGameEngine.player.location.y - SimpleGameEngine.player.radius - SimpleGameEngine.py,
				SimpleGameEngine.player.radius * 2, SimpleGameEngine.player.radius * 2);

		for (Iterator<hitDetObj> it = this.objects.iterator(); it.hasNext();) {
			hitDetObj o = it.next();
			o.hitdetect(SimpleGameEngine.player.hitDetCircle, (int) SimpleGameEngine.player.radius);	//b�da hitdets runnar, b�da finns i hitdetobj, men bara ena "inneh�ller" kod per objeky
			o.hitdetect(SimpleGameEngine.player.hitDetCircle, (int) SimpleGameEngine.player.radius, SimpleGameEngine.player.prevHitDetCircle );	//se hitdetobj

		}
		for (Iterator<enemy> it = this.enemies.iterator(); it.hasNext();) {
			enemy e = it.next();
			
			//e.hitDetCircle.hitdetect(SimpleGameEngine.player.hitDetCircle, (int) SimpleGameEngine.player.radius);
			// funkar inte...
		}

	}

	public void Bullet(Graphics2D g) {
		for (int i = bullets.size() - 1; i >= 0; i--) {
			Bullet bullet = bullets.get(i);
			bullet.update();
			g.setColor(Color.black);
			bullet.draw(g);
			for (int i2 = objects.size() - 1; i2 >= 0; i2--) {
				hitDetObj o = objects.get(i2);

				if (o.hitdetect(bullet.hitDetBullet, bullet.radius) || o.hitdetect(bullet.hitDetBullet, bullet.radius, bullet.prevHitDetBullet)) {
					bullets.remove(bullet);
				}
			
			}
		}
	}

	public void drawWorld(Graphics2D g) {

		if (enemies.size() < 2) {
			addEnemy(Math.random() * 1000, Math.random() * 1000); // addar
																	// enemies.
																	// N�r vi
																	// b�rjar
																	// med
																	// levels
																	// ska vi
																	// v�l inte
																	// anv�nda
																	// random
																	// utan ha
																	// dem att
																	// spawna p�
																	// specifika
																	// st�llen?
		}

		for (Iterator<enemy> it = enemies.iterator(); it.hasNext();) {
			enemy e = it.next();
			e.update();
			e.drawEnemy(g);// uppdaterar enemies position och detection av
							// player, drawar den

			if (e.ReloadTimer == 120) {
				e.shoot(e, e.targetActor.location.x, e.targetActor.location.y);
				e.ReloadTimer = 0;
			}
			if (e.toRemove)
				it.remove(); // removar enemyn om toRemove=true
		}

		g.setColor(Color.blue);
		for (Iterator<hitDetObj> it = this.objects.iterator(); it.hasNext();) {
			hitDetObj o = it.next();
			o.draw(g);
			
		}
		g.setColor(Color.green);
		for (Iterator<noHitObj> it = this.noHitObjects.iterator(); it.hasNext();) {
			noHitObj o = it.next();
			o.draw(g);
		}

		this.Bullet(g);

	}

	public void drawForeground(Graphics2D g) {

		g.setColor(Color.black);
		for (Iterator<noHitObj> it = this.foregroundStuff.iterator(); it.hasNext();) {
			noHitObj o = it.next();
			o.draw(g);
		}
	}

	public void addhitDetRect(ArrayList<hitDetObj> objects, double ox, double oy, double ow, double oh, String texture) {

		hitDetRect r = new hitDetRect(ox, oy, ow, oh);
		r.textureName = texture;
		objects.add(r);
	}

	public void addhitDetCircle(ArrayList<hitDetObj> objects, double ox, double oy, double ow, double oh, String texture) {

		hitDetCircle c = new hitDetCircle(ox, oy, ow, oh);
		objects.add(c);
		c.textureName = texture;
	}

	public void addhitDetTriangle(ArrayList<hitDetObj> objects, double ox, double oy, double ox2, double oy2, double ox3, double oy3) {

		hitDetTriangle t = new hitDetTriangle(ox, oy, ox2, oy2, ox3, oy3);
		objects.add(t);
	}

	public void addhitDetLine(ArrayList<hitDetObj> objects, double ox, double oy, double ox2, double oy2) {

		hitDetectLine l = new hitDetectLine(ox, oy, ox2, oy2);
		objects.add(l);
	}

	public void addNoHitRect(ArrayList<noHitObj> noHitObjects, double ox, double oy, double ow, double oh, String texture) {

		noHitRect r = new noHitRect(ox, oy, ow, oh);
		r.textureName = texture;
		noHitObjects.add(r);
	}

	public void addNoHitCircle(ArrayList<noHitObj> noHitObjects, double ox, double oy, double ow, double oh, String texture) {

		noHitCircle c = new noHitCircle(ox, oy, ow, oh);
		c.textureName = texture;
		noHitObjects.add(c);
	}

	public void addInteractionArea(ArrayList<noHitObj> noHitObjects, double ox, double oy, double ow, double oh, String type,
			String texture) {
		Interaction I = new Interaction(ox, oy, ow, oh);
		I.textureName = texture;
		noHitObjects.add(I);
	}

	public void addTree(ArrayList<noHitObj> nho, ArrayList<hitDetObj> obj, ArrayList<noHitObj> foregroundStuff,
			int treeRadius, int tx, int ty, int tw, int th, int topX, int topY, int topW, int topH, String trunk,
			String crown) {
		// hitbox
		BasicTree t = new BasicTree(nho, obj, foregroundStuff, treeRadius, tx, ty, tw, th, topX, topY, topW, topH,
				trunk, crown);

	}

	public void addMultipleTrees(ArrayList<noHitObj> nho, ArrayList<hitDetObj> obj, ArrayList<noHitObj> fgs,
			int treeRadius, int tw, int th, int topW, int topH, String trunk, String crown, int ax, int ay, int aw,
			int ah) {
		for (int i = ax; i < aw; i += treeRadius * 4) {
			for (int j = ay; j < ah; j += treeRadius * 4) {
				int dx = ThreadLocalRandom.current().nextInt(0, 30 + 1);
				int dy = ThreadLocalRandom.current().nextInt(0, 30 + 1);
				int PosOrNeg1 = ThreadLocalRandom.current().nextInt(0, 1 + 1);
				if (PosOrNeg1 == 0) {
					dx = -dx;
				}
				int PosOrNeg2 = ThreadLocalRandom.current().nextInt(0, 1 + 1);
				if (PosOrNeg2 == 0) {
					dy = -dy;
				}

				addTree(nho, obj, fgs, treeRadius, i + dx, j + dy, 60, 100, i + dx, j + dy - 30, 60, 60, trunk, crown);
			}
		}
	}

	private void addEnemy(double d, double e) { // addenemy metoden. skapar en
												// ny enemy vid positionen som
												// ins�tts i metoden
		{
			enemy Enemy = new enemy(d, e);
			enemies.add(Enemy);
		}
	}

}
