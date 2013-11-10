package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.utils.Functions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CmdBroadcast extends ICommand {

	public CmdBroadcast(MinecraftRP plugin) {
		super("broadcast");
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s.hasPermission("mcrp.broadcast")){
			if(args.length == 0){
				Functions.tell(s, "§cInvalid arguments. §f/broadcast <msg>");
			} else {
				String m = "";
				for(int i = 0; i < args.length; i++){
					m = m + args[i] + ' ';
				}
				plugin.getServer().broadcastMessage("§6[Broadcast] " + Functions.colour(m));
			}
		} else Functions.noPerm(s);
		return true;
	}

}
