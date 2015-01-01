package gamejam.lelworks.src.entities;

import gamejam.lelworks.src.Images;
import gamejam.lelworks.src.World;
import gamejam.lelworks.src.util.Vector2D;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Entity {

	public Vector2D pos = null;
	public Vector2D acc = null;
	public Vector2D size = null;
	
	public Image sprite = null;
	
	public World world = null;
	
	public int health = 0;
	public boolean removed = false;
	
	public Rectangle collisionBox = null;
	
	public Entity(Vector2D pos, String file) {
		this.pos = pos;
		acc = new Vector2D(0, 0);
		size = new Vector2D(0, 0);
		if(file == "" || file == null) {
			sprite = new ImageIcon(Images.class.getResource("/missing.png")).getImage();
		} else {
			sprite = new ImageIcon(Images.class.getResource(file)).getImage();
		}
	}
	
	public void remove() {
		removed = true;
	}
	
	public void update() {	
	}
	
	public void render() {	
	}
	
}
