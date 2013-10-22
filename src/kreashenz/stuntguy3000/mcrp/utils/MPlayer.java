package kreashenz.stuntguy3000.mcrp.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class MPlayer {

	private static Map<String, MPlayer> managers = new HashMap<String, MPlayer>();

	private CommandSender reply = null;

	private MinecraftRP plugin;
	private Player p;

	private File file;
	private FileConfiguration conf;

	public void releaseManager() {
		managers.remove(p.getName());
	}

	public static MPlayer getMPlayer(Player p) {
		String name = p.getName();
		if (managers.containsKey(name)) {
			return managers.get(name);
		} else {
			MPlayer mgr = new MPlayer(p);
			managers.put(name, mgr);
			return mgr;
		}
	}

	private MPlayer(Player p){
		this.plugin = MinecraftRP.getInstance();
		this.p = p;
		
		File folder = new File(plugin.getDataFolder() + File.separator + "users");
		
		if(!folder.exists())folder.mkdir();

		file = new File(folder, p.getName() + ".yml");
		if(file.exists()){
			conf = YamlConfiguration.loadConfiguration(file);
		} else {
			try {
				file.createNewFile();
				conf = YamlConfiguration.loadConfiguration(file);
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}

	}

	public void set(String path, Object value){
		getPlayerConfig().set(path, value);
		try {
			conf.save(file);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public void setReplyTo(CommandSender s){
		this.reply = s;
	}

	public File getPlayerFile(){
		return file;
	}

	public FileConfiguration getPlayerConfig(){
		return conf;
	}

	public CommandSender getReplyTo(){
		return reply;
	}

	public String getBalance() {
		return String.valueOf(conf.getInt(""));
	}

}