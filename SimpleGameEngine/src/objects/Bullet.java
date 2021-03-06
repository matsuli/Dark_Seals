package objects;

import world.world;
import world.hitDetCircle;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import engine.Actor;
import engine.SimpleGameEngine;

public class Bullet {
	Actor shooter;
	boolean toRemove = false;
	public int radius = 5;
	double oldPosX, oldPosY;
	double rotation, speed;
	Point2D.Double location;// bullet location
	public hitDetCircle hitDetBullet;
	public hitDetCircle prevHitDetBullet;

	public Bullet(Actor shooter, double targetPosX, double targetPosY) {
		this.shooter = shooter;
		location = new Point2D.Double((shooter.shooterLocation.getX()), (shooter.shooterLocation.getY()));
		hitDetBullet = new hitDetCircle(location.x - radius, location.y - radius, radius * 2, radius * 2);
		prevHitDetBullet = new hitDetCircle(location.x - radius, location.y - radius, radius * 2, radius * 2);
		oldPosX = targetPosX;
		oldPosY = targetPosY;
		rotation = Math.toDegrees(Math.atan2((oldPosY - location.y), (oldPosX - location.x)));
		speed = 5;
	}

	public void update() {

		prevHitDetBullet.circle.setFrame(location.x - radius, location.y - radius, radius * 2, radius * 2);

		location.x = (location.x + Math.cos(Math.PI * rotation / 180) * speed);
		location.y = (location.y + Math.sin(Math.PI * rotation / 180) * speed);

		// remove denna bullet om den hamnar utanf�r screen
		if (location.x > 0 - SimpleGameEngine.px && location.x < SimpleGameEngine.windowWidth - SimpleGameEngine.px
				&& location.y > 0 - SimpleGameEngine.py
				&& location.y < SimpleGameEngine.windowHeight - SimpleGameEngine.py && toRemove == false) {
		} else {
			world.bullets.remove(this);
		}

		hitDetBullet.circle.setFrame(location.x - radius, location.y - radius, radius * 2, radius * 2);
	}

	public void draw(Graphics2D g) {
		g.fill(hitDetBullet.circle);
	}

}
