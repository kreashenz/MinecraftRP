package kreashenz.stuntguy3000.mcrp.commands;

import java.util.logging.Level;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;
import kreashenz.stuntguy3000.mcrp.utils.Functions;
import kreashenz.stuntguy3000.mcrp.utils.MPlayer;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class ICommand {

	protected MinecraftRP plugin;

	public ICommand(MinecraftRP plugin){
		this.plugin = plugin;
	}

	public abstract void execute(CommandSender s, Command cmd, String[] args);

	protected void kick(Player p, String[] args, boolean ban){
		String reason = "";
		for (int i = 1; i < args.length; i++) {
			reason = reason + args[i] + ' ';
			reason = ChatColor.translateAlternateColorCodes('&', reason);
		}
		p.kickPlayer("§cYou were " + (ban ? "banned" : "kicked") + " by §6" + p.getName() + " §cfor §6" + reason);
		if(ban){
			p.setBanned(true);
			MPlayer.getMPlayer(p).set("ban.reason", reason);
		}
	}

	protected void message(CommandSender p, CommandSender t, String[] msg, boolean reply){
		String m = "";
		for (int i = (reply ? 0 : 1); i < msg.length; i++) {
			m = m + msg[i] + ' ';
			if(p.hasPermission("mcrp.chat.color"))m = ChatColor.translateAlternateColorCodes('&', m);
		}
		p.sendMessage("§6[§a" + p.getName() + " -> §7" + t.getName() + "§6] §7" + m);
		t.sendMessage("§6[§7" + p.getName() + "§a -> " + t.getName() + "§6] §7" + m);
	}

	protected boolean isInt(String sInt){
		boolean valid = true;

		try {
			Functions.log(Level.SEVERE, "||" + sInt + "||");
			Integer.parseInt(sInt);
		}
		catch(NumberFormatException e){
			valid = false;
		}
		
		return valid;
	}
	
	@SuppressWarnings("deprecation")
	protected void giveItem(int matID, int data, int amount, Player p, Player sender) {
		if (p == null || sender == null) {
			Functions.tell(p, "§cInvalid player. §f/i <item>[:data] <amount> [player]");
			return;
		}

		Material item = Material.getMaterial(matID);

		if (item == null) {
			Functions.tell(p, "§cInvalid item. §f/i <item>[:data] <amount> [player]");
			return;
		}

		ItemStack is = null;

		if (data == -1) {
			is = new ItemStack(item, amount);
		} else {
			try {
				is = new ItemStack(item, amount, Short.parseShort(String.valueOf(data)));
			} catch (NumberFormatException ex) {
				Functions.tell(p, "§cInvalid item. §f/i <item>[:data] <amount> [player]");
				return;
			}
		}

		p.getInventory().addItem(is); // DONE
		
		if (!p.getName().equals(sender.getName())) {
			Functions.tell(sender, "§3Giving " + p.getName() + " " + amount + " of " + item);
			Functions.tell(p, "§3You have recieved " + amount + " of " + item + " from" + sender.getName());
		}
	}
}