package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.utils.Functions;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdKick extends ICommand {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.kick")){
				if(args.length == 0){
					Functions.tell(p, "§cInvalid arguments! Usage §f/kick <player> [reason]");
				} else {
					Player t = Bukkit.getPlayer(args[0]);
					if(t != null){
						if(!t.hasPermission("mcrp.kick.bypass")){
							if(args.length == 1){
								t.kickPlayer("§cYou were kicked by a server administrator");
							} else {
								kick(t, args, false);
							}
						} else Functions.tell(p, "§cYou can't kick that player!");
					} else Functions.unknownPlayer(p);
				}
			} else Functions.noPerm(p);
		} else {
			if(args.length == 0){
				Functions.tell(s, "§cInvalid arguments! Usage §f/kick <player> [reason]");
			} else {
				Player t = Bukkit.getPlayer(args[0]);
				if(args.length == 1){
					if(t != null){
						t.kickPlayer("§cYou were kicked by Console.");
					} else Functions.unknownPlayer(s);
				} else {
					if(t != null){
						kick(t, args, false);
					} else Functions.unknownPlayer(s);
				}
			}
		}
		return true;
	}
}