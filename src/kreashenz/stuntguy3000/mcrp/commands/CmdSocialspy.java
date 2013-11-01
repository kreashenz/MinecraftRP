package kreashenz.stuntguy3000.mcrp.commands;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;
import kreashenz.stuntguy3000.mcrp.utils.MPlayer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSocialspy extends ICommand {

	public CmdSocialspy(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			MPlayer pm = MPlayer.getMPlayer(p);
			if(p.hasPermission("mcrp.socialspy")){
				if(args.length == 0){
					if(plugin.getConfig().getBoolean("socialspy-listen-all") == true){
						if(pm.isSocialSpying()){
							Functions.tell(p, "§6You have disabled global socialspy (Nobody is being socialspyed)");
							pm.setSocialSpying(false);
						} else {
							Functions.tell(p, "§6You have enabled global socialspy");
							pm.setSocialSpying(true);
						}
					} else Functions.tell(p, "§cInvalid arguments. §f/socialspy <player>");
				} else {
					Player t = Bukkit.getPlayer(args[0]);
					if(t != null){
						pm.setSpying(t);
					} else Functions.unknownPlayer(p);
				}
			} else Functions.noPerm(p);
		}
	}

}
