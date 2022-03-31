package me.minercoffee.minerexpansion.elyra.utils;

import org.bukkit.ChatColor;

public class ChatUtils {
        public ChatUtils() {
        }
        public static String colour(String msg) {
            return ChatColor.translateAlternateColorCodes('&', msg);
        }
}
