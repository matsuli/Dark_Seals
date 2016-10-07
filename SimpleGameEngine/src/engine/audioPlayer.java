package engine;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class audioPlayer {
	public audioPlayer () {
		
	}
	AudioInputStream audioIn = AudioSystem.getAudioInputStream(MyClazz.class.getResource("music.wav"));
	Clip clip = AudioSystem.getClip();
	clip.open(audioIn);
	clip.start();
}
