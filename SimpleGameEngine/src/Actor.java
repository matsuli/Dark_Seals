import java.awt.Point;

public class Actor {
	Point location = new Point (); //actor location?
	boolean canShoot=false;
	int canShootCounter;
	
	void shoot(Actor shooter, float targetPosX, float targetPosY) {	
		world.bullets.add(new Bullet(shooter, targetPosX, targetPosY));
		shooter.canShoot = false;
		shooter.canShootCounter = 0;
		
		if (canShoot == false) {
		      canShootCounter ++;
		      if (canShootCounter == 20) {
		        canShoot = true;
		      }
		    }
	}
}
