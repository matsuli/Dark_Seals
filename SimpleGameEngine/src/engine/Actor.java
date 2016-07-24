package engine;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Actor {
	Point2D.Double location = new Point2D.Double (); //actor location?
	Point2D.Double shooterLocation = new Point2D.Double ();
	boolean canShoot=true;
	int canShootCounter = 0;
	Ellipse2D hitDetCircle;
	Ellipse2D prevHitDetCircle;
	public int radius;
	
	void shoot(Actor shooter, double targetPosX, double targetPosY) {	
				
		if (shooter.canShoot == false) {
		      shooter.canShootCounter ++;
		      if (shooter.canShootCounter == 20) {
		        shooter.canShoot = true;
		        shooter.canShootCounter = 0;
		      }
		}
		if (shooter.canShoot == true) {
			world.bullets.add(new Bullet(shooter, targetPosX, targetPosY));
			shooter.canShoot = false;
		}
	}
}
