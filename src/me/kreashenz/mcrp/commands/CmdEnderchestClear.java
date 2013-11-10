package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.utils.Functions;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdEnderchestClear extends ICommand {

	public CmdEnderchestClear(MinecraftRP plugin) {
		super("enderchestClear");
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.enderchestclear")){
				if(args.length == 1){
					Player t = Bukkit.getPlayer(args[0]);
					if(t != null){
						t.getEnderChest().clear();
						Functions.tell(p, "§6Cleared §c" + t.getName() + "§6's enderchest");
						Functions.tell(t, "§c" + p.getName() + " §6cleared your enderchest");
					} else Functions.unknownPlayer(p);
				} else Functions.tell(p, "§cInvalid arguments. §f/ecc <player>");
			} else Functions.noPerm(p);
		}
		return true;
	}

}
