package kreashenz.stuntguy3000.mcrp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;

public class CmdSpawn extends ICommand {

	public CmdSpawn(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.spawn")){
				if(args.length == 0){
					// Hmmmm..
				}
			} else Functions.noPerm(p);
		}
	}

}
