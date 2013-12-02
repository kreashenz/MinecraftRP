package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.utils.Functions;
import me.kreashenz.mcrp.utils.MPlayer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdTpaccept extends ICommand {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			MPlayer pm = MPlayer.getMPlayer(p);
			if(p.hasPermission("mcrp.tpaccept")){
				if(args.length == 0){
					if(pm.getTeleportPlayer() != null){

					} else Functions.tell(p, "§cYou don't have anyone requesting to teleport to you");
				} else Functions.tell(p, "§cInvalid arguments. §f/tpaccept");
			}
		}
		return true;
	}

}
