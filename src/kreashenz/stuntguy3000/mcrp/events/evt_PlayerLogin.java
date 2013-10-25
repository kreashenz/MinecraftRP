package kreashenz.stuntguy3000.mcrp.events;

import kreashenz.stuntguy3000.mcrp.utils.MPlayer;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class evt_PlayerLogin implements Listener {

	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e){
		Player p = e.getPlayer();
		MPlayer pm = MPlayer.getMPlayer(p);
		if(p.isBanned()){
			e.setResult(Result.KICK_BANNED);
			e.setKickMessage("§4Banned! §c" + pm.getConfig().getString("ban-reason"));
		}
		if(pm.getConfig().contains("nickname")){
			p.setDisplayName(pm.getConfig().getString("nickname"));
		}
	}

}
