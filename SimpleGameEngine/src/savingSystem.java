import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class savingSystem {
boolean save;
boolean load;
menu saveMenu;	
menu overWriteWarning;
File file;
int saveSlots=6;
File[] saves = new File[6];
ArrayList <String> saveNames= new ArrayList<String>();
	public savingSystem() {
		
		file = new File("Saved games");		//Skapar foldern Saved games om den inte finns.
		 if (!file.exists()) {
	        file.mkdirs();
	        }		 
		  File[] savesNonSorted=file.listFiles();
		  
		  for(int i =savesNonSorted.length; i>0; i--){
			
				try {
			  FileInputStream fis = new FileInputStream(savesNonSorted[i-1]);
				ObjectInputStream ois = new ObjectInputStream(fis);
				saveObject save = (saveObject) ois.readObject();
				ois.close();
				saves[save.saveSlot]=savesNonSorted[i-1];
				}
				catch(Exception ex) {
				    ex.printStackTrace();
				}
											
			}
		  
		//System.out.println(saves.length); 
			
			for(int i =saves.length; i>0; i--){
				if(saves[i-1]!=null){
				saveNames.add(saves[i-1].getName());}
				else{
				saveNames.add("Empty slot");
				}
			}
			Collections.reverse(saveNames);
			System.out.println(saveNames); 
			saveMenu= new menu("saveMenu", saveNames.get(0),saveNames.get(1),saveNames.get(2),saveNames.get(3),saveNames.get(4),saveNames.get(5), "Cancel");
			overWriteWarning= new menu("overWriteWarning", "HEADER 2 Overwrite existing save?", "Yes", "No, cancel");
	}
	
	public void save(){
		int i=saveMenu.getClickedMenuBox();
		//System.out.println(i);
		
		if(saveNames.get(i-1).equals("Empty slot")){
			System.out.println("wooo");
			try {
				FileOutputStream fis = new FileOutputStream("Saved games"+"/Save"+i+"-"+SimpleGameEngine.currentWorld);
				ObjectOutputStream oos = new ObjectOutputStream(fis);
				saveObject save= new saveObject(i-1);
				oos.writeObject(save);
				oos.close();
				
				} catch(Exception ex) {
					    ex.printStackTrace();
					}
			
		}
		else if(saveNames.get(i-1).equals(saves[i-1].getName())){
			System.out.println("yayy");
			SimpleGameEngine.currentMenu="overWriteWarning";
			
			try {
				saves[i-1].delete();
				String replacementSave ="Saved games"+"/"+ saveNames.get(i-1).substring(0, saveNames.get(i-1).lastIndexOf("-"))+"-"+SimpleGameEngine.currentWorld;
				FileOutputStream fis = new FileOutputStream(replacementSave);
				ObjectOutputStream oos = new ObjectOutputStream(fis);
				saveObject save= new saveObject(i-1);
				oos.writeObject(save);
				oos.close();
				saves[i-1]=Paths.get(replacementSave).toFile();	
				
				} catch(Exception ex) {
					    ex.printStackTrace();
					}	
		}
		//update();
	}

	public void load() {
		int i=saveMenu.getClickedMenuBox();
		//System.out.println(i);
		try {
			  FileInputStream fis = new FileInputStream(saves[i-1]);
				ObjectInputStream ois = new ObjectInputStream(fis);
				saveObject save = (saveObject) ois.readObject();				
				ois.close();
				SimpleGameEngine.px=save.px;
				SimpleGameEngine.py=save.py;				
				SimpleGameEngine.currentWorld=save.currentWorld;
				SimpleGameEngine.currentMenu=SimpleGameEngine.menuHandler.pauseMenu.thisMenu;
				}
				catch(Exception ex) {
				    ex.printStackTrace();
				}
		
	}
	public void update(){
		
		file = new File("Saved games");		
		 if (!file.exists()) {
	        file.mkdirs();
	        }		 
		  File[] savesNonSorted=file.listFiles();
		  
		  for(int i =savesNonSorted.length; i>0; i--){
			
				try {
			  FileInputStream fis = new FileInputStream(savesNonSorted[i-1]);
				ObjectInputStream ois = new ObjectInputStream(fis);
				saveObject save = (saveObject) ois.readObject();
				ois.close();
				saves[save.saveSlot]=savesNonSorted[i-1];
				}
				catch(Exception ex) {
				    ex.printStackTrace();
				}
											
			}
		  

			
			for(int i =saves.length; i>0; i--){
				if(saves[i-1]!=null){
				saveNames.add(saves[i-1].getName());}
				else{
				saveNames.add("Empty slot");
				}
			}
			Collections.reverse(saveNames);
			System.out.println(saveNames); 
			saveMenu= new menu("saveMenu", saveNames.get(0),saveNames.get(1),saveNames.get(2),saveNames.get(3),saveNames.get(4),saveNames.get(5), "Cancel");
		
	}

}
