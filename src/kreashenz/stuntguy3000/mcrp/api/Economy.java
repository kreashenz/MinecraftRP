package kreashenz.stuntguy3000.mcrp.api;

import kreashenz.stuntguy3000.mcrp.utils.MPlayer;

import org.bukkit.entity.Player;

public class Economy {

	public static void addMoney(Player p, double amount){
		MPlayer pm = MPlayer.getMPlayer(p);
		double d = pm.getPlayerConfig().getInt("economy.balance");
		pm.set("economy.balance", amount + d);
	}
	
}
