package kreashenz.stuntguy3000.mcrp.events;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;
import kreashenz.stuntguy3000.mcrp.utils.MPlayer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class evt_PlayerCommandPreprocess implements Listener {

	private MinecraftRP plugin;

	public evt_PlayerCommandPreprocess(MinecraftRP plugin){
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e){
		Player p = e.getPlayer();
		String command = e.getMessage();

		MPlayer pm = null;

		for(String str : plugin.spyers){
			pm = MPlayer.getMPlayer(Bukkit.getPlayerExact(str));
		}

		if (!e.getMessage().startsWith("/")){
			return;
		}

		// Global spying
		if(pm.isSocialSpying()){ // NPE.
			Functions.tell(p, "§e[§fSocialSpy§e] §3" + p.getName() + " §eused: §r" + command);
		}

		// Player spying
		if(pm.getSpying() != null && p == pm.getSpying()){
			Functions.tell(p, "§e[§fSocialSpy§e] §3" + p.getName() + " §eused: §r" + command);
		}

	}

}
