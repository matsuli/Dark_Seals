

import processing.core.*;

public class enemy extends actor {	//enemyn extendar actor, enemyn �r allts� en actor
	
	  PVector elocationTest;
	  float eoldPosX, eoldPosY, erotation2, espeed;
	  float EnemyRadius = 20;
	  boolean toRemove;
	  int enemyState=1;
	  float sightRadius=180;
	  boolean nearCenter;
	  float noDetectTimer = 0;
	  float ReloadTimer = 0;
	  float ad=0;
	  float a1;
	  float a2;

	  
	  enemy (float posX, float posY) {
		   		    
		    location = new PVector(posX,posY);
		    elocationTest= new PVector(posX, posY);
		    eoldPosX =  player.PlayerLocation.x;
		    eoldPosY = player.PlayerLocation.y;
		    erotation2 = atan2(eoldPosY - location.y, eoldPosX - location.x) / PI * 180;
		    espeed = 4;
		    ad=atan2(eoldPosY - location.y, eoldPosX - location.x);
		  }
	  
	  
	  public void draw() {


		    if(enemyState==1){
		    eoldPosX = player.PlayerLocation.x;
		    eoldPosY = player.PlayerLocation.y;
		    erotation2 = atan2(eoldPosY - elocationTest.y+player.playerY, eoldPosX - elocationTest.x+player.playerX) / PI * 180;
		    elocationTest.x = elocationTest.x + cos(erotation2/180*PI)*espeed;
		    elocationTest.y = elocationTest.y + sin(erotation2/180*PI)*espeed;
		    ad=atan2(eoldPosY - location.y, eoldPosX - location.x);}
		    
		    location.x=elocationTest.x-player.playerX;				//location �r enemyns position. OBS! man beh�ver inte kompensera f�r playerX/Y, d� det redan gjorts h�r.
		    location.y=elocationTest.y-player.playerY;
		    
		             //This angle is the direction (p� enhetscirkel) where the enemy2 is heading.
		    
		    
		     a1=(float) (ad - PI*2.5/6);                            //The angles that determine the circle sector for detection of player. 
		     a2=(float) (ad + PI*2.5/6);                           //They are calculated based on were the enemy is heading, meaning the enemy is always looking in the direction he is heading.
		                                                     //These angles must lie betwwn PI and -PI, otherwise the detection calculations wont work. They don�t always do, in that case they have to be compensated for.
		    
		     float a=atan2(player.PlayerLocation.y-location.y, player.PlayerLocation.x-location.x);              //a �r vinkeln mellan player, enemien, och enemiens x-axel som startar fr�n enemy. Inte 100% s�ker p� hur det funkar, 
		                                                                             //tror det r�knas som man sku transleita ti enemys position, sen r�kna vinkeln mellan player o origo o x-axel.
		   if(sq(player.PlayerLocation.x-location.x)+sq(player.PlayerLocation.y-location.y)<=sq(sightRadius))      //Nearcenter is true when player is inside sightradius
		    {nearCenter=true;}
		    else{nearCenter=false;}
		    
		       if(a1<-PI){                    //If a1 is smaller than -PI, which it must not be, add 2PI to it
		     a1=a1+PI*2;
		     
		     if(nearCenter==true && !(a >=a2 && a<=a1)){      //If player is within sightradius and inside the angle of the circle sector, player is detected and enemyState changes.
		      enemyState=1;}                                  //F�r att a1 har kompenserats genom att addera 2PI, m�ste negation (!) anv�ndas f�r a >=a2 && a<=a1, 
		                                                      //eftersom a2 och a1 nu �r gr�nserna f�r circlesektorns vinklars komplementvinklar. (Vet inte varf�r det �r s�, men det funkar....
		      a1=a1-PI*2;                          //Returns a1 to its original value, so that the circle sector looks correct

		     }
		     
		      if(a2>PI){                //If a2 is greater than -PI, which it must not be, subtract 2PI from it
		     a2=a2-PI*2;
		     
		     if(nearCenter==true && !(a >=a2 && a<=a1)){       //If player is within sightradius and inside the angle of the circle sector, player is detected and enemyState changes.
		      enemyState=1;}                                   //F�r att a2 har kompenserats genom att subtarhera 2PI, m�ste negation (!) anv�ndas f�r a >=a2 && a<=a1, 
		                                                       //eftersom a2 och a1 nu �r gr�nserna f�r circlesektorns vinklars komplementvinklar. (Vet inte varf�r det �r s�, men det funkar...)
		      a2=a2+PI*2;                                      //Returns a2 to its original value, so that the circle sector looks correct

		     }

		    
		                //Ritar cirkelsektorn
		    
		    if(nearCenter==true && a >=a1 && a<=a2){                //If player is within sightradius and inside the angle of the circle sector, player is detected and enemyState changes.
		      enemyState=1;
		      ReloadTimer ++;}                                        //Det h�r �r alltid av n�gon orsak false om a1<-PI eller a2>PI, men de specialfallen har vi redan checkat f�r tidigare

		    
		    if (nearCenter == false && enemyState == 1) {          //om player �r out of sight, men enemy jagar, startar en timer (noDetectTimer)
		      noDetectTimer ++;
		    }
		    if (noDetectTimer >= 240) {                              //om noDetectTimer n�r ett visst tal slutar enemy jaga och timer resetas
		      enemyState = 0;
		      noDetectTimer = 0;
		      noDetectTimer += 0;
		    }
	  
		  
	  
		    for (Bullet b : Processing.bullets) {
		    	
		    	
		        if ((location.x - b.location.x) * (location.x - b.location.x) + (location.y - b.location.y) * (location.y - b.location.y) <
		          (EnemyRadius + b.radius) * (EnemyRadius + b.radius) && b.shooter!=this) {		//hit detection. Igen, b.shooter!=this f�r att den inte ska d�da sig sj�lv d� den skjuter
		          toRemove = true;
		          b.toRemove = true;		//removar ocks� bullet
		        }
		      }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  }
}

