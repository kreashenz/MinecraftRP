package kreashenz.stuntguy3000.mcrp.chat;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class evt_AsyncPlayerChat implements Listener {

	private WordReplacer word;

	public evt_AsyncPlayerChat(){
		this.word = new WordReplacer(MinecraftRP.getInstance());
	}

	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		String msg = e.getMessage();
		if(p.hasPermission("mcrp.chat.color")) msg = ChatColor.translateAlternateColorCodes('&', msg);
		e.setFormat((word.convertChatFormat(p)).replace("{MESSAGE}", msg));
	}

}