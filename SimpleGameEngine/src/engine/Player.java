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
	double stamina = 100;
	boolean sprint, sneak;
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

	private BufferedImage[] WalkingBackSprite = { Sprite.getSprite(0, 0, src), Sprite.getSprite(1, 0, src), Sprite.getSprite(2, 0, src) };
	private Animation WalkingBack = new Animation(WalkingBackSprite, 10, false);
	private BufferedImage[] WalkingForwardSprite = { Sprite.getSprite(0, 1, src), Sprite.getSprite(1, 1, src), Sprite.getSprite(2, 1, src) };
	private Animation WalkingForward = new Animation(WalkingForwardSprite, 10, false);
	private BufferedImage[] WalkingRightSprite = { Sprite.getSprite(0, 2, src), Sprite.getSprite(1, 2, src), Sprite.getSprite(2, 2, src) };
	private Animation WalkingRight = new Animation(WalkingRightSprite, 10, false);
	private BufferedImage[] WalkingLeftSprite = { Sprite.getSprite(0, 3, src), Sprite.getSprite(1, 3, src), Sprite.getSprite(2, 3, src) };
	private Animation WalkingLeft = new Animation(WalkingLeftSprite, 10, false);
	private BufferedImage[] CrouchSprite = { Sprite.getSprite(0, 5, src) };
	private Animation Crouching = new Animation(CrouchSprite, 10, false);
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

		right = speed;
		left = speed;
		down = speed;
		up = speed;

		hitLeft = false;
		hitRight = false;
		hitUp = false;
		hitDown = false;
		hit = false;

		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_D)) {
			playerSprite = WalkingRight;
			SimpleGameEngine.px -= right;
			space.HitDetect();
			if (hitRight == true) {
				SimpleGameEngine.px += hitCorrectionRight;

			}
		}
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_A)) {
			playerSprite = WalkingLeft;
			SimpleGameEngine.px += left;
			space.HitDetect();
			if (hitLeft == true) {
				SimpleGameEngine.px -= hitCorrectionLeft;

			}
		}
		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_S)) {
			playerSprite = WalkingBack;
			SimpleGameEngine.py -= down;
			space.HitDetect();
			if (hitDown == true) {
				SimpleGameEngine.py += hitCorrectionDown;

			}
		}

		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_W)) {
			playerSprite = WalkingForward;
			SimpleGameEngine.py += up;
			space.HitDetect();
			if (hitUp == true) {
				SimpleGameEngine.py -= hitCorrectionUp;

			}
		}

		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_SHIFT)) {
			sprint = true;
		} else {
			sprint = false;
		}
		if (sprint == true) {
			if (stamina >= 2 / 60) {
				stamina = stamina - (2.0 / 5);
			} else if (stamina >= 0) {
				stamina = stamina - (1.0 / 5);
			}
			if (speed < 10 && stamina > 0) {
				speed = speed + 1.0 / 20;
			}
			if (stamina <= 0 && speed > 5) {
				speed = speed - 1.0 / 20;
			}
		}
		if (sprint == false) {
			if (speed > 10) {
				speed = speed - 1.0 / 20;
			}
		}
		if (stamina <= 100 && sprint == false) {
			stamina = stamina + 1;
		}

		playerSprite.update();

		hitDetCircle.circle.setFrame(location.x - radius - SimpleGameEngine.px,
				location.y - radius - SimpleGameEngine.py, radius * 2, radius * 2);
		shooterLocation.setLocation(location.x - SimpleGameEngine.px, location.y - SimpleGameEngine.py);
	}

}
