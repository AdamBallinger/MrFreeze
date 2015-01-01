package gamejam.lelworks.src.util;

public class MathHelper {

	/**
	 * Gets the number of updates that will pass before the specified time is met.
	 * Example:
	 * 			if seconds is 2. the game runs at roughly 59-61 updates per second.
	 * 			so 2 seconds would be around 120 updates.
	 * @param seconds The number of seconds you want to convert to updates.
	 * @return The number of updates for the value of seconds.
	 */
	public static double getSeconds(double seconds) {
		return seconds * 60;
	}
}
