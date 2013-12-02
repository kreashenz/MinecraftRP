package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.utils.Functions;
import me.kreashenz.mcrp.utils.MPlayer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdHome extends ICommand {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.home")){
				MPlayer pm = MPlayer.getMPlayer(p);
				if(args.length == 0){
					Functions.splitObject(pm.getHomes(), '6', 'c');
				}
			}
		}
		return true;
	}

}
