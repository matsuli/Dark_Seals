import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

public class menuHandler {
	ArrayList<menu> menus = new ArrayList<menu>();
	menu pauseMenu;
	menu warningMenu;
	menu prevMenu;
	public menuHandler() {
		
		pauseMenu= new menu("Pause","Continue", "Save Game", "Settings", "Exit to main menu", "Quit");
		menus.add(pauseMenu);
		warningMenu= new menu("Warning","HEADER 2 Unsaved progress will be lost", "Continue to main menu", "Cancel");	//En rubrik addas genom att sätta till 		
		menus.add(warningMenu);							//HEADER i början av stringen och sedan en siffra. Siffran säger hur många gånger större font rubriken har.
	}													//OBS! MÅste vara mellanrum som ovan. Se menuBox.
	
	menu getCurrentMenu(){
		
	menu returnMenu=null;
		for(Iterator<menu> it = this.menus.iterator(); it.hasNext(); ) {
		menu m = it.next();	
		
			if(SimpleGameEngine.currentMenu==m.thisMenu){
				returnMenu=m;
			}
		}
		return returnMenu;
	}
	
	void updateCurrentMenu(Graphics2D g){
		this.getCurrentMenu().update(g);
	}
	
	void menuClicked(String clicked){
		
		if(clicked=="New Game"){
			SimpleGameEngine.px=0;
			SimpleGameEngine.py=0;
			prevMenu=this.getCurrentMenu();
			SimpleGameEngine.play=true;
			SimpleGameEngine.currentMenu=null;
		}

		if(clicked=="Continue"){
			prevMenu=this.getCurrentMenu();
			SimpleGameEngine.play=true;
			SimpleGameEngine.currentMenu=null;
		}
		if(clicked == "Quit") {
			System.exit(0);
		}
		if(clicked == "Exit to main menu") {
			prevMenu=this.getCurrentMenu();
			SimpleGameEngine.play=false;
			SimpleGameEngine.currentMenu="Warning";
		}
		if(clicked == "Continue to main menu") {
			prevMenu=this.getCurrentMenu();
			SimpleGameEngine.currentMenu="Main";
		}
		if(clicked == "Cancel") {
			SimpleGameEngine.currentMenu=prevMenu.thisMenu;
		}
		
		
		
		if(clicked==null){
			
		}	
	}

}
