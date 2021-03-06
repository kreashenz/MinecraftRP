package me.kreashenz.mcrp.events;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.chat.WordReplacer;
import me.kreashenz.mcrp.utils.Functions;
import me.kreashenz.mcrp.utils.MPlayer;

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
		MPlayer pm = MPlayer.getMPlayer(p);
		if(!p.hasPlayedBefore()) e.setJoinMessage(new WordReplacer(plugin).convertFormat("messages.new-player", p));
		else e.setJoinMessage(Functions.colour(plugin.getConfig().getString("messages.join-message").replace("{PLAYER}", p.getName())));
		if(pm.isOnDuty())plugin.dutyAdmins.add(p.getName());
	}

}
