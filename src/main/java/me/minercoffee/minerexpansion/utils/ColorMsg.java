package me.minercoffee.minerexpansion.utils;

import org.bukkit.ChatColor;

public class ColorMsg {
    public ColorMsg(){
    }
    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
