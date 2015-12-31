

import processing.core.*;
import processing.data.FloatList;


public class player extends actor {			//player extends actor, playern �r en actor

	
	 public static float playerX;		//OBS! dessa �r som sagt statics. Detta betyder att player.playerX kan anv�ndas var som helst i detta package.
	 public static float playerY;
	 public static PVector PlayerLocation;	//Ocks� en static. I actor finns redan en location vektor, som �r players location. MEN denna kan INTE vara static, 
	 										//d� den h�r till actor o det finns m�nga actors. D�rf�r skapar vi en PlayerLocation vektor f�r player, som ocks� anger players position men �r static. 
	 public int phasetimer=0;				//player.PlayerLocation kan allts� anv�ndas �verallt i denna package.
	 public int teleporttimer=0;
	 public boolean teleportNow=false;
	 public float teleportX;
	 public float teleportY;
	 public float teleportAccuracy=0;
	 public int playerRadius=25;
	 public int teleportSpeed=26;
	 public boolean teleportPerk=true;


	  
	  float speed = 7;
	  float right = 0;
	  float left = 0;
	  float up = 0;
	  float down = 0;
	  boolean sneak;
	  
	  public static FloatList pplayerX = new FloatList();    //Floatlist that contains the previous and current x-cordinates for player (OBS!) Denna �r ocks� static.
	  public static FloatList pplayerY = new FloatList();    //Same for y
	   
	   PVector hitbox1=new PVector ();      // These vectors conatin the x and y-values for the points on the playercircle that are used to calculate hitboxes
	   PVector hitbox2=new PVector ();
	   PVector hitbox3=new PVector ();
	   PVector hitbox4=new PVector ();
	   PVector hitbox5=new PVector ();
	   PVector hitbox6=new PVector ();
	   PVector hitbox7=new PVector ();
	   PVector hitbox8=new PVector ();
	   
  
	   player(int ScreenWidth, int ScreenHeight) {
		   location = new PVector(ScreenWidth/2, ScreenHeight/2);			//skapar location vektorn, playerns position �r i mitten av screenen
		   PlayerLocation = new PVector(location.x, location.y);		//skapar den statiska PLayerLocation vektorn, som �r samma som location. Denna kan anv�ndas var som helst i detta package.
	  }						//OBS! PlayerLocation uppdateras inte, denna konstruktor runnar bara en g�ng. �ndrar vi allts� n�ng�ng players location vektor m�ste vi samtidigt �ndra players PlayerLocation vektor. 
	   						//annars har vi problem d� location anv�nds inom drawPlayer men PlayerLocation anv�nds i processing, enemies osv.
	  
	  
	void drawPlayer () {
	  //player
	  
	  // X=centerX+-radie
	// Y=+-(sqrt(pow(radie, 2)-pow(X,2)+2*centerX*X-pow(centerX,2))+-centerY);
	float x1= location.x-playerRadius;
	float y1=-(sqrt(pow(playerRadius, 2)-pow(x1,2)+2*location.x*x1-pow(location.x,2))-location.y);      //Calculates the hitbox points for player used for colour based hit detection. These points are points on the player circle. (Anv�nder cirkelns eklvation)
	hitbox1.x=x1;                                                                              //Since it�s possible for a small object, for example a small bullet, to pass through the player without touching these point, 
	hitbox1.y=y1;                                                                              //detecting with them only works for large objects, like the laser. The upside is that no complicated math is needed, as these points can be used to detect according to a colour.


	float x2=location.x+playerRadius;
	float y2=(sqrt(pow(playerRadius, 2)-pow(x2,2)+2*location.x*x2-pow(location.x,2))+location.y);
	hitbox2.x=x2;
	hitbox2.y=y2;


	float x3=location.x;
	float y3=(sqrt(pow(playerRadius, 2)-pow(x3,2)+2*location.x*x3-pow(location.x,2))+location.y);
	hitbox3.x=x3;
	hitbox3.y=y3;


	float x4=location.x;
	float y4=-(sqrt(pow(playerRadius, 2)-pow(x4,2)+2*location.x*x4-pow(location.x,2))-location.y);
	hitbox4.x=x4;
	hitbox4.y=y4;


	float x5=location.x+17;
	float y5=-(sqrt(pow(playerRadius, 2)-pow(x5,2)+2*location.x*x5-pow(location.x,2))-location.y);
	hitbox5.x=x5;
	hitbox5.y=y5;


	float x6=location.x-17;
	float y6=-(sqrt(pow(playerRadius, 2)-pow(x6,2)+2*location.x*x6-pow(location.x,2))-location.y);
	hitbox6.x=x6;
	hitbox6.y=y6;


	float x7=location.x-17;
	float y7=+(sqrt(pow(playerRadius, 2)-pow(x7,2)+2*location.x*x7-pow(location.x,2))+location.y);
	hitbox7.x=x7;
	hitbox7.y=y7;


	float x8=location.x+17;
	float y8=+(sqrt(pow(playerRadius, 2)-pow(x8,2)+2*location.x*x8-pow(location.x,2))+location.y);
	hitbox8.x=x8;
	hitbox8.y=y8;


	  // sneak control
	  if (sneak == true) {
	    speed = 2;
	  } else {speed = 5;}
	  
	    if(teleportPerk==true){ 
	  
	if(teleportNow==false && teleporttimer<120){
	    teleporttimer++;
	    }
	if(teleporttimer==120 && mousePressed == true && mouseButton == RIGHT){
	      teleportNow=true;
	      phasetimer=0;
	    
	    }    
	  }
	  
	//  teleportPlayer();
	  
	   
	     //this is player guy appearance

	  //player movement
	  
	  
	  
	  

	 playerX += (right - left) * speed;		//movement systemet �r samma som i processing, allt utom player r�r sig runt player baserat p� playerX/Y
	  


	 playerY += (down - up) * speed;
	  

	  

	pplayerX.append(playerX);      //append adds a new entry to the floatlist. Adds the current values for playerX and Y into the pplayerX/Y floatlists, where they are saved for use in later frames. (The Laser)
	pplayerY.append(playerY);



	if(pplayerX.size()>20){        //Removes the oldest entry in the lists (entry number 1). The size of the lists is always 20, this means the positions of the player in up to 19 frames ago are saved. (+the current frame)
	  pplayerX.remove(1);}        //Entry number 19 is the newest position (current frame). Entry number 0 is the oldest. For example, if you want to know where player was 5 frames ago, you can use pplayerX.get(14) and pplayerY.get(14).
	  
	if(pplayerY.size()>20){
	  pplayerY.remove(1);}
	  
	 if (canShoot == false) {
	      canShootCounter ++;
	      if (canShootCounter == 20) {
	        canShoot = true;
	      }
	    }

	 for (Bullet b : Processing.bullets) {
	        if ((location.x - b.location.x) * (location.x - b.location.x) + (location.y - b.location.y) * (location.y - b.location.y) <
	          (playerRadius + b.radius) * (playerRadius + b.radius) && b.shooter!=this) {		//hit detection. OBS! d� vi bara har en bulletclass, m�ste b.shooter!=this vara d�r f�r att player inte ska skjuta sig sj�lv d� bulleten spawnar i player. Dock g�r detta att player �r immun till sina egna bullets, vilket kan vara problematiskt. 
	          Processing.gameOver = true;														//vill vi bli av med det h�r tror jag det l�nar sig att adda n�n sorts timer hit, s� att player e immun till sina egna bullets en kort stund efter att han skjutit dem
	        }
	      }
	 
	 
	}
}



