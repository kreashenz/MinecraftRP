package kreashenz.stuntguy3000.mcrp.commands;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CmdBroadcast extends ICommand {

	public CmdBroadcast(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s.hasPermission("mcrp.broadcast")){
			if(args.length == 0){
				Functions.tell(s, "§cInvalid arguments. §f/broadcast <msg>");
			} else {
				String m = "";
				for(int i = 0; i <= args.length; i++){
					m = m + args[i] + ' ';
					m = Functions.colour(m);
				}
				plugin.getServer().broadcastMessage("§6[Broadcast] " + m);
			}
		} else Functions.noPerm(s);
	}

}
