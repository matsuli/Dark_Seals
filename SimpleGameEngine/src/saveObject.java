import java.io.Serializable;

public class saveObject implements Serializable {

int px;
int py;

String currentWorld;
String currentMenu;

//Player Player;
	
	
	public saveObject() {		//Addar här alla variabler som ska sparas (all info)
		
		this.px=SimpleGameEngine.px;
		this.py=SimpleGameEngine.py;
		//this.Player=SimpleGameEngine.player;
		this.currentWorld=SimpleGameEngine.currentWorld;
		this.currentMenu=SimpleGameEngine.currentMenu;
	}

}
