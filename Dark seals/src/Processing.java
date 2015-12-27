import processing.core.*;

public class Processing extends PApplet {

  public void setup() {
    background(0);
  }
  public void settings() {
	  size(1000, 1000);
	}

  public void draw() {
    stroke(255);
    if (mousePressed) {
      line(mouseX,mouseY,pmouseX,pmouseY);
    }
  }
  public static void main(String args[]) {
	    PApplet.main(new String[] { "--present", "Processing" });
	  }
}