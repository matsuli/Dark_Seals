import java.awt.Point;

public class Bullet {
	Actor shooter;
	boolean toRemove=false;
	float radius = 5;
	float oldPosX, oldPosY, rotation, speed;
	Point location = new Point ();//bullet location
	
	Bullet (actor shooter, float targetPosX, float targetPosY) {
		this.shooter=shooter;
	    location= new PVector(shooter.location.x, shooter.location.y);
	    oldPosX = targetPosX;
	    oldPosY = targetPosY;
	    rotation = atan2(oldPosY - location.y, oldPosX - location.x) / PI * 180;
	    speed = 15;
	  }
}
