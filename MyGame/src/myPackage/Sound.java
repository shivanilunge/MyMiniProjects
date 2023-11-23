package myPackage;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	Clip clip; //class to import audio file in java
	URL soundUrl[] = new URL[30]; //URL class is used to store sound files
	
	public Sound()
	{
		soundUrl[0] = getClass().getResource("/sound/BaseSound.wav");
		
		soundUrl[1] = getClass().getResource("/Sound/coin.wav");
		
		soundUrl[2] = getClass().getResource("/Sound/powerup.wav");
		
		soundUrl[3] = getClass().getResource("/Sound/unlock.wav");
		
		soundUrl[4] = getClass().getResource("/Sound/fanfare.wav");
	}
	
	public void setFile(int i)
	{
		try {
			
			//To open audio file in java
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			}
		catch(Exception e) {
			}
	}
	
	public void play()
	{
		clip.start();
	}
	
	public void loop()
	{
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop()
	{
		clip.stop();
	}
}
