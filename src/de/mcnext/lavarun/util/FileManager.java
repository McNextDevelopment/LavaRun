package de.mcnext.lavarun.util;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import de.mcnext.lavarun.LavaRun;

public class FileManager {
	
	private File file;
	private YamlConfiguration cfg;
	
	public FileManager(LavaRun instance, String filename) {
		instance.getDataFolder().mkdir();
		file = new File(instance.getDataFolder(), filename + ".yml");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				instance.getLogger().warning(instance.getData().Prefix() + "Error trying to create File: " + filename + ".yml");
				e.printStackTrace();
			}
		}
		cfg = YamlConfiguration.loadConfiguration(file);
	}
	
	public void saveConfig() {
		try {
			cfg.save(file);
		} catch (IOException e) {
		}
	}
	
	public void setValue(String path, Object value) {
		cfg.set(path, value);
		saveConfig();
	}
	
	public Object getValue(String path) {
		return cfg.get(path);
	}
}
