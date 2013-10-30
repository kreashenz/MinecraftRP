package kreashenz.stuntguy3000.mcrp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;
import kreashenz.stuntguy3000.mcrp.utils.Utils;

public class CmdSetspawn extends ICommand {

	public CmdSetspawn(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.setspawn")){
				if(args.length == 0){
					Utils.setSpawn(p.getLocation());
				} else Functions.tell(p, "§cInvalid arguments. §f/setspawn");
			}
		}
	}

}
