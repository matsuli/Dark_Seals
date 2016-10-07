package objects;

import java.util.ArrayList;

import engine.SimpleGameEngine;
import world.hitDetObj;
import world.hitDetRect;
import world.noHitObj;
import world.noHitRect;

public class BasicTree {

	hitDetRect tr;
	noHitRect tt;
	noHitRect tr2;
	
	public BasicTree(ArrayList<noHitObj> noHitObjects, ArrayList<hitDetObj> objects, ArrayList<noHitObj> foregroundStuff, int treeRadius, int tx, int ty, int tw, int th, int topX, int topY, int topW, int topH, String trunk, String crown){
		
		tr = new hitDetRect (tx+tw/20, ty+th-tw/2, tw-tw/6, tw/2-tw/20);
		objects.add(tr);	
		tr.texture = null;
		
		tt = new  noHitRect(tx, ty, tw, th);
		tt.texture = trunk;
		if (SimpleGameEngine.playerX > tx && SimpleGameEngine.playerX < (tx+tw) && SimpleGameEngine.playerY > (ty+th)) {
			noHitObjects.add(tt);
		} else {
			foregroundStuff.add(tt);
		}
		
		
		//trädkrona
		tr2 = new  noHitRect(topX, topY, topW, topH);
		tr2.texture = crown;
		foregroundStuff.add(tr2);
	}
}
