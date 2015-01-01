package gamejam.lelworks.src.screens;

import gamejam.lelworks.src.Game;
import gamejam.lelworks.src.Images;
import gamejam.lelworks.src.Sound;
import gamejam.lelworks.src.util.MathHelper;

public class ScreenSplash extends Screen {

	int ticksShown = 0;
	
	public ScreenSplash(Game game) {
		super(game);
		Sound.RANDOM_SOUNDS[0].play();
	}
	
	public void update() {
		ticksShown++;
		
		if(ticksShown >= MathHelper.getSeconds(1.5)) {
			game.setScreen(new ScreenTitle(game));
		}
	}
	
	public void render() {
		game.get2DGraphics().drawImage(Images.LOGO, 10, 65, Game.WIDTH - 40, Game.HEIGHT - 180, null);
	}

}
