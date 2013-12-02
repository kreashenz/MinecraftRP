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
import me.kreashenz.mcrp.utils.stuff.Enchants;
import me.kreashenz.mcrp.utils.stuff.ItemManager;
import me.kreashenz.mcrp.utils.stuff.Potions;

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

	@Override
	public void onEnable(){		
		clazz = this;

		saveDefaultConfig();

		spyers = new ArrayList<String>();
		dutyAdmins = new ArrayList<String>();
		hasReported = new ArrayList<String>();
		reports = new ArrayList<String>();

		registerCommands();
		registerListeners();

		runReportScheduler();

		load();
	}

	private void registerCommands(){
		command("adminchat", new CmdAdminChat());
		command("balance", new CmdBalance());
		command("ban", new CmdBan());
		command("break", new CmdBreak());
		command("broadcast", new CmdBroadcast());
		command("burn", new CmdBurn());
		command("clear", new CmdClear());
		command("clearchat", new CmdClearChat());
		command("duty", new CmdDuty());
		command("enchant", new CmdEnchant());
		command("enderchest", new CmdEnderchest());
		command("enderchestclear", new CmdEnderchestClear());
		command("ext", new CmdExt());
		command("give", new CmdGive());
		command("help", new CmdHelp());
		command("item", new CmdItem());
		command("kick", new CmdKick());
		command("msg", new CmdMsg());
		command("nick", new CmdNick());
		command("reply", new CmdReply());
		command("report", new CmdReport());
		command("reports", new CmdReports());
		command("setspawn", new CmdSetspawn());
		command("socialspy", new CmdSocialspy());
		command("spawn", new CmdSpawn());
		command("speed", new CmdSpeed());
		command("tp", new CmdTp());
		command("tpa", new CmdTpa());
		command("tpaccept", new CmdTpaccept());
		command("tphere", new CmdTphere());
		command("tptoggle", new CmdTptoggle());
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

	private void load(){
		saveResource("items.csv", false);
		ItemManager.loadItems(new File(getDataFolder(), "items.csv"));
		Enchants.loadNames();
		Potions.loadNames();

	}

	private void runReportScheduler(){
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunnable(){
			@Override
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