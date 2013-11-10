package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.utils.Functions;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdClear extends ICommand {

	public CmdClear(MinecraftRP plugin) {
		super("clear");
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.clear")){
				if(args.length == 0){
					p.getInventory().clear();
					Functions.tell(p, "§6Cleared your inventory");
				} else {
					Player t = Bukkit.getPlayer(args[0]);
					if(t != null){
						t.getInventory().clear();
						Functions.tell(p, "§6Cleared §c" + t.getName() + "§6's inventory");
						Functions.tell(t, "§6Cleared your inventory");
					} else Functions.unknownPlayer(p);
				}
			} else Functions.noPerm(p);
		}
		return true;
	}

}
