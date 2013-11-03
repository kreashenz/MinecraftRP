package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.utils.Functions;
import me.kreashenz.mcrp.utils.MPlayer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdTphere extends ICommand {

	public CmdTphere(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.tphere")){
				if(args.length == 1){
					Player t = Bukkit.getPlayer(args[0]);
					if(t != null){
						if(t != p){
							if(!MPlayer.getMPlayer(t).getTPO()){
								t.teleport(p.getLocation().add(0, 1, 0));
							} else Functions.tell(p, "§6" + t.getName() + " §chas teleporting disabled");
						} else Functions.tell(p, "§cYou can't teleport to yourself.");
					} else Functions.unknownPlayer(p);
				} else Functions.tell(p, "§cInvalid arguments. §f/tphere <player>");
			} else Functions.noPerm(p);
		} else Functions.tell(s, "You can't teleport players to you, you're a console!");
	}

}
