package world;

import engine.ResourceLoader;

public class WorldAssembler extends world {
	ResourceLoader r = new ResourceLoader();
	
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
		addMultipleTrees(noHitObjects, objects, foregroundStuff, 30, 60, 80, 60, 60, r.resources.get("trunk"), r.resources.get("crown"),-500,-500,1500,1500);
		engine.SimpleGameEngine.savingSystem.saveWorld("multipleTrees", this);
		objects.clear();
		noHitObjects.clear();
		foregroundStuff.clear();
	}
}
