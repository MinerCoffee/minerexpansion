package me.minercoffee.minerexpansion.staffhomes;

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

public class staffhomecmd implements CommandExecutor {

    MinerExpansion plugin;

    public staffhomecmd(MinerExpansion plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if (plugin.getConfig().getBoolean("enable")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("miner.staff")) {

                    if (args.length == 1 && args[0].equalsIgnoreCase("set")) {
                        if (plugin.getConfig().isConfigurationSection("savedlocations." + p.getName())) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("override-message") + plugin.getConfig().getInt("savedlocations." + p.getName() + ".x") + " " + plugin.getConfig().getInt("savedlocations." + p.getName() + ".y") + " " + plugin.getConfig().getInt("savedlocations." + p.getName() + ".z")));
                            saveLocation(p);
                        } else {
                            saveLocation(p);
                        }
                    } else if (args.length == 1 && args[0].equalsIgnoreCase("return")) {
                        if (plugin.getConfig().isConfigurationSection("savedlocations." + p.getName())) {
                            Location return_location = new Location(p.getWorld(), plugin.getConfig().getInt("savedlocations." + p.getName() + ".x"), plugin.getConfig().getInt("savedlocations." + p.getName() + ".y"), plugin.getConfig().getInt("savedlocations." + p.getName() + ".z"));
                            p.teleport(return_location);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("return-message"))));
                            plugin.getConfig().set("savedlocations." + p.getName(), null);
                            plugin.saveConfig();
                        } else {
                            p.sendMessage(ChatColor.DARK_RED + "You never set a staff home.");
                        }
                    }else if(args.length == 1){
                        if(p.hasPermission("miner.staff")){
                            Player t = Bukkit.getPlayer(args[0]);
                            if (!(t == null)){
                                if (plugin.getConfig().isConfigurationSection("savedlocations." + t.getName())) {
                                    p.sendMessage(ChatColor.GREEN + "Teleporting to temporary staff home(" + t.getName() + ") @: " + ChatColor.GRAY + plugin.getConfig().getInt("savedlocations." + t.getName() + ".x") + " " + plugin.getConfig().getInt("savedlocations." + t.getName() + ".y") + " " + plugin.getConfig().getInt("savedlocations." + t.getName() + ".z"));
                                    Location return_location = new Location(t.getWorld(), plugin.getConfig().getInt("savedlocations." + t.getName() + ".x"), plugin.getConfig().getInt("savedlocations." + t.getName() + ".y"), plugin.getConfig().getInt("savedlocations." + t.getName() + ".z"));
                                    p.teleport(return_location);
                                }else{
                                    p.sendMessage(ChatColor.DARK_RED + "That player does not have a home set.");
                                }

                            }
                        }else{
                            p.sendMessage(ChatColor.DARK_RED + "You don't have permission to use this command.");
                        }
                    }else{
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7==&a&lStaff&eHomes&7 by MinerCoffee=="));
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&o/staffhome set &7- &9Set a Temporary Home"));
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&o/staffhome return &7- &9Return to Home and Remove it"));
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&o/staffhome <name> &7- &9Teleport to a temporary home"));
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7========================="));
                    }
                }else{
                    p.sendMessage(ChatColor.DARK_RED + "You don't have permission to use this command.");
                }
            } else {
                System.out.println("A player must execute this command.");
            }
        }
        return true;
    }

    private void saveLocation(Player p) {
        Location l = p.getLocation();
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("set-message") + Math.round(l.getX()) + " " + Math.round(l.getY()) + " " + Math.round(l.getZ())));
        plugin.getConfig().createSection("savedlocations." + p.getName());
        plugin.getConfig().set("savedlocations." + p.getName() + ".x", l.getX());
        plugin.getConfig().set("savedlocations." + p.getName() + ".y", l.getY());
        plugin.getConfig().set("savedlocations." + p.getName() + ".z", l.getZ());
        plugin.saveConfig();
    }
}
