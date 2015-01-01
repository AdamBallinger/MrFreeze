package gamejam.lelworks.src.entities;

import gamejam.lelworks.src.Animation;
import gamejam.lelworks.src.Images;
import gamejam.lelworks.src.util.MathHelper;
import gamejam.lelworks.src.util.Vector2D;

import java.awt.Rectangle;

public class EntityShot extends Entity {

	private int direction = 0;
	private int ticksAlive = 0;
	public int currentLayer = 0;
	
	private Animation rightAnim = null;
	
	public EntityShot(Vector2D pos, String file, int direction, int layer) {
		super(pos, file);
		this.direction = direction;
		currentLayer = layer;
		size.x = 64;
		size.y = 64;
		collisionBox = new Rectangle((int)pos.x, (int)pos.y, (int)size.x, (int)size.y);
		rightAnim = new Animation(Images.BULLET_RIGHT, .08, 0, (int)pos.y, (int)size.x, (int)size.y);
	}
	
	public void update() {
		ticksAlive++;
		if(direction == 0) {
			pos.x -= 8;
		} else {
			pos.x += 8;
		}
		
		rightAnim.x = (int)pos.x;
		
		rightAnim.altPlay();
		
		this.collisionBox.x = (int)pos.x;
		
		if(ticksAlive >= MathHelper.getSeconds(0.8)) {
			remove();
		}
	}
	
	public void render() {	
		rightAnim.render();
	}
}
