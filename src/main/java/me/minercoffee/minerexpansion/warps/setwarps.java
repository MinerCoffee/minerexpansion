package me.minercoffee.minerexpansion.warps;

import me.minercoffee.minerexpansion.MinerExpansion;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;

public class setwarps implements CommandExecutor {
    private final MinerExpansion plugin;

    public setwarps(MinerExpansion plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("setwarp")).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Player only command!");
            return false;
        }
        Player p = (Player) commandSender;
        if (!p.hasPermission("warp.setwarp")) {
            p.sendMessage(Color("&cYou don't have enough permissions!"));
            return false;
        }
        if (strings.length == 0) {
            p.sendMessage(Color("&cYou need to give me a name"));
            return false;
        }
        String name = strings[0].toLowerCase();
        if (plugin.getConfig().get(name) != null) {
            p.sendMessage(Color("&cThere is already a warp with that name!"));
            return false;
        }
        Location loc = p.getLocation();
        plugin.getConfig().set(name + ".World", loc.getWorld().getName());
        plugin.getConfig().set(name + ".X", loc.getX());
        plugin.getConfig().set(name + ".Y", loc.getY());
        plugin.getConfig().set(name + ".Z", loc.getZ());
        plugin.getConfig().set(name + ".Pitch", loc.getPitch());
        plugin.getConfig().set(name + ".Yaw", loc.getYaw());
        plugin.saveConfig();
        p.sendMessage(Color("&aWarp set!"));
        return true;
    }

    private String Color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
