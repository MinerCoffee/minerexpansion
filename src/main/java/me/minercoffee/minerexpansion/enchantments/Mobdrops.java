package me.minercoffee.minerexpansion.enchantments;

import me.minercoffee.minerexpansion.Items.itemscreation;
import me.minercoffee.minerexpansion.MinerExpansion;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
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
    private Player player;
    MinerExpansion plugin;
    public Mobdrops(MinerExpansion plugin){
        this.plugin = plugin;
    }
    //Copy skeletonDeath code on the other mobs for it to function.
    @EventHandler
    public void skeletonDeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity victim = e.getEntity();
        if (victim instanceof Skeleton) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(victim.getLocation().getWorld()).dropItemNaturally(victim.getLocation(), new ItemStack(Material.BONE, 1));
            }
        }
    }

    @EventHandler
    public void slimeDeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity victim = e.getEntity();
        if (victim instanceof Slime) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(victim.getLocation().getWorld()).dropItemNaturally(victim.getLocation(), new ItemStack(Material.SLIME_BALL, 1));
            }
        }
    }
    @EventHandler
    public void zombieDeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity victim = e.getEntity();
        if (victim instanceof Zombie) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(victim.getLocation().getWorld()).dropItemNaturally(victim.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
            }
        }
    }
    @EventHandler
    public void endermanDeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity victim = e.getEntity();
        if (victim instanceof Enderman) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(victim.getLocation().getWorld()).dropItemNaturally(victim.getLocation(), new ItemStack(Material.ENDER_PEARL, 1));
            }
        }
    }
    @EventHandler
    public void evokerDeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity victim = e.getEntity();
        if (victim instanceof Evoker) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                victim.getLocation().getWorld().dropItemNaturally(victim.getLocation(), new ItemStack(Material.EMERALD, 1));
                double chance = 5.0 / 100.0;
                if (random.nextDouble() <= chance) {
                    Objects.requireNonNull(victim.getLocation().getWorld()).dropItemNaturally(victim.getLocation(), new ItemStack(Material.TOTEM_OF_UNDYING, 1));
                }
            }
        }
    }
    @EventHandler
    public void cavespiderDeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity victim = e.getEntity();
        if (victim instanceof CaveSpider) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(victim.getLocation().getWorld()).dropItemNaturally(victim.getLocation(), new ItemStack(Material.STRING, 1));
            }
        }
    }
    @EventHandler
    public void zombiepigmanDeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity victim = e.getEntity();
        if (victim instanceof PigZombie) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(victim.getLocation().getWorld()).dropItemNaturally(victim.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
                double chance = 1.0 / 10.0;
                if (random.nextDouble() <= chance) {
                    victim.getLocation().getWorld().dropItemNaturally(victim.getLocation(), new ItemStack(Material.GOLD_NUGGET, 1));
                }
            }
        }
    }
    @EventHandler
    public void piglinDeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();
        if (entity instanceof Piglin) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
                double chance = 10.0 / 100.0;
                if (random.nextDouble() <= chance) {
                    entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.GOLDEN_SWORD, 1));  //needs to add a chance
                }
            }
        }
    }
    @EventHandler
    public void Vindicatordeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();
        if (entity instanceof Vindicator) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.EMERALD, 1)); //needs
                double chance = 05.0 / 100.0;
                if (random.nextDouble() <= chance) {
                    entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), itemscreation.Vindicatoraxe);
                }
            }
        }
    }
    @EventHandler
    public void Pillager(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();
        if (entity instanceof Pillager) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ARROW, 1));
            }
        }
    }
    @EventHandler
    public void ravagerDeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();
        if (entity instanceof Ravager) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.SADDLE, 1));
            }
        }
    }
    @EventHandler
    public void Guardiandeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();
        if (entity instanceof Guardian) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.TROPICAL_FISH, 1));
                double chance = 1.0 / 10.0;
                if (random.nextDouble() <= chance) {
                    entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.SALMON, 1));
                }
            }
        }
    }
    @EventHandler
    public void ElderGuardiandeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();
        if (entity instanceof ElderGuardian) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.SALMON, 1));
                entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.PRISMARINE_SHARD, 1));
                double chance = 1.0 / 10.0;
                if (random.nextDouble() <= chance) {
                    entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.PRISMARINE_CRYSTALS, 1));
                }
            }
        }
    }
    //continue here
    @EventHandler
    public void ShulkerDeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();
        if (entity instanceof Shulker) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                double chance = 20.0 / 100.0;
                if (random.nextDouble() <= chance) {
                    Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.SHULKER_SHELL, 1));
                }
            }
        }
    }
    @EventHandler
    public void skeletonhorseDeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();
        if (entity instanceof Skeleton) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
                double chance = 1.0 / 10.0;
                if (random.nextDouble() <= chance) {
                    Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.LEATHER, 1));
                }
            }
        }
    }
    @EventHandler
    public void HuskDeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();
        if (entity instanceof Husk) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
                double chance = 10.0 / 100.0;
                if (random.nextDouble() <= chance) {
                    Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.IRON_INGOT, 1));
                }
            }
        }
    }
    @EventHandler
    public void StrayDeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();
        if (entity instanceof Stray) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.BOW, 1));
                double chance = 15.0 / 100.0;
                if (random.nextDouble() <= chance) {
                    Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.BONE, 1));
                    Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.POTION, 1));
                }
            }
        }
    }
    @EventHandler
    public void phantomDeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();
        if (entity instanceof Phantom) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.PHANTOM_MEMBRANE, 1));
            }
        }
    }
    @EventHandler
    public void BlazeDeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();
        if (entity instanceof Blaze) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.BLAZE_ROD, 1));
            }
        }
    }
    @EventHandler
    public void Creeperdeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();
        if (entity instanceof Creeper) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.GUNPOWDER, 1));
            }
        }
    }
    @EventHandler
    public void GhastDeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();
        if (entity instanceof Ghast) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.GHAST_TEAR, 1));
            }
        }
    }
    @EventHandler
    public void MagacubeDeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();
        if (entity instanceof MagmaCube) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.MAGMA_CREAM, 1));
            }
        }
    }
    @EventHandler
    public void ZombieVillagerDeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();
        if (entity instanceof ZombieVillager) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
            }
        }
    }
    @EventHandler
    public void drownedDeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();
        if (entity instanceof Drowned) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
                double chance = 15.0 / 100.0;
                if (random.nextDouble() <= chance) {
                    Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.COPPER_INGOT, 1));
                }
                double chance1 = 8.0 / 100.0;
                if (random.nextDouble() <= chance1) {
                    Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.TRIDENT, 1));
                }
            }
        }
    }
    @EventHandler
    public void witherskeletonDeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();
        if (entity instanceof WitherSkeleton) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                double chance1 = 15.0 / 100.0;
                if (random.nextDouble() <= chance1) {
                    Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.BONE, 1));
                    Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.COAL, 1));
                }
            }
        }
    }
    @EventHandler
    public void Witch(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();
        if (entity instanceof Witch) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.REDSTONE, 1));
            }
        }
    }
    @EventHandler
    public void PiglinBruteDeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();
        if (entity instanceof PiglinBrute) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.GOLDEN_AXE, 1));
            }
        }
    }
    @EventHandler
    public void ZoglinDeath(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity entity = e.getEntity();
        if (entity instanceof Zoglin) {
            player = (Player) damager;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(MobdropsUtils.MobDrops) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(MobdropsUtils.MobDrops)) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
            }
        }
    }
    @EventHandler
    public void AddEnchantment(PrepareAnvilEvent e) {
        Player player = (Player) e.getView().getPlayer();
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
                player.updateInventory();
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
