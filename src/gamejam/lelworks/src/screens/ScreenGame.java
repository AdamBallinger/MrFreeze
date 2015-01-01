package gamejam.lelworks.src.screens;

import gamejam.lelworks.src.Game;
import gamejam.lelworks.src.World;

public class ScreenGame extends Screen {

	public World world;
	
	public ScreenGame(Game game) {
		super(game);
		world = new World();
		game.world = world;
	}
	
	public void update() {
		world.update();
	}
	
	public void render() {
		world.render();
	}

}
