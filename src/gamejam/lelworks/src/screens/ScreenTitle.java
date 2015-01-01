package gamejam.lelworks.src.screens;

import gamejam.lelworks.src.Game;
import gamejam.lelworks.src.Images;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

public class ScreenTitle extends Screen {

	int menuSelection = 1;
	
	public ScreenTitle(Game game) {
		super(game);
	}
	
	public void update() {
		if(game.input.up.clicked) {
			if(menuSelection == 1) {
				menuSelection = 2;
			} else {
				menuSelection = 1;
			}
		}
		
		if(game.input.down.clicked) {
			if(menuSelection == 1) {
				menuSelection = 2;
			} else {
				menuSelection = 1;
			}
		}
		
		if(game.input.space.clicked) {
			if(menuSelection == 1) {
				game.setScreen(new ScreenGame(game));
			} else {
				game.stop();
			}
		}
	}
	
	public void render() {
		game.get2DGraphics().drawImage(Images.TITLE, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		game.get2DGraphics().setColor(Color.white);
		game.get2DGraphics().setFont(new Font("Arial", Font.BOLD, 32));
		FontMetrics fm = game.get2DGraphics().getFontMetrics();
		int stringWidth = fm.stringWidth("PLAY GAME") / 2;
		game.get2DGraphics().drawString("PLAY GAME", (Game.WIDTH / 2) - stringWidth, 410);
		stringWidth = fm.stringWidth("EXIT") / 2;
		game.get2DGraphics().drawString("EXIT", (Game.WIDTH / 2) - stringWidth, 470);
		stringWidth = fm.stringWidth("WASD - Move : Space - Shoot : E - Grenade(3)") / 2;
		game.get2DGraphics().drawString("WASD - Move : Space - Shoot : E - Grenade(3)", (Game.WIDTH / 2) - stringWidth, 500);
		
		if(menuSelection == 1) {
			int stringWidthh = fm.stringWidth("PLAY GAME") / 2;
			game.get2DGraphics().drawString(">", (Game.WIDTH / 2) - stringWidthh - 20, 410);
			game.get2DGraphics().drawString("<", (Game.WIDTH / 2) + stringWidthh, 410);
		}
		
		if(menuSelection == 2) {
			int stringWidthh = fm.stringWidth("EXIT") / 2;
			game.get2DGraphics().drawString(">", (Game.WIDTH / 2) - stringWidthh - 20, 470);
			game.get2DGraphics().drawString("<", (Game.WIDTH / 2) + stringWidthh, 470);
		}
	}

}
