import processing.core.*;

public class enemy extends actor {

	  PVector elocationTest;
	  float eoldPosX, eoldPosY, erotation2, espeed;
	  float EnemyRadius = 20;
	  boolean toRemove;
	  int enemyState=1;
	  float sightRadius=180;
	  boolean nearCenter;
	  float noDetectTimer = 0;
	  float ad=0;
	  float a1;
	  float a2;
	  player Player;
	  
	  enemy (player Player, float posX, float posY) {


		    this.Player=Player;
		    

//		    spawn2X=random(width)+Player.playerX;
//		    spawn2Y=random(height)+Player.playerY; 
//		    while (sqrt(((width/2-spawn2X)*(width/2-spawn2X))+((height/2-spawn2Y)*(height/2-spawn2Y)))<400) {
//		      spawn2X=random(width)+Player.playerX;
//		      spawn2Y=random(height)+Player.playerY;
//		    } 
		    location = new PVector(posX,posY);
		    elocationTest= new PVector(posX, posY);
		    eoldPosX = Player.location.x;
		    eoldPosY = Player.location.y;
		    erotation2 = atan2(eoldPosY - location.y, eoldPosX - location.x) / PI * 180;
		    espeed = 4;
		    ad=atan2(eoldPosY - location.y, eoldPosX - location.x);
		  }
	  
	  
	  public void draw() {


		    if(enemyState==1){
		    eoldPosX = Player.location.x;
		    eoldPosY = Player.location.y;
		    erotation2 = atan2(eoldPosY - elocationTest.y+player.playerY, eoldPosX - elocationTest.x+player.playerX) / PI * 180;
		    elocationTest.x = elocationTest.x + cos(erotation2/180*PI)*espeed;
		    elocationTest.y = elocationTest.y + sin(erotation2/180*PI)*espeed;
		    ad=atan2(eoldPosY - location.y, eoldPosX - location.x);}
		    
		    location.x=elocationTest.x-player.playerX;
		    location.y=elocationTest.y-player.playerY;
		    
		             //This angle is the direction (på enhetscirkel) where the enemy2 is heading.
		    
		    
		     a1=(float) (ad - PI*2.5/6);                            //The angles that determine the circle sector for detection of player. 
		     a2=(float) (ad + PI*2.5/6);                           //They are calculated based on were the enemy is heading, meaning the enemy is always looking in the direction he is heading.
		                                                     //These angles must lie betwwn PI and -PI, otherwise the detection calculations wont work. They don´t always do, in that case they have to be compensated for.
		    
		     float a=atan2(Player.location.y-location.y, Player.location.x-location.x);              //a är vinkeln mellan player, enemien, och enemiens x-axel som startar från enemy. Inte 100% säker på hur det funkar, 
		                                                                             //tror det räknas som man sku transleita ti enemys position, sen räkna vinkeln mellan player o origo o x-axel.
		   if(sq(Player.location.x-location.x)+sq(Player.location.y-location.y)<=sq(sightRadius))      //Nearcenter is true when player is inside sightradius
		    {nearCenter=true;}
		    else{nearCenter=false;}
		    
		       if(a1<-PI){                    //If a1 is smaller than -PI, which it must not be, add 2PI to it
		     a1=a1+PI*2;
		     
		     if(nearCenter==true && !(a >=a2 && a<=a1)){      //If player is within sightradius and inside the angle of the circle sector, player is detected and enemyState changes.
		      enemyState=1;}                                  //För att a1 har kompenserats genom att addera 2PI, måste negation (!) användas för a >=a2 && a<=a1, 
		                                                      //eftersom a2 och a1 nu är gränserna för circlesektorns vinklars komplementvinklar. (Vet inte varför det är så, men det funkar....
		      a1=a1-PI*2;                          //Returns a1 to its original value, so that the circle sector looks correct

		     }
		     
		      if(a2>PI){                //If a2 is greater than -PI, which it must not be, subtract 2PI from it
		     a2=a2-PI*2;
		     
		     if(nearCenter==true && !(a >=a2 && a<=a1)){       //If player is within sightradius and inside the angle of the circle sector, player is detected and enemyState changes.
		      enemyState=1;}                                   //För att a2 har kompenserats genom att subtarhera 2PI, måste negation (!) användas för a >=a2 && a<=a1, 
		                                                       //eftersom a2 och a1 nu är gränserna för circlesektorns vinklars komplementvinklar. (Vet inte varför det är så, men det funkar...)
		      a2=a2+PI*2;                                      //Returns a2 to its original value, so that the circle sector looks correct

		     }

		    
		                //Ritar cirkelsektorn
		    
		    if(nearCenter==true && a >=a1 && a<=a2){                //If player is within sightradius and inside the angle of the circle sector, player is detected and enemyState changes.
		      enemyState=1;}                                        //Det här är alltid av någon orsak false om a1<-PI eller a2>PI, men de specialfallen har vi redan checkat för tidigare

		    
		    if (nearCenter == false && enemyState == 1) {          //om player är out of sight, men enemy jagar, startar en timer (noDetectTimer)
		      noDetectTimer ++;
		    }
		    if (noDetectTimer >= 240) {                              //om noDetectTimer når ett visst tal slutar enemy jaga och timer resetas
		      enemyState = 0;
		      noDetectTimer = 0;
		      noDetectTimer += 0;
		    }
	  
	  
	  
		    for (Bullet b : Processing.bullets) {
		        if ((location.x - b.location.x) * (location.x - b.location.x) + (location.y - b.location.y) * (location.y - b.location.y) <
		          (EnemyRadius + b.radius) * (EnemyRadius + b.radius)) {
		          toRemove = true;
		        }
		      }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  }
}
