package me.kreashenz.mcrp.chat;

import java.io.File;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.utils.Functions;
import me.kreashenz.mcrp.utils.MPlayer;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WordReplacer {

	private MinecraftRP plugin;

	public WordReplacer(MinecraftRP plugin){
		this.plugin = plugin;
	}

	public String convertFormat(String path, CommandSender s){
		path = plugin.getConfig().getString(path);
		String worldName;
		String ipAddress;
		String balance;
		String displayName;
		String name;
		String unique;
		if(s instanceof Player){
			Player p = (Player)s;
			MPlayer pm = MPlayer.getMPlayer(p);
			displayName = p.getDisplayName();
			name = p.getName();
			ipAddress = (p.getAddress() != null ? p.getAddress().toString() : "");
			worldName = p.getWorld().getName();
			balance = "$" + Functions.formatAsCurrency(pm.getMoney());
			unique = String.valueOf(new File(Bukkit.getWorldContainer() + File.separator + "players").listFiles().length);

			path = path.replace("{PLAYERS}", Functions.splitObject(plugin.getServer().getOnlinePlayers(), 'a', '7'));
			path = path.replace("{PLUGINS}", Functions.splitObject(plugin.getServer().getPluginManager().getPlugins(), 'a', '7'));
			path = path.replace("{UNIQUE}", unique);
			path = path.replace("{DISPLAYNAME}", displayName);
			path = path.replace("{WORLD}", worldName);
			path = path.replace("{IPADDRESS}", ipAddress);
			path = path.replace("{BALANCE}", balance);
			path = path.replace("{NAME}", name);
			path = Functions.colour(path);
		} else worldName = ipAddress = balance = displayName = name = "";
		return path;
	}

}
