package me.kreashenz.mcrp.commands;

import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import me.kreashenz.mcrp.MinecraftRP;
import me.kreashenz.mcrp.utils.Functions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CmdHelp extends ICommand {

	public CmdHelp(MinecraftRP plugin) {
		super("help");
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args){
		if(s instanceof Player){
			Player p = (Player)s;
			if(p.hasPermission("mcrp.help")){
				SortedMap<Integer, String> map = new TreeMap<Integer, String>();
				int length = 0;
				for(Plugin pl : plugin.getServer().getPluginManager().getPlugins()){
					length++;
					map.put(length, pl.getName());
				}
				paginate(p, map, 1, length);
			}
		}
		return true;
	}

	private void paginate(CommandSender s, SortedMap<Integer, String> map, int page, int pageLength) {
		Functions.tell(s, "§cList: Page (" + String.valueOf(page) + " of " + (((map.size() % pageLength) == 0) ? map.size() / pageLength : (map.size() / pageLength) + 1) + ")");
		int i = 0, k = 0;
		page--;
		for (final Entry<Integer, String> e : map.entrySet()) {
			k++;
			if ((((page * pageLength) + i + 1) == k) && (k != ((page * pageLength) + pageLength + 1))) {
				i++;
				Functions.tell(s, "§4" + e.getValue() + "§7: ");
			}
		}
	}

}
