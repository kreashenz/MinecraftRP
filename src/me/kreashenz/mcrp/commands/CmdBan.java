package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.utils.Functions;
import me.kreashenz.mcrp.utils.MPlayer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdBan extends ICommand {

	public CmdBan(MinecraftRP plugin) {
		super("ban");
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.ban")){
				if(args.length == 0){
					Functions.tell(p, "§cInvalid arguments! Usage §f/ban <player> [reason]");
				} else {
					Player t = Bukkit.getPlayer(args[0]);
					if(t != null){
						MPlayer tm = MPlayer.getMPlayer(t);
						if(!t.hasPermission("mcrp.ban.bypass")){
							if(args.length == 1){
								t.kickPlayer("§cYou were banned by a server administrator.");
								t.setBanned(true);
							} else {
								kick(t, args, true);
								tm.set("ban.banner", p.getName());
								tm.set("ban.date", Functions.getDate());
							}
						} else Functions.tell(p, "§cYou can't ban that player");
					} else Functions.unknownPlayer(p);
				}
			} else Functions.noPerm(p);
		} else {
			if(args.length == 0){
				Functions.tell(s, "§cInvalid arguments! Usage §f/ban <player> [reason]");
			} else {
				Player t = Bukkit.getPlayer(args[0]);
				if(t != null){
					MPlayer tm = MPlayer.getMPlayer(t);
					if(args.length == 1){
						t.kickPlayer("§cYou were banned by Console.");
						t.setBanned(true);
					} else {
						kick(t, args, true);
						tm.set("ban.banner", "CONSOLE");
						tm.set("ban.date", Functions.getDate());
					}
				} else Functions.unknownPlayer(s);
			}
		}
		return true;
	}
}