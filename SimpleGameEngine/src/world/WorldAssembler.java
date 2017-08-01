package world;

import java.util.Iterator;

import editor.levelEditor;
import engine.ResourceLoader;
import engine.SimpleGameEngine;

public class WorldAssembler extends world {
	public ResourceLoader r = new ResourceLoader();
	public levelEditor editor; //level editor


	//all resources should be added here, but to avoid spaghetti, try adding parameters in a document and reading it from there, maybe with "for"
	public WorldAssembler() {
		// tree
		r.addResource("trunk", "image", "images/tree_trunk.png");
		r.addResource("crown", "image", "images/tree_crown.png");
		r.addResource("chicken", "image", "images/chicken.gif");
		r.addResource("engage", "image", "images/engage.png");
	}

	public void LoadWorld(){
		if(write==false){
			assemble("world3");}
			else{
			AssembleNewWorld();	
			}
	}
	
	//I think the point with this is to be able to load a specific world where needed, whereas AssembleWorld would create and save the worlds (levels)
	//needs work
	//nevermind, done
	public void assemble(String levelName) {
		SimpleGameEngine.savingSystem.loadWorld(levelName, this);
	}

	public void AssembleNewWorld() {
		// world with many trees
		editor= new levelEditor();
		/*
		 * 
		 * addTree (noHitObjects, objects, foregroundStuff, 30, 200, 200, 60,
		 * 80, 200, 170, 60, 60, "trunk", "crown");
		 * SimpleGameEngine.savingSystem.saveWorld("tree", this);
		 * SimpleGameEngine.savingSystem.loadWorld("tree", this);
		 * 
		 * 
		 */ 
		 addhitDetCircle(objects, 100, 100, 100, 100, null);	//	OBS! Cirkel hitdetection är broken, kolla varför?
		//addhitDetLine(objects, 0, 0, 79, 450);					Line också, line använder cirkel!
		addhitDetRect(objects, 100, 100, 100, 100, null);
		addhitDetRect(objects, 400, 400, 200, 100, null);
		addNoHitRect(noHitObjects, 250, 250, 150, 150,"chicken");
		addInteractionArea (noHitObjects, 200,100,100,100, null, null);
		SimpleGameEngine.savingSystem.saveWorld("world3", this);
		SimpleGameEngine.savingSystem.loadWorld("world3", this);
		 

	}

}
