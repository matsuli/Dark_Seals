import java.awt.Point;

public class Bullet {
	Actor shooter;
	boolean toRemove=false;
	float radius = 5;
	float oldPosX, oldPosY, speed;
	double rotation;
	Point location;//bullet location
	
	Bullet (Actor shooter, float targetPosX, float targetPosY) {
		this.shooter=shooter;
	    location = new Point (shooter.location.x, shooter.location.y);
	    oldPosX = targetPosX;
	    oldPosY = targetPosY;
	    rotation = Math.atan2(oldPosY - location.y, oldPosX - location.x) / Math.PI * 180;
	    speed = 15;
	  }
	
	void update() {
	    location.x = (int) (location.x + Math.cos(rotation/180*Math.PI)*speed);
	    location.y = (int) (location.y + Math.sin(rotation/180*Math.PI)*speed);


	    if (location.x > 0 && location.x < SimpleGameEngine.windowWidth && location.y > 0 && location.y < SimpleGameEngine.windowHeight && toRemove==false) {
	    } else {
	      SimpleGameEngine.bullets.remove(this);		//removar denna bullet
	    }
	   
	  }
}
