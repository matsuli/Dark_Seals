package world;

import java.util.Iterator;

import engine.ResourceLoader;
import engine.SimpleGameEngine;

public class WorldAssembler extends world {
public ResourceLoader r = new ResourceLoader();
	
	public WorldAssembler() {
		//tree
		r.addResource("trunk","image","images/tree_trunk.png");
		r.addResource("crown", "image", "images/tree_crown.png");
		r.addResource("chicken", "image", "images/chicken.gif");
		r.addResource("engage", "image", "images/engage.png");
	}
	
	public void assemble() {
		AssembleWorld();
		
	}
	public void AssembleWorld() {
		//world with many trees
		
		//addMultipleTrees(noHitObjects, objects, foregroundStuff, 30, 60, 80, 60, 60, "trunk", "crown",-500,-500,1500,1500);
		//engine.SimpleGameEngine.savingSystem.saveWorld("multipleTrees", this);
		
		SimpleGameEngine.savingSystem.loadWorld("multipleTrees", this);
		
		
		/*
		
		 addTree (noHitObjects, objects, foregroundStuff, 30, 200, 200, 60, 80, 200, 170, 60, 60,  "trunk", "crown");
		SimpleGameEngine.savingSystem.saveWorld("tree", this);
		SimpleGameEngine.savingSystem.loadWorld("tree", this);
		
		
	
		//chicken
		
		addhitDetRect(objects, 100, 100, 100, 100, "chicken");
		addNoHitRect(noHitObjects, 250, 250, 150, 150,"chicken");
		addInteractionArea (noHitObjects, 200,100,100,100, null, null);
		SimpleGameEngine.savingSystem.saveWorld("world3", this);	
		SimpleGameEngine.savingSystem.loadWorld("world3", this);	*/
		
	}
	
}	
	
