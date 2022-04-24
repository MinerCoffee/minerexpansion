package me.minercoffee.minerexpansion.silktouchspawners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import static me.minercoffee.minerexpansion.MinerExpansion.plugin;

public class BreakBlockListener implements Listener {

    @EventHandler
    public void onBreakBlock(BlockBreakEvent e) {
        Block blockBroken = e.getBlock();
        if (plugin.getConfig().getBoolean("SilkSpawners")) {
            if (blockBroken.getType().equals(Material.SPAWNER) && e.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                Bukkit.getServer().getPluginManager().callEvent(new SpawnerBreakEvent(e.getPlayer(), blockBroken));
            }
        }
    }
}