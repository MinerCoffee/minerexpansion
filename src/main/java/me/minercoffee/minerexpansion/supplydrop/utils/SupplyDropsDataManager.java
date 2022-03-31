package me.minercoffee.minerexpansion.supplydrop.utils;

import java.io.File;
import java.io.IOException;

import me.minercoffee.minerexpansion.MinerExpansion;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;


public class SupplyDropsDataManager {
    private static File customConfigFile;
    private static FileConfiguration customConfig;

    public SupplyDropsDataManager() {
    }

    public static FileConfiguration getSupplyDropsData() {
        return customConfig;
    }

    public static void saveDefaultConfig() {
        customConfigFile = new File(MinerExpansion.getPlugin().getDataFolder(), "supplydrops.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            MinerExpansion.getPlugin().saveResource("supplydrops.yml", false);
        }

        customConfig = new YamlConfiguration();

        try {
            customConfig.load(customConfigFile);
        } catch (InvalidConfigurationException | IOException var1) {
            var1.printStackTrace();
        }

    }

    public static void saveSupplyDropsData() {
        try {
            getSupplyDropsData().save(customConfigFile);
        } catch (IOException var1) {
            var1.printStackTrace();
        }

    }
}