package gamejam.lelworks.src.entities;

import gamejam.lelworks.src.util.Vector2D;

public class EntityWeapon extends Entity {
	
	public int damage = 0;
	public int ammo = 0;

	public EntityWeapon(Vector2D pos, String file) {
		super(pos, file);
	}
	
	public void update() {
	}
	
	public void render() {
	}
}
