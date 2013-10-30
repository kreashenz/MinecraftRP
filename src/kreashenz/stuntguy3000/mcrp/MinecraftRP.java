package kreashenz.stuntguy3000.mcrp;

import java.util.ArrayList;
import java.util.List;

import kreashenz.stuntguy3000.mcrp.chat.evt_AsyncPlayerChat;
import kreashenz.stuntguy3000.mcrp.commands.CmdMain;
import kreashenz.stuntguy3000.mcrp.events.evt_PlayerCommandPreprocess;
import kreashenz.stuntguy3000.mcrp.events.evt_PlayerJoin;
import kreashenz.stuntguy3000.mcrp.events.evt_PlayerLogin;
import kreashenz.stuntguy3000.mcrp.events.evt_PlayerQuit;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class MinecraftRP extends JavaPlugin {

	public List<String> spyers;

	private static MinecraftRP clazz;

	private CmdMain cmds;

	public void onEnable(){		
		clazz = this;

		saveDefaultConfig();

		cmds = new CmdMain();

		spyers = new ArrayList<String>();

		registerCommands();
		registerListeners();

	}

	private void registerCommands(){
		command("ban");
		command("broadcast");
		command("setspawn");
		command("spawn");
		command("kick");
		command("msg");
		command("reply");
		command("tp");
		command("tphere");
		command("tptoggle");
		command("nick");
		command("clear");
		command("socialspy");
		command("speed");
	}

	private void registerListeners(){
		listeners(new evt_PlayerJoin(this));
		listeners(new evt_PlayerQuit(this));
		listeners(new evt_PlayerCommandPreprocess(this));
		listeners(new evt_PlayerLogin());
		listeners(new evt_AsyncPlayerChat());
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