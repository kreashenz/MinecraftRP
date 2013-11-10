package me.kreashenz.mcrp.events;

import me.kreashenz.mcrp.utils.Functions;
import me.kreashenz.mcrp.utils.MPlayer;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class evt_PlayerMove implements Listener {
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		MPlayer pm = MPlayer.getMPlayer(p);
		Location to = new Location(e.getTo().getWorld(), e.getTo().getX(), e.getTo().getY(), e.getTo().getZ());
		if(pm.hasTPRequested() && pm.getTeleportLocation() != null){
			Location tpLoc = pm.getTeleportLocation();
			if(tpLoc != to){
				// Cancel teleports.
				Functions.tell(p, "§cTeleporting cancelled.");
			}
		}
	}

}
