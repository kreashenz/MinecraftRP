package kreashenz.stuntguy3000.mcrp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;

public class CmdExt extends ICommand {

	public CmdExt(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.extinguish")){
				if(args.length == 0){
					if(p.getFireTicks() != 0){
						p.setFireTicks(0);
					} else Functions.tell(p, "§cYou aren't on fire.");
				} else {
					if(p.hasPermission("mcrp.extinguish.others")){
						Player t = Bukkit.getPlayer(args[0]);
						if(t != null){
							if(t.getFireTicks() != 0){
								t.setFireTicks(0);
							} else Functions.tell(p, "§c" + t.getName() + " isn't on fire.");
						} else Functions.unknownPlayer(p);
					} else Functions.noPerm(p);
				}
			} else Functions.noPerm(p);
		}
	}

}
