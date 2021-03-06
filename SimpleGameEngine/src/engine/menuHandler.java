package engine;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

import world.world;

public class menuHandler {
	ArrayList<menu> menus = new ArrayList<menu>();
	menu pauseMenu;
	menu warningMenu;
	menu prevMenu;

	public menuHandler() {

		pauseMenu = new menu("Pause", "Continue", "Save Game", "Load Game", "Settings", "Exit to main menu", "Quit");
		menus.add(pauseMenu);
		warningMenu = new menu("Warning", "HEADER 2 Unsaved progress will be lost", "Continue to main menu", "Cancel"); 
																														
																														
		//En rubrik kan addas genom att s�tta till																												
		// HEADER i b�rjan av stringen och sedan en
		// siffra. Siffran s�ger hur m�nga g�nger st�rre
		// font rubriken har.																												
		// OBS! M�ste vara
		// mellanrum som
		// ovan. Se menuBox.																												
																													
		menus.add(warningMenu); 
		
		menus.add(SimpleGameEngine.savingSystem.saveMenu); 
		
		menus.add(SimpleGameEngine.savingSystem.overWriteWarning);
	}

	menu getCurrentMenu() {

		menu returnMenu = null;
		for (Iterator<menu> it = this.menus.iterator(); it.hasNext();) {
			menu m = it.next();

			if (SimpleGameEngine.currentMenu == m.thisMenu) {
				returnMenu = m;
			}
		}
		return returnMenu;
	}

	void updateCurrentMenu(Graphics2D g) {
		this.getCurrentMenu().update(g);
	}

	void menuClicked(String clicked) {

		if (clicked == "New Game") {
			SimpleGameEngine.px = 0;
			SimpleGameEngine.py = 0;
			prevMenu = this.getCurrentMenu();
			SimpleGameEngine.play = true;
			SimpleGameEngine.currentMenu = null;
			SimpleGameEngine.space.enemies.clear();
			world.bullets.clear();
		}

		else if (clicked == "Continue") {
			prevMenu = this.getCurrentMenu();
			SimpleGameEngine.play = true;
			SimpleGameEngine.currentMenu = null;
		} else if (clicked == "Save Game") {
			prevMenu = this.getCurrentMenu();
			SimpleGameEngine.play = false;
			SimpleGameEngine.currentMenu = "saveMenu";
			SimpleGameEngine.savingSystem.save = true;
			SimpleGameEngine.savingSystem.load = false;
		} else if (clicked == "Load Game") {
			SimpleGameEngine.space.enemies.clear();
			world.bullets.clear();
			prevMenu = this.getCurrentMenu();
			SimpleGameEngine.play = false;
			SimpleGameEngine.currentMenu = "saveMenu";
			SimpleGameEngine.savingSystem.save = false;
			SimpleGameEngine.savingSystem.load = true;
		} else if (clicked == "No, cancel") {
			SimpleGameEngine.play = false;
			SimpleGameEngine.currentMenu = SimpleGameEngine.savingSystem.saveMenu.thisMenu;
		} else if (clicked == "Quit") {
			System.exit(0);
		} else if (clicked == "Exit to main menu") {
			prevMenu = this.getCurrentMenu();
			SimpleGameEngine.play = false;
			SimpleGameEngine.currentMenu = "Warning";
		} else if (clicked == "Continue to main menu") {
			prevMenu = this.getCurrentMenu();
			SimpleGameEngine.currentMenu = "Main";
		} else if (clicked == "Cancel") {
			SimpleGameEngine.currentMenu = prevMenu.thisMenu;
		} else if (clicked == "Yes" && SimpleGameEngine.currentMenu == "overWriteWarning") {
			SimpleGameEngine.savingSystem.overWrite();
		}

		else if (clicked != "Cancel" && clicked != null && SimpleGameEngine.currentMenu == "saveMenu") {
			if (SimpleGameEngine.savingSystem.save) {
				SimpleGameEngine.savingSystem.save();
			} else {
				SimpleGameEngine.savingSystem.load();
			}
		} else if (clicked == "Write") {
			SimpleGameEngine.space.write = true;
			SimpleGameEngine.space.LoadWorld();
			SimpleGameEngine.currentMenu = "Main";
		} else if (clicked == "Read") {
			SimpleGameEngine.space.write = false;
			SimpleGameEngine.space.LoadWorld();
			SimpleGameEngine.currentMenu = "Main";
		}

		else if (clicked == null) {

		}
	}

}
