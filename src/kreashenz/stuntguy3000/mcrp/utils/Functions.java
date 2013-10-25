package kreashenz.stuntguy3000.mcrp.utils;

import java.util.logging.Level;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Functions {

	public static void tell(CommandSender s, String msg){
		s.sendMessage(s instanceof Player ? colour(msg) : ChatColor.stripColor(msg) + " ");
	}

	public static void noPerm(CommandSender s){
		tell(s, MinecraftRP.getInstance().getConfig().getString("messages.no-permission"));
	}

	public static String colour(String s){
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	public static void log(Level level, String msg){
		MinecraftRP.getInstance().getLogger().log(level, msg);
	}

	public static void unknownPlayer(CommandSender s){
		tell(s, "§cThat player wasn't found!");
	}

}
