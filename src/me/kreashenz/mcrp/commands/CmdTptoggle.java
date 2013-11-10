package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.utils.Functions;
import me.kreashenz.mcrp.utils.MPlayer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdTptoggle extends ICommand {

	public CmdTptoggle(MinecraftRP plugin) {
		super("tptoggle");
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			MPlayer pm = MPlayer.getMPlayer(p);
			if(p.hasPermission("mcrp.tptoggle")){
				if(args.length == 0){
					if(pm.getTPO()){
						pm.setTPO(false);
						Functions.tell(p, "§6You have §cenabled §6teleporting");
					} else {
						pm.setTPO(true);
						Functions.tell(p, "§6You have §cdisabled §6teleporting");
					}
				} else Functions.tell(p, "§cInvalid arguments. §f/tptoggle");
			} else Functions.noPerm(p);
		} else Functions.tell(s, "You can't toggle teleporting when you can't teleport!");
		return true;
	}

}
