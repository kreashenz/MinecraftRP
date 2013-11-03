package me.kreashenz.mcrp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.kreashenz.mcrp.chat.evt_AsyncPlayerChat;
import me.kreashenz.mcrp.commands.CmdMain;
import me.kreashenz.mcrp.events.evt_PlayerCommandPreprocess;
import me.kreashenz.mcrp.events.evt_PlayerJoin;
import me.kreashenz.mcrp.events.evt_PlayerLogin;
import me.kreashenz.mcrp.events.evt_PlayerQuit;
import me.kreashenz.mcrp.events.evt_PlayerReport;
import me.kreashenz.mcrp.utils.stuff.ItemManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class MinecraftRP extends JavaPlugin {

	public List<String> spyers;
	public List<String> dutyAdmins;

	public List<String> hasReported;
	public List<String> reports;

	private static MinecraftRP clazz;

	private CmdMain cmds;

	public void onEnable(){		
		clazz = this;

		saveDefaultConfig();

		saveResource("items.csv", false);
		ItemManager.loadItems(new File(getDataFolder(), "items.csv"));

		cmds = new CmdMain();

		spyers = new ArrayList<String>();
		dutyAdmins = new ArrayList<String>();
		hasReported = new ArrayList<String>();
		reports = new ArrayList<String>();

		registerCommands();
		registerListeners();

		runReportScheduler();
	}

	private void registerCommands(){
		command("adminchat");
		command("balance");
		command("ban");
		command("break");
		command("broadcast");
		command("burn");
		command("clear");
		command("clearchat");
		command("duty");
		command("enchant");
		command("enderchest");
		command("enderchestclear");
		command("ext");
		command("give");
		command("help");
		command("item");
		command("kick");
		command("msg");
		command("nick");
		command("reply");
		command("report");
		command("reports");
		command("setspawn");
		command("socialspy");
		command("spawn");
		command("speed");
		command("tp");
		command("tpa");
		command("tpaccept");
		command("tphere");
		command("tptoggle");
	}

	private void registerListeners(){
		listeners(new evt_PlayerJoin(this));
		listeners(new evt_PlayerQuit(this));
		listeners(new evt_PlayerCommandPreprocess(this));
		listeners(new evt_PlayerLogin());
		listeners(new evt_AsyncPlayerChat(this));
		listeners(new evt_PlayerReport(this));
	}

	private void runReportScheduler(){
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunnable(){
			public void run(){
				for(String str : hasReported){
					Player t = Bukkit.getPlayerExact(str);
					if(t != null){
						hasReported.remove(t.getName());
					}
				}
			}
		}, 0L, getConfig().getInt("report-time-out")*20);
	}

	private void command(String cmd){
		getCommand(cmd).setExecutor(cmds);
	}

	private void listeners(Listener listener){
		getServer().getPluginManager().registerEvents(listener, this);
	}

	public static MinecraftRP getInstance(){
		return clazz;
	}

}