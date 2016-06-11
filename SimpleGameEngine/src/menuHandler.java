import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

public class menuHandler {
	ArrayList<menu> menus = new ArrayList<menu>();
	menu pauseMenu;
	public menuHandler() {
		
		pauseMenu= new menu("Pause","Continue", "Save Game", "Settings", "Exit to main menu", "Quit");
		menus.add(pauseMenu);
	}
	
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
			SimpleGameEngine.play=true;
			SimpleGameEngine.currentMenu=null;
		}

		if(clicked=="Continue"){
			SimpleGameEngine.play=true;
			SimpleGameEngine.currentMenu=null;
		}
		if(clicked == "Quit") {
			System.exit(0);
		}
		
		if(clicked==null){
			
		}	
	}

}
