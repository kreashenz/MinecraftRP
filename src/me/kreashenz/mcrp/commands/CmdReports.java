package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.utils.Functions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdReports extends ICommand {

	public CmdReports(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.reports")){
				if(args.length == 0){
					if(plugin.reports.size() != 0){
						for(int i = 1; i < plugin.reports.size(); i++){
							Functions.tell(p, "§6" + i + " - " + plugin.reports.get(i));
						}
					} else Functions.tell(p, "§cThere's no reports.");
				} else if(args.length == 1){
					if(args[0].equalsIgnoreCase("clear")){
						plugin.reports.clear();
						Functions.tell(p, "§6Successfully cleared the reports list");
					} else Functions.tell(p, "§cUnknown sub command. §f/report [clear]");
				} else Functions.tell(p, "§cInvalid arguments. §f/report [clear]");
			} else Functions.noPerm(p);
		}
	}

}
