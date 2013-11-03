package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.utils.Functions;
import me.kreashenz.mcrp.utils.stuff.ItemManager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class CmdGive extends ICommand {

	public CmdGive(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.give")){
				if(args.length < 2 || args.length > 3){
					Functions.tell(p, "§cInvalid arguments. §f/give <player> <item>[:data] <amount>");
				} else {
					Player t = Bukkit.getPlayer(args[0]);
					if(t != null){
						Integer amount = (args.length > 1) ? Functions.parseInteger(args[1]) : 64;
						MaterialData md = ItemManager.getItem(args[2]);
						if(md != null){
							ItemStack i = md.toItemStack(amount);
							t.getInventory().addItem(i);
							Functions.tell(s, "§3Giving " + t.getName() + " " + i.getAmount() + " of " + i.getType() + ".");
							if (!t.getName().equals(s.getName())) {
								Functions.tell(t, "§3You have received " + i.getAmount() + " of " + i.getType() + " from " + s.getName() + ".");
							}
						} else Functions.tell(p, "§cUnknown item. §f/give <player> <item>[:data] <amount>");
					} else Functions.unknownPlayer(p);
				}
			} else Functions.noPerm(p);
		}
	}

}
