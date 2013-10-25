package kreashenz.stuntguy3000.mcrp.commands;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;
import kreashenz.stuntguy3000.mcrp.utils.MPlayer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdTpa extends ICommand {

	public CmdTpa(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.tpa")){
				MPlayer pm = MPlayer.getMPlayer(p);
				if(args.length == 1){
					Player t = Bukkit.getPlayer(args[0]);
					if(t != null){
						pm.setTeleportLocation(p.getLocation());
						pm.setTeleportTo(t);
						MPlayer.getMPlayer(t).setTeleportTo(p);
					} else Functions.unknownPlayer(p);
				} else Functions.tell(p, "§cInvalid arguments. §f/tpa <player>");
			} else Functions.noPerm(p);
		} else Functions.tell(s, "You can't teleport, you're a console!");
	}

}
