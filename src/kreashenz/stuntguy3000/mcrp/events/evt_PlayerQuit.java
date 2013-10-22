package kreashenz.stuntguy3000.mcrp.events;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class evt_PlayerQuit implements Listener {

	private MinecraftRP plugin;

	public evt_PlayerQuit(MinecraftRP plugin){
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e){
		e.setQuitMessage(Functions.colour(plugin.getConfig().getString("messages.quit-message").replace("{PLAYER}", e.getPlayer().getName())));
	}

}
