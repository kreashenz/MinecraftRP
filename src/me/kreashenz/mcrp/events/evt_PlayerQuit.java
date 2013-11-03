package me.kreashenz.mcrp.events;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.utils.Functions;
import me.kreashenz.mcrp.utils.MPlayer;

import org.bukkit.entity.Player;
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
		Player p = e.getPlayer();
		e.setQuitMessage(Functions.colour(plugin.getConfig().getString("messages.quit-message").replace("{PLAYER}", p.getName())));
		MPlayer.getMPlayer(p).releaseManager();
	}

}
