package me.minercoffee.minerexpansion.rtp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

import static me.minercoffee.minerexpansion.MinerExpansion.plugin;

public class rtpcmd implements CommandExecutor {
    private final HashMap<UUID, Long> cooldown = new HashMap<>();
    int cooldowntime = (plugin.getConfig().getInt("cooldowntimer"));

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

            Player player = (Player) sender;
            if (plugin.getConfig().getBoolean("rtp")) {
                if (cooldown.containsKey(player.getUniqueId())) {
                    long secondsleft = ((cooldown.get(player.getUniqueId()) / 1000) + cooldowntime) - (System.currentTimeMillis() / 1000);
                    if (secondsleft > 0) {
                        player.sendMessage(ChatColor.GOLD + "You cannot teleport for " + secondsleft + " seconds.");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You now have a cooldown for " + cooldowntime + " seconds.");
                    if (args.length < 1){
                        player.sendMessage("You must provide" + " " + player.getName());
                    }
                    if (args.length == 0) {
                        Location randomLocation = teleportutils.findSafeLocation(player);
                        Location spawn = teleportutils.findSafeSpawnLocation(randomLocation);
                        player.teleport(spawn);
                        player.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Teleported to Random Location!");
                        player.sendMessage(ChatColor.AQUA + "New Coordinates: " + ChatColor.LIGHT_PURPLE + randomLocation.getX() + " " + randomLocation.getY() + " " + randomLocation.getZ());
                        cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                    }
                }
                if (args.length == 1 && player.hasPermission("rtp.others")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    Location randomLocation = teleportutils.findSafeLocation(player);
                    Location spawn = teleportutils.findSafeSpawnLocation(randomLocation);
                    if (target != null) {
                        target.teleport(spawn);
                        target.sendMessage(ChatColor.GREEN + player.getDisplayName() + ChatColor.GOLD + " just Random Teleported you!");
                        target.sendMessage(ChatColor.AQUA + "New Coordinates: " + ChatColor.LIGHT_PURPLE + randomLocation.getX() + " " + randomLocation.getY() + " " + randomLocation.getZ());
                        player.sendMessage(ChatColor.RED + "Player successfully teleported to: " + ChatColor.LIGHT_PURPLE + randomLocation.getX() + " " + randomLocation.getY() + " " + randomLocation.getZ());
                    }
                }
            } else {
                System.out.println("You need to be a player to execute this command.");
            }
        return true;
        }
    }