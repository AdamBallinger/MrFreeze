package gamejam.lelworks.src.entities;

import gamejam.lelworks.src.Animation;
import gamejam.lelworks.src.Game;
import gamejam.lelworks.src.Images;
import gamejam.lelworks.src.util.MathHelper;
import gamejam.lelworks.src.util.Vector2D;

public class EntityWeaponGrenade extends EntityWeapon {

	public int radius;
	public int speed;
	private int ticksAlive = 0;
	
	public Animation anim = null;

	public EntityWeaponGrenade(Vector2D pos, String file) {
		super(pos, file);
		ammo = 3;
		radius = 20;
		size.x = 64;
		size.y = 64;
		speed = 3;
		anim = new Animation(Images.GRENADE, .08, (int)pos.x, (int)pos.y, (int)size.x, (int)size.y);
	}
	
	public void update() {
		ticksAlive++;
		pos.x += speed;
		
		anim.x = (int)pos.x;
		
		anim.play();
		
		if(ticksAlive >= MathHelper.getSeconds(0.8)) {
			remove();
			for(EntityDino d : Game.getInstance().world.dinos0) {
				d.speed = 0;
			}
			
			for(EntityDino d : Game.getInstance().world.dinos1) {
				d.speed = 0;
			}
			
			for(EntityDino d : Game.getInstance().world.dinos2) {
				d.speed = 0;
			}
		}
	}
	
	public void render() {
		anim.render();
	}
}