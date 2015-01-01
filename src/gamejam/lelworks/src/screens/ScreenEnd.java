package gamejam.lelworks.src.screens;

import java.awt.Color;
import java.awt.Font;

import gamejam.lelworks.src.Game;
import gamejam.lelworks.src.Images;

public class ScreenEnd extends Screen {

	public ScreenEnd(Game game) {
		super(game);
	}

	public void update() {
		if(game.input.space.clicked) {
				game.setScreen(new ScreenTitle(game));
		}
	}
	
	public void render() {
		game.get2DGraphics().drawImage(Images.SCREEN_FREEZE[2], 0, 0,  800,  600, null);
		game.get2DGraphics().setColor(Color.white);
		game.get2DGraphics().setFont(new Font("Arial", Font.BOLD, 32));
		game.get2DGraphics().drawString("GAME OVER", 320, 300);
	}
}
