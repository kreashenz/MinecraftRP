package me.kreashenz.mcrp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.utils.Functions;

public class CmdFly extends ICommand {

	public CmdFly(MinecraftRP plugin) {
		super("fly");
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.fly")){
				if(args.length == 0){
					if(p.getAllowFlight()){
						p.setAllowFlight(false);
						Functions.tell(p, "§6You have §cdisabled §6fly");
					} else {
						p.setAllowFlight(true);
						Functions.tell(p, "§6You have §cenabled §6fly");
					}
				} else {
					Player t = Bukkit.getPlayer(args[0]);
					if(t != null){
						if(t.getAllowFlight()){
							t.setAllowFlight(false);
							Functions.tell(p, "§c" + t.getName() + " §6has no fly mode");
						} else {
							t.setAllowFlight(true);						
							Functions.tell(p, "§c" + t.getName() + " §6has fly mode");
						}
					} else Functions.unknownPlayer(p);
				}
			} else Functions.noPerm(p);
		}
		return true;
	}

}
