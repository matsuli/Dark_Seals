

import java.util.ArrayList;
import java.util.Iterator;

import processing.core.*;



public class Processing extends PApplet {

	
	public static boolean gameOver;
	public player Player = new player(1000, 1000);		//skapar playerobjektet (class player). OBS! M�ste manuellt insertta width o height hit, annars funkar det inte (inte riktigt s�ker varf�r...)
	public weapon playerweapon = new weapon();	//skapar ett playerweapon (class weapon)
	
	ArrayList<enemy> enemies = new ArrayList<enemy>();	//enemies arraylist
	
	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();	//bullets arraylist
	
	
  public  void setup() {
    background(255);
    
  }
  
  public void settings() {
	  size(1000, 1000);
		   
	}

  public void draw() {
	  
	

	  
	 if(enemies.size()<2){
		 addEnemy(random(1000), random(1000)); 	//addar enemies. N�r vi b�rjar med levels ska vi v�l inte anv�nda random utan ha dem att spawna p� specifika st�llen?
	 }
	 
	 background(255); 
	 
	  fill(0);				//OBS! Processings inbyggda colour selector funkar int. Sku vara bra om vi kunde hitta n�got liknande f�r eclipse...
	  stroke(0);
	 
	 Player.drawPlayer(); //uppdaterar playern och dess variabler (playerX, playerY mm.) (se "player"). Hit detectar ocks�.
	 
	 
	  if (mousePressed == true && mouseButton==LEFT && Player.canShoot==true) {			//Player shooter genom att anv�nda shoot-metoden. Target �r mouseX, mouseY
		  shoot(Player, mouseX, mouseY);
		  
	  }
	 
	  int i;
	  for (i = bullets.size()-1; i >= 0; i--) {
		    Bullet bullet = bullets.get(i);
		    bullet.update();									//updaterar bullets position (variablerna f�r bulletten, speciellt location-vektorn)
		    fill(0);
		    ellipse(bullet.location.x, bullet.location.y, bullet.radius*2, bullet.radius*2);	//ritar bulleten baserat p� location-vektorn, som just uppdaterades
		  }
	 	 
	
	 if(Player.swordL==true || Player.swordU==true || Player.swordR==true || Player.swordD==true){
		   playerweapon.melee (Player);								//playerweapon g�r melee-metoden. Attackern som anv�nder playermetod �r Player. Melee updaterar variablerna f�r playerweapon (sX, sY)
		   line( player.PlayerLocation.x,  player.PlayerLocation.y, playerweapon.sX, playerweapon.sY);  //ritar weapon, enligt players position och sX, sY som uppdaterades i melee
			 int e;
			 
			  for (e = enemies.size()-1; e >= 0; e--){
				  enemy enemy = enemies.get(e);
				  								//koden nedan checkar om weapon tr�ffat enemyn genom att j�mf�ra vinkeln mellan enemy och player och playerweapon och player samt av�ndet fr�n vapnets spets (sX, sY) till players position. 
				  								// talet 0.402715841581 �r inte draget ur hatten, utan utr�knat med perus l�ng mattauppgift. Det �r vinkeln mellan linjen fr�n player till enemys mittpunkt och vapenlinjen, d� vapnet �r tangent till enemyscirkeln. 
				  if(abs(atan2(enemy.location.y-player.PlayerLocation.y, enemy.location.x-player.PlayerLocation.x)-playerweapon.wa)<0.402715841581/2 && dist(player.PlayerLocation.x, player.PlayerLocation.y, enemy.location.x, enemy.location.y)-enemy.EnemyRadius<=dist(playerweapon.sX, playerweapon.sY, player.PlayerLocation.x, player.PlayerLocation.y))
				 {
				      enemy.toRemove=true;
				  }
				 				 
				  }
	  }	
	 
	 
	 for (Iterator<enemy> it = enemies.iterator(); it.hasNext(); ) {
		    enemy e = it.next(); 
		    e.draw();				//draw metoden uppdaterar enemies position och detection av player
			 ellipse(e.location.x, e.location.y, e.EnemyRadius*2, e.EnemyRadius*2);  //ritar enemyn
			 fill(90, 90);
			 stroke(90, 90);
			 arc(e.location.x, e.location.y, e.sightRadius*2, e.sightRadius*2, e.a1, e.a2);		//ritar enemyns detection cirkelsektor (det h�r ska v�l bort sen i riktiga spelet, eller?)
			 fill(0);
			 stroke(0);
		    if (e.toRemove)
		      it.remove();			//removar enemyn om toRemove=true
		  }
	 

	 
	 
	 fill(0);
	 stroke(0);
	 
	 
	 
	 ellipse(player.PlayerLocation.x, player.PlayerLocation.y, Player.playerRadius*2, Player.playerRadius*2);			//f�rst h�r ritas playercirkeln. Dess position r�knades ut i Player.drawPlayer
    
    
	 rect(200-player.playerX, 200-player.playerY, 100, 100);		//rektangeln, f�r att testa movement systemet.  
	 																//D� playerX och playerY �r static, finns bara en version av dessa variabler, som alla objekt av playerclassen sharear (inget probelm, finns bara en Player) Detta g�r dock att player.playerX kan anv�ndas var som helst i denna package, undviker errorn "cannot make static reference to non-static field"
	 																//d�rf�r kan man ist�llet f�r att anv�nda Player.playerX (playerobjektets playerX) anv�nda player.playerX (playerclassens playerX, gemensamt f�r alla player objekt)
	 																// i praktiken inneb�r detta att player.playerX kan anv�ndas var som helst i denna package, lite som en global i processing

    if (mousePressed && mouseButton==RIGHT) {
    	exit();										//st�nger av vid h�gerklick
    	}
  //  println(gameOver);
    
    }
  
  private void addEnemy(float posX, float posY) {				// addenemy metoden. skapar en ny enemy vid positionen som ins�tts i metoden
	{			
			enemy Enemy  = new enemy(posX, posY);
			enemies.add(Enemy);
		}
	
}
public static void main(String args[]) {
	    PApplet.main(new String[] { "--present", "Processing" });	//viktigt, men ingen aning vad denna g�r. troligen �r det den som best�mmer att koden i processing �r den som runnar d� man runnar applicationen och har n�got att g�ra ned hur vi importerat processing.
	    
	  }

  public  void keyPressed() {			// keypressed och keyreleased g�r samma sak som i processing-koden. sk�ter playerns movement baserar p� wasd och plasyerweapon baserat p� pilar etc.

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
	
	 void shoot(actor shooter, float targetPosX, float targetPosY) {	//finns inte mera n�gon skild playershoot eller enemyshoot, bara en shoot metod
		 																//actor shooter �r den som skjuter, targetPosX/Y �r vart denna skjuter. Dessa insertas i bullet s� att bullerten spawnar vid shooter actorn och flyger mot target
		        bullets.add( new Bullet(shooter, targetPosX, targetPosY));
		        shooter.canShoot = false;
		        shooter.canShootCounter = 0;
	    

		  }
}

