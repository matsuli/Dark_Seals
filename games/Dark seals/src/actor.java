import processing.core.*;

public class actor extends PApplet { //Actor �r en class jag t�nkte anv�nda f�r alla "m�nniskor" i v�rt spel, dvs. b�de player, perus enemies och NPC:s. Mer speciliserade actors, som player o enemies, extendar denna class
	  public PVector location;		//Iden �r att h�r finns basic variabler som vi beh�ver vid metoder som vi vill kunna anv�nda p� alla actors
	  boolean swordL=false;			//t.ex. location som ger dess position, och booleans f�r weapons (ifall vi vill att player o enemies o andra characters kan anv�nda samma weapon.
	  boolean swordR=false;			// det t.ex. �r mha actor som vi kan anv�nda samma metod, t.ex. "shoot", f�r att ha b�de enemies o player att skjuta
	  boolean swordU=false;			//vi insertar i shoot en actor, som borde kunna vara b�de en player eller en enemy. G�r vi detta kan vi dock bara anv�nda de variabler som finns i actor. F�r mer specifika metoder m�ste man allts� anv�nda player eller enemy.
	  boolean swordD=false;
	  boolean canShoot=false;
	  int canShootCounter;
	  boolean isPlayer=false;		//vill vi �nd� ha en metod med actor som g�r olika saker �t player o enemy, kan vi anv�nda isPlayer. isPlayer �r true f�r player, false f�r alla andra.
	  								// vet int om isPlayer kommer att beh�vas. Jag kan t�nka mig n�go som har att g�ra med ingame cutscenes, eller t.ex. en area of effect attack som healar player och skadar enemies? (eller vice versa)
	
}					//Ordet actor kommer f�rresten fr�n Skyrim, Skyrims game engine funkar just s� att alla npc:s, monster, enemies, i princip n�stan allt med en AI, �r "actors".
