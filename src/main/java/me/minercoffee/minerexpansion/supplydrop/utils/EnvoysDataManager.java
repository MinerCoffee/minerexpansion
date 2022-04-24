package me.minercoffee.minerexpansion.supplydrop.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import me.minercoffee.minerexpansion.MinerExpansion;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;


public class EnvoysDataManager {
    private final MinerExpansion plugin;
    private static File customConfigFile;
    private static FileConfiguration customConfig = null;

    public EnvoysDataManager(MinerExpansion plugin) {
        this.plugin = plugin;
    }

    public static FileConfiguration getEnvoysData() {
        return customConfig;
    }

    public static void saveDefaultConfig() {
        customConfigFile = new File(MinerExpansion.getPlugin().getDataFolder(), "envoys.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            MinerExpansion.getPlugin().saveResource("envoys.yml", false);
        }
        customConfig = new YamlConfiguration();

        try {
            customConfig.load(customConfigFile);
        } catch (InvalidConfigurationException | IOException var1) {
            var1.printStackTrace();
        }

    }
    public FileConfiguration getConfig() {
        if (customConfig == null)
            reloadConfig();
        return customConfig;
    }
    public void reloadConfig(){
        if(customConfigFile == null)
            customConfigFile = new File(this.plugin.getDataFolder(), "envoys.yml");
        customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
        InputStream defaultStream = this.plugin.getResource("envoys.yml");
        if(defaultStream != null){
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            customConfig.setDefaults(defaultConfig);
        }
    }

    public static void saveEnvoysData() {
        try {
            getEnvoysData().save(customConfigFile);
        } catch (IOException var1) {
            var1.printStackTrace();
        }

    }
}
