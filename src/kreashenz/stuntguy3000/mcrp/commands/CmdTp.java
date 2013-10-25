package kreashenz.stuntguy3000.mcrp.commands;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;
import kreashenz.stuntguy3000.mcrp.utils.MPlayer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdTp extends ICommand {

	public CmdTp(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.tp")){
				if(args.length == 1){
					Player t = Bukkit.getPlayer(args[0]);
					if(t != null){
						if(t != p){
							if(!MPlayer.getMPlayer(t).getTPO()){
								p.teleport(t.getLocation().add(0, 1, 0));
							} else Functions.tell(p, "§6" + t.getName() + " §chas teleporting disabled");
						} else Functions.tell(p, "§cYou can't teleport to yourself.");
					} else Functions.unknownPlayer(p);
				} else Functions.tell(p, "§cInvalid arguments. §f/tp <player>");
			} else Functions.noPerm(p);
		} else Functions.tell(s, "You're a console, you can't teleport... :)");
	}

}
