package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.utils.Functions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CmdSpawn extends ICommand {

	public CmdSpawn(MinecraftRP plugin) {
		super("spawn");
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.spawn")){
				if(args.length == 0){
					// Hmmmm..
				}
			} else Functions.noPerm(p);
		}
		return true;
	}

}
