package me.kreashenz.mcrp.commands;

import java.util.ArrayList;
import java.util.List;

import me.kreashenz.mcrp.utils.Functions;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class CmdFix extends ICommand {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			PlayerInventory pi = p.getInventory();
			if(p.hasPermission("mcrp.fix")){
				if(args.length == 0){
					if(pi.getItemInHand() != null || pi.getItemInHand().getType() != Material.AIR){
						pi.getItemInHand().setDurability((short)0);
					} else Functions.tell(p, "§cYou can't repair your hand..");
				} else if(args.length == 1){
					if(args[0].equalsIgnoreCase("all")){
						if(isFixable(pi)){
							fix(pi);
						} else Functions.tell(p, "§cEverything is repaired already!");
					} else Functions.tell(p, "§cInvalid arguments. §f/fix [all]");
				} else Functions.tell(p, "§cInvalid arguments. §f/fix [all]");
			} else Functions.noPerm(p);
		}
		return true;
	}

	private boolean isFixable(PlayerInventory pi){
		for(ItemStack i : getContents(pi)){
			if(i != null){
				if(i.getDurability() > 0){
					return true;
				}
			}
		}
		return false;
	}

	private void fix(PlayerInventory pi){
		for(ItemStack i : getContents(pi)){
			i.setDurability((short)0);
			Functions.tell((Player)pi.getHolder(), "§6Successfully fixed your inventory.");
		}
	}

	private List<ItemStack> getContents(PlayerInventory pi){
		List<ItemStack> all = new ArrayList<ItemStack>();
		for(ItemStack a : pi.getArmorContents())if(a != null)all.add(a);
		for(ItemStack a : pi.getContents())if(a != null)all.add(a);
		return all;
	}

}
