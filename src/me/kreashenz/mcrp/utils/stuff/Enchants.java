package me.kreashenz.mcrp.utils.stuff;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.bukkit.enchantments.Enchantment;

public class Enchants {

	private static HashMap<String, Enchantment> names = new HashMap<String, Enchantment>();

	public static void loadNames(){
		names.put("alldamage", Enchantment.DAMAGE_ALL);
		names.put("alldmg", Enchantment.DAMAGE_ALL);
		names.put("sharpness", Enchantment.DAMAGE_ALL);
		names.put("sharp", Enchantment.DAMAGE_ALL);

		names.put("arthropodsdamage", Enchantment.DAMAGE_ARTHROPODS);
		names.put("ardmg", Enchantment.DAMAGE_ARTHROPODS);
		names.put("baneofarthropods", Enchantment.DAMAGE_ARTHROPODS);
		names.put("baneofarthropod", Enchantment.DAMAGE_ARTHROPODS);
		names.put("arthropod", Enchantment.DAMAGE_ARTHROPODS);

		names.put("undeaddamage", Enchantment.DAMAGE_UNDEAD);
		names.put("smite", Enchantment.DAMAGE_UNDEAD);

		names.put("digspeed", Enchantment.DIG_SPEED);
		names.put("efficiency", Enchantment.DIG_SPEED);
		names.put("minespeed", Enchantment.DIG_SPEED);
		names.put("cutspeed", Enchantment.DIG_SPEED);

		names.put("durability", Enchantment.DURABILITY);
		names.put("dura", Enchantment.DURABILITY);
		names.put("unbreaking", Enchantment.DURABILITY);

		names.put("fireaspect", Enchantment.FIRE_ASPECT);
		names.put("fire", Enchantment.FIRE_ASPECT);
		names.put("meleefire", Enchantment.FIRE_ASPECT);
		names.put("meleeflame", Enchantment.FIRE_ASPECT);

		names.put("knockback", Enchantment.KNOCKBACK);

		names.put("blockslootbonus", Enchantment.LOOT_BONUS_BLOCKS);
		names.put("fortune", Enchantment.LOOT_BONUS_BLOCKS);

		names.put("mobslootbonus", Enchantment.LOOT_BONUS_MOBS);
		names.put("mobloot", Enchantment.LOOT_BONUS_MOBS);
		names.put("looting", Enchantment.LOOT_BONUS_MOBS);

		names.put("oxygen", Enchantment.OXYGEN);
		names.put("respiration", Enchantment.OXYGEN);
		names.put("breathing", Enchantment.OXYGEN);
		names.put("breath", Enchantment.OXYGEN);

		names.put("protection", Enchantment.PROTECTION_ENVIRONMENTAL);
		names.put("prot", Enchantment.PROTECTION_ENVIRONMENTAL);
		names.put("protect", Enchantment.PROTECTION_ENVIRONMENTAL);

		names.put("explosionsprotection", Enchantment.PROTECTION_EXPLOSIONS);
		names.put("explosionprotection", Enchantment.PROTECTION_EXPLOSIONS);
		names.put("expprot", Enchantment.PROTECTION_EXPLOSIONS);
		names.put("blastprotection", Enchantment.PROTECTION_EXPLOSIONS);
		names.put("blastprotect", Enchantment.PROTECTION_EXPLOSIONS);

		names.put("fallprotection", Enchantment.PROTECTION_FALL);
		names.put("fallprot", Enchantment.PROTECTION_FALL);
		names.put("featherfall", Enchantment.PROTECTION_FALL);
		names.put("featherfalling", Enchantment.PROTECTION_FALL);

		names.put("fireprotection", Enchantment.PROTECTION_FIRE);
		names.put("flameprotection", Enchantment.PROTECTION_FIRE);
		names.put("fireprotect", Enchantment.PROTECTION_FIRE);
		names.put("flameprotect", Enchantment.PROTECTION_FIRE);

		names.put("projectileprotection", Enchantment.PROTECTION_PROJECTILE);
		names.put("projprot", Enchantment.PROTECTION_PROJECTILE);

		names.put("silktouch", Enchantment.SILK_TOUCH);
		names.put("softtouch", Enchantment.SILK_TOUCH);

		names.put("waterworker", Enchantment.WATER_WORKER);
		names.put("aquaaffinity", Enchantment.WATER_WORKER);

		names.put("firearrow", Enchantment.ARROW_FIRE);
		names.put("flame", Enchantment.ARROW_FIRE);
		names.put("flamearrow", Enchantment.ARROW_FIRE);

		names.put("arrowdamage", Enchantment.ARROW_DAMAGE);
		names.put("power", Enchantment.ARROW_DAMAGE);
		names.put("arrowpower", Enchantment.ARROW_DAMAGE);

		names.put("arrowknockback", Enchantment.ARROW_KNOCKBACK);
		names.put("arrowkb", Enchantment.ARROW_KNOCKBACK);
		names.put("punch", Enchantment.ARROW_KNOCKBACK);
		names.put("arrowpunch", Enchantment.ARROW_KNOCKBACK);

		names.put("infinitearrows", Enchantment.ARROW_INFINITE);
		names.put("infarrows", Enchantment.ARROW_INFINITE);
		names.put("infinity", Enchantment.ARROW_INFINITE);
		names.put("infinite", Enchantment.ARROW_INFINITE);
		names.put("unlimited", Enchantment.ARROW_INFINITE);
		names.put("unlimitedarrows", Enchantment.ARROW_INFINITE);
	}

	public static Enchantment get(String name){
		return names.get(name);
	}
	
	public static Set<String> getAll(){
		Set<String> ench = new TreeSet<String>();
		for(Entry<String, Enchantment> entry : names.entrySet()){
			ench.add(entry.getKey());
		}
		return ench;
	}

}
