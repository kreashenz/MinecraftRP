package kreashenz.stuntguy3000.mcrp;

import java.io.File;

import kreashenz.stuntguy3000.mcrp.utils.MPlayer;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Economy {

	private static MPlayer getMPlayer(String name){
		Player p = Bukkit.getPlayer(name);
		if(p != null){
			MPlayer pm = MPlayer.getMPlayer(p);
			return pm;
		}
		return null;
	}

	public static double getMoney(String name){
		MPlayer pm = getMPlayer(name);
		return pm.getMoney();
	}

	public static void setMoney(String name, double amount){
		MPlayer pm = getMPlayer(name);
		pm.setMoney(amount);
	}

	public static void addMoney(String name, double amount){
		MPlayer pm = getMPlayer(name);
		pm.addMoney(amount);
	}

	public static void takeMoney(String name, double amount){
		MPlayer pm = getMPlayer(name);
		pm.takeMoney(amount);
	}

	public static void resetMoney(String name){
		MPlayer pm = getMPlayer(name);
		pm.resetMoney();
	}

	public static boolean hasEnough(String name, double afford){
		MPlayer pm = getMPlayer(name);
		return pm.canAfford(afford);
	}

	public static boolean exists(String name){
		return getMPlayer(name) != null;
	}

	private static void createBase(String name){
		File f = new File(MinecraftRP.getInstance().getDataFolder(), "users");
		if(!f.exists())f.mkdir();
		FileConfiguration conf = getMPlayer(name).getConfig();
		conf.set("npc", Boolean.valueOf(true));
		conf.set("balance", MinecraftRP.getInstance().getConfig().getInt("economy.starting-balance"));
	}

	public static boolean create(String name){
		if(getMPlayer(name) != null){
			createBase(name);
			return true;
		}
		return false;
	}

}
