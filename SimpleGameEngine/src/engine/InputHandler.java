package engine;

import java.awt.Component;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class InputHandler implements KeyListener {

	boolean[] keys = new boolean[256];
	boolean pressed = false;
	boolean firstPress = true;

	public InputHandler(Component c) {
		c.addKeyListener(this);
	}

	public boolean isKeyDown(int keyCode) {
		if (keyCode > 0 && keyCode < 256) {
			return keys[keyCode];
		}
		return false;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() > 0 && e.getKeyCode() < 256) {
			keys[e.getKeyCode()] = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() > 0 && e.getKeyCode() < 256) {
			keys[e.getKeyCode()] = false;
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public boolean keyPressed2(int keyEvent) { // bootleg keyPressed. OBS kan
												// bara callas för en key åt
												// gången, annars buggar det då
												// pressed och firstpress är
												// samma för alla keys. Vore bra
												// om vi kunde fixa det...

		if (SimpleGameEngine.input.isKeyDown(keyEvent)) {
			if (pressed == false && firstPress == true) {
				pressed = true;
				firstPress = false;
			}

			else if (pressed == true && firstPress == true) {
				pressed = false;
				firstPress = false;
			} else {
				pressed = false;
			}
		} else {
			firstPress = true;
			pressed = false;
		}

		return pressed;
	}

	public void handleInput() {

		if (keyPressed2(KeyEvent.VK_ESCAPE)) {

			if (SimpleGameEngine.play) {
				SimpleGameEngine.play = false;
				SimpleGameEngine.currentMenu = SimpleGameEngine.menuHandler.pauseMenu.thisMenu;
			} else {
				SimpleGameEngine.play = true;
				SimpleGameEngine.currentMenu = null;
			}
		}

		if (SimpleGameEngine.input.isKeyDown(KeyEvent.VK_DELETE)) {

			if (SimpleGameEngine.currentMenu == "saveMenu") {
				SimpleGameEngine.savingSystem.delete();
			}

		}

		// shoot
		if (SimpleGameEngine.mouse.buttonDown(1)) {
			if (SimpleGameEngine.play) {
				SimpleGameEngine.player.shoot(SimpleGameEngine.player, SimpleGameEngine.mouseX, SimpleGameEngine.mouseY);
			} else if (!SimpleGameEngine.play) {
				String menuString = SimpleGameEngine.menuHandler.getCurrentMenu().returnString;
				if (menuString != null) {
					SimpleGameEngine.menuHandler.menuClicked(menuString);
				}
			}
		}
	}
}
