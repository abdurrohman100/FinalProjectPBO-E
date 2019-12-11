package com.fp.spacewar.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound{
	URL urlClick;
	Clip click;
	String path;
	AudioInputStream audioIn;

	public Sound(String path) {
		this.path= "src/com/fp/spacewar/main/res/"+path;
		try {
			audioIn = AudioSystem.getAudioInputStream(new File(this.path).getAbsoluteFile());
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 try {
			click = AudioSystem.getClip();
			click.open(audioIn);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public void play() {
		click.start();
	}
	public void stop() {
		click.stop();
		click.setFramePosition(0);
	}
	public void loop() {
		click.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public boolean isRunning() {
		if(click.isActive())return true;
		else return false;	
	}
	public void setStart() {
        click.setFramePosition(0);
	}
	
	public float getVolume() {
	    FloatControl gainControl = (FloatControl) click.getControl(FloatControl.Type.MASTER_GAIN);        
	    return (float) Math.pow(10f, gainControl.getValue() / 20f);
	}

	public void setVolume(float volume) {
	    if (volume < 0f || volume > 1f)
	        throw new IllegalArgumentException("Volume not valid: " + volume);
	    FloatControl gainControl = (FloatControl) click.getControl(FloatControl.Type.MASTER_GAIN);        
	    gainControl.setValue(20f * (float) Math.log10(volume));
	}
	

}