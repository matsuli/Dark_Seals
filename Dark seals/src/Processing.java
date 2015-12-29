

import processing.core.*;



public class Processing extends PApplet {

	
	public static boolean gameOver;
	
	public player Player = new player();
	public weapon playerweapon = new weapon();

  public  void setup() {
    background(255);
    
  }
  public void settings() {
	  size(1000, 1000);

	    
	   
	}

  public void draw() {
	  
	 background(255); 
	 
	  fill(0);
	  stroke(0);
	 
	 Player.drawPlayer();
	
	 if(Player.swordL==true || Player.swordU==true || Player.swordR==true || Player.swordD==true){
		   playerweapon.melee (Player);
		   line( Player.location.x,  Player.location.y, playerweapon.sX, playerweapon.sY);
	  }	
	  
	 
	 ellipse(Player.location.x, Player.location.y, Player.playerRadius*2, Player.playerRadius*2);
    
    
	 rect(200-Player.playerX, 200-Player.playerY, 100, 100);
    
    
    

    if (mousePressed && mouseButton==RIGHT) {
    	exit();
    	}
    
    }
  
  public static void main(String args[]) {
	    PApplet.main(new String[] { "--present", "Processing" });
	    
	  }

  public  void keyPressed() {

	  //  if (settings==false) {

	    if (key == 'd')
	    {
	    	Player.right = 1;
	    }
	    if  (key == 'a')
	    {
	    	Player.left = 1;
	    }
	    if (key == 'w')
	    {
	    	Player.up = 1;
	    }
	    if (key == 's')
	    {
	    	Player.down = 1;
	    }
	    if (keyCode == SHIFT) {
	    	Player.sneak = true;
	    }
	    if (keyCode == LEFT && Player.swordR == false && Player.swordU == false && Player.swordD == false) {
	    	Player.swordL = true;
	    }
	    if (keyCode == RIGHT && Player.swordL == false && Player.swordU == false && Player.swordD == false) {
	    	Player.swordR = true;
	    }
	    if (keyCode == UP && Player.swordR == false && Player.swordL == false && Player.swordD == false) {
	    	Player.swordU = true;
	    }
	    if (keyCode == DOWN && Player.swordR == false && Player.swordU == false && Player.swordL == false) {
	    	Player.swordD = true;
	    }

	}
	public void keyReleased() {

	  if (key == 'd')
	  {
		  Player.right = 0;
	  }
	  if  (key == 'a')
	  {
		  Player.left = 0;
	  }
	  if (key == 'w')
	  {
		  Player.up = 0;
	  }
	  if (key == 's')
	  {
		  Player.down = 0;
	  }
	  if (keyCode == SHIFT) {
	      Player.sneak = false;
	    }
	}
  
}

