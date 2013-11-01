package kreashenz.stuntguy3000.mcrp.chat;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;
import kreashenz.stuntguy3000.mcrp.utils.MPlayer;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class evt_AsyncPlayerChat implements Listener {

	private WordReplacer word;
	private MinecraftRP plugin;

	public evt_AsyncPlayerChat(MinecraftRP plugin){
		this.plugin = plugin;
		this.word = new WordReplacer(plugin);
	}

	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		String msg = e.getMessage();
		if(p.hasPermission("mcrp.chat.color")) msg = Functions.colour(msg);
		e.setFormat((word.convertFormat(p)).replace("{MESSAGE}", msg));
		if(MPlayer.getMPlayer(p).inAdminChat()){
			e.setCancelled(true);
			for(Player ps : plugin.getServer().getOnlinePlayers()){
				if(ps.hasPermission("mcrp.adminchat")){
					Functions.tell(ps, Functions.colour(plugin.getConfig().getString("messages.admin-chat").replace("{MESSAGE}", msg)));
				}
			}
		}
	}

}