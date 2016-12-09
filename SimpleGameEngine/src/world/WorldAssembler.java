package world;

public class WorldAssembler extends world {
	public void assemble() {
		treeWorld();
	}
	public void treeWorld() {
		//world with many trees
		addMultipleTrees(noHitObjects, objects, foregroundStuff, 30, 60, 80, 60, 60, "images/tree_trunk.png", "images/tree_crown.png",-500,-500,1500,1500);
		engine.SimpleGameEngine.savingSystem.saveWorld("multipleTrees", this);
		objects.clear();
		noHitObjects.clear();
		foregroundStuff.clear();
	}
}
