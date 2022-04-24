package me.minercoffee.minerexpansion.warps;

import me.minercoffee.minerexpansion.MinerExpansion;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
public class delwarp implements CommandExecutor {
    private final MinerExpansion plugin;
    public String name;

    public delwarp(MinerExpansion plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("delwarp")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Player only command!");
            return false;
        }
        if (plugin.getConfig().getBoolean("warp")) {
            Player p = (Player) commandSender;
            if (!p.hasPermission("warp.delwarp")) {
                p.sendMessage(Color("&cYou don't have enough permissions!"));
                return false;
            }
            if (strings.length == 0) {
                p.sendMessage(Color("&cPlease provide a name!"));
                return false;
            }
            name = strings[0].toLowerCase();
            if (plugin.getConfig().get("warps." + name) == null) {
                p.sendMessage(Color("&cThere is no warp with this name!"));
                return false;
            }
            plugin.getConfig().set("warps." + name, null);
            plugin.saveConfig();
            p.sendMessage(Color("&aWarp &b" + name + " &asuccessfully deleted!"));
            return true;
        }
        return true;
    }

    private String Color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}