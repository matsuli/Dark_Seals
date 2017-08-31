package objects;

import world.world;
import world.hitDetCircle;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import engine.Actor;
import engine.SimpleGameEngine;

public class enemy extends Actor { // enemyn extendar actor, enemyn �r allts� en
									// actor

	Point2D.Double elocationTest = new Point2D.Double();
	double eoldPosX, eoldPosY, erotation2, espeed;
	public boolean toRemove;
	int enemyState = 1;
	float sightRadius = 180;
	boolean nearCenter;
	float noDetectTimer = 0;
	public float ReloadTimer = 0;
	double ad = 0;
	double a1;
	double a2;
	public Actor targetActor;

	public enemy(double d, double e) {
		radius = 8;
		location.x = d;
		location.y = e;
		elocationTest.x = d;
		elocationTest.y = e;
		hitDetCircle = new hitDetCircle(location.x - radius, location.y - radius, radius * 2, radius * 2);
		targetActor = SimpleGameEngine.player;
		eoldPosX = targetActor.hitDetCircle.circle.getX() + targetActor.radius - targetActor.radius;
		eoldPosY = targetActor.hitDetCircle.circle.getY() + targetActor.radius - targetActor.radius;
		erotation2 = Math.atan2(eoldPosY - location.y, eoldPosX - location.x) / Math.PI * 180;
		espeed = 0.8;
		ad = Math.atan2(eoldPosY - location.y, eoldPosX - location.x);
	}

	public void update() {


		if (enemyState == 1) {
			eoldPosX = targetActor.hitDetCircle.circle.getX() + targetActor.radius;
			eoldPosY = targetActor.hitDetCircle.circle.getY() + targetActor.radius;
			erotation2 = Math.atan2(eoldPosY - elocationTest.y, eoldPosX - elocationTest.x) / Math.PI * 180;
			elocationTest.x = elocationTest.x + Math.cos(erotation2 / 180 * Math.PI) * espeed;
			elocationTest.y = elocationTest.y + Math.sin(erotation2 / 180 * Math.PI) * espeed;
			ad = Math.atan2(eoldPosY - location.y, eoldPosX - location.x);
		}

		location.x = elocationTest.x; // location �r enemyns position. OBS! man
										// beh�ver inte kompensera f�r
										// playerX/Y, d� det redan gjorts h�r.
		location.y = elocationTest.y;
		
		hitDetCircle.circle.setFrame(location.x - radius, location.y - radius, radius * 2, radius * 2);

		// This angle is the direction (p� enhetscirkel) where the enemy2 is
		// heading.

		a1 = (ad - Math.PI * 2.5 / 6); // The angles that determine the circle
										// sector for detection of player.
		a2 = (ad + Math.PI * 2.5 / 6); // They are calculated based on were the
										// enemy is heading, meaning the enemy
										// is always looking in the direction he
										// is heading.
		// These angles must lie betwwn PI and -PI, otherwise the detection
		// calculations wont work. They don�t always do, in that case they have
		// to be compensated for.

		double a = Math.atan2(targetActor.hitDetCircle.circle.getY() + targetActor.radius - location.y,
				targetActor.hitDetCircle.circle.getX() + targetActor.radius - location.x);
		// a �r vinkeln mellan player, enemin o enemiens x-axel som startar fr�n
		// enemy. Inte 100%s�ker // p� // hur // det // funkar,
		// tror det r�knas som man sku transleita ti enemys position, sen r�kna
		// vinkeln mellan player o origo o x-axel.
		if (Math.sqrt(targetActor.hitDetCircle.circle.getX() + targetActor.radius - location.x)
				+ Math.sqrt(targetActor.hitDetCircle.circle.getY() + targetActor.radius - location.y) <= Math
						.sqrt(sightRadius)) // Nearcenter is true when player is
											// inside sightradius
		{
			nearCenter = true;
		} else {
			nearCenter = false;
		}

		if (a1 < -Math.PI) { // If a1 is smaller than -PI, which it must not be,
								// add 2PI to it
			a1 = a1 + Math.PI * 2;

			if (nearCenter == true && !(a >= a2 && a <= a1)) { // If player is
																// within
																// sightradius
																// and inside
																// the angle of
																// the circle
																// sector,
																// player is
																// detected and
																// enemyState
																// changes.
				enemyState = 1;
			} // F�r att a1 har kompenserats genom att addera 2PI, m�ste
				// negation (!) anv�ndas f�r a >=a2 && a<=a1,
				// eftersom a2 och a1 nu �r gr�nserna f�r circlesektorns
				// vinklars komplementvinklar. (Vet inte varf�r det �r s�, men
				// det funkar....
			a1 = a1 - Math.PI * 2; // Returns a1 to its original value, so that
									// the circle sector looks correct

		}

		if (a2 > Math.PI) { // If a2 is greater than -PI, which it must not be,
							// subtract 2PI from it
			a2 = a2 - Math.PI * 2;

			if (nearCenter == true && !(a >= a2 && a <= a1)) { // If player is
																// within
																// sightradius
																// and inside
																// the angle of
																// the circle
																// sector,
																// player is
																// detected and
																// enemyState
																// changes.
				enemyState = 1;
			} // F�r att a2 har kompenserats genom att subtarhera 2PI, m�ste
				// negation (!) anv�ndas f�r a >=a2 && a<=a1,
				// eftersom a2 och a1 nu �r gr�nserna f�r circlesektorns
				// vinklars komplementvinklar. (Vet inte varf�r det �r s�, men
				// det funkar...)
			a2 = a2 + Math.PI * 2; // Returns a2 to its original value, so that
									// the circle sector looks correct

		}

		// Ritar cirkelsektorn

		if (nearCenter == true && a >= a1 && a <= a2) { // If player is within
														// sightradius and
														// inside the angle of
														// the circle sector,
														// player is detected
														// and enemyState
														// changes.
			enemyState = 1;
			ReloadTimer++;
		} // Det h�r �r alltid av n�gon orsak false om a1<-PI eller a2>PI, men
			// de specialfallen har vi redan checkat f�r tidigare

		if (nearCenter == false && enemyState == 1) { // om player �r out of
														// sight, men enemy
														// jagar, startar en
														// timer (noDetectTimer)
			noDetectTimer++;
		}
		if (noDetectTimer >= 10000) { // om noDetectTimer n�r ett visst tal
										// slutar enemy jaga och timer resetas
			enemyState = 0;
			noDetectTimer = 0;
			noDetectTimer += 0;
		}

		for (Bullet b : world.bullets) {

			if (b.hitDetBullet.hitdetect(hitDetCircle,(int) radius)
					&& b.shooter != this) { // hit detection. Igen,
											// b.shooter!=this f�r att den inte
											// ska d�da sig sj�lv d� den skjuter
				toRemove = true;
				b.toRemove = true; // removar ocks� bullet
			}
		}

		
	}

	public void drawEnemy(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fill(hitDetCircle.circle);
		g.setColor(Color.YELLOW);
		g.drawArc((int) location.x, (int) location.y, (int) sightRadius * 2, (int) sightRadius * 2, (int) a1, (int) a2); // ritar
																															// enemyns
																															// detection
																															// cirkelsektor
																															// (det
																															// h�r
																															// ska
																															// v�l
																															// bort
																															// sen
																															// i
																															// riktiga
																															// spelet,
																															// eller?)

	}

}
