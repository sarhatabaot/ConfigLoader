package net.sarhatabaot.configloader;

import net.sarhatabaot.configloader.transform.YamlTransform;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ConfigLoader {
	private static final Logger logger = LoggerFactory.getLogger(ConfigLoader.class);

	/**
	 *
	 * @param config
	 */
	public static void load(Config config) {
		try {
			YamlConfiguration yamlConfiguration = new YamlConfiguration();
			yamlConfiguration.load(config.getFile());
			for (Field field : config.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				ConfigOption option = field.getAnnotation(ConfigOption.class);
				if (option != null) {
					YamlTransform transform = option.transform().getDeclaredConstructor().newInstance();
					String path = option.path();
					Object newValue = field.get(config);
					if (yamlConfiguration.contains(path)) {
						newValue = transform.fromYaml(yamlConfiguration.get(path));
					} else {
						for (String legacyPath : option.legacyPath()) {
							if (yamlConfiguration.contains(legacyPath)) {
								newValue = transform.fromYaml(yamlConfiguration.get(legacyPath));
								break;
							}
						}
					}
					option.postLoad().getDeclaredConstructor().newInstance().postLoad(newValue);
					field.set(config, newValue);
				}
			}
		} catch (FileNotFoundException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}catch (IOException | IllegalAccessException | InstantiationException | InvalidConfigurationException e) {
			logger.error("Unable to load config "+config.getClass().getSimpleName() + ", defaulting to already configured or default values", e);
		}
	}

	/**
	 *
	 * @param config
	 */
	public static void save(Config config) {
		try {
			YamlConfiguration yamlConfiguration = new YamlConfiguration();
			for (Field field : config.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				ConfigOption option = field.getAnnotation(ConfigOption.class);
				if (option != null) {
					YamlTransform transform = option.transform().getDeclaredConstructor().newInstance();
					yamlConfiguration.set(option.path(), transform.toYaml(field.get(config)));
				}
			}
			yamlConfiguration.save(config.getFile());
		} catch (IOException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
			logger.error("Unable to save config "+config.getClass().getSimpleName(), e);
		}
	}


	/**
	 *
	 * @param config
	 */
	public static void loadAndSave(Config config) {
		load(config);
		save(config);
	}

}
