package gamejam.lelworks.src;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Images {

	public static final Image LOGO = new ImageIcon(Images.class.getResource("/logo.jpg")).getImage();
	public static final Image TITLE = new ImageIcon(Images.class.getResource("/title.png")).getImage();
	
	public static final Image PLAYER = new ImageIcon(Images.class.getResource("/freeze/freeze.png")).getImage();
	
	public static final Image HEALTH = new ImageIcon(Images.class.getResource("/health/icicle.png")).getImage();
	
	public static final Image[] FREEZE_IDLE = {
		new ImageIcon(Images.class.getResource("/freeze/freeze_idle_1.png")).getImage(),
		new ImageIcon(Images.class.getResource("/freeze/freeze_idle_2.png")).getImage(),
		new ImageIcon(Images.class.getResource("/freeze/freeze_idle_3.png")).getImage()
	};
	
	public static final Image[] FREEZE_RUNNING = {
		new ImageIcon(Images.class.getResource("/freeze/freeze_run_0.png")).getImage(),
		new ImageIcon(Images.class.getResource("/freeze/freeze_run_1.png")).getImage(),
		new ImageIcon(Images.class.getResource("/freeze/freeze_run_2.png")).getImage(),
		new ImageIcon(Images.class.getResource("/freeze/freeze_run_3.png")).getImage(),
		new ImageIcon(Images.class.getResource("/freeze/freeze_run_4.png")).getImage(),
		new ImageIcon(Images.class.getResource("/freeze/freeze_run_5.png")).getImage(),
		new ImageIcon(Images.class.getResource("/freeze/freeze_run_6.png")).getImage(),
		new ImageIcon(Images.class.getResource("/freeze/freeze_run_7.png")).getImage(),
		new ImageIcon(Images.class.getResource("/freeze/freeze_run_8.png")).getImage()
	};
	
	public static final Image[] FREEZE_SHOOT = {
		new ImageIcon(Images.class.getResource("/freeze/freeze_run_0.png")).getImage()
	};
	
	public static final Image[] DINO_WALK_RIGHT = {
		new ImageIcon(Images.class.getResource("/enemies/dino0.png")).getImage(),
		new ImageIcon(Images.class.getResource("/enemies/dino1.png")).getImage(),
		new ImageIcon(Images.class.getResource("/enemies/dino2.png")).getImage(),
		new ImageIcon(Images.class.getResource("/enemies/dino3.png")).getImage(),
		new ImageIcon(Images.class.getResource("/enemies/dino4.png")).getImage(),
		new ImageIcon(Images.class.getResource("/enemies/dino5.png")).getImage()
	};
	
	public static final Image[] DINO_WALK_LEFT1 = {
		new ImageIcon(Images.class.getResource("/enemies/dino0_.png")).getImage(),
		new ImageIcon(Images.class.getResource("/enemies/dino1_.png")).getImage(),
		new ImageIcon(Images.class.getResource("/enemies/dino2_.png")).getImage(),
		new ImageIcon(Images.class.getResource("/enemies/dino3_.png")).getImage(),
		new ImageIcon(Images.class.getResource("/enemies/dino4_.png")).getImage(),
		new ImageIcon(Images.class.getResource("/enemies/dino5_.png")).getImage()
	};
	
	public static final Image[] DINO_WALK_LEFT2 = {
		new ImageIcon(Images.class.getResource("/enemies/dinoAnim2/dino0.png")).getImage(),
		new ImageIcon(Images.class.getResource("/enemies/dinoAnim2/dino1.png")).getImage(),
		new ImageIcon(Images.class.getResource("/enemies/dinoAnim2/dino2.png")).getImage(),
		new ImageIcon(Images.class.getResource("/enemies/dinoAnim2/dino3.png")).getImage(),
		new ImageIcon(Images.class.getResource("/enemies/dinoAnim2/dino4.png")).getImage(),
		new ImageIcon(Images.class.getResource("/enemies/dinoAnim2/dino5.png")).getImage()
	};
	
	public static final Image[] DINO_WALK_LEFT3 = {
		new ImageIcon(Images.class.getResource("/enemies/dinoAnim3/dino0.png")).getImage(),
		new ImageIcon(Images.class.getResource("/enemies/dinoAnim3/dino1.png")).getImage(),
		new ImageIcon(Images.class.getResource("/enemies/dinoAnim3/dino2.png")).getImage(),
		new ImageIcon(Images.class.getResource("/enemies/dinoAnim3/dino3.png")).getImage(),
		new ImageIcon(Images.class.getResource("/enemies/dinoAnim3/dino4.png")).getImage(),
		new ImageIcon(Images.class.getResource("/enemies/dinoAnim3/dino5.png")).getImage()
	};
	
	public static final Image[] BULLET_RIGHT = {
		new ImageIcon(Images.class.getResource("/weapons/shotInitial.png")).getImage(),
		new ImageIcon(Images.class.getResource("/weapons/shot0.png")).getImage(),
		new ImageIcon(Images.class.getResource("/weapons/shot1.png")).getImage(),
		new ImageIcon(Images.class.getResource("/weapons/shot2.png")).getImage(),
		new ImageIcon(Images.class.getResource("/weapons/shot3.png")).getImage(),
	};
	
	public static final Image[] SCREEN_FREEZE = {
		new ImageIcon(Images.class.getResource("/screenFreeze/freeze1.png")).getImage(),
		new ImageIcon(Images.class.getResource("/screenFreeze/freeze2.png")).getImage(),
		new ImageIcon(Images.class.getResource("/screenFreeze/freeze3.png")).getImage()
	};
	
	public static final Image[] GRENADE = {
		new ImageIcon(Images.class.getResource("/weapons/grenade/grenade1.png")).getImage(),
		new ImageIcon(Images.class.getResource("/weapons/grenade/grenade2.png")).getImage()
	};
	
	public static final Image GREANDE_ICON = new ImageIcon(Images.class.getResource("/weapons/grenade/grenadeHud.png")).getImage();
	
	public static final Image FROZEN_DINO = new ImageIcon(Images.class.getResource("/enemies/dinoFrozen.png")).getImage();
}
