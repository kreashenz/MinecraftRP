package kreashenz.stuntguy3000.mcrp.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.logging.Level;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Functions {

	public static void tell(CommandSender s, String msg){
		s.sendMessage(s instanceof Player ? colour(msg) : ChatColor.stripColor(msg) + " ");
	}

	public static void noPerm(CommandSender s){
		tell(s, MinecraftRP.getInstance().getConfig().getString("messages.no-permission"));
	}

	public static String colour(String s){
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	public static void log(Level level, String msg){
		MinecraftRP.getInstance().getLogger().log(level, msg);
	}

    public static void log(Level level, String msg, Exception e){
        MinecraftRP.getInstance().getLogger().log(level, msg, e);
    }

	public static void unknownPlayer(CommandSender s){
		tell(s, "§cThat player wasn't found!");
	}

	public static String formatAsCurrency(double value) {
		DecimalFormat decimal = new DecimalFormat("#0.00", DecimalFormatSymbols.getInstance(Locale.US));
		String str = decimal.format(value);
		if (str.endsWith(".00")){
			str = str.substring(0, str.length() - 3);
		}
		return str;
	}

    public static Integer parseInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Byte parseByte(String str) {
        try {
            return Byte.parseByte(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
