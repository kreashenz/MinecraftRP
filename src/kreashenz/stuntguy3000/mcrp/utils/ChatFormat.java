package kreashenz.stuntguy3000.mcrp.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;

public class ChatFormat {
	final Pattern REPLACE_COLOR_PATTERN = Pattern.compile("&([0-9a-f])");
	final Pattern REPLACE_MAGIC_PATTERN = Pattern.compile("&(k)");
	final Pattern REPLACE_PATTERN = Pattern.compile("&([0-9a-fk-or])");
	final Pattern VANILLA_PATTERN = Pattern.compile("§+[0-9A-FK-ORa-fk-or]");
	final Pattern VANILLA_COLOR_PATTERN = Pattern.compile("§+[0-9A-Fa-f]");
	final Pattern REPLACE_FORMAT_PATTERN = Pattern.compile("&([l-or])");
	final Pattern VANILLA_FORMAT_PATTERN = Pattern.compile("§+[L-ORl-or]");
	final Pattern VANILLA_MAGIC_PATTERN = Pattern.compile("§+[Kk]");
	final Pattern URL_PATTERN = Pattern.compile("((?:(?:https?)://)?[\\w-_\\.]{2,})\\.([a-z]{2,3}(?:/\\S+)?)");
	DecimalFormat dFormat = new DecimalFormat("#0.00", DecimalFormatSymbols.getInstance(Locale.US));

	String stripColor(String input, Pattern pattern){
		return pattern.matcher(input).replaceAll("");
	}

	public String stripColor(String input){
		if (input == null){
			return null;
		}
		return VANILLA_COLOR_PATTERN.matcher(input).replaceAll("");
	}

	public String blockURL(String input){
		if (input == null)
		{
			return null;
		}
		String text = URL_PATTERN.matcher(input).replaceAll("$1 $2");
		while (URL_PATTERN.matcher(text).find())
		{
			text = URL_PATTERN.matcher(text).replaceAll("$1 $2");
		}
		return text;
	}

	public String replaceColor(String input, Pattern pattern)
	{
		return pattern.matcher(input).replaceAll("§$1");
	}

	public String stripFormat(String input)
	{
		if (input == null)
		{
			return null;
		}
		return VANILLA_PATTERN.matcher(input).replaceAll("");
	}

	public String replaceFormat(String input)
	{
		if (input == null)
		{
			return null;
		}
		return REPLACE_PATTERN.matcher(input).replaceAll("§$1");
	}

	public String formatAsCurrency(double value) {
		String str = dFormat.format(value);
		if (str.endsWith(".00"))
		{
			str = str.substring(0, str.length() - 3);
		}
		return str;
	}

	public enum ColourUtils {
		BLACK("&0", ChatColor.BLACK.toString()),
		DARK_BLUE("&1", ChatColor.DARK_BLUE.toString()),
		DARK_GREEN("&2", ChatColor.DARK_GREEN.toString()),
		DARK_AQUA("&3", ChatColor.DARK_AQUA.toString()),
		DARK_RED("&4", ChatColor.DARK_RED.toString()),
		DARK_PURPLE("&5", ChatColor.DARK_PURPLE.toString()),
		GOLD("&6", ChatColor.GOLD.toString()),
		GRAY("&7", ChatColor.GRAY.toString()),
		DARK_GRAY("&8", ChatColor.DARK_GRAY.toString()),
		BLUE("&9", ChatColor.BLUE.toString()),
		GREEN("&a", ChatColor.GREEN.toString()),
		AQUA("&b", ChatColor.AQUA.toString()),
		RED("&c", ChatColor.RED.toString()),
		LIGHT_PURPLE("&d", ChatColor.LIGHT_PURPLE.toString()),
		YELLOW("&e", ChatColor.YELLOW.toString()),
		WHITE("&f", ChatColor.WHITE.toString()),
		MAGIC("&k", ChatColor.MAGIC.toString()),
		BOLD("&l", ChatColor.BOLD.toString()),
		STRIKETHROUGH("&m", ChatColor.STRIKETHROUGH.toString()),
		UNDERLINE("&n", ChatColor.UNDERLINE.toString()),
		ITALIC("&o", ChatColor.ITALIC.toString()),
		RESET("&r", ChatColor.RESET.toString());

		private final String input;
		private final String MinecraftColor;

		private ColourUtils(String input, String MinecraftColor) {
			this.input = input;
			this.MinecraftColor = MinecraftColor;
		}

		public String getMinecraftColor()
		{
			return this.MinecraftColor;
		}

		public String getInput() {
			return this.input;
		}

		public static String format(String message) {
			String msg = message;
			for (ColourUtils c : values()) {
				msg = msg.replace(c.getInput(), c.getMinecraftColor());
			}
			return msg;
		}
	}
}