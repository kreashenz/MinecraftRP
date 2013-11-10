package me.kreashenz.mcrp.utils;

import java.lang.reflect.Field;

import org.bukkit.entity.Player;

public class TPSHandler implements Runnable {
	
	/*
	 * @author LazyLemons
	 */

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
	
	// I made this method
	public static int getPing(Player p){
		try {
			Object nmsPlayer = p.getClass().getMethod("getHandle").invoke(p);
			Field field = nmsPlayer.getClass().getField("ping");
			return (int)field.getInt(nmsPlayer);
		}
		catch(Throwable t){
			t.printStackTrace();
			return 0;
		}
	}
	
}