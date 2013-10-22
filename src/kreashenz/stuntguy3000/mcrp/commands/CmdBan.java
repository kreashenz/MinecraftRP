package kreashenz.stuntguy3000.mcrp.commands;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;
import kreashenz.stuntguy3000.mcrp.utils.MPlayer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdBan extends ICommand {

	public CmdBan(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.ban")){
				if(args.length == 0){
					Functions.tell(p, "§cInvalid arguments! Usage §f/ban <player> [reason]");
				} else {
					Player t = Bukkit.getPlayer(args[0]);
					if(t == null){
						Functions.tell(p, "§cThat player isn't found!");
						return;
					}

					if(args.length == 1){
						t.kickPlayer("§cYou were banned by an server admin.");
						t.setBanned(true);
					} else {
						kick(t, args, true);
						MPlayer.getMPlayer(t).set("ban.banner", p.getName());
						// Figure a way to get the date/time/whatever MPlayer.getMPlayer(t).set("ban.date", "");
					}
				}
			} else Functions.noPerm(p);
		} else {
			if(args.length == 0){
				Functions.tell(s, "§cInvalid arguments! Usage §f/ban <player> [reason]");
			} else {
				Player t = Bukkit.getPlayer(args[0]);
				if(t == null){
					Functions.tell(s, "§cThat player isn't found!");
					return;
				}

				if(args.length == 1){
					t.kickPlayer("§cYou were banned by Console.");
					t.setBanned(true);
				} else {
					kick(t, args, true);
					MPlayer.getMPlayer(t).set("ban.banner", s.getName());
					// Figure a way to get the date/time/whatever MPlayer.getMPlayer(t).set("ban.date", "");
				}
			}
		}
	}
}