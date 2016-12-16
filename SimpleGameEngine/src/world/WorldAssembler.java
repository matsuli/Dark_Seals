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
	}
	
	public void assemble() {
		treeWorld();
		
	}
	public void treeWorld() {
		//world with many trees
		addMultipleTrees(noHitObjects, objects, foregroundStuff, 30, 60, 80, 60, 60, "trunk", "crown",-500,-500,1500,1500);
		for (Iterator<noHitObj> it =  foregroundStuff.iterator(); it.hasNext(); ) {
			noHitObj o = it.next();
		
			//Image img2 = null;
			
			if (o.textureName == null) {
			} else {
				o.textureImg  =SimpleGameEngine.space.r.resources.get(o.textureName);
			}
		}
		engine.SimpleGameEngine.savingSystem.saveWorld("multipleTrees", this);	
		
	
	}
}	
	
