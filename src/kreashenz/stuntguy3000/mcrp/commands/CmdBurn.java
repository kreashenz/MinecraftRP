package kreashenz.stuntguy3000.mcrp.commands;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdBurn extends ICommand {

	public CmdBurn(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.burn")){
				if(args.length != 2){
					Functions.tell(p, "§cInvalid arguments. §f/burn <player> <seconds>");
				} else {
					Player t = Bukkit.getPlayer(args[0]);
					int i = Functions.parseInteger(args[1]);
					if(t != null){
						t.setFireTicks(i * 20);
						Functions.tell(p, "§6Set §c" + t.getName() + " §6on fire for §c" + i + " §6seconds");
					} else Functions.unknownPlayer(p);
				}
			} else Functions.noPerm(s);
		}
	}

}
