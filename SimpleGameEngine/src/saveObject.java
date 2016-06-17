import java.io.Serializable;

public class saveObject implements Serializable {

int px;
int py;

String currentWorld;
String currentMenu;
int saveSlot;

//Player Player;
	
	
	public saveObject(int saveSlot) {		//Addar här alla variabler som ska sparas (all info)
		this.saveSlot=saveSlot;
		this.px=SimpleGameEngine.px;
		this.py=SimpleGameEngine.py;
		//this.Player=SimpleGameEngine.player;
		this.currentWorld=SimpleGameEngine.currentWorld;
		this.currentMenu=SimpleGameEngine.currentMenu;
	}

}
