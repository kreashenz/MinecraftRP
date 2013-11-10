package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.utils.Functions;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CmdEnderchest extends ICommand {

	public CmdEnderchest(MinecraftRP plugin) {
		super("enderchest");
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.enderchest")){
				if(args.length == 0){
					p.openInventory(p.getEnderChest());
					Functions.tell(p, "§6Viewing your own enderchest.");
				} else {
					Player t = Bukkit.getPlayer(args[0]);
					if(t != null){
						p.openInventory(t.getEnderChest());
						Functions.tell(p, "§6Viewing §c" + t.getName() + "§6's enderchest.");
					} else Functions.unknownPlayer(p);
				}
			} else Functions.noPerm(p);
		}
		return true;
	}

}
