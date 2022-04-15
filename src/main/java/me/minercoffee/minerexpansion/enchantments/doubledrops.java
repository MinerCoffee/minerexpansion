package me.minercoffee.minerexpansion.enchantments;

import me.minercoffee.minerexpansion.MinerExpansion;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class doubledrops implements Listener, CommandExecutor {
    public doubledrops(MinerExpansion plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent e){
        Block blockBroken = e.getBlock();
        if(blockBroken.getType() == Material.DIAMOND_ORE){
            e.setCancelled(true);
            blockBroken.setType(Material.AIR);
            ItemStack item = new ItemStack(Material.DIAMOND, 2);
            blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), item);
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return true;
    }
}