package kreashenz.stuntguy3000.mcrp.commands;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;
import kreashenz.stuntguy3000.mcrp.utils.stuff.ItemNames;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CmdItem extends ICommand {

	public CmdItem(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.i")){
				if(args.length == 0){
					Functions.tell(p, "§cInvalid arguments. §f/i <item>[:data] <amount> [player]");
				} else if(args.length == 1){
					if(isInt(args[0])){
						ItemStack i = new ItemStack(Material.getMaterial(args[0]), 64);
						p.getInventory().addItem(i);
					} else {
						ItemStack i = new ItemStack(ItemNames.getByName(args[0]), 64);
						p.getInventory().addItem(i);
					}
				} else if(args.length == 2){
					if(isInt(args[0])){
						if(isInt(args[1])){
							
						} else {
							
						}
					} else {
						
					}
				}
			}
		}
	}

}
