import processing.core.*;


public class Bullet extends PApplet {				//samma som i processing, men det finns BARA EN SORTS BULLETS. Ingen skild ebullet f�r enemies, de skjuter samma bullets.
	PVector location;								//D�rmed finns ocks� bara en bullets arraylist. detta �r m�jligt genom att det finns bara en shoot-metod, som b�de player och enemies kan anv�nda (se "actor")
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
	  void update() {
	    location.x = location.x + cos(rotation/180*PI)*speed-(player.playerX-player.pplayerX.get(18));
	    location.y = location.y + sin(rotation/180*PI)*speed-(player.playerY-player.pplayerY.get(18));


	    if (location.x > 0 && location.x < 1000 && location.y > 0 && location.y < 1000 && toRemove==false) {
	    } else {
	      Processing.bullets.remove(this);		//removar denna bullet
	    }
	   
	  }
	}
