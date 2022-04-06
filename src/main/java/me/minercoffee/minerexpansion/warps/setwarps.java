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
    public String name;

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
        name = strings[0].toLowerCase();
        if (plugin.getConfig().get(name) != null) {
            p.sendMessage(Color("&cThere is already a warp with that name!"));
            return false;
        }
        Location loc = p.getLocation();
            plugin.getConfig().isConfigurationSection("warps.");
            plugin.getConfig().set("warps." + name + ".World", Objects.requireNonNull(loc.getWorld()).getName());
            plugin.getConfig().set("warps." + name + ".X", loc.getX());
            plugin.getConfig().set("warps." + name + ".Y", loc.getY());
            plugin.getConfig().set("warps." + name + ".Z", loc.getZ());
            plugin.getConfig().set("warps." + name + ".Pitch", loc.getPitch());
            plugin.getConfig().set("warps." + name + ".Yaw", loc.getYaw());
            plugin.saveConfig();
            p.sendMessage(Color("&aWarp set!"));

        return true;
    }

    private String Color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
