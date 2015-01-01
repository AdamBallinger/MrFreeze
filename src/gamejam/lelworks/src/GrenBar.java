package gamejam.lelworks.src;

import gamejam.lelworks.src.entities.Entity;
import gamejam.lelworks.src.util.Vector2D;


public class GrenBar extends Entity {

	public GrenBar(Vector2D pos, String file) {
		super(pos, file);	
		this.size.x = 64;
		this.size.y = 64;
	}
		
	public void update() {		
	}
		
	public void render() {
		Game.getInstance().get2DGraphics().drawImage(Images.GREANDE_ICON, (int)this.pos.x, (int)this.pos.y, (int)this.size.x, (int)this.size.y, null);
	}
		
}
