package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.utils.Functions;
import me.kreashenz.mcrp.utils.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSetspawn extends ICommand {

	public CmdSetspawn(MinecraftRP plugin) {
		super("setspawn");
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.setspawn")){
				if(args.length == 0){
					Utils.setSpawn(p.getLocation());
				} else Functions.tell(p, "§cInvalid arguments. §f/setspawn");
			}
		}
		return true;
	}

}
