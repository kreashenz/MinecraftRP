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

public class CmdItem extends ICommand {

	public CmdItem(MinecraftRP plugin) {
		super("item");
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.i")){
				if (args.length == 0) {
					Functions.tell(p, "§cInvalid arguments. §f/i <item>[:data] <amount> [player]");
				} else {
					Player t = (args.length > 2) ? Bukkit.getPlayer(args[2]) : p;
					if(t != null){
						Integer amount = (args.length > 1) ? Functions.parseInteger(args[1]) : 64;
						if(amount != null){
							MaterialData item = ItemManager.getItem(args[0]);
							if(item != null){
								ItemStack stack = item.toItemStack(amount);
								t.getInventory().addItem(stack);
								Functions.tell(s, "§3Giving " + t.getName() + " " + stack.getAmount() + " of " + args[0] + ".");
								if (!t.getName().equals(s.getName())) {
									Functions.tell(t, "§3You have received " + stack.getAmount() + " of " + stack.getType() + " from " + s.getName() + ".");
								}
							} else Functions.tell(p, "§cUnknown item. §f/i <item>[:data] <amount> [player]");
						} else Functions.tell(p, "§cInvalid item amount. §f/i <item>[:data] <amount> [player]");
					} else Functions.unknownPlayer(p);
				}
			} else Functions.noPerm(p);
		}
		return true;
	}

}
