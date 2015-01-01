package gamejam.lelworks.src;

import gamejam.lelworks.src.screens.Screen;
import gamejam.lelworks.src.screens.ScreenSplash;
import gamejam.lelworks.src.util.Log;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private static Game instance;
	
	private final String TITLE = "Mr Freeze - ThE GaME";
	public static int WIDTH = 1024;
	public static int HEIGHT = 720;
	
	private boolean running = false;
	private Thread thread = null;
	
	private JFrame frame = null;
	private Screen screen = null;
	public World world = null;
	private Graphics2D g2D = null;
	
	public InputHandler input = null;

	public Game() {
		instance = this;
		input = new InputHandler(instance);
		frame = new JFrame(TITLE);
		frame.setSize(new Dimension(WIDTH, HEIGHT));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exits program when frame is closed.
		frame.setLocationRelativeTo(null); // Positions the frame in the middle of the screen.
		frame.add(instance); // Adds the game (canvas) to the frame so we can draw 'n shit.
		frame.setVisible(true); // Makes the window visible.
		init();
	}
	
	private void init() {
		setScreen(new ScreenSplash(this));
	}
	
	public void stop() {
		if(running) {
			running = false;
		} else {
			Log.info("Already stopped.");
		}
	}
	
	private void start() {
		if(!running) {
			running = true;
			thread = new Thread(this, "Thread-Game");
			thread.start();
		} else {
			Log.info("Thread already running!");
		}
	}

	public void run() {
		double nsPerUpdate = 1000000000.0 / 60; // Determines how many updates per second (60 here).
		long lastUpdateTime = System.nanoTime(); // Gets the current update time (in nanoseconds for accuracy)
		long lastTime = System.currentTimeMillis(); // This is used to update the FPS and UPS every second at bottom
		double unprocessedUpdates = 0; // The number of updates that need to be called this loop.

		int fps = 0; // guess..
		int ups = 0; // updates per second

		// Main game loop
		while(running) {
			long currentUpdateTime = System.nanoTime(); // gets the current update time (because it just started).
			unprocessedUpdates += (currentUpdateTime - lastUpdateTime) / nsPerUpdate; // sets the number of updates based on the time of last
																					// update and the current.
			lastUpdateTime = currentUpdateTime; // Updates the last update time to the current time so the loop works again

			// If there are updates needed.. then update
			while(unprocessedUpdates >= 1) {
				update();
				ups++;
				unprocessedUpdates -= 1;
			}
			
			render();
			fps++;
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException ex) {
				Log.error(ex);
			}

			// heres the thing that updates the fps in game window title each second.
			// just checks if 1000ms (1 second) has passed
			if(System.currentTimeMillis() - lastTime > 1000) {
				lastTime += 1000;
				frame.setTitle(TITLE + "  FPS: " + fps + "   UPS: " + ups);
				fps = 0;
				ups = 0;
			}
		}
		System.exit(0);
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3); // Creates 3 buffers to prevent screen from flickering.
			requestFocus(); // Requests focus (grabs mouse attention).
			return; // breaks out of this render call because we had to create the buffer strategy (basically buffers the stuff we draw)
		}

		Graphics g = bs.getDrawGraphics(); // Gets graphics from the buffer strategy.
		g2D = (Graphics2D)g; // Casts the basic graphics object into a 2D graphics object. (Has rendering hints(anti-aliasing))
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // adds anti aliasing to the g2D object.
		g2D.clearRect(0, 0, WIDTH, HEIGHT); // clears the screen
		
		g2D.setColor(new Color(22, 0, 66));
		g2D.fillRect(0, 0, WIDTH, HEIGHT);

		if(screen != null) {
			screen.render();
		}
		
		g.dispose(); // disposes of graphics object resources
		g2D.dispose(); // disposes of 2d graphics object resources
		bs.show(); // shows the stuff in the buffer
		
	}
	
	private void update() {
		if(screen != null) {
			screen.update();
		}
		
		if(input != null) {
			input.update();
		}
	}
	
	public void setScreen(Screen screen) {
		this.screen = screen;
	}
	
	public Graphics2D get2DGraphics() {
		return g2D;
	}
	
	public static Game getInstance() {
		return instance;
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
}
