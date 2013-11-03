package kreashenz.stuntguy3000.mcrp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;

public class CmdEnderchestClear extends ICommand {

	public CmdEnderchestClear(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.enderchestclear")){
				if(args.length == 1){
					Player t = Bukkit.getPlayer(args[0]);
					if(t != null){
						t.getEnderChest().clear();
						Functions.tell(p, "§6Cleared §c" + t.getName() + "§6's enderchest");
						Functions.tell(t, "§c" + p.getName() + " §6cleared your enderchest");
					} else Functions.unknownPlayer(p);
				} else Functions.tell(p, "§cInvalid arguments. §f/ecc <player>");
			} else Functions.noPerm(p);
		}
	}

}
