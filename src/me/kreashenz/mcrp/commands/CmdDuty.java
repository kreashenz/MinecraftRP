package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.utils.Functions;
import me.kreashenz.mcrp.utils.MPlayer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CmdDuty extends ICommand {

	public CmdDuty(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.duty")){
				MPlayer pm = MPlayer.getMPlayer(p);
				if(args.length == 0){
					if(pm.isOnDuty()){
						pm.setDuty(false);
						Functions.tell(p, "§6You have §cdisabled §6duty mode. You will not recieve reports.");
					} else {
						pm.setDuty(true);
						Functions.tell(p, "§6You have §cenabled §6duty mode. You will now recieve reports.");
					}
				} else Functions.tell(p, "§cInvalid arguments. §f/duty");
			} else Functions.noPerm(p);
		}
	}

}
