package me.minercoffee.minerexpansion.chuck;
import me.minercoffee.minerexpansion.MinerExpansion;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
    @SuppressWarnings("deprecation")
    public class seechucks implements CommandExecutor {
        MinerExpansion plugin;
        @SuppressWarnings("deprecation")
        public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only works from players!");
            } else {
                Player player = (Player)sender;
                if (!player.hasPermission("chunkvisualizer.view") && !player.isOp() && this.plugin.getConfig().getBoolean("requiresop")) {
                    player.sendMessage(ChatColor.GOLD + "You do not have permission to execute this command.");
                } else
                if (args[0].equalsIgnoreCase("on")) {
                    player.playSound(player.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 1, 1);
                    MinerExpansion.viewers.add(player);
                    MinerExpansion.viewerslocs.add(player.getLocation());
                    Chunk chunk = player.getLocation().getChunk();
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
                                player.sendBlockChange(corner1, Material.GLASS, (byte)0);
                            }

                            if (corner2.getBlock().getType() == Material.AIR) {
                                player.sendBlockChange(corner2, Material.GLASS, (byte)0);
                            }

                            if (corner3.getBlock().getType() == Material.AIR) {
                                player.sendBlockChange(corner3, Material.GLASS, (byte)0);
                            }

                            if (corner4.getBlock().getType() == Material.AIR) {
                                player.sendBlockChange(corner4, Material.GLASS, (byte)0);
                            }
                        }
                    }

                    player.sendMessage(ChatColor.GOLD + "Glass blocks now (inclusively) showing the border of your current chunk.");
                } else
                if (args[0].equalsIgnoreCase("off")) {
                    MinerExpansion.viewers.remove(player);
                    player.sendMessage(ChatColor.GOLD + "Turning off chuck visualizer");
                    player.playSound(player.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 1, 1);
                }
            }
            return true;
        }
    }