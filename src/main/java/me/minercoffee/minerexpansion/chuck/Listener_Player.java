package me.minercoffee.minerexpansion.chuck;

import me.minercoffee.minerexpansion.MinerExpansion;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Listener_Player implements Listener {

    public Listener_Player(MinerExpansion plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (MinerExpansion.viewers.contains(player)) {
            Location loc = player.getLocation();
            World world = loc.getWorld();
            int index = MinerExpansion.viewers.indexOf(player);
            Location prevloc = MinerExpansion.viewerslocs.get(index);
            Chunk chunk = prevloc.getChunk();
            if (loc.getX() != MinerExpansion.viewerslocs.get(index).getX() || loc.getY() != MinerExpansion.viewerslocs.get(index).getY() || loc.getZ() != MinerExpansion.viewerslocs.get(index).getZ()) {
                chunk.getBlock(0, 0, 0).getLocation();
                Location corner1;
                chunk.getBlock(15, 0, 0).getLocation();
                Location corner2;
                chunk.getBlock(0, 0, 15).getLocation();
                Location corner3;
                chunk.getBlock(15, 0, 15).getLocation();
                Location corner4;

                for(int i = 0; i < 127; ++i) {
                    for(int i2 = 0; i2 < 15; ++i2) {
                        corner1 = chunk.getBlock(i2, i, 0).getLocation();
                        corner2 = chunk.getBlock(15, i, i2).getLocation();
                        corner3 = chunk.getBlock(15 - i2, i, 15).getLocation();
                        corner4 = chunk.getBlock(0, i, 15 - i2).getLocation();
                        if (corner1.getBlock().getType() == Material.AIR) {
                            player.sendBlockChange(corner1, Material.AIR, (byte)0);
                        }

                        if (corner2.getBlock().getType() == Material.AIR) {
                            player.sendBlockChange(corner2, Material.AIR, (byte)0);
                        }

                        if (corner3.getBlock().getType() == Material.AIR) {
                            player.sendBlockChange(corner3, Material.AIR, (byte)0);
                        }

                        if (corner4.getBlock().getType() == Material.AIR) {
                            player.sendBlockChange(corner4, Material.AIR, (byte)0);
                        }
                    }
                }

                player.sendMessage(ChatColor.GOLD + "Glass disabled because of movement.");
                MinerExpansion.viewers.remove(player);
                MinerExpansion.viewerslocs.remove(index);
            }
        }

    }
}
