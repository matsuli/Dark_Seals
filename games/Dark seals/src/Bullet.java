import processing.core.*;


public class Bullet extends PApplet {
	PVector location;
	  float radius=5;
	  float oldPosX, oldPosY, rotation, speed;
	  boolean toRemove=false;
	  actor shooter;
	  Bullet(actor shooter, float targetPosX, float targetPosY) {
		this.shooter=shooter;
	    location= new PVector(shooter.location.x, shooter.location.y);
	    oldPosX = targetPosX;
	    oldPosY = targetPosY;
	    rotation = atan2(oldPosY - location.y, oldPosX - location.x) / PI * 180;
	    speed = 15;
	  }
	  void update( int i) {
	    location.x = location.x + cos(rotation/180*PI)*speed-(player.playerX-player.pplayerX.get(18));
	    location.y = location.y + sin(rotation/180*PI)*speed-(player.playerY-player.pplayerY.get(18));


	    if (location.x > 0 && location.x < 1000 && location.y > 0 && location.y < 1000 && toRemove==false) {
	    } else {
	      Processing.bullets.remove(i);
	    }
	   
	  }
	}
