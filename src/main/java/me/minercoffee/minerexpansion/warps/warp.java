package me.minercoffee.minerexpansion.warps;

import me.minercoffee.minerexpansion.MinerExpansion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class warp implements CommandExecutor {
    private final MinerExpansion plugin;

    public String name;
    public String name2;

    public warp(MinerExpansion plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("warp")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        Player p = (Player) commandSender;
        try {
            if (plugin.getConfig().getBoolean("warp")) {
                if (strings.length == 0) {
                    p.sendMessage(Color("&cPlease provide a warp name!"));
                    return false;
                }
                if (strings.length == 1) {
                    name2 = strings[0].toLowerCase();
                    Player target = Bukkit.getPlayer(strings[0]);
                    Location loc;
                    double x = plugin.getConfig().getDouble("warps." + name2 + ".X");
                    double y = plugin.getConfig().getDouble("warps." + name2 + ".Y");
                    double z = plugin.getConfig().getDouble("warps." + name2 + ".Z");
                    float yaw = (float) plugin.getConfig().getDouble("warps." + name2 + ".Yaw");
                    float pitch = (float) plugin.getConfig().getDouble("warps." + name2 + ".Pitch");
                    String world = plugin.getConfig().getString("warps." + name2 + ".World");
                    if (world != null) {
                        loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
                        if (target != null) {
                            target.teleport(loc);
                        }
                    }
                    p.sendMessage(Color("&aYou've been teleported to &b" + name2));
                }
                name = strings[0].toLowerCase();
                if (plugin.getConfig().get("warps." + name) == null) {
                    p.sendMessage(Color("&cNo warp with that name!"));
                    return false;
                }
                if (s.equalsIgnoreCase("warp" + name)) {
                    Location loc;
                    double x = plugin.getConfig().getDouble("warps." + name + ".X");
                    double y = plugin.getConfig().getDouble("warps." + name + ".Y");
                    double z = plugin.getConfig().getDouble("warps." + name + ".Z");
                    float yaw = (float) plugin.getConfig().getDouble("warps." + name + ".Yaw");
                    float pitch = (float) plugin.getConfig().getDouble("warps." + name + ".Pitch");
                    String world = plugin.getConfig().getString("warps." + name + ".World");
                    if (world != null) {
                        loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
                        p.teleport(loc);
                    }
                    p.sendMessage(Color("&aYou've been teleported to &b" + name));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private String Color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}