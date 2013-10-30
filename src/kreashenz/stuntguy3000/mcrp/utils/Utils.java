package kreashenz.stuntguy3000.mcrp.utils;

import java.util.logging.Level;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Utils {

	private static MinecraftRP plugin = MinecraftRP.getInstance();

	public static void setSpawn(Location loc){
		plugin.getConfig().set("spawn.x", loc.getBlockX());
		plugin.getConfig().set("spawn.y", loc.getBlockY());
		plugin.getConfig().set("spawn.z", loc.getBlockZ());
		plugin.getConfig().set("spawn.world", loc.getWorld().getName());
		plugin.saveConfig();
	}

	public static Location getSpawn(){
		if(plugin.getConfig().contains("spawn")){
			return new Location(Bukkit.getWorld(plugin.getConfig().getString("spawn.world")),
					plugin.getConfig().getInt("spawn.x"),
					plugin.getConfig().getInt("spawn.y"),
					plugin.getConfig().getInt("spawn.z"));
		} else {
			Functions.log(Level.SEVERE, "Spawn isn't set!");
			return null;
		}
	}

}
