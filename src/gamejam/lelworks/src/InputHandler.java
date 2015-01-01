package gamejam.lelworks.src;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener {

	// stores a list of all the keys
	private List<Key> keys = new ArrayList<Key>();

	// Up Arrow key
	public Key up = new Key();
	// Left Arrow Key
	public Key left = new Key();
	// Down Arrow Key
	public Key down = new Key();
	// Right Arrow Key
	public Key right = new Key();
	// E Key
	public Key e = new Key();
	// Space key
	public Key space = new Key();
	// Enter Key
	public Key enter = new Key();
	// Escape Key
	public Key escape = new Key();

	public class Key {
		public boolean down, clicked;
		int presses, absorbs;

		public Key() {
			keys.add(this);
		}

		public void toggle(boolean pressed) {
			if(pressed != down) {
				down = pressed;
			}
			if(pressed) {
				presses++;
			}
		}

		public void update() {
			if(absorbs < presses) {
				absorbs++;
				clicked = true;
			} else {
				clicked = false;
			}
		}
	}

	public InputHandler(Game game) {
		game.addKeyListener(this);
	}

	/**
	 * Releases all the keys. not really used.
	 */
	public void releaseAll() {
		for(Key key : keys) { // this means for each Key object in the keys list, that key objects down boolean is set to false.
			key.down = false;
		}
	}

	public void toggle(int keyCode, boolean pressed) {
		if(keyCode == KeyEvent.VK_W) up.toggle(pressed);
		if(keyCode == KeyEvent.VK_A) left.toggle(pressed);
		if(keyCode == KeyEvent.VK_S) down.toggle(pressed);
		if(keyCode == KeyEvent.VK_D) right.toggle(pressed);
		if(keyCode == KeyEvent.VK_E) e.toggle(pressed);
		if(keyCode == KeyEvent.VK_SPACE) space.toggle(pressed);
		if(keyCode == KeyEvent.VK_ENTER) enter.toggle(pressed);
		if(keyCode == KeyEvent.VK_ESCAPE) escape.toggle(pressed);
	}

	public void update() {
		for(Key key: keys) {
			key.update();
		}
	}

	/**
	 * Below methods are included from Java's KeyListener class and are what listen for input from the keyboard.
	 */


	public void keyPressed(KeyEvent ke) {
		toggle(ke.getKeyCode(), true);
	}

	public void keyReleased(KeyEvent ke) {
		toggle(ke.getKeyCode(), false);
	}

	public void keyTyped(KeyEvent ke) {
	}

}
