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
		for(String str : plugin.dutyAdmins){
			Player admins = Bukkit.getPlayerExact(str);
			if(admins != null){
				if(admins.hasPermission("mcrp.report.receive")){
					Functions.tell(p, reason);
				}
			}
		}
	}

}
