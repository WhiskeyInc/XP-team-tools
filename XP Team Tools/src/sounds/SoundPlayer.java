package sounds;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * SoundPlayer class to play audio file
 * when a time countdown expires
 * @author alessandro
 */
public class SoundPlayer {
	
	 String filename ;
	 AudioInputStream stream;
	 AudioFormat format;
	 DataLine.Info info;
	 Clip clip;
	 
	 public SoundPlayer(String filename) {
		this.filename = filename;
	}
	 
	 /**
	  * Function to play the charged song in the runtime execution of the tool
	  */
	 public void playSong(){
		 try {
			stream = AudioSystem.getAudioInputStream(new File(filename));
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    format = stream.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    try {
				clip = (Clip) AudioSystem.getLine(info);
			} catch (LineUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    try {
				clip.open(stream);
			} catch (LineUnavailableException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    clip.start();
	 }

}
