package engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import world.world;
import world.hitDetCircle;

public class Player extends Actor {

	// used in movement
	double right = 5;
	double left = 5;
	double down = 5;
	double up = 5;
	double speed = 10;
	boolean sneak;
	//sealcode, used for hit detection
	public boolean hit;
	public boolean hitLeft;
	public boolean hitRight;
	public boolean hitUp;
	public boolean hitDown;
	public double hitCorrectionLeft;
	public double hitCorrectionRight;
	public double hitCorrectionUp;
	public double hitCorrectionDown;
	public boolean hitCorrected;
	//player animation spritesheet
	String src = "PlayerSpritesheet";
	//used to determine which direction player is facing when standing still
	String lastDir;
	
	//walking animations
	private BufferedImage[] WalkingDownSprite = { Sprite.getSprite(0, 0, src), Sprite.getSprite(1, 0, src), Sprite.getSprite(2, 0, src) };
	private Animation WalkingDown = new Animation(WalkingDownSprite, 10, false);
	private BufferedImage[] WalkingUpSprite = { Sprite.getSprite(0, 1, src), Sprite.getSprite(1, 1, src), Sprite.getSprite(2, 1, src) };
	private Animation WalkingUp = new Animation(WalkingUpSprite, 10, false);
	private BufferedImage[] WalkingRightSprite = { Sprite.getSprite(0, 2, src), Sprite.getSprite(1, 2, src), Sprite.getSprite(2, 2, src) };
	private Animation WalkingRight = new Animation(WalkingRightSprite, 10, false);
	private BufferedImage[] WalkingLeftSprite = { Sprite.getSprite(0, 3, src), Sprite.getSprite(1, 3, src), Sprite.getSprite(2, 3, src) };
	private Animation WalkingLeft = new Animation(WalkingLeftSprite, 10, false);
	//sneak animation
	private BufferedImage[] CrouchSprite = { Sprite.getSprite(0, 5, src) };
	private Animation Crouching = new Animation(CrouchSprite, 10, false);
	//idle animations
	private BufferedImage[] IdleDownSprite = { Sprite.getSprite(0, 4, src)};
	private Animation IdleDown = new Animation(IdleDownSprite, 10, false);
	private BufferedImage[] IdleUpSprite = { Sprite.getSprite(1, 4, src)};
	private Animation IdleUp = new Animation(IdleUpSprite, 10, false);
	private BufferedImage[] IdleRightSprite = { Sprite.getSprite(2, 4, src)};
	private Animation IdleRight = new Animation(IdleRightSprite, 10, false);
	private BufferedImage[] IdleLeftSprite = { Sprite.getSprite(3, 4, src)};
	private Animation IdleLeft = new Animation(IdleLeftSprite, 10, false);
	//player current animation
	private Animation playerSprite = Crouching;
	

	public Player() {
		radius = 10;
		location.x = SimpleGameEngine.playerX;
		location.y = SimpleGameEngine.playerY;
		shooterLocation.setLocation(location.x - SimpleGameEngine.px, location.y - SimpleGameEngine.py);
		hitDetCircle = new hitDetCircle(location.x - radius, location.y - radius, radius * 2, radius * 2); // Används
																											// för
																											// att
																											// hitdetecta
																											// player.
																											// Blir
																											// "translated"
																											// i
																											// hitdetect()
																											// med
																											// objekten
		prevHitDetCircle = new hitDetCircle(location.x - radius, location.y - radius, radius * 2, radius * 2);
	} // location-radius innebär att location e cirkelns mitt

	public void drawPlayer(Graphics2D g, int x, int y) { // ritar player med x,
															// y, (dvs. playerX,
															// playerY) i
															// mitten. (OBS!
															// Filloval ritar
															// som vänster övre
															// hörn.)
		g.setColor(Color.RED);
		x = (int) (x - (radius));
		y = (int) (y - (radius));
		g.fillOval(x, y, (int) radius * 2, (int) radius * 2);

		g.drawImage(playerSprite.getSprite(), x, y, (int) radius * 2, (int) radius * 2, null);
	}

	public void Control(world space) {
		hitDetCircle.circle.setFrame(location.x - radius - SimpleGameEngine.px,
				location.y - radius - SimpleGameEngine.py, radius * 2, radius * 2);
		prevHitDetCircle.circle.setFrame(location.x - radius - SimpleGameEngine.px,
				location.y - radius - SimpleGameEngine.py, radius * 2, radius * 2);

		speed = 10;
		if (sneak) {
			speed = 5;
		}
		right = speed;
		left = speed;
		down = speed;
		up = speed;

		hitLeft = false;
		hitRight = false;
		hitUp = false;
		hitDown = false;
		hit = false;
		
		if (lastDir == "right") {
			playerSprite = IdleRight;
		}
		if (lastDir == "left") {
			playerSprite = IdleLeft;
		}
		if (lastDir == "down") {
			playerSprite = IdleDown;
		}
		if (lastDir == "up") {
			playerSprite = IdleUp;
		}

		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_D)) {
			playerSprite = WalkingRight;
			lastDir = "right";
			SimpleGameEngine.px -= right;
			space.HitDetect();
			if (hitRight == true) {
				SimpleGameEngine.px += hitCorrectionRight;

			}
		}
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_A)) {
			playerSprite = WalkingLeft;
			lastDir = "left";
			SimpleGameEngine.px += left;
			space.HitDetect();
			if (hitLeft == true) {
				SimpleGameEngine.px -= hitCorrectionLeft;

			}
		}
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_S)) {
			playerSprite = WalkingDown;
			lastDir = "down";
			SimpleGameEngine.py -= down;
			space.HitDetect();
			if (hitDown == true) {
				SimpleGameEngine.py += hitCorrectionDown;

			}
		}

		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_W)) {
			playerSprite = WalkingUp;
			lastDir = "up";
			SimpleGameEngine.py += up;
			space.HitDetect();
			if (hitUp == true) {
				SimpleGameEngine.py -= hitCorrectionUp;

			}
		}
		
		
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_SHIFT)) {
			sneak = true;
			playerSprite = Crouching;
		} else {
			sneak = false;
		}

		playerSprite.update();

		hitDetCircle.circle.setFrame(location.x - radius - SimpleGameEngine.px,
				location.y - radius - SimpleGameEngine.py, radius * 2, radius * 2);
		shooterLocation.setLocation(location.x - SimpleGameEngine.px, location.y - SimpleGameEngine.py);
	}

}
