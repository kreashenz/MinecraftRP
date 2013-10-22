package kreashenz.stuntguy3000.mcrp.commands;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdKick extends ICommand {

	public CmdKick(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.kick")){
				if(args.length == 0){
					Functions.tell(p, "§cInvalid arguments! Usage §f/kick <player> [reason]");
					return;
				} else {
					Player t = Bukkit.getPlayer(args[0]);
					if(t == null){
						Functions.tell(p, "§cThat player isn't found!");
						return;
					}
					
					if(t.hasPermission("mcrp.kick.bypass")){
						Functions.tell(p, "§cYou can't kick that player.");
						return;
					}

					if(args.length == 1){
						t.kickPlayer("§cYou were kicked by an server admin.");
					} else {
						kick(t, args, false);
					}
				}
			} else Functions.noPerm(p);
		} else {
			if(args.length == 0){
				Functions.tell(s, "§cInvalid arguments! Usage §f/kick <player> [reason]");
			} else {
				Player t = Bukkit.getPlayer(args[0]);
				if(t == null){
					Functions.tell(s, "§cThat player isn't found!");
					return;
				}
				if(args.length == 1){
					t.kickPlayer("§cYou were kicked by Console.");
				} else {
					kick(t, args, false);
				}
			}
		}
	}
}