import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class savingSystem {
boolean save;
boolean load;
menu saveMenu;	
File[] saves; 
ArrayList <String> saveNames= new ArrayList<String>();
	public savingSystem() {
		
		File file = new File("Saved games");		//Skapar foldern Saved games om den inte finns.
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
			saveMenu= new menu("saveMenu", saveNames.get(0),saveNames.get(1),saveNames.get(2),saveNames.get(3),saveNames.get(4),saveNames.get(5), "Cancel");
	}
	
	public void save(){
		int i=saveMenu.getClickedMenuBox();
		System.out.println(i);
		
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
	}

	public void load() {
		int i=saveMenu.getClickedMenuBox();
		System.out.println(i);
		
	}

}
