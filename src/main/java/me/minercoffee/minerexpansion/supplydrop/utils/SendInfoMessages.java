package me.minercoffee.minerexpansion.supplydrop.utils;

import me.minercoffee.minerexpansion.MinerExpansion;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SendInfoMessages {
    public SendInfoMessages() {
    }

    public static void sendInfoMessage(Player p, String messageConfigPath, String dropName, String envoyName) {
        String msg = MinerExpansion.getPlugin().getConfig().getString("Messages." + messageConfigPath);
        if (msg != null) {
            msg = msg.replaceAll("%dropName%", dropName);
        }
        if (msg != null) {
            msg = msg.replaceAll("%envoyName%", envoyName);
        }
        if (msg != null) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
        }
    }

    public static void sendInfoMessage(Player p, String messageConfigPath) {
        String msg = MinerExpansion.getPlugin().getConfig().getString("Messages." + messageConfigPath);
        if (msg != null) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
        }
    }
}

