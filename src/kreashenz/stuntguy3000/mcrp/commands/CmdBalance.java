package kreashenz.stuntguy3000.mcrp.commands;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;
import kreashenz.stuntguy3000.mcrp.utils.MPlayer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdBalance extends ICommand {

	public CmdBalance(MinecraftRP plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender s, Command cmd, String[] args) {
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
	}

}
