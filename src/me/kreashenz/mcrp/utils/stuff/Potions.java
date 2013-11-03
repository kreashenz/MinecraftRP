package me.kreashenz.mcrp.utils.stuff;

import java.util.HashMap;

import org.bukkit.potion.PotionEffectType;

public class Potions {

	private static HashMap<String, PotionEffectType> names = new HashMap<String, PotionEffectType>();

	public static void loadNames(){
		names.put("absorbtion", PotionEffectType.ABSORPTION);
		names.put("blindness", PotionEffectType.BLINDNESS);
		names.put("confusion", PotionEffectType.CONFUSION);
		names.put("resistance", PotionEffectType.DAMAGE_RESISTANCE);
		names.put("haste", PotionEffectType.FAST_DIGGING);
		names.put("fireresistance", PotionEffectType.FIRE_RESISTANCE);
		names.put("boost", PotionEffectType.HEALTH_BOOST);
		names.put("hunger", PotionEffectType.HUNGER);
		names.put("strength", PotionEffectType.INCREASE_DAMAGE);
		names.put("jump", PotionEffectType.JUMP);
		names.put("poison", PotionEffectType.POISON);
		names.put("regen", PotionEffectType.REGENERATION);
		names.put("saturation", PotionEffectType.SATURATION);
		names.put("slow", PotionEffectType.SLOW);
		names.put("fatigue", PotionEffectType.SLOW_DIGGING);
		names.put("speed", PotionEffectType.SPEED);
		names.put("breathing", PotionEffectType.WATER_BREATHING);
		names.put("weakness", PotionEffectType.WEAKNESS);
		names.put("heal", PotionEffectType.HEAL);
		names.put("harm", PotionEffectType.HARM);
		names.put("invisibility", PotionEffectType.INVISIBILITY);
		names.put("nightvision", PotionEffectType.NIGHT_VISION);
		names.put("wither", PotionEffectType.WITHER);
	}

	public static PotionEffectType get(String name){
		return names.get(name);
	}

}
