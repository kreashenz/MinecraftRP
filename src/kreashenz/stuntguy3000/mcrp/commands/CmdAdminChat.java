package kreashenz.stuntguy3000.mcrp.commands;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.chat.WordReplacer;
import kreashenz.stuntguy3000.mcrp.utils.Functions;
import kreashenz.stuntguy3000.mcrp.utils.MPlayer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdAdminChat extends ICommand {

	public CmdAdminChat(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.adminchat")){
				MPlayer pm = MPlayer.getMPlayer(p);
				if(args.length == 0){
					if(pm.inAdminChat()){
						pm.setAdminChat(false);
						Functions.tell(p, "§6You have §cenabled §6admin chat");
					} else {
						pm.setAdminChat(true);
					}
				} else {
					String msg = "";
					for(int i = 0; i < args.length; i++){
						msg = msg + args[i] + ' ';
					}
					for(Player ps : plugin.getServer().getOnlinePlayers()){
						if(ps.hasPermission("mcrp.adminchat")){
							Functions.tell(ps, Functions.colour(new WordReplacer(plugin).convertFormat(plugin.getConfig().getString("messages.admin-chat").replace("{MESSAGE}", msg), p)));
						}
					}
				}
			}
		}
	}

}
