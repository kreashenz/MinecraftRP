package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.utils.Functions;
import me.kreashenz.mcrp.utils.MPlayer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CmdTpa extends ICommand {

	public CmdTpa(MinecraftRP plugin) {
		super("tpa");
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.tpa")){
				final MPlayer pm = MPlayer.getMPlayer(p);
				if(args.length == 1){
					Player t = Bukkit.getPlayer(args[0]);
					if(t != null){
						final MPlayer tm = MPlayer.getMPlayer(t);
						pm.setTeleportTo(t);
						tm.setTeleportTo(p);
						Functions.tell(p, "§6Teleport request sent");
						Functions.tell(t, "§c" + p.getName() + " §6has requested to teleport to you. Use §c/tpaccept §6to accept. §c" + plugin.getConfig().getInt("teleport-time-out") + " seconds §6till request times out.");
						new BukkitRunnable(){
							public void run(){
								pm.setTeleportTo(null);
								tm.setTeleportTo(null);
							}
						}.runTaskLater(plugin, plugin.getConfig().getInt("teleport-time-out"));
					} else Functions.unknownPlayer(p);
				} else Functions.tell(p, "§cInvalid arguments. §f/tpa <player>");
			} else Functions.noPerm(p);
		} else Functions.tell(s, "You can't teleport, you're a console!");
		return true;
	}

}
