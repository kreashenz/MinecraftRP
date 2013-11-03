package kreashenz.stuntguy3000.mcrp.commands;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;
import kreashenz.stuntguy3000.mcrp.utils.stuff.Enchants;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class CmdEnchant extends ICommand {

	public CmdEnchant(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.enchant")){
				if(args.length == 0 || args.length > 2){
					StringBuilder strB = new StringBuilder();
					for(String str : Enchants.getAll()){
						if(strB.length() > 0){
							strB.append(",");
						}
						strB.append("§c" + str + "§7");
						Functions.tell(p, "§6Available enchantments: " + strB.toString());
					}
					Functions.tell(p, "§cInvalid arguments. §f/enchant <enchantment> [level]");
				} else {
					Enchantment ench = Enchants.get(args[0]);
					int level = (args.length == 2 ? Functions.parseInteger(args[1]) : 1);
					if(ench != null){
						if(p.getItemInHand().getType() != Material.AIR){
							p.getInventory().getItemInHand().addUnsafeEnchantment(ench, level);
							Functions.tell(p, "§6Successfully enchanted §c" + ench.getName() + " §6on your current item");
						} else Functions.tell(p, "§cYou can't enchant your hand, silly!");
					} else Functions.tell(p, "§cUnknown enchantment. §f/enchant <enchantment> [level]");
				}
			} else Functions.noPerm(p);
		}
	}

}
