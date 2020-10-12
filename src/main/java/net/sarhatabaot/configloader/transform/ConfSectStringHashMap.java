package net.sarhatabaot.configloader.transform;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 */
public class ConfSectStringHashMap implements YamlTransform {

	@SuppressWarnings("unchecked")
	@Override
	public Object toYaml(Object obj) {
		Map<String, List<String>> map = (Map<String, List<String>>) obj;
		MemorySection section = new YamlConfiguration();
		for (Entry<String, List<String>> entry : map.entrySet()) {
			section.set(entry.getKey(), entry.getValue());
		}
		return section;
	}

	@Override
	public Object fromYaml(Object obj) {
		ConfigurationSection sect = (ConfigurationSection) obj;
		Map<String, List<String>> map = new LinkedHashMap<>();
		for (String key : sect.getKeys(false)) {
			map.put(key, sect.getStringList(key));
		}
		return map;
	}

}
