package me.kreashenz.mcrp.commands;

import me.kreashenz.mcrp.utils.Functions;
import me.kreashenz.mcrp.utils.MPlayer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdBalance extends ICommand {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String labal, String[] args){
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.balance")){
				if(args.length == 0){
					MPlayer pm = MPlayer.getMPlayer(p);
					Functions.tell(p, "§6Balance: §c" + pm.getMoney());
				} else {
					if(p.hasPermission("mcrp.balance.others")){
						Player t = Bukkit.getPlayer(args[0]);
						if(t != null){
							MPlayer tm = MPlayer.getMPlayer(t);
							Functions.tell(p, "§c" + t.getName() + "§6's balance: §c" + tm.getMoney());
						} else Functions.noPerm(p);
					} else Functions.noPerm(p);
				}
			} else Functions.noPerm(p);
		}
		return true;
	}
}
