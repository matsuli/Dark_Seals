import processing.core.*;

public class actor extends PApplet { //Actor är en class jag tänkte använda för alla "människor" i vårt spel, dvs. både player, perus enemies och NPC:s. Mer speciliserade actors, som player o enemies, extendar denna class
	  public PVector location;		//Iden är att här finns basic variabler som vi behöver vid metoder som vi vill kunna använda på alla actors
	  boolean swordL=false;			//t.ex. location som ger dess position, och booleans för weapons (ifall vi vill att player o enemies o andra characters kan använda samma weapon.
	  boolean swordR=false;			// det t.ex. är mha actor som vi kan använda samma metod, t.ex. "shoot", för att ha både enemies o player att skjuta
	  boolean swordU=false;			//vi insertar i shoot en actor, som borde kunna vara både en player eller en enemy. Gör vi detta kan vi dock bara använda de variabler som finns i actor. För mer specifika metoder måste man alltså använda player eller enemy.
	  boolean swordD=false;
	  boolean canShoot=false;
	  int canShootCounter;
	  boolean isPlayer=false;		//vill vi ändå ha en metod med actor som gör olika saker åt player o enemy, kan vi använda isPlayer. isPlayer är true för player, false för alla andra.
	  								// vet int om isPlayer kommer att behövas. Jag kan tänka mig någo som har att göra med ingame cutscenes, eller t.ex. en area of effect attack som healar player och skadar enemies? (eller vice versa)
	
}					//Ordet actor kommer förresten från Skyrim, Skyrims game engine funkar just så att alla npc:s, monster, enemies, i princip nästan allt med en AI, är "actors".
