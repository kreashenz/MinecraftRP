package kreashenz.stuntguy3000.mcrp.commands;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdGive extends ICommand {

	public CmdGive(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
		}
	}

}
