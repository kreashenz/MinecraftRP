package kreashenz.stuntguy3000.mcrp.chat;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.MPlayer;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WordReplacer {

	private MinecraftRP plugin;

	public WordReplacer(MinecraftRP plugin){
		this.plugin = plugin;
	}

	public String convertConfigPath(CommandSender s){
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
			balance = pm.getBalance();

			path = path.replaceAll("{DISPLAYNAME}", displayName);
			path = path.replaceAll("{WORLD}", worldName);
			path = path.replaceAll("{IPADDRESS}", ipAddress);
			path = path.replaceAll("{BALANCE}", balance);
			path = path.replaceAll("{NAME}", name);
		} else worldName = ipAddress = balance = displayName = name = "";
		return path;
	}

}
