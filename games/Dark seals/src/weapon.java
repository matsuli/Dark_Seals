
import processing.core.*;

public class weapon extends PApplet {

	float reach=100;			//radien på cirkeln som weapon rör sig
	int e;  
	float rotX = 0;  
	  

	float sX;
	float sY;
	float wa;
	
	void melee (actor attacker) {		//samma som i processing, men använder nu actor som argument. Actorn som attackerar med weaponet kan vara både en player eller enemy.

		  if (attacker.swordL == true) {
		    if(rotX>=0 && rotX<radians(135)){
		      rotX=radians(135);}
		  sX = reach * cos( rotX ) + attacker.location.x;		//sX är punkt på cirkel med mittpunkt attackerlocation och radien reach, rotX är rotationsvinkeln (i grader).
		  sY = reach * sin( rotX) + attacker.location.y;  		//SE MAOLS TABELLER s.38
		   
		    rotX=rotX+radians(4);
		    
		        if(rotX>=radians(225)){
		      rotX=radians(0);
		      attacker.swordL = false;}
		    
		  }
		  
		    if (attacker.swordU == true) {
		    if(rotX>=0 && rotX<radians(225)){
		      rotX=radians(225);}
		  sX = reach * cos( rotX ) + attacker.location.x;
		  sY = reach * sin( rotX) + attacker.location.y;  
		   
		    rotX=rotX+radians(4);
		    
		        if(rotX>=radians(315)){
		      rotX=radians(0);
		      attacker.swordU = false;}
		    
		  }
		  
		    if (attacker.swordR == true) {
		    if(rotX>=0 && rotX<radians(315)){
		      rotX=radians(315);}
		  sX = reach * cos( rotX ) + attacker.location.x;
		  sY = reach * sin( rotX) + attacker.location.y;  
		   
		    rotX=rotX+radians(4);
		    
		        if(rotX>=radians(405)){
		      rotX=radians(0);
		      attacker.swordR = false;}
		    
		  }
		      if (attacker.swordD == true) {
		    if(rotX>=0 && rotX<radians(405)){
		      rotX=radians(405);}
		  sX = reach * cos( rotX ) + attacker.location.x;
		  sY = reach * sin( rotX) + attacker.location.y;  
		   
		    rotX=rotX+radians(4);
		    
		        if(rotX>=radians(495)){
		      rotX=radians(0);
		      attacker.swordD = false;}
		    
		  }

		 

		  
		  wa = atan2(sY-attacker.location.y, sX-attacker.location.x);		//vinkeln som vapnet är i, jämfört med attackers location (tror jag, inte samma som rotX, underligt nog?)

	
	
	}
}
