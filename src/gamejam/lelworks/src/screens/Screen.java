package gamejam.lelworks.src.screens;

import gamejam.lelworks.src.Game;

public abstract class Screen {

	protected Game game = null;
	
	public Screen(Game game) {
		this.game = game;
	}
	
	public abstract void update();	
	public abstract void render();
}
