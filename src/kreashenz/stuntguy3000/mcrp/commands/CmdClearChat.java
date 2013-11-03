package kreashenz.stuntguy3000.mcrp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;

public class CmdClearChat extends ICommand {

	public CmdClearChat(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.clearchat")){
				for(int i = 0; i <= 77; i++){
					for(Player ps : Bukkit.getOnlinePlayers()){
						ps.sendMessage("");
						Functions.tell(ps, "§6=== Chat cleared by §b" + p.getName() + " §6===");
					}
				}
			}
		}
	}

}
