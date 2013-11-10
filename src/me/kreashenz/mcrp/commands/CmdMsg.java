package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.utils.Functions;
import me.kreashenz.mcrp.utils.MPlayer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdMsg extends ICommand {

	public CmdMsg(MinecraftRP plugin) {
		super("msg");
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.msg")){
				if(args.length <= 1){
					Functions.tell(p, "§cInvalid arguments. Usage: §f/msg <player> <msg>");
				} else {
					Player t = Bukkit.getPlayer(args[0]);
					if(t != null){
						message(p, t, args, false);

						MPlayer.getMPlayer(p).setReplyTo(t);
						MPlayer.getMPlayer(t).setReplyTo(p);
					} else Functions.unknownPlayer(p);
				}
			} else Functions.noPerm(p);
		}
		return true;
	}

}
