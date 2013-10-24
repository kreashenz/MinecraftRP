package kreashenz.stuntguy3000.mcrp.chat;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class evt_AsyncPlayerChat implements Listener {

	private WordReplacer word;
	private MinecraftRP plugin; // Unused

	public evt_AsyncPlayerChat(MinecraftRP plugin){
		this.plugin = plugin;
		this.word = new WordReplacer(plugin);
	}

	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e){
		e.setFormat((word.convertChatFormat(e.getPlayer())).replace("{MESSAGE}", e.getMessage()));
	}

}