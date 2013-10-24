package kreashenz.stuntguy3000.mcrp.utils;

import java.util.logging.Level;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Functions {

	public static void tell(CommandSender s, String msg){
		String st = colour(MinecraftRP.getInstance().getConfig().getString("messages.prefix"));
		if(s instanceof Player){
			s.sendMessage(st + msg);
		} else {
			s.sendMessage(ChatColor.stripColor(st + msg));
		}
	}

	public static void noPerm(CommandSender s){
		tell(s, "§cYou don't have permission!");
	}
	
	public static String colour(String s){
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static void log(Level level, String msg){
		MinecraftRP.getInstance().getLogger().log(level, msg);
	}

}
