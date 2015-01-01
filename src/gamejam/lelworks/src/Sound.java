package gamejam.lelworks.src;

import gamejam.lelworks.src.util.Log;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	private AudioClip clip;
	
	public static final Sound[] RANDOM_SOUNDS = {
		new Sound("/sfx/AFreeze.wav"),
		new Sound("/sfx/BreakTheIce.wav"),
		new Sound("/sfx/ColdTown.wav"),
		new Sound("/sfx/EveryoneChill.wav"),
		new Sound("/sfx/Freeze.wav"),
		new Sound("/sfx/Freeze2.wav"),
		new Sound("/sfx/Frosty.wav"),
		new Sound("/sfx/WinterAtLast.wav")
	};
	
	public static final Sound Argh = new Sound("/sfx/Argh.wav");

	public Sound(String file) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(file));
		} catch(Exception e) {
			Log.error(e);
		}
	}

	public void stop() {
		clip.stop();
	}

	public void play() {
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start();
		} catch (Exception e) {
			Log.error(e);
		}
	}
}
