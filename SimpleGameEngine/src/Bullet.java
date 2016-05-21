import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class Bullet {
	Actor shooter;
	boolean toRemove=false;
	int radius = 5;
	float oldPosX, oldPosY;
	double rotation, speed;
	Point location;//bullet location
	Ellipse2D hitDetBullet;
	
	Bullet (Actor shooter, float targetPosX, float targetPosY) {
		this.shooter=shooter;
	    location = new Point (shooter.shooterLocation.x, shooter.shooterLocation.y);
		hitDetBullet = new Ellipse2D.Double(location.x-radius, location.y-radius, radius*2, radius*2);
	    oldPosX = targetPosX;
	    oldPosY = targetPosY;
	    //rotation = Math.atan2(oldPosY - location.y, oldPosX - location.x) / Math.PI * 180;
	    rotation = Math.toDegrees(Math.atan2(oldPosY - location.y, oldPosX - location.x));
	    speed = 5;
	  }
	
	void update() {
	    location.x = (int) (location.x + Math.cos(rotation/180*Math.PI)*speed);
	    location.y = (int) (location.y + Math.sin(rotation/180*Math.PI)*speed);
	    
	    
	    if (location.x > 0 - SimpleGameEngine.px && location.x < SimpleGameEngine.windowWidth - SimpleGameEngine.px && location.y > 0 - SimpleGameEngine.py && location.y < SimpleGameEngine.windowHeight - SimpleGameEngine.py && toRemove==false) {
	    } else {
	      world.bullets.remove(this);		//removar denna bullet
	    }
	    hitDetBullet.setFrame(location.x-radius-SimpleGameEngine.px, location.y-radius-SimpleGameEngine.py, radius*2, radius*2);
	  }
}
