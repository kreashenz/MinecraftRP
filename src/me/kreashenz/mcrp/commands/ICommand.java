package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.utils.MPlayer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class ICommand implements CommandExecutor {

	protected MinecraftRP plugin;

	public ICommand(){
		this.plugin = MinecraftRP.getInstance();
	}

	protected void kick(Player p, String[] args, boolean ban){
		String reason = "";
		for (int i = 1; i < args.length; i++) {
			reason = reason + args[i] + ' ';
			reason = ChatColor.translateAlternateColorCodes('&', reason);
		}
		p.kickPlayer("§cYou were " + (ban ? "banned" : "kicked") + " by §6" + p.getName() + " §cfor §6" + reason);
		if(ban){
			p.setBanned(true);
			MPlayer.getMPlayer(p).set("ban.reason", reason);
		}
	}

	protected void message(CommandSender p, CommandSender t, String[] msg, boolean reply){
		String m = "";
		for (int i = (reply ? 0 : 1); i < msg.length; i++) {
			m = m + msg[i] + ' ';
			if(p.hasPermission("mcrp.chat.color"))m = ChatColor.translateAlternateColorCodes('&', m);
		}
		p.sendMessage("§6[§a" + p.getName() + " -> §7" + t.getName() + "§6] §7" + m);
		t.sendMessage("§6[§7" + p.getName() + "§a -> " + t.getName() + "§6] §7" + m);
	}

	@Override
	public abstract boolean onCommand(CommandSender s, Command cmd, String label, String[] args);
}