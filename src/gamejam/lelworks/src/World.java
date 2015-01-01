package gamejam.lelworks.src;

import gamejam.lelworks.src.entities.Entity;
import gamejam.lelworks.src.entities.EntityDino;
import gamejam.lelworks.src.entities.EntityPlayer;
import gamejam.lelworks.src.entities.EntityWeaponGrenade;
import gamejam.lelworks.src.screens.ScreenEnd;
import gamejam.lelworks.src.util.Log;
import gamejam.lelworks.src.util.Vector2D;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
	
	public EntityPlayer player = null;
	
	Entity[] healthBars = {
			new HealthBar(new Vector2D(0,0), "/health/icicle.png"),
			new HealthBar(new Vector2D(60,0), "/health/icicle.png"),
			new HealthBar(new Vector2D(120,0), "/health/icicle.png")
	};
	
	Entity[] grenBars = {
			new GrenBar(new Vector2D(0,64), "/weapons/grenade/grenadeHud.png"),
			new GrenBar(new Vector2D(60,64), "/weapons/grenade/grenadeHud.png"),
			new GrenBar(new Vector2D(120,64), "/weapons/grenade/grenadeHud.png")
	};
	
	Entity[] backgroundStars = {
			new Entity(new Vector2D(0,0), "/stars.png" ),
			new Entity(new Vector2D(0,0), "/stars.png" )
	};
	
	Entity[] backgroundSkyline = {
			new Entity(new Vector2D(0,0), "/skyline.png" ),
			new Entity(new Vector2D(0,0), "/skyline.png" )
	};
	
	Entity[] backgroundBuildings = {
			new Entity(new Vector2D(0,0), "/buildings.png" ),
			new Entity(new Vector2D(0,0), "/buildings.png" )
	};
	
	public List<EntityDino> dinos0 = new ArrayList<EntityDino>();
	public List<EntityDino> dinos1 = new ArrayList<EntityDino>();
	public List<EntityDino> dinos2 = new ArrayList<EntityDino>();
	
	public int multiplier = 1;
	
	private Random random = new Random();
	
	public int xScrollOffset = 0;
	
	public World() {
		int playerPosX = Game.WIDTH / 8;
		int playerPosY = Game.HEIGHT - 256;  
		player = new EntityPlayer(new Vector2D(playerPosX, playerPosY), "/freeze/freeze.png");
		player.gren = new EntityWeaponGrenade(new Vector2D(0,0), "/weapons/grenade/grenade1.png");
		
		//initialise all of the background instances
		for(Entity ent: backgroundStars) {
			ent.size = new Vector2D(Game.WIDTH, Game.HEIGHT- 45);
		}
		
		for(Entity ent: backgroundSkyline) {
			ent.size = new Vector2D(Game.WIDTH, Game.HEIGHT - 45);
		}
		
		for(Entity ent: backgroundBuildings) {
			ent.size = new Vector2D(Game.WIDTH, Game.HEIGHT - 30);
		}
		
		backgroundStars[0].pos.x = 0;
		backgroundStars[1].pos.x = Game.WIDTH;
		
		backgroundSkyline[0].pos.x = 0;
		backgroundSkyline[1].pos.x = Game.WIDTH;
		
		backgroundBuildings[0].pos.x = 0;
		backgroundBuildings[1].pos.x = Game.WIDTH;
	}
	
	int ticks = 0;
	public void update() {
		ticks++;
		updateBackground();
		
		// This is used to update the dino enemies
		// Fixes a bug so we can run away/after the dinoes
		if(!Game.getInstance().input.left.down && !Game.getInstance().input.right.down) {
			xScrollOffset = 0;
		}
		
		// Spawn Random Dinoswoles
		if(random.nextBoolean()) {
			if(random.nextBoolean()) {
				if(random.nextInt(10) > 8) {
					spawnEnemy();
				}
			}
		}
		
		if(ticks % 180 == 0) {
			if(random.nextBoolean()) {
				Sound.RANDOM_SOUNDS[random.nextInt(8)].play();
			}	
		}
		
		player.update();
		
		//update enemies
		updateEnemies();
		
		if(multiplier <0) {
			multiplier = 1;
		}
		
		if(player.health == 0) {
			Game.getInstance().setScreen(new ScreenEnd(Game.getInstance()));
		}
	}
	
	public void render() {
		drawBackground();
		drawEnemies();
		
		//ScreenFreeze
		switch(player.health) {
		case 0 :
		case 1 : 
			Game.getInstance().get2DGraphics().drawImage(Images.SCREEN_FREEZE[2], 0, 0,  Game.WIDTH,  Game.HEIGHT, null);
			break;
		case 2 : 
			Game.getInstance().get2DGraphics().drawImage(Images.SCREEN_FREEZE[1], 0, 0,  Game.WIDTH,  Game.HEIGHT, null);
			break;
		case 3 : 
			Game.getInstance().get2DGraphics().drawImage(Images.SCREEN_FREEZE[0], 0, 0,  Game.WIDTH,  Game.HEIGHT, null);
			break;
		}
		
		drawHealthBars();
		drawGrenades();
		
		Game.getInstance().get2DGraphics().setFont(new Font("Arial", Font.BOLD, 55) );
		Game.getInstance().get2DGraphics().setColor(Color.WHITE);
		Game.getInstance().get2DGraphics().drawString("Score: " + player.score * multiplier, 0, 170);
	}
	
	private void spawnEnemy() {
		int layer = random.nextInt(3);
		
		switch(layer) {
		case 0:
			dinos0.add(new EntityDino(new Vector2D(Game.WIDTH + 160, player.pos.y - 96), "/enemies/dino0.png", false, layer));
			break;
		case 1:
			dinos1.add(new EntityDino(new Vector2D(Game.WIDTH + 160, player.pos.y - 96), "/enemies/dino0.png", false, layer));
			break;
		case 2:
			dinos2.add(new EntityDino(new Vector2D(Game.WIDTH + 160, player.pos.y - 96), "/enemies/dino0.png", false, layer));
			break;
		}
	}
	
	// Update the screen position depending on what button is pressed
	private void updateBackground() {
		// move screen left
		if(Game.getInstance().input.right.down) {
			xScrollOffset = 1;
			
			for(Entity ent : backgroundStars) {
				ent.pos.x -= 0.5;
				if (ent.pos.x <= Game.WIDTH - Game.WIDTH * 2) {
					ent.pos.x = Game.WIDTH;
				}
			}
			
			for(Entity ent : backgroundSkyline) {
				ent.pos.x -= 1;
				if (ent.pos.x <= Game.WIDTH - Game.WIDTH * 2) {
					ent.pos.x = Game.WIDTH;
				}
			}
			
			for(Entity ent : backgroundBuildings) {
				ent.pos.x -= 2;
				if (ent.pos.x <= Game.WIDTH - Game.WIDTH * 2) {
					ent.pos.x = Game.WIDTH;
					multiplier++;
					Log.info(multiplier + "");
				}
			}
		}
		
		// move screen right
		if(Game.getInstance().input.left.down) {
			xScrollOffset = -1;
			
			for(Entity ent : backgroundStars) {
				ent.pos.x += 0.5;
				if (ent.pos.x >= Game.WIDTH) {
					ent.pos.x = 0 - Game.WIDTH;
				}
			}
			
			for(Entity ent : backgroundSkyline) {
				ent.pos.x += 1;
				if (ent.pos.x >= Game.WIDTH) {
					ent.pos.x = 0 - Game.WIDTH;
				}
			}
			
			for(Entity ent : backgroundBuildings) {
				ent.pos.x += 2;
				if (ent.pos.x >= Game.WIDTH) {
					ent.pos.x = 0 - Game.WIDTH;
				}
			}		
		}
	}
	
	// update all the enemy instances on each track
	private void updateEnemies() {
		for(int i = 0; i < dinos0.size(); ++i) {
			dinos0.get(i).update();
			if(dinos0.get(i).removed) {
				dinos0.remove(i);
			}
		}
		
		for(int i = 0; i < dinos1.size(); ++i) {
			dinos1.get(i).update();
			if(dinos1.get(i).removed) {
				dinos1.remove(i);
			}
		}
		
		for(int i = 0; i < dinos2.size(); ++i) {
			dinos2.get(i).update();
			if(dinos2.get(i).removed) {
				dinos2.remove(i);
			}
		}
	}
	
	// Draws the scrolling background
	private void drawBackground() {
		for(Entity ent : backgroundStars) {
			Game.getInstance().get2DGraphics().drawImage(ent.sprite, (int)ent.pos.x, (int)ent.pos.y,  (int)ent.size.x,  (int)ent.size.y, null);
		}
		
		for(Entity ent : backgroundSkyline) {
			Game.getInstance().get2DGraphics().drawImage(ent.sprite, (int)ent.pos.x, (int)ent.pos.y,  (int)ent.size.x,  (int)ent.size.y, null);
		}
		
		for(Entity ent : backgroundBuildings) {
			Game.getInstance().get2DGraphics().drawImage(ent.sprite, (int)ent.pos.x, (int)ent.pos.y,  (int)ent.size.x,  (int)ent.size.y, null);
		}
	}
	
	// Draw all the enemies on each track
	private void drawEnemies() {
		if(player.currentLayer == 2)
			player.render();
		
		for(int i = dinos2.size() - 1; i >= 0; --i) {
			dinos2.get(i).render();
		}
		
		if(player.currentLayer == 1)
			player.render();
		
		for(int i = dinos1.size() - 1; i >= 0; --i) {
			dinos1.get(i).render();
		}
		
		if(player.currentLayer == 0)
			player.render();
		
		for(int i = dinos0.size() - 1; i >= 0; --i) {
			dinos0.get(i).render();
		}
	}
	
	private void drawHealthBars() {
		for (int i = 0; i < player.health; ++i) {
			healthBars[i].render();
		}
	}
	
	private void drawGrenades() {
		for (int i = 0; i < player.gren.ammo; ++i) {
			grenBars[i].render();
		}	
	}
}
