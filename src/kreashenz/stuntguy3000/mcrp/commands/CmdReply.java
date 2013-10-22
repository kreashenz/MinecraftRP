package kreashenz.stuntguy3000.mcrp.commands;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;
import kreashenz.stuntguy3000.mcrp.utils.MPlayer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdReply extends ICommand {

	public CmdReply(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			MPlayer pm = MPlayer.getMPlayer(p);
			if(p.hasPermission("mcrp.reply")){
				if(args.length > 0){
					if(pm.getReplyTo() != null){
						CommandSender reply = pm.getReplyTo();
						message(p, reply, args, true);
					} else Functions.tell(p, "§cYou don't have anyone to reply to.");
				} else Functions.tell(p, "§cInvalid arguments. §f/reply <msg>");
			} else Functions.noPerm(p);
		}
	}

}