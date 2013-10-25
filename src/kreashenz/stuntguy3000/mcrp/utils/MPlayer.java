package kreashenz.stuntguy3000.mcrp.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class MPlayer {

	private static Map<String, MPlayer> managers = new HashMap<String, MPlayer>();

	private boolean hasTPO = false;

	private CommandSender reply = null;

	private Location tpLoc;

	private MinecraftRP plugin;

	private Player tpReq;
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
		conf.set(path, value);
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

	public void setTPO(boolean tpo){
		hasTPO = tpo;
	}

	public void setTeleportTo(Player p){
		this.tpReq = p;
	}

	public void setTeleportLocation(Location loc){
		this.tpLoc = loc;
	}

	public File getPlayerFile(){
		return file;
	}

	public FileConfiguration getConfig(){
		return conf;
	}

	public CommandSender getReplyTo(){
		return reply;
	}

	public void addMoney(double amount){
		double d = conf.getInt("economy.balance");
		set("economy.balance", amount + d);
	}

	public void setMoney(double amount){
		set("economy.balance", amount);
	}

	public void takeMoney(double amount){
		double d = conf.getInt("economy.balance");
		set("economy.balance", d - amount);
	}

	public void resetMoney(){
		set("economy.balance", 0);
	}

	public double getMoney(){
		return conf.getDouble("economy.balance");
	}

	public Location getTeleportLocation(){
		return tpLoc;
	}

	public Player getTeleportPlayer(){
		return tpReq;
	}

	public boolean canAfford(double afford){
		return (getMoney() >= afford);
	}

	public boolean getTPO(){
		return hasTPO;
	}

}