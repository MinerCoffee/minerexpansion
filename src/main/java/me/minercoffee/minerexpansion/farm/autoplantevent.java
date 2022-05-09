package me.minercoffee.minerexpansion.farm;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class autoplantevent implements Listener {

    @EventHandler
    public void onReplant(BlockBreakEvent e){
        Player player = e.getPlayer();
        Block block = e.getBlock();
        Material material = block.getType();
    }
}
