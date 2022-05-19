package me.minercoffee.minerexpansion.enchantments;

import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.bukkit.Bukkit.getLogger;

public class MobdropsUtils {
    public static final Enchantment MobDrops = new EnchantmentWrapper("mobdrops", "MobDrops", 1);
    public static void register() {
        boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(MobDrops);
        if(!registered)
            registerEnchantment(MobDrops);
    }

    public static void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);

        } catch(Exception e) {
            registered = false;
            e.printStackTrace();
        }
        if(registered){
            //send message to console
            getLogger().info("MobDrops I Enchantment has loaded in!");
        }
    }
}