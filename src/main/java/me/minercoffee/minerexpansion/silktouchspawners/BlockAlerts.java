package me.minercoffee.minerexpansion.silktouchspawners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static me.minercoffee.minerexpansion.MinerExpansion.plugin;

public class BlockAlerts implements Listener {

    int i = 1;

    @EventHandler
    public void onBreakOreBlock(BlockBreakEvent event) {
        if (plugin.getConfig().getBoolean("Ore-alerts")) {
            Player p = event.getPlayer();
            Block block = event.getBlock();
            Material material = block.getType();
            if ((i != 8000000)) {
                if (material.equals(Material.valueOf(plugin.getConfig().getString(("ore_check_material_1")))) || (material.equals(Material.valueOf(plugin.getConfig().getString(("ore_check_material_2")))))) {
                    plugin.ore_players.add(p);
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        if (online.hasPermission("miner.staff")) {
                            online.sendMessage(ChatColor.RED + p.getName() + " has mined " + ChatColor.GOLD + material + " " + i);
                            online.playSound(online.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 2, 2);
                            i++;
                        }
                    }
                }
                if (material.equals(Material.valueOf(plugin.getConfig().getString(("ore_check_material_3")))) || (material.equals(Material.valueOf(plugin.getConfig().getString(("ore_check_material_4")))))) {
                    plugin.ore_players.add(p);
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        if (online.hasPermission("miner.staff")) {
                            online.sendMessage(ChatColor.RED + p.getName() + " has mined " + ChatColor.GOLD + material + " " + i);
                            online.playSound(online.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 2, 2);
                            i++;
                        }
                    }
                }
                if (material.equals(Material.valueOf(plugin.getConfig().getString(("ore_check_material_5")))) || (material.equals(Material.valueOf(plugin.getConfig().getString(("ore_check_material_6")))))) {
                    plugin.ore_players.add(p);
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        if (online.hasPermission("miner.staff")) {
                            online.sendMessage(ChatColor.RED + p.getName() + " has mined " + ChatColor.GOLD + material + " " + i);
                            online.playSound(online.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 2, 2);
                            i++;
                        }
                    }
                }
                if (material.equals(Material.valueOf(plugin.getConfig().getString(("ore_check_material_7")))) || (material.equals(Material.valueOf(plugin.getConfig().getString(("ore_check_material_8")))))) {
                    plugin.ore_players.add(p);
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        if (online.hasPermission("miner.staff")) {
                            online.sendMessage(ChatColor.RED + p.getName() + " has mined " + ChatColor.GOLD + material + " " + i);
                            online.playSound(online.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 2, 2);
                            i++;
                        }
                    }
                }
                if (material.equals(Material.valueOf(plugin.getConfig().getString(("ore_check_material_9")))) || (material.equals(Material.valueOf(plugin.getConfig().getString(("ore_check_material_10")))))) {
                    plugin.ore_players.add(p);
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        if (online.hasPermission("miner.staff")) {
                            online.sendMessage(ChatColor.RED + p.getName() + " has mined " + ChatColor.GOLD + material + " " + i);
                            online.playSound(online.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 2, 2);
                            i++;
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        plugin.ore_players.remove(e.getPlayer());
    }
    @EventHandler
    public void onRejoin(PlayerJoinEvent e){
        plugin.ore_players.add(e.getPlayer());
    }
}