package gamejam.lelworks.src.util;

import java.util.logging.Level;

public class Log {

	private static void out(Level level, String out) {
		System.out.println("[" + level + "] " + out);
	}
	
	public static void info(String info) {
		out(Level.INFO, info);
	}
	
	public static void error(Exception ex) {
		out(Level.SEVERE, ex.getLocalizedMessage());
	}
	
}
