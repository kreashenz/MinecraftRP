package kreashenz.stuntguy3000.mcrp.utils;

public class TPSHandler implements Runnable {

	public static int tickCount = 0;
	public static long[] ticks = new long[600];
	public static long lastTick = 0L;

	public static double getTPS() {
		return getTPS(100);
	}

	public static double getTPS(int tick) {
		if (tickCount < tick) {
			return 20.0D;
		}

		return tick / ((System.currentTimeMillis() - ticks[(tickCount - 1 - tick) % ticks.length]) / 1000.0D);
	}

	public static long getElapsed(int tickID) {
		//if (tickCount - tickID >= ticks.length) {
		//}

		long time = ticks[(tickID % ticks.length)];
		return System.currentTimeMillis() - time;
	}

	public void run() {
		ticks[(tickCount % ticks.length)] = System.currentTimeMillis();

		tickCount += 1;
	}
}