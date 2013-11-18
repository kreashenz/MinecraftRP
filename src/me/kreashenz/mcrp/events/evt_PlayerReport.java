package me.kreashenz.mcrp.events;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.events.custom.PlayerReportEvent;
import me.kreashenz.mcrp.utils.Functions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class evt_PlayerReport implements Listener {

	private MinecraftRP plugin;

	public evt_PlayerReport(MinecraftRP plugin){
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerReport(PlayerReportEvent e){
		Player p = e.getPlayer();
		String report = e.getReport();
		String reason = "§c" + p.getName() + " §7> §6" + report;
		plugin.reports.add(reason);
		for(Player t : Bukkit.getOnlinePlayers()){
			if(t != null){
				if(t.hasPermission("mcrp.report.receive") || plugin.dutyAdmins.contains(t.getName())){
					Functions.tell(t, reason);
				}
			}
		}
	}

}
