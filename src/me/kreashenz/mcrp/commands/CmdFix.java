package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.MinecraftRP;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class CmdFix extends ICommand {

	public CmdFix(MinecraftRP plugin) {
		super("fix");
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			PlayerInventory pi = p.getInventory();
			if(p.hasPermission("mcrp.fix")){
				if(args.length == 0){
					
				}
			}
		}
		return true;
	}
	
	private boolean isFixable(){
		for(Material mats : Material.values()){
			if(!(mats.isBlock() || mats.isEdible() || mats == null || mats == Material.AIR)){
				return true;
			}
		}
		return false;
	}

}
