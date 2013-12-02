package me.kreashenz.mcrp.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.kreashenz.mcrp.MinecraftRP;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class MPlayer {

	private static Map<String, MPlayer> managers = new HashMap<String, MPlayer>();

	private boolean hasTPO = false;
	private boolean hasTPReq = false;
	private boolean isSocialSpying = false;
	private boolean adminChat = false;
	private boolean duty = true;

	private CommandSender reply = null;

	private Location tpLoc;

	private MinecraftRP plugin;

	private Player spying;
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

	public void setSpying(Player s){
		this.spying = s;
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
		this.tpLoc = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
	}

	public void setAdminChat(boolean adminChat){
		this.adminChat = adminChat;
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
		double d = conf.getInt("balance");
		set("balance", amount + d);
	}

	public void setMoney(double amount){
		set("balance", amount);
	}

	public void takeMoney(double amount){
		double d = conf.getInt("balance");
		set("balance", d - amount);
	}

	public void resetMoney(){
		set("balance", 0);
	}

	public void setTPRequested(boolean tped){
		hasTPReq = tped;
	}

	public void setDuty(boolean duty){
		if(duty)plugin.dutyAdmins.add(p.getName());
		else plugin.dutyAdmins.remove(p.getName());
		this.duty = duty;
	}

	public void setSocialSpying(boolean spying){
		if(spying) plugin.spyers.add(p.getName()); 
		else plugin.spyers.remove(p.getName());
		isSocialSpying = spying;
	}

	public void saveHome(String name, Location loc){
		String home = "homes." + name + ".";
		set(home + "x", loc.getBlockX());
		set(home + "y", loc.getBlockY());
		set(home + "z", loc.getBlockZ());
		set(home + "world", loc.getWorld().getName());
	}

	public Location getHome(String name){
		String home = "homes." + name + ".";
		return new Location(Bukkit.getWorld(conf.getString(home + "world")), conf.getInt(home + "x"), conf.getInt(home + "y"), conf.getInt(home + "z"));
	}

	public boolean homeExists(String name){
		if(conf.contains("homes." + name)){
			return true;
		}
		return false;
	}

	public List<String> getHomes(){
		List<String> homes = new ArrayList<String>();
		for(String str : conf.getConfigurationSection("homes").getKeys(false)){
			homes.add(str);
			System.out.print("[DEBUUUGGG] L184 - MPlayer class " + homes.toString());
		}
		return homes;
	}

	public double getMoney(){
		return conf.getDouble("balance");
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

	public Player getSpying(){
		return spying;
	}

	public boolean inAdminChat(){
		return adminChat;
	}

	public boolean hasTPRequested(){
		return hasTPReq;
	}

	public boolean isSocialSpying(){
		return isSocialSpying;
	}

	public boolean isOnDuty(){
		return duty;
	}

}