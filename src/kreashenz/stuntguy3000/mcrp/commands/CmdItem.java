package kreashenz.stuntguy3000.mcrp.commands;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdItem extends ICommand {

	public CmdItem(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.item")){
				if(args.length == 0){
					Functions.tell(p, "§cInvalid arguments. §f/i <item>[:data] [amount] [player]");
				} else if(args.length == 1){
					String inputItem = args[0];

					if (inputItem.contains(":")) {
						try {
							String inputBefore = inputItem.split(":")[0];
							String inputAfter = inputItem.split(":")[1];

							int item = Integer.parseInt(inputBefore);
							int data = Integer.parseInt(inputAfter);

							giveItem(item, data, 64, p, p);
						} catch (IndexOutOfBoundsException | NumberFormatException ex) {
							Functions.tell(p, "§cInvalid item. §f/i <item>[:data] <amount> [player]");
						}

					} else {
						try {
							giveItem(Short.parseShort(inputItem), -1, 64, p, p);
						} catch (NumberFormatException ex) {
							Functions.tell(p, "§cInvalid item. §f/i <item>[:data] <amount> [player]");
						}
					}

				} else if(args.length == 2){
					if(isInt(args[1])){
						String inputItem = args[0];
						if (inputItem.contains(":")) {
							try {
								String inputBefore = inputItem.split(":")[0];
								String inputAfter = inputItem.split(":")[1];

								int item = Integer.parseInt(inputBefore);
								int data = Integer.parseInt(inputAfter);

								giveItem(item, data, Integer.valueOf(args[1]), p, p);
							} catch (IndexOutOfBoundsException | NumberFormatException ex) {
								Functions.tell(p, "§cInvalid item. §f/i <item>[:data] <amount> [player]");
							}

						} else {
							try {
								giveItem(Short.parseShort(inputItem), -1, Integer.valueOf(args[1]), p, p);
							} catch (NumberFormatException ex) {
								Functions.tell(p, "§cInvalid item. §f/i <item>[:data] <amount> [player]");
							}
						}
					}else Functions.tell(p, "§cInvalid arguments. §f/i <item>[:data] <amount> [player]");
				} else if(args.length == 3){
					if(isInt(args[1])){
						String inputItem = args[0];
						if (inputItem.contains(":")) {
							try {
								String inputBefore = inputItem.split(":")[0];
								String inputAfter = inputItem.split(":")[1];

								int item = Integer.parseInt(inputBefore);
								int data = Integer.parseInt(inputAfter);

								giveItem(item, data, Integer.valueOf(args[1]), Bukkit.getPlayer(args[2]), p);
							} catch (IndexOutOfBoundsException | NumberFormatException ex) {
								Functions.tell(p, "§cInvalid item. §f/i <item>[:data] <amount> [player]");
							}

						} else {
							try {
								giveItem(Short.parseShort(inputItem), -1, Integer.valueOf(args[1]), Bukkit.getPlayer(args[2]), p);
							} catch (NumberFormatException ex) {
								Functions.tell(p, "§cInvalid item. §f/i <item>[:data] <amount> [player]");
							}
						}
					}else Functions.tell(p, "§cInvalid arguments. §f/i <item>[:data] <amount> [player]");
				} 
			}
		}
	}

}
