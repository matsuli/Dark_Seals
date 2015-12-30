

import java.util.ArrayList;
import java.util.Iterator;

import processing.core.*;



public class Processing extends PApplet {

	
	public static boolean gameOver;
	
	public player Player = new player();
	public weapon playerweapon = new weapon();
	
	ArrayList<enemy> enemies = new ArrayList<enemy>();
	
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
  public  void setup() {
    background(255);
    
  }
  public void settings() {
	  size(1000, 1000);
	
	    
	   
	}

  public void draw() {
	  
	

	  
	 if(enemies.size()<2){
		 addEnemy(random(1000), random(1000)); 
	 }
	 
	 background(255); 
	 
	  fill(0);
	  stroke(0);
	 
	 Player.drawPlayer();
	 
	 
	  if (mousePressed == true && mouseButton==LEFT && Player.canShoot==true) {
		  shoot(Player, mouseX, mouseY);
		  
	  }
	 
	  int i;
	  for (i = bullets.size()-1; i >= 0; i--) {
		    Bullet bullet = bullets.get(i);
		    bullet.update(i);
		    fill(0);
		    ellipse(bullet.location.x, bullet.location.y, bullet.radius*2, bullet.radius*2);
		  }
	 	 
	
	 if(Player.swordL==true || Player.swordU==true || Player.swordR==true || Player.swordD==true){
		   playerweapon.melee (Player);
		   line( Player.location.x,  Player.location.y, playerweapon.sX, playerweapon.sY);
			 int e;
			 
			  for (e = enemies.size()-1; e >= 0; e--){
				  enemy enemy = enemies.get(e);
				  

				  if(abs(atan2(enemy.location.y-Player.location.y, enemy.location.x-Player.location.x)-playerweapon.wa)<0.402715841581/2 && dist(Player.location.x, Player.location.y, enemy.location.x, enemy.location.y)-enemy.EnemyRadius<=dist(playerweapon.sX, playerweapon.sY, Player.location.x, Player.location.y))
				 {
				      enemy.toRemove=true;
				  }
				 				 
				  }
	  }	
	 
	 
	 for (Iterator<enemy> it = enemies.iterator(); it.hasNext(); ) {
		    enemy e = it.next(); 
		    e.draw();
			 ellipse(e.location.x, e.location.y, e.EnemyRadius*2, e.EnemyRadius*2);  
			 fill(90, 90);
			 stroke(90, 90);
			 arc(e.location.x, e.location.y, e.sightRadius*2, e.sightRadius*2, e.a1, e.a2);
			 fill(0);
			 stroke(0);
		    if (e.toRemove)
		      it.remove();
		  }
	 

	 
	 
	 fill(0);
	 stroke(0);
	 
	 
	 
	 ellipse(Player.location.x, Player.location.y, Player.playerRadius*2, Player.playerRadius*2);
    
    
	 rect(200-player.playerX, 200-player.playerY, 100, 100);
    
    
    

    if (mousePressed && mouseButton==RIGHT) {
    	exit();
    	}
    
    }
  
  private void addEnemy(float posX, float posY) {
	{			
			enemy Enemy  = new enemy(Player, posX, posY);
			enemies.add(Enemy);
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
	
	 void shoot(actor shooter, float targetPosX, float targetPosY) {

		        bullets.add( new Bullet(shooter, targetPosX, targetPosY));
		        shooter.canShoot = false;
		        shooter.canShootCounter = 0;
	    

		  }
}

