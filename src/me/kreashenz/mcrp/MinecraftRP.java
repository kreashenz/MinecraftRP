package me.kreashenz.mcrp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.kreashenz.mcrp.chat.evt_AsyncPlayerChat;
import me.kreashenz.mcrp.commands.CmdAdminChat;
import me.kreashenz.mcrp.commands.CmdBalance;
import me.kreashenz.mcrp.commands.CmdBan;
import me.kreashenz.mcrp.commands.CmdBreak;
import me.kreashenz.mcrp.commands.CmdBroadcast;
import me.kreashenz.mcrp.commands.CmdBurn;
import me.kreashenz.mcrp.commands.CmdClear;
import me.kreashenz.mcrp.commands.CmdClearChat;
import me.kreashenz.mcrp.commands.CmdDuty;
import me.kreashenz.mcrp.commands.CmdEnchant;
import me.kreashenz.mcrp.commands.CmdEnderchest;
import me.kreashenz.mcrp.commands.CmdEnderchestClear;
import me.kreashenz.mcrp.commands.CmdExt;
import me.kreashenz.mcrp.commands.CmdGive;
import me.kreashenz.mcrp.commands.CmdHelp;
import me.kreashenz.mcrp.commands.CmdItem;
import me.kreashenz.mcrp.commands.CmdKick;
import me.kreashenz.mcrp.commands.CmdMsg;
import me.kreashenz.mcrp.commands.CmdNick;
import me.kreashenz.mcrp.commands.CmdReply;
import me.kreashenz.mcrp.commands.CmdReport;
import me.kreashenz.mcrp.commands.CmdReports;
import me.kreashenz.mcrp.commands.CmdSetspawn;
import me.kreashenz.mcrp.commands.CmdSocialspy;
import me.kreashenz.mcrp.commands.CmdSpawn;
import me.kreashenz.mcrp.commands.CmdSpeed;
import me.kreashenz.mcrp.commands.CmdTp;
import me.kreashenz.mcrp.commands.CmdTpa;
import me.kreashenz.mcrp.commands.CmdTpaccept;
import me.kreashenz.mcrp.commands.CmdTphere;
import me.kreashenz.mcrp.commands.CmdTptoggle;
import me.kreashenz.mcrp.commands.ICommand;
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

	public void onEnable(){		
		clazz = this;

		saveDefaultConfig();

		saveResource("items.csv", false);
		ItemManager.loadItems(new File(getDataFolder(), "items.csv"));

		spyers = new ArrayList<String>();
		dutyAdmins = new ArrayList<String>();
		hasReported = new ArrayList<String>();
		reports = new ArrayList<String>();

		registerCommands();
		registerListeners();

		runReportScheduler();
	}

	private void registerCommands(){
		command("adminchat", new CmdAdminChat(this));
		command("balance", new CmdBalance(this));
		command("ban", new CmdBan(this));
		command("break", new CmdBreak(this));
		command("broadcast", new CmdBroadcast(this));
		command("burn", new CmdBurn(this));
		command("clear", new CmdClear(this));
		command("clearchat", new CmdClearChat(this));
		command("duty", new CmdDuty(this));
		command("enchant", new CmdEnchant(this));
		command("enderchest", new CmdEnderchest(this));
		command("enderchestclear", new CmdEnderchestClear(this));
		command("ext", new CmdExt(this));
		command("give", new CmdGive(this));
		command("help", new CmdHelp(this));
		command("item", new CmdItem(this));
		command("kick", new CmdKick(this));
		command("msg", new CmdMsg(this));
		command("nick", new CmdNick(this));
		command("reply", new CmdReply(this));
		command("report", new CmdReport(this));
		command("reports", new CmdReports(this));
		command("setspawn", new CmdSetspawn(this));
		command("socialspy", new CmdSocialspy(this));
		command("spawn", new CmdSpawn(this));
		command("speed", new CmdSpeed(this));
		command("tp", new CmdTp(this));
		command("tpa", new CmdTpa(this));
		command("tpaccept", new CmdTpaccept(this));
		command("tphere", new CmdTphere(this));
		command("tptoggle", new CmdTptoggle(this));
	}

	private void command(String cmd, ICommand cmde){
		getCommand(cmd).setExecutor(cmde);
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

	private void listeners(Listener listener){
		getServer().getPluginManager().registerEvents(listener, this);
	}

	public static MinecraftRP getInstance(){
		return clazz;
	}

}