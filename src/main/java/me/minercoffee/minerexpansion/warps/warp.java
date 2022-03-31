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

    public warp(MinerExpansion plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("warp")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Player only command!");
            return false;
        }
        Player p = (Player) commandSender;
        if (strings.length == 0) {
            p.sendMessage(Color("&cPlease provide a warp name!"));
            return false;
        }
        String name = strings[0].toLowerCase();
        if (plugin.getConfig().get(name) == null) {
            p.sendMessage(Color("&cNo warp with that name!"));
            return false;
        }
        Location loc;
        double x = plugin.getConfig().getDouble(name + ".X");
        double y = plugin.getConfig().getDouble(name + ".Y");
        double z = plugin.getConfig().getDouble(name + ".Z");
        float yaw = (float) plugin.getConfig().getDouble(name + ".Yaw");
        float pitch = (float) plugin.getConfig().getDouble(name + ".Pitch");
        String world = plugin.getConfig().getString(name + ".World");
        loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
        p.teleport(loc);
        p.sendMessage(Color("&aYou've been teleported to &b" + name));
        return true;
    }

    private String Color (String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}