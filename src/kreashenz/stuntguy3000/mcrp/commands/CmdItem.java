package kreashenz.stuntguy3000.mcrp.commands;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;

import kreashenz.stuntguy3000.mcrp.utils.stuff.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class CmdItem extends ICommand {

	public CmdItem(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
        if(s instanceof Player){
            Player p = (Player)s;
            if(p.hasPermission("mcrp.i")){
                if (args.length == 0) {
                    Functions.tell(p, "§cInvalid arguments. §f/i <item>[:data] <amount> [player]");
                } else {
                    Player target = (args.length > 2) ? Bukkit.getPlayer(args[2]) : p;
                    if (target == null) {
                        Functions.unknownPlayer(p);
                    } else {
                        Integer amount = (args.length > 1) ? Functions.parseInteger(args[1]) : 64;
                        if (amount == null) {
                            Functions.tell(p, "§cInvalid item amount. §f/i <item>[:data] <amount> [player]");
                        } else {
                            MaterialData item = ItemManager.getItem(args[0]);
                            if (item == null) {
                                Functions.tell(p, "§cUnknown item. §f/i <item>[:data] <amount> [player]");
                            } else {
                                ItemStack stack = item.toItemStack(amount);

                                target.getInventory().addItem(stack);

                                Functions.tell(s, "§3Giving " + target.getName() + " " + stack.getAmount() + " of " + stack.getType() + ".");
                                if (!target.getName().equals(s.getName())) {
                                    Functions.tell(target, "§3You have received " + stack.getAmount() + " of " + stack.getType() + " from " + s.getName() + ".");
                                }
                            }
                        }
                    }
                }
            } else {
                Functions.noPerm(p);
            }
        }
	}

}
