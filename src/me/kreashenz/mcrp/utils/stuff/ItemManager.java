package me.kreashenz.mcrp.utils.stuff;

import me.kreashenz.mcrp.utils.Functions;

import org.bukkit.Material;
import org.bukkit.material.MaterialData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public final class ItemManager {
	private static Map<String, MaterialData> items = new HashMap<String, MaterialData>();

	@SuppressWarnings({"deprecation", "resource"})
	public static void loadItems(File file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;

			while ((line = reader.readLine()) != null) {
				try {
					if (line.startsWith("#")) continue;
					String[] itemData = line.split(",");
					if (itemData.length <= 1) continue;
					items.put(itemData[0], new MaterialData(Integer.parseInt(itemData[1]), (itemData.length > 2) ? Byte.parseByte(itemData[2]) : 0));
				} catch (Exception e) {}
			}
		} catch (Exception e) {
			Functions.log(Level.SEVERE, "An error occurred while reading the items from file '" + file.getPath() + "'!", e);
		}
	}

	public static Map<String, MaterialData> getItems() {
		return items;
	}

	@SuppressWarnings("deprecation")
	public static MaterialData getItem(String item) {
		item = item.toLowerCase();
		String[] itemData = item.split(":");

		Material type = Material.matchMaterial(itemData[0]);
		MaterialData data = (type != null) ? new MaterialData(type) : null;

		if (data == null) data = items.get(itemData[0]);

		if (data != null && itemData.length > 1) {
			Byte byteData = Functions.parseByte(itemData[1]);
			if (byteData != null) data.setData(byteData);
		}

		return data;
	}
}
