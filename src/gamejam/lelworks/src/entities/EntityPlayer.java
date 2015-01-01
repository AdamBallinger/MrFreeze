package gamejam.lelworks.src.entities;


import gamejam.lelworks.src.Animation;
import gamejam.lelworks.src.Game;
import gamejam.lelworks.src.Images;
import gamejam.lelworks.src.util.Vector2D;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class EntityPlayer extends Entity {
	
	public int score = 0;
	
	private Animation idleAnimation = null;
	private Animation runAnimation = null;
	private Animation shootAnimation = null;
	
	public List<EntityShot> bullets = new ArrayList<EntityShot>();
	
	public List<EntityWeaponGrenade> grenades = new ArrayList<EntityWeaponGrenade>();
	
	public int currentLayer = 0;
	
	public EntityWeaponGrenade gren = null;

	public EntityPlayer(Vector2D pos, String name) {
		super(pos, name);
		health = 3;
		size.x = 128;
		size.y = 178;
		collisionBox = new Rectangle((int)pos.x, (int)pos.y, (int)size.x, (int)size.y);
		
		idleAnimation = new Animation(Images.FREEZE_IDLE, .2, (int)pos.x, (int)pos.y, (int)size.x, (int)size.y);
		runAnimation = new Animation(Images.FREEZE_RUNNING, .1, (int)pos.x, (int)pos.y, (int)size.x, (int)size.y);
		shootAnimation = new Animation(Images.FREEZE_SHOOT, 1, (int)pos.x, (int)pos.y, (int)size.x, (int)size.y);
	}

	public void update() {
		// Player fires primary weapon
		if (Game.getInstance().input.space.clicked) {
			bullets.add(new EntityShot(new Vector2D(pos.x + 128, pos.y + 64), "/weapons/shot0.png", 1, currentLayer));
		}
		
		if(Game.getInstance().input.e.clicked) {
			if(gren != null && gren.ammo > 0) {
				// throw a freeze grenade.
				grenades.add(new EntityWeaponGrenade(new Vector2D(pos.x + 128, pos.y + 64), "/weapons/grenade/grenade1.png"));
				gren.ammo--;
			}
		}
		
		if (Game.getInstance().input.up.clicked) {
			if(currentLayer < 2) {
				currentLayer++;
			}
		}
		
		if (Game.getInstance().input.down.clicked) {
			if(currentLayer > 0) {
				currentLayer--;
			}
		}
		
		if(Game.getInstance().input.right.down) {
			runAnimation.play();
		} else if(Game.getInstance().input.space.down) {
			shootAnimation.loop();
		} else {
			idleAnimation.loop();
		}
		
		switch(currentLayer) {
			case 0 : 
				pos.y = Game.HEIGHT - 250;
				break;
			case 1 : 
				pos.y = Game.HEIGHT - 280;
				break;
			case 2 : 
				pos.y = Game.HEIGHT - 310;
				break;
		}
		
		collisionBox.x = (int)pos.x;
		collisionBox.y = (int)pos.y;
		
		idleAnimation.x = (int)pos.x;
		idleAnimation.y = (int)pos.y;
		runAnimation.x = (int)pos.x;
		runAnimation.y = (int)pos.y;
		shootAnimation.x = (int)pos.x;
		shootAnimation.y = (int)pos.y;
		
		if(bullets.size() >= 7) {
			bullets.get(bullets.size() - 1).remove();
		}
		
		for(int i = 0; i < bullets.size(); ++i) {
			if(bullets.get(i).removed) {
				bullets.remove(i);
			} else {
				bullets.get(i).update();
			}
		}
		
		for(int i = 0; i < grenades.size(); ++i) {
			if(grenades.get(i).removed) {
				grenades.remove(i);
			} else {
				grenades.get(i).update();
			}
		}
		
		if(health < 0) {
			health = 0;
		}
	}
	
	public void render() {			
		if(Game.getInstance().input.right.down) {
			runAnimation.render();
		} else if(Game.getInstance().input.space.down) {
			shootAnimation.render();
		} else {
			idleAnimation.render();
		}
		
		for(EntityShot shot : bullets) {
			shot.render();
		}
		
		for(EntityWeaponGrenade g : grenades) {
			g.render();
		}
	}
}