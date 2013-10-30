package kreashenz.stuntguy3000.mcrp.utils.stuff;

import java.util.HashMap;

import org.bukkit.Material;

public class ItemNames {
	
	private static HashMap<String, Material> names = new HashMap<String, Material>();
	
	public static void loadNames(){
		names.put("stone", Material.STONE);
		names.put("grass", Material.GRASS);
	}

	public static Material getByName(String name) {
		return names.get(name);
	}

}
