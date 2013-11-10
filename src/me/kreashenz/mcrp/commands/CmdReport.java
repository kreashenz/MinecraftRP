package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.events.custom.PlayerReportEvent;
import me.kreashenz.mcrp.utils.Functions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdReport extends ICommand {

	public CmdReport(MinecraftRP plugin) {
		super("report");
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.report")){
				if(args.length == 0){
					Functions.tell(p, "§cInvalid arguments. §f/report <reason>");
				} else {
					String reason = "";
					for (int i = 0; i < args.length; i++) {
						reason = reason + args[i] + ' ';
					}
					PlayerReportEvent e = new PlayerReportEvent(p, reason);
					plugin.getServer().getPluginManager().callEvent(e);
					Functions.tell(p, "§6Successfully sent the report to admins.");
				}
			}
		}
		return true;
	}

}
