package me.minercoffee.minerexpansion.enchantments;

import me.minercoffee.minerexpansion.Items.itemscreation;
import me.minercoffee.minerexpansion.MinerExpansion;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;


public class Mobdrops implements Listener, CommandExecutor {
    private final Random random = new Random();
    MinerExpansion plugin;

    public Mobdrops(MinerExpansion plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void skeletonDeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager = (Player) e.getDamager();
                if (entity instanceof Skeleton) return;
                if (entity.isDead()) return;
                if (damager.getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                    double chance1 = 15 / 100.0;
                    if (random.nextDouble() <= chance1) {
                        damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                        Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.BONE, 2));
                    }
                }
            }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void slimeDeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager = (Player) e.getDamager();
                if (entity instanceof Slime) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                        double chance1 = 10.0 / 100.0;
                        if (random.nextDouble() <= chance1) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.SLIME_BALL, 1));
                        }
                    }
                }
            }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void zombieDeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager = (Player) e.getDamager();
                if (entity instanceof Zombie) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                        double chance1 = 10.0 / 100.0;
                        if (random.nextDouble() <= chance1) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
                        }
                    }
                }
            }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void endermanDeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager = (Player) e.getDamager();
                if (entity instanceof Enderman) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                        double chance1 = 10.0 / 100.0;
                        if (random.nextDouble() <= chance1) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ENDER_PEARL, 1));
                        }
                    }
                }
            }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void evokerDeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager = (Player) e.getDamager();
                if (entity instanceof Evoker) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                        double chance1 = 10.0 / 100.0;
                        if (random.nextDouble() <= chance1) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.EMERALD, 1));
                        }
                        double chance2 = 5.0 / 100.0;
                        if (random.nextDouble() <= chance2) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.TOTEM_OF_UNDYING, 1));
                        }
                    }
                }
            }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void cavespiderDeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager = (Player) e.getDamager();
                if (entity instanceof CaveSpider) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                        double chance1 = 10.0 / 100.0;
                        if (random.nextDouble() <= chance1) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.STRING, 1));
                        }
                    }
                }
            }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void zombiepigmanDeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager = (Player) e.getDamager();
                if (entity instanceof PigZombie) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                        Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
                        double chance = 10.0 / 100.0;
                        if (random.nextDouble() <= chance) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.GOLD_NUGGET, 1));
                        }
                    }
                }
            }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void piglinDeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager = (Player) e.getDamager();
                if (entity instanceof Piglin) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                        double chance1 = 20.0 / 100.0;
                        if (random.nextDouble() <= chance1) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
                        }
                        double chance2 = 10.0 / 100.0;
                        if (random.nextDouble() <= chance2) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.GOLDEN_SWORD, 1));
                        }
                    }
                }
            }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void Vindicatordeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager = (Player) e.getDamager();
                if (entity instanceof Vindicator) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                        double chance1 = 10.0 / 100.0;
                        if (random.nextDouble() <= chance1) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.EMERALD, 1));
                        }
                        double chance2 = 5.0 / 100.0;
                        if (random.nextDouble() <= chance2) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), itemscreation.Vindicatoraxe);
                        }
                    }
                }
            }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void Pillager(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager = (Player) e.getDamager();
                if (entity instanceof Pillager) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                        double chance1 = 10.0 / 100.0;
                        if (random.nextDouble() <= chance1) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ARROW, 1));
                        }
                    }
                }
            }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();

        }
    }

    @EventHandler
    public void ravagerDeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager = (Player) e.getDamager();
                if (entity instanceof Ravager) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                        double chance1 = 10.0 / 100.0;
                        if (random.nextDouble() <= chance1) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.SADDLE, 1));
                        }
                    }
                }
            }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void Guardiandeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager = (Player) e.getDamager();
                if (entity instanceof Guardian) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                        double chance1 = 20.0 / 100.0;
                        if (random.nextDouble() <= chance1) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.TROPICAL_FISH, 1));
                        }
                        double chance2 = 35.0 / 100.0;
                        if (random.nextDouble() <= chance2) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.SALMON, 1));
                        }
                    }
                }
            }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void ElderGuardiandeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager = (Player) e.getDamager();
                if (entity instanceof ElderGuardian) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                        double chance1 = 30.0 / 100.0;
                        if (random.nextDouble() <= chance1) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.SALMON, 1));
                        }
                        double chance2 = 20.0 / 100.0;
                        if (random.nextDouble() <= chance2) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.PRISMARINE_SHARD, 1));
                        }
                        double chance3 = 10.0 / 100.0;
                        if (random.nextDouble() <= chance3) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.PRISMARINE_CRYSTALS, 1));
                        }
                    }
                }
            }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void ShulkerDeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager = (Player) e.getDamager();
                if (entity instanceof Shulker) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                        double chance = 20.0 / 100.0;
                        if (random.nextDouble() <= chance) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.SHULKER_SHELL, 1));
                        }
                    }
                }
            }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void skeletonhorseDeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager = (Player) e.getDamager();
                if (entity instanceof SkeletonHorse) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                        double chance1 = 20.0 / 100.0;
                        if (random.nextDouble() <= chance1) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
                        }
                        double chance2 = 10.0 / 100.0;
                        if (random.nextDouble() <= chance2) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.LEATHER, 1));
                        }
                    }
                }
            }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void HuskDeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager = (Player) e.getDamager();
                if (entity instanceof Husk) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                        double chance1 = 20.0 / 100.0;
                        if (random.nextDouble() <= chance1) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
                        }
                        double chance2 = 10.0 / 100.0;
                        if (random.nextDouble() <= chance2) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.IRON_INGOT, 1));
                        }
                    }
                }
            }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void StrayDeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager = (Player) e.getDamager();
                if (entity instanceof Stray) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                        double chance1 = 10.0 / 100.0;
                        if (random.nextDouble() <= chance1) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.BOW, 1));
                        }
                        double chance2 = 15.0 / 100.0;
                        if (random.nextDouble() <= chance2) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.BONE, 1));
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.POTION, 1));
                        }
                    }
                }
            }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void phantomDeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager = (Player) e.getDamager();
                if (entity instanceof Phantom) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                        double chance1 = 10.0 / 100.0;
                        if (random.nextDouble() <= chance1) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.PHANTOM_MEMBRANE, 1));
                        }
                    }
                }
            }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void BlazeDeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager = (Player) e.getDamager();
                if (entity instanceof Blaze) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                        double chance1 = 10.0 / 100.0;
                        if (random.nextDouble() <= chance1) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.BLAZE_ROD, 2));
                        }
                    }
                }
            }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }
    @EventHandler
    public void Creeperdeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager =  (Player) e.getDamager();
                if(entity instanceof Creeper) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                        double chance1 = 10.0 / 100.0;
                        if (random.nextDouble() <= chance1) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.GUNPOWDER, 2));
                            }
                        }
                }
            }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }
    @EventHandler
    public void GhastDeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager =  (Player) e.getDamager();
                if(entity instanceof Ghast) {
                    if (entity.isDead()) return;
                        if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                            double chance1 = 10.0 / 100.0;
                            if (random.nextDouble() <= chance1) {
                                damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.GHAST_TEAR, 2));
                            }
                        }
                    }
                }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }
    @EventHandler
    public void MagacubeDeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager =  (Player) e.getDamager();
                if(entity instanceof MagmaCube) {
                    if (entity.isDead()) return;
                        if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                            double chance1 = 10.0 / 100.0;
                            if (random.nextDouble() <= chance1) {
                                damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.MAGMA_CREAM, 1));
                            }
                        }
                    }
                }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }
    @EventHandler
    public void ZombieVillagerDeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager =  (Player) e.getDamager();
                if(entity instanceof ZombieVillager) {
                    if (entity.isDead()) return;
                        if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                            double chance1 = 10.0 / 100.0;
                            if (random.nextDouble() <= chance1) {
                                damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
                            }
                        }
                    }
            }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }
    @EventHandler
    public void drownedDeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager =  (Player) e.getDamager();
                if(entity instanceof Drowned) {
                    if (entity.isDead()) return;
                        if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
                            double chance = 15.0 / 100.0;
                            if (random.nextDouble() <= chance) {
                                damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.COPPER_INGOT, 1));
                            }
                            double chance1 = 8.0 / 100.0;
                            if (random.nextDouble() <= chance1) {
                                damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.TRIDENT, 1));
                            }
                        }
                    }
                }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }
    @EventHandler
    public void witherskeletonDeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager =  (Player) e.getDamager();
                if(entity instanceof WitherSkeleton) {
                    if (entity.isDead()) return;
                        if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                            double chance1 = 15.0 / 100.0;
                            if (random.nextDouble() <= chance1) {
                                damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.BONE, 1));
                                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.COAL, 1));
                            }
                        }
                    }
                }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }
    @EventHandler
    public void Witch(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager =  (Player) e.getDamager();
                if(entity instanceof Witch) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                        double chance1 = 15 / 100.0;
                        if (random.nextDouble() <= chance1) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.REDSTONE, 1));
                                    }
                                }
                        }
                    }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }
    @EventHandler
    public void PiglinBruteDeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager =  (Player) e.getDamager();
                if(entity instanceof PiglinBrute) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                            double chance1 = 10.0 / 100.0;
                            if (random.nextDouble() <= chance1) {
                                damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.GOLDEN_AXE, 1));
                            }
                        }
                    }
                }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }
    @EventHandler
    public void ZoglinDeath(EntityDamageByEntityEvent e) {
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager =  (Player) e.getDamager();
                if(entity instanceof Zoglin) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                            double chance1 = 10.0 / 100.0;
                            if (random.nextDouble() <= chance1) {
                                damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 10, 1);
                                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
                            }
                        }
                    }
                }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }
    @EventHandler
    public void WardenDeath(EntityDamageByEntityEvent e){
        try {
            Entity entity = e.getEntity();
            if (e.getDamager() instanceof Player) {
                Player damager =  (Player) e.getDamager();
                if(entity instanceof Warden) {
                    if (entity.isDead()) return;
                    if ((damager).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (damager).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                        double reward1 = 03.0 / 100.0;
                        if (random.nextDouble() <= reward1) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 33, 3);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.GOLDEN_APPLE, 1));
                        }
                        double reward2 = 05.0 / 100.0;
                        if (random.nextDouble() <= reward2) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 33, 3);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.BREAD, 2));
                        }
                        double reward3 = 08.0 / 100.0;
                        if (random.nextDouble() <= reward3) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 33, 3);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.COAL, 4));
                        }
                        double reward4 = 10.0 / 100.0;
                        if (random.nextDouble() <= reward4) {
                            damager.playSound(damager.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, 33, 3);
                            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.STICK, 3));
                        }
                    }
                }
            }
        } catch (ExceptionInInitializerError ex) {
            ex.printStackTrace();
        }
    }
    @EventHandler
    public void AddEnchantment(PrepareAnvilEvent e) {
        Player damager = (Player) e.getView().getPlayer();
        if (e.getInventory().getItem(1) == null || e.getInventory().getItem(0) == null) return;
        if (Objects.requireNonNull(e.getInventory().getItem(1)).containsEnchantment(MobdropsUtils.MobDrops)) {
                ItemStack a = new ItemStack(Objects.requireNonNull(e.getInventory().getItem(0)));
                a.addEnchantment(MobdropsUtils.MobDrops, (Objects.requireNonNull(e.getInventory().getItem(1))).getEnchantmentLevel(MobdropsUtils.MobDrops));
                ItemMeta meta = a.getItemMeta();
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.GRAY + "MobDrops I");
                if (meta != null && meta.hasLore()) lore.addAll(Objects.requireNonNull(meta.getLore()));
                if (meta != null) {
                    meta.setLore(lore);
                }
                a.setItemMeta(meta);
                e.getInventory().setRepairCost(40);
                e.setResult(a);
                damager.updateInventory();
                plugin.getServer().getScheduler().runTask(plugin, () -> e.getInventory().setRepairCost(40));
            }
        }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.isOp()){
                if (command.getName().equalsIgnoreCase("mobdrops")) {
                    ItemStack book = new ItemStack((Material.ENCHANTED_BOOK), 1);
                    book.addUnsafeEnchantment(MobdropsUtils.MobDrops, 1);
                    List<String> lore = new ArrayList<>();
                    lore.add(ChatColor.GRAY + "MobDrops I");
                    ItemMeta meta = book.getItemMeta();
                    if (meta != null && meta.hasLore()) lore.addAll(Objects.requireNonNull(meta.getLore()));
                    if (meta != null) {
                        meta.setLore(lore);
                    }
                    book.setItemMeta(meta);
                    p.getInventory().addItem(book);
                    p.closeInventory();
                    p.updateInventory();
                }
            }
        }
        return true;
    }
}
