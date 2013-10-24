package kreashenz.stuntguy3000.mcrp.chat;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.MPlayer;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WordReplacer {

	private MinecraftRP plugin;

	public WordReplacer(MinecraftRP plugin){
		this.plugin = plugin;
	}

	public String convertChatFormat(CommandSender s){
		String path = plugin.getConfig().getString("chat.format");
		String worldName;
		String ipAddress;
		String balance;
		String displayName;
		String name;
		if(s instanceof Player){
			Player p = (Player)s;
			MPlayer pm = MPlayer.getMPlayer(p);
			displayName = p.getDisplayName();
			name = p.getName();
			ipAddress = (p.getAddress() != null ? p.getAddress().toString() : "");
			worldName = p.getWorld().getName();
			balance = Double.valueOf(pm.getMoney()).toString();

			path = path.replace("{DISPLAYNAME}", displayName);
			path = path.replace("{WORLD}", worldName);
			path = path.replace("{IPADDRESS}", ipAddress);
			path = path.replace("{BALANCE}", balance);
			path = path.replace("{NAME}", name);
			path = ChatColor.translateAlternateColorCodes('&', path);
		} else worldName = ipAddress = balance = displayName = name = "";
		return path;
	}

}
