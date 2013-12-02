package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.utils.Functions;
import me.kreashenz.mcrp.utils.MPlayer;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSethome extends ICommand {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			MPlayer pm = MPlayer.getMPlayer(p);
			if(p.hasPermission("mcrp.sethome")){
				if(args.length == 1){
					Location oldHome = new Location(pm.getHome(args[0]).getWorld(), pm.getHome(args[0]).getBlockX(), pm.getHome(args[0]).getBlockY(), pm.getHome(args[0]).getBlockZ());
					String str = "§6world = §c" + oldHome.getWorld().getName() + "§6, x = §c" + oldHome.getBlockX() + "§6, y = " + oldHome.getBlockY() + "§6, z = " + oldHome.getBlockZ();
					if(pm.homeExists(args[0])){
						Functions.tell(p, "§6Overrided the home with the same name set the location: " + str);
					} else Functions.tell(p, "§6Set home §c" + args[0] + "§6!");
					pm.saveHome(args[0], p.getLocation());
				} else Functions.tell(p, "§cInvalid arguments. §f/sethome <name>");
			} else Functions.noPerm(p);
		}
		return true;
	}

}
