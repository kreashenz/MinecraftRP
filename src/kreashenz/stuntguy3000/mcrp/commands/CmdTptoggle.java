package kreashenz.stuntguy3000.mcrp.commands;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;
import kreashenz.stuntguy3000.mcrp.utils.MPlayer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdTptoggle extends ICommand {

	public CmdTptoggle(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			MPlayer pm = MPlayer.getMPlayer(p);
			if(p.hasPermission("mcrp.tptoggle")){
				if(args.length == 0){
					if(pm.getTPO()){
						pm.setTPO(false);
						Functions.tell(p, "§6You have §cenabled §6teleporting");
					} else {
						pm.setTPO(true);
						Functions.tell(p, "§6You have §cdisabled §6teleporting");
					}
				} else Functions.tell(p, "§cInvalid arguments. §f/tptoggle");
			} else Functions.noPerm(p);
		} else Functions.tell(s, "You can't toggle teleporting when you can't teleport!");
	}

}
