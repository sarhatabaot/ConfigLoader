package net.sarhatabaot.configloader.transform;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 */
public class ConfSectIntHashMap implements YamlTransform {
	private final Logger logger = LoggerFactory.getLogger(ConfSectIntHashMap.class);
	@SuppressWarnings("unchecked")
	@Override
	public Object toYaml(Object obj) {
		Map<Integer, List<String>> map = (Map<Integer, List<String>>) obj;
		MemorySection section = new YamlConfiguration();
		for (Entry<Integer, List<String>> entry : map.entrySet()) {
			section.set(entry.getKey().toString(), entry.getValue());
		}
		return section;
	}

	@Override
	public Object fromYaml(Object obj) {
		ConfigurationSection sect = (ConfigurationSection) obj;
		Map<Integer, List<String>> map = new LinkedHashMap<>();
		for (String key : sect.getKeys(false)) {
			try {
				map.put(Integer.parseInt(key), sect.getStringList(key));
			} catch (NumberFormatException e) {
				logger.error(e.getMessage());
			}
		}
		return map;
	}

}
