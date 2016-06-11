import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

public class menuHandler {
	ArrayList<menu> menus = new ArrayList<menu>();

	public menuHandler() {
		
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
		
	}
	
	void menuClicked(String clicked){
		
	if(clicked=="New Game"){
		SimpleGameEngine.play=true;
		SimpleGameEngine.currentMenu=null;
	}
		
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	if(clicked==null){
		
	}	
	}

}
