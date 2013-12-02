package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.utils.Functions;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdClearChat extends ICommand {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.clearchat")){
				for(int i = 0; i <= 77; i++){
					for(Player ps : Bukkit.getOnlinePlayers()){
						ps.sendMessage("");
						Functions.tell(ps, "§6=== Chat cleared by §b" + p.getName() + " §6===");
					}
				}
			}
		}
		return true;
	}

}
