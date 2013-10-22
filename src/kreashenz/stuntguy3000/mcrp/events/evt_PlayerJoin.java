package kreashenz.stuntguy3000.mcrp.events;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class evt_PlayerJoin implements Listener {

	private MinecraftRP plugin;

	public evt_PlayerJoin(MinecraftRP plugin){
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		e.setJoinMessage(Functions.colour(plugin.getConfig().getString("messages.join-message").replace("{PLAYER}", p.getName())));
	}

}
