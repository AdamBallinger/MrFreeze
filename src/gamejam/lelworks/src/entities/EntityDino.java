package gamejam.lelworks.src.entities;

import gamejam.lelworks.src.Animation;
import gamejam.lelworks.src.Game;
import gamejam.lelworks.src.Images;
import gamejam.lelworks.src.Sound;
import gamejam.lelworks.src.util.Vector2D;

import java.awt.Rectangle;
import java.util.Random;

public class EntityDino extends Entity {

	private Animation walkAnimation = null;
	private boolean right = false;
	public int currentLayer = 0;
	public int speed = 0;
	
	public EntityDino(Vector2D pos, String file, boolean right, int layer) {
		super(pos, file);
		size.x = 390;
		size.y = 256;
		collisionBox = new Rectangle((int)pos.x, (int)pos.y, (int)size.x, (int)size.y);
		currentLayer = layer;
		
		switch(currentLayer) {
			case 0 : 
				pos.y = Game.HEIGHT - 320;
				break;
			case 1 : 
				pos.y = Game.HEIGHT - 350;
				break;
			case 2 : 
				pos.y = Game.HEIGHT - 380;
				break;
		}
		
		if(right) {
			right = true;
			walkAnimation = new Animation(Images.DINO_WALK_RIGHT, .17, (int)pos.x, (int)pos.y, (int)size.x, (int)size.y);
		} else {
			right = false;
			Random r = new Random();
			switch(r.nextInt(3)) {
				case 0: 
					walkAnimation = new Animation(Images.DINO_WALK_LEFT1, .17, (int)pos.x, (int)pos.y, (int)size.x, (int)size.y);
					speed = 2;
					break;
				case 1: 
					walkAnimation = new Animation(Images.DINO_WALK_LEFT2, .17, (int)pos.x, (int)pos.y, (int)size.x, (int)size.y);
					speed = 3;
					break;
				case 2: 
					walkAnimation = new Animation(Images.DINO_WALK_LEFT3, .17, (int)pos.x, (int)pos.y, (int)size.x, (int)size.y);
					speed = 4;
					break;
			}
		}
	}
	
	public void update() {
		if(Game.getInstance().world.xScrollOffset == 1) {
			pos.x -= 2;
		}
		
		if (Game.getInstance().world.xScrollOffset == -1) {
			pos.x += 2;
		}
		
		if(right == true) {
			pos.x += 1.5;
		} else {
			pos.x -= speed;
		}
		
		if(pos.x < 0 - size.x) {
			remove();
		}
		
		collisionBox.x = (int)pos.x;
		collisionBox.y = (int)pos.y;
		
		walkAnimation.x = (int)pos.x;
		walkAnimation.y = (int)pos.y;
		walkAnimation.play();
		
		// Collision Detection
		collisionDetection();		
	}
	
	public void render() {
		if(speed != 0) {
			walkAnimation.render();
		} else {
			Game.getInstance().get2DGraphics().drawImage(Images.FROZEN_DINO, (int)pos.x, (int)pos.y,  (int)size.x,  (int)size.y, null);
		}
	}

	private void collisionDetection() {
		// bullet collision detection
		for(EntityShot shot : Game.getInstance().world.player.bullets) {
			if( shot.pos.x > pos.x && shot.pos.x < pos.x + size.x && shot.currentLayer == currentLayer) {
				//collided
				shot.remove();
				remove();
				Game.getInstance().world.player.score++;
			}
		}
		
		// player collision detection
		if( Game.getInstance().world.player.pos.x > pos.x && Game.getInstance().world.player.pos.x < pos.x + size.x && Game.getInstance().world.player.currentLayer == currentLayer) {
			//collided
			Game.getInstance().world.player.health--;
			remove();
			Game.getInstance().world.multiplier--;
			Sound.Argh.play();
		}
		
		if(pos.x < 0 - size.x) {
			remove();
			Game.getInstance().world.multiplier--;
			Game.getInstance().world.player.score--;
		}
	}
}
