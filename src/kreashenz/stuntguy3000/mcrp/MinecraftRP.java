package kreashenz.stuntguy3000.mcrp;

import kreashenz.stuntguy3000.mcrp.chat.evt_AsyncPlayerChat;
import kreashenz.stuntguy3000.mcrp.commands.CmdMain;
import kreashenz.stuntguy3000.mcrp.events.evt_PlayerJoin;
import kreashenz.stuntguy3000.mcrp.events.evt_PlayerLogin;
import kreashenz.stuntguy3000.mcrp.events.evt_PlayerQuit;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class MinecraftRP extends JavaPlugin {

	private static MinecraftRP clazz;

	private CmdMain cmds;

	public void onEnable(){		
		clazz = this;

		saveDefaultConfig();

		cmds = new CmdMain();

		registerCommands();
		registerListeners();

	}

	private void registerCommands(){
		command("ban");
		command("kick");
		command("msg");
		command("reply");
	}

	private void registerListeners(){
		listeners(new evt_PlayerJoin(this));
		listeners(new evt_PlayerQuit(this));
		listeners(new evt_PlayerLogin());
		listeners(new evt_AsyncPlayerChat(this));
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