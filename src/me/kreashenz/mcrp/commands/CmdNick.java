package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.utils.Functions;
import me.kreashenz.mcrp.utils.MPlayer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdNick extends ICommand {

	public CmdNick(MinecraftRP plugin) {
		super("nick");
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.nick")){
				if(args.length == 0){
					Functions.tell(p, "§cInvalid arguments. §f/nick <nickname> [player]");
				} else if(args.length == 1){
					if(isNickValid(args[0])){
						setNickname(p, args[0]);
					} else Functions.tell(p, "§cSome characters in your nickname are illegal.");
				} else if(args.length == 2){
					Player t = Bukkit.getPlayer(args[1]);
					if(t != null){
						if(isNickValid(args[0])){
							setNickname(t, args[0]);
						} else Functions.tell(p, "§cSome characters in your nickname are illegal.");
					} else Functions.unknownPlayer(p);
				} else Functions.tell(p, "§cInvalid arguments. §f/nick <nickname> [player]");
			} else Functions.noPerm(p);
		} else {
			if(args.length == 2){	
				Player t = Bukkit.getPlayer(args[1]);
				if(isNickValid(args[0])){
					if(t != null){
						setNickname(t, args[0]);
					} else Functions.unknownPlayer(s);
				} else Functions.tell(s, "§cSome characters in your nickname are illegal.");
			} else Functions.tell(s, "Invalid arguments. §f/nick <nickname> <player>");
		}
		return true;
	}

	private boolean isNickValid(String nick){
		return nick.matches("^&[a-zA-Z_0-9\u00a7]+$");
	}

	private void setNickname(Player p, String nick){
		p.setDisplayName(nick);
		MPlayer pm = MPlayer.getMPlayer(p);
		pm.set("nickname", nick);
		Functions.tell(p, "§6Your new nickname is: " + nick);
	}

}
