import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class savingSystem {
boolean save;
boolean load;
menu saveMenu;	
menu overWriteWarning;
File[] saves; 
File file;
ArrayList <String> saveNames= new ArrayList<String>();
	public savingSystem() {
		
		file = new File("Saved games");		//Skapar foldern Saved games om den inte finns.
		 if (!file.exists()) {
	        file.mkdirs();
	        }		 
			saves=file.listFiles();
			
			for(int i =saves.length; i>0; i--){
				saveNames.add(saves[i-1].getName());
			}
			Collections.reverse(saveNames);
			
			if(saveNames.size()<6){
			
				for(int i2=6-saveNames.size(); i2>0; i2--){
					saveNames.add("Empty slot");
				}
			}
			saveMenu= new menu("saveMenu", saveNames.get(0),saveNames.get(1),saveNames.get(2),saveNames.get(3),saveNames.get(4),saveNames.get(5), "Return to pause menu");
			overWriteWarning= new menu("overWriteWarning", "HEADER 2 Overwrite existing save?", "Yes", "Cancel");
	}
	
	public void save(){
		int i=saveMenu.getClickedMenuBox();
		//System.out.println(i);
		
		if(saveNames.get(i-1).equals("Empty slot")){
			try {
				FileOutputStream fis = new FileOutputStream("Saved games"+"/Save"+i+"-"+SimpleGameEngine.currentWorld);
				ObjectOutputStream oos = new ObjectOutputStream(fis);
				saveObject save= new saveObject();
				oos.writeObject(save);
				oos.close();
				
				} catch(Exception ex) {
					    ex.printStackTrace();
					}
			
		}
		else if(saveNames.get(i-1).equals(saves[i-1].getName())){
			//System.out.println("wooo");
			SimpleGameEngine.menuHandler.prevMenu=SimpleGameEngine.menuHandler.getCurrentMenu();
			SimpleGameEngine.currentMenu="overWriteWarning";
			
		//	try {
		//		FileOutputStream fis = new FileOutputStream(saves[i-1].getPath());
		//		ObjectOutputStream oos = new ObjectOutputStream(fis);
		//		saveObject save= new saveObject();
		//		oos.writeObject(save);
		//		oos.close();
		//		saves[i-1].toPath().toFile().deleteOnExit();
		//		saves[i-1].renameTo((Paths.get("Saved games"+"/Save"+i+"-"+SimpleGameEngine.currentWorld)).toFile());
				
		//		} catch(Exception ex) {
		//			    ex.printStackTrace();
		//			}	
		}
		//update();
	}

	public void load() {
		int i=saveMenu.getClickedMenuBox();
		//System.out.println(i);
		
	}
	public void update(){
		
		saves=file.listFiles();
		saveNames.clear();
		for(int i =saves.length; i>0; i--){
			saveNames.add(saves[i-1].getName());
		}
		Collections.reverse(saveNames);
		
		if(saveNames.size()<6){
		
			for(int i2=6-saveNames.size(); i2>0; i2--){
				saveNames.add("Empty slot");
			}
		}
		saveMenu= new menu("saveMenu", saveNames.get(0),saveNames.get(1),saveNames.get(2),saveNames.get(3),saveNames.get(4),saveNames.get(5), "Return to pause menu");
		
	}

}
