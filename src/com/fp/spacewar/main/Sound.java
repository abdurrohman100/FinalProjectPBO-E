package com.fp.spacewar.main;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound{
	
	Clip click;
	String path;
	AudioInputStream audioIn;

	/**
	 * @param path string tampat menyimpan file (root src/com/fp/spacewar/main/res/)
	 */
	public Sound(String path) {
		this.path= "src/com/fp/spacewar/main/res/"+path;
		try {
			audioIn = AudioSystem.getAudioInputStream(new File(this.path).getAbsoluteFile());
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 try {
			click = AudioSystem.getClip();
			click.open(audioIn);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	/**
	 * mainkan clip
	 */
	public void play() {
		click.start();
	}
	/**
	 *  stop clip dan set clip kembali ke awal
	 * 
	 */
	public void stop() {
		click.stop();
		click.setFramePosition(0);
	}
	/**
	 * Loop clip
	 */
	public void loop() {
		click.loop(Clip.LOOP_CONTINUOUSLY);
	}
	/**
	 * @return true or false
	 */
	public boolean isRunning() {
		if(click.isActive())return true;
		else return false;	
	}
	/**
	 * setClip ke posisi awal
	 */
	public void setStart() {
        click.setFramePosition(0);
	}
	
	/**
	 * @return volume in float
	 */
	public float getVolume() {
	    FloatControl gainControl = (FloatControl) click.getControl(FloatControl.Type.MASTER_GAIN);        
	    return (float) Math.pow(10f, gainControl.getValue() / 20f);
	}

	/**
	 * @param volume set volume as float parameter
	 */
	public void setVolume(float volume) {
	    if (volume < 0f || volume > 1f)
	        throw new IllegalArgumentException("Volume not valid: " + volume);
	    FloatControl gainControl = (FloatControl) click.getControl(FloatControl.Type.MASTER_GAIN);        
	    gainControl.setValue(20f * (float) Math.log10(volume));
	}
	

}