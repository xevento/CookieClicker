package de.zillolp.cookieclicker.utils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigUtil {
	private YamlConfiguration config;
	private File Config;
	private FileConfigurationOptions options;

	public ConfigUtil(File Config) {
		this.config = YamlConfiguration.loadConfiguration(Config);
		this.Config = Config;
		this.options = config.options();
	}

	public ConfigUtil(YamlConfiguration config, File Config) {
		this.config = config;
		this.Config = Config;
		this.options = config.options();
	}

	public ConfigUtil(YamlConfiguration config, File Config, FileConfigurationOptions options) {
		this.config = config;
		this.Config = Config;
		this.options = options;
	}

	public void createConfig() {
		createHeader();
		options.copyDefaults(true);
		save();
	}

	public void createHeader() {
		options.header("#########################################\n" + "############ [CookieClicker] ############\n"
				+ "#########################################\n\n\n");
	}

	public void addDefaultStrings(LinkedHashMap<String, String> defaults) {
		for (Entry<String, String> section : defaults.entrySet()) {
			config.addDefault(section.getKey(), section.getValue());
		}
		options.copyDefaults(true);
		save();
	}

	public void addDefaultString(String section, String value) {
		config.addDefault(section, value);
		options.copyDefaults(true);
		save();
	}

	public void addDefaultBooleans(LinkedHashMap<String, Boolean> defaults) {
		for (Entry<String, Boolean> section : defaults.entrySet()) {
			config.addDefault(section.getKey(), section.getValue());
		}
		options.copyDefaults(true);
		save();
	}

	public void addDefaultBoolean(String section, boolean value) {
		config.addDefault(section, value);
		options.copyDefaults(true);
		save();
	}

	public void addDefaultIntegers(LinkedHashMap<String, Integer> defaults) {
		for (Entry<String, Integer> section : defaults.entrySet()) {
			config.addDefault(section.getKey(), section.getValue());
		}
		options.copyDefaults(true);
		save();
	}

	public void addDefaultInteger(String section, int value) {
		config.addDefault(section, value);
		options.copyDefaults(true);
		save();
	}

	public void addDefaultLongs(LinkedHashMap<String, Long> defaults) {
		for (Entry<String, Long> section : defaults.entrySet()) {
			config.addDefault(section.getKey(), section.getValue());
		}
		options.copyDefaults(true);
		save();
	}

	public void addDefaultLong(String section, Long value) {
		config.addDefault(section, value);
		options.copyDefaults(true);
		save();
	}

	public void setString(String section, String value) {
		config.set(section, value);
		save();
	}

	public void setBoolean(String section, Boolean value) {
		config.set(section, value);
		save();
	}

	public void setInteger(String section, int value) {
		config.set(section, value);
		save();
	}

	public void setLong(String section, Long value) {
		config.set(section, value);
		save();
	}

	public void setDouble(String section, double value) {
		config.set(section, value);
		save();
	}

	public YamlConfiguration getConfig() {
		return config;
	}

	public String getString(String section) {
		return config.getString(section);
	}

	public boolean getBoolean(String section) {
		return config.getBoolean(section);
	}

	public int getInt(String section) {
		return config.getInt(section);
	}

	public Long getLong(String section) {
		return config.getLong(section);
	}

	public double getDouble(String section) {
		return config.getDouble(section);
	}

	public void save() {
		try {
			config.save(Config);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
