package kreashenz.stuntguy3000.mcrp.utils.stuff;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kreashenz.stuntguy3000.mcrp.MinecraftRP;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ItemNames {

	private File f = new File(MinecraftRP.getInstance().getDataFolder(), "items.yml");
	private FileConfiguration conf;

	private final Map<String, Integer> items = new HashMap<String, Integer>();
	private final Map<ItemData, List<String>> names = new HashMap<ItemData, List<String>>();
	private final Map<String, Short> data = new HashMap<String, Short>();

	public ItemNames(){
		if(!f.exists()){
			try {
				f.createNewFile();
				conf = YamlConfiguration.loadConfiguration(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else conf = YamlConfiguration.loadConfiguration(f);
	}

	public void load(){
		for(String id : conf.getConfigurationSection("items").getKeys(false)){
			names.put(new ItemData(Integer.valueOf(id), (short)0), conf.getStringList("items." + id));
		}
	}

	class ItemData {

		private int id;
		private short data;

		ItemData(int id, short data){
			this.id = id;
			this.data = data;
		}

		public int getId(){
			return id;
		}

		public short getData(){
			return data;
		}

		public boolean equals(Object o){
			if (o == null || !(o instanceof ItemData)){
				return false;
			}
			ItemData a = (ItemData)o;
			return (this.id == a.getId()) && (this.data == a.getData());
		}
	}

}
