package engine;

import world.world;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.imageio.ImageIO;

import world.hitDetObj;
import world.noHitObj;

public class savingSystem {
	boolean save;
	boolean load;
	menu saveMenu;
	menu overWriteWarning;
	File file;
	int saveSlots = 6;
	int currentSaveSlot;
	File[] saves = new File[6];
	ArrayList<String> saveNames = new ArrayList<String>();

	public savingSystem() {

		file = new File("Saved games"); // Skapar foldern Saved games om den
										// inte finns.
		if (!file.exists()) {
			file.mkdirs();
		}
		File[] savesNonSorted = file.listFiles(); // listar alla filer i saved
													// games mappen, dvs.alla
													// saves

		for (int i = savesNonSorted.length; i > 0; i--) {

			try {
				FileInputStream fis = new FileInputStream(savesNonSorted[i - 1]);
				ObjectInputStream ois = new ObjectInputStream(fis);
				saveObject save = (saveObject) ois.readObject();
				ois.close();
				saves[save.saveSlot] = savesNonSorted[i - 1];	//addar save filerna till saves array
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			

		}

		// System.out.println(saves.length);

		for (int i = saves.length; i > 0; i--) {
			if (saves[i - 1] != null) {
				saveNames.add(saves[i - 1].getName());		//läser savernas namn, lägger till i savenames array
			} else {
				saveNames.add("Empty slot");
			}
		}
		Collections.reverse(saveNames);
		saveMenu = new menu("saveMenu", saveNames.get(0), saveNames.get(1), saveNames.get(2), saveNames.get(3),	//skapar menun med saverna
				saveNames.get(4), saveNames.get(5), "Cancel");
		overWriteWarning = new menu("overWriteWarning", "HEADER 2 Overwrite existing save?", "Yes", "No, cancel");
	}

	public void save() {
		currentSaveSlot = saveMenu.getSelectedMenuBox();
		// System.out.println(currentSaveSlot);

		if (saveNames.get(currentSaveSlot - 1).equals("Empty slot")) { // om man
																		// clickar
																		// empty
																		// slot
			System.out.println("wooo");
			try {
				FileOutputStream fis = new FileOutputStream(
						"Saved games" + "/Save" + currentSaveSlot + "-" + SimpleGameEngine.currentWorld); // skapar
																											// filen
																											// i
																											// saved
																											// games
				ObjectOutputStream oos = new ObjectOutputStream(fis);
				saveObject save = new saveObject(currentSaveSlot - 1); // skapar
																		// saveObject
																		// med
																		// saveslot=i-1
				oos.writeObject(save);
				oos.close();

			} catch (Exception ex) {
				ex.printStackTrace();
			}

		} else if (saveNames.get(currentSaveSlot - 1).equals(saves[currentSaveSlot - 1].getName())) { // om
																										// klickade
																										// namnet
																										// är
																										// samma
																										// som
																										// filens
																										// namn,
																										// ska
																										// overwrite
			System.out.println("yayy");
			SimpleGameEngine.currentMenu = "overWriteWarning";

		}
		update();
	}

	public void load() {
		currentSaveSlot = saveMenu.getSelectedMenuBox();
		// System.out.println(i);
		try {
			if (saves[currentSaveSlot - 1] != null) {
				FileInputStream fis = new FileInputStream(saves[currentSaveSlot - 1]); // laddar
																						// input
																						// ur
																						// klickade
																						// saven
				ObjectInputStream ois = new ObjectInputStream(fis);
				saveObject save = (saveObject) ois.readObject(); // läser
																	// objektet
				ois.close();
				SimpleGameEngine.px = save.px; // uppdaterar simplegameengines
												// värden så de matchar objektet
				SimpleGameEngine.py = save.py;
				SimpleGameEngine.currentWorld = save.currentWorld;
				SimpleGameEngine.currentMenu = SimpleGameEngine.menuHandler.pauseMenu.thisMenu;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void overWrite() {

		try {
			saves[currentSaveSlot - 1].delete(); // deletar gamla
			String replacementSave = "Saved games" + "/" + saveNames.get(currentSaveSlot - 1).substring(0,
					saveNames.get(currentSaveSlot - 1).lastIndexOf("-")) + "-" + SimpleGameEngine.currentWorld;
			FileOutputStream fis = new FileOutputStream(replacementSave);
			ObjectOutputStream oos = new ObjectOutputStream(fis);
			saveObject save = new saveObject(currentSaveSlot - 1);
			oos.writeObject(save);
			oos.close();
			saves[currentSaveSlot - 1] = Paths.get(replacementSave).toFile(); // addar
																				// nya
																				// på
																				// gamlas
																				// plats
																				// i
																				// array

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		SimpleGameEngine.currentMenu = "saveMenu";
		update();
	}

	public void delete() {
		currentSaveSlot = saveMenu.getSelectedMenuBox();

		if (currentSaveSlot != 0 && currentSaveSlot != 7) {
			try {
				if (saves[currentSaveSlot - 1] != null) {
					saves[currentSaveSlot - 1].delete();
					saves[currentSaveSlot - 1] = null;
				} // deletar gamla
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			SimpleGameEngine.currentMenu = "saveMenu";
			update();
		}
	}

	public void update() { // updaterar savemenu vid saving
		// System.out.println("ok");
		file = new File("Saved games"); // samma saker som i konstruktor
		if (!file.exists()) {
			file.mkdirs();
		}
		File[] savesNonSorted = file.listFiles();
		// System.out.println(savesNonSorted.length);
		for (int i = savesNonSorted.length; i > 0; i--) {

			try {
				FileInputStream fis = new FileInputStream(savesNonSorted[i - 1]);
				ObjectInputStream ois = new ObjectInputStream(fis);
				saveObject save = (saveObject) ois.readObject();
				ois.close();
				saves[save.saveSlot] = savesNonSorted[i - 1];
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

		saveNames.clear(); // tar bort tidigare save names

		for (int i = saves.length; i > 0; i--) {
			if (saves[i - 1] != null) {
				saveNames.add(saves[i - 1].getName());
			} else {
				saveNames.add("Empty slot");
			}
		}
		Collections.reverse(saveNames);
		saveMenu.menuBoxes.clear();
		SimpleGameEngine.menuHandler.menus.remove(saveMenu); // removar gamla
																// menun ur
																// menuhanlder
		saveMenu = new menu("saveMenu", saveNames.get(0), saveNames.get(1), saveNames.get(2), saveNames.get(3),
				saveNames.get(4), saveNames.get(5), "Cancel");
		SimpleGameEngine.menuHandler.menus.add(saveMenu); // addar nya menun
															// till menuhandler
	}

	public void saveWorld(String world, world w) {
		try {
			new File(world).mkdirs();

			FileOutputStream fos = new FileOutputStream(world + "/hitDet");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(w.objects);
			oos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {

			FileOutputStream fos = new FileOutputStream(world + "/noHitObj");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(w.noHitObjects);
			oos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			FileOutputStream fos = new FileOutputStream(world + "/foreGround");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(w.foregroundStuff);
			oos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void loadWorld(String world, world w) {
		try {
			FileInputStream fis = new FileInputStream(world + "/hitDet");
			ObjectInputStream ois = new ObjectInputStream(fis);
			w.objects = (ArrayList<hitDetObj>) ois.readObject();
			ois.close();
			SimpleGameEngine.currentWorld = world;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		for (Iterator<hitDetObj> it = w.objects.iterator(); it.hasNext();) {
			hitDetObj o = it.next();
			if (o.textureName == null) {
			} else {
				o.textureImg = SimpleGameEngine.space.r.resources.get(o.textureName);

			}
		}
		try {
			FileInputStream fis = new FileInputStream(world + "/noHitObj");
			ObjectInputStream ois = new ObjectInputStream(fis);
			w.noHitObjects = (ArrayList<noHitObj>) ois.readObject();
			ois.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		for (Iterator<noHitObj> it = w.noHitObjects.iterator(); it.hasNext();) {
			noHitObj o = it.next();
			if (o.textureName == null) {
			} else {
				o.textureImg = SimpleGameEngine.space.r.resources.get(o.textureName);

			}

			o.interactSymbol = SimpleGameEngine.space.r.resources.get("engage");
		}
		try {
			FileInputStream fis = new FileInputStream(world + "/foreGround");
			ObjectInputStream ois = new ObjectInputStream(fis);
			w.foregroundStuff = (ArrayList<noHitObj>) ois.readObject();
			ois.close();
			SimpleGameEngine.currentWorld = world;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		for (Iterator<noHitObj> it = w.foregroundStuff.iterator(); it.hasNext();) {
			noHitObj o = it.next();
			if (o.textureName == null) {
			} else {
				o.textureImg = SimpleGameEngine.space.r.resources.get(o.textureName);

			}
		}
	}

}
