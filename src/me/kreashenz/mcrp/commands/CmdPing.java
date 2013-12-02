package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.utils.Functions;
import me.kreashenz.mcrp.utils.TPSHandler;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdPing extends ICommand {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.ping")){
				if(args.length == 0){
					Functions.tell(p, "§c" + p.getName() + "§6's ping is: §c" + TPSHandler.getPing(p));
				} else {
					Player t = Bukkit.getPlayer(args[0]);
					if(t != null){
						Functions.tell(p, "§c" + t.getName() + "§6's ping is: §c" + TPSHandler.getPing(t));
					} else Functions.unknownPlayer(p);
				}
			} else Functions.noPerm(p);
		}
		return true;
	}

}
