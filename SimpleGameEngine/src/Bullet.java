import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Bullet {
	Actor shooter;
	boolean toRemove=false;
	int radius = 5;
	float oldPosX, oldPosY;
	double rotation, speed;
	Point2D.Double location;//bullet location
	Ellipse2D hitDetBullet;
	
	Bullet (Actor shooter, float targetPosX, float targetPosY) {
		this.shooter=shooter;
	    location = new Point2D.Double ((shooter.shooterLocation.getX()), (shooter.shooterLocation.getY()));
		hitDetBullet = new Ellipse2D.Double(location.x-radius, location.y-radius, radius*2, radius*2);
	    oldPosX = targetPosX;
	    oldPosY = targetPosY;
	    //rotation = Math.atan2(oldPosY - location.y, oldPosX - location.x) / Math.PI * 180;
	    rotation = Math.toDegrees(Math.atan2((double)(oldPosY - location.y), (double)(oldPosX - location.x)));
	    speed = 5;
	  }
	
	void update() {
		//System.out.println(rotation);
	    location.x =  (location.x + Math.cos(Math.PI*rotation/180)*speed);
	    location.y =  (location.y + Math.sin(Math.PI*rotation/180)*speed);
	    
	    
	    if (location.x > 0 - SimpleGameEngine.px && location.x < SimpleGameEngine.windowWidth - SimpleGameEngine.px && location.y > 0 - SimpleGameEngine.py && location.y < SimpleGameEngine.windowHeight - SimpleGameEngine.py && toRemove==false) {
	    } else {
	      world.bullets.remove(this);		//removar denna bullet
	    }
	    hitDetBullet.setFrame(location.x-radius, location.y-radius, radius*2, radius*2);
	  }
	void draw(Graphics2D g){
		g.fill(hitDetBullet);
	}
	
}

