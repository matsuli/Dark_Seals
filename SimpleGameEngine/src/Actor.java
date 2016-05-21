import java.awt.Point;

public class Actor {
	Point location = new Point (); //actor location?
	Point shooterLocation = new Point ();
	boolean canShoot=false;
	int canShootCounter = 0;
	
	void shoot(Actor shooter, float targetPosX, float targetPosY) {	
		shooter.canShoot = false;
		
		if (shooter.canShoot == false) {
		      shooter.canShootCounter ++;
		      if (shooter.canShootCounter == 20) {
		        shooter.canShoot = true;
		        shooter.canShootCounter = 0;
		      }
		}
		if (shooter.canShoot == true) {
			world.bullets.add(new Bullet(shooter, targetPosX, targetPosY));
		}
	}
}
