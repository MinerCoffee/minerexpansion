package me.minercoffee.minerexpansion.supplydrop.utils;

import java.io.IOException;
import java.util.Objects;

import me.minercoffee.minerexpansion.MinerExpansion;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;




public class Drop {
    FileConfiguration cfg = MinerExpansion.getPlugin().getConfig();

    public Drop(final String dropName, final World curWorld, final int locX, final int locY, final int locZ, int height, final boolean isEnvoy) {
        (new BukkitRunnable() {
            int currentDropHeight;
            {
                this.currentDropHeight = height;
            }

            public void run() {
                Location prevLoc = new Location(curWorld, locX, this.currentDropHeight + 1, locZ);
                Location dropLoc = new Location(curWorld, locX, this.currentDropHeight, locZ);
                if (this.currentDropHeight > locY) {
                    prevLoc.getBlock().setType(Material.AIR);
                    dropLoc.getBlock().setType(Material.CHEST);
                    --this.currentDropHeight;
                } else {
                    prevLoc.getBlock().setType(Material.AIR);
                    dropLoc.getBlock().setType(Material.CHEST);
                    if (cfg != null) {
                        if (Drop.this.cfg.getBoolean("Options.SpawnTorchesAround") && !isEnvoy) {
                            Drop.this.setBlock(curWorld, locX + 1, locY, locZ, Material.REDSTONE_TORCH);
                            Drop.this.setBlock(curWorld, locX, locY, locZ + 1, Material.REDSTONE_TORCH);
                            Drop.this.setBlock(curWorld, locX - 1, locY, locZ, Material.REDSTONE_TORCH);
                            Drop.this.setBlock(curWorld, locX, locY, locZ - 1, Material.REDSTONE_TORCH);
                        }

                        Chest chest = (Chest) dropLoc.getBlock().getState();
                        Inventory inv = chest.getInventory();
                        chest.setCustomName(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(Drop.this.cfg.getString("Options.SupplyDropName")).replaceAll("%dropName%", dropName)));
                        chest.update();

                        try {
                            Inventory loadedDropInv = InventorySerializer.fromBase64(SupplyDropsDataManager.getSupplyDropsData().getString("drops." + dropName));
                            ItemStack[] dropInvItems = loadedDropInv.getContents();

                            for (int i = 0; i < dropInvItems.length; ++i) {
                                if (dropInvItems[i] != null) {
                                    inv.setItem(i, dropInvItems[i]);
                                }
                            }
                        } catch (IOException var8) {
                            var8.printStackTrace();
                        }

                        Objects.requireNonNull(dropLoc.getWorld()).spawnParticle(Particle.HEART, locX, locY + 1, locZ, 6);
                        dropLoc.getWorld().spawnParticle(Particle.END_ROD, locX, locY + 1, locZ, 3);
                        dropLoc.getWorld().playSound(dropLoc, Sound.ITEM_SHOVEL_FLATTEN, 1.0F, -10.0F);
                        Firework f = dropLoc.getWorld().spawn(new Location(curWorld, (double) locX + 0.5D, locY, (double) locZ + 0.5D), Firework.class);
                        FireworkMeta meta = f.getFireworkMeta();
                        meta.addEffect(FireworkEffect.builder().flicker(false).trail(false).with(Type.BURST).withColor(Color.RED).withColor(Color.WHITE).withFade(Color.GRAY).build());
                        f.setFireworkMeta(meta);
                        f.eject();
                        this.cancel();
                    }
                }

            }
        }).runTaskTimer(MinerExpansion.getPlugin(), isEnvoy ? 0L : 3L, 8L);
    }

    public void setBlock(World world, int x, int y, int z, Material matName) {
        (new Location(world, x, y, z)).getBlock().setType(matName);
    }
}
