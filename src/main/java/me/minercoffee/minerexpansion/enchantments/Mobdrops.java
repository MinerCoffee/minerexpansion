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
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
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
    public Mobdrops(MinerExpansion plugin){
        this.plugin =plugin;
    }
    @EventHandler
    public void skeletonDeath(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();
        if (entity instanceof Skeleton){
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.BONE, 1));
        }
    }
    @EventHandler
    public void slimeDeath(EntityDeathEvent s){
        LivingEntity entity = s.getEntity();
        if (entity instanceof Slime) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.SLIME_BALL, 1));
        }
    }
    @EventHandler
    public void zombieDeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof Zombie) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
        }
    }
    @EventHandler
    public void endermanDeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof Enderman) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ENDER_EYE, 1));
        }
    }
    @EventHandler
    public void evokerDeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof Evoker) {
            entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.EMERALD, 1));
            double chance = 5.0 / 100.0;
            if (random.nextDouble() <= chance) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.TOTEM_OF_UNDYING, 1));
            }
        }
    }
    @EventHandler
    public void cavespiderDeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof CaveSpider) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.STRING, 1));
        }
    }
    @EventHandler
    public void zombiepigmanDeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof PigZombie) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
            double chance = 1.0 / 10.0;
            if (random.nextDouble() <= chance) {
                entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.GOLD_NUGGET, 1));
            }
        }
    }
    @EventHandler
    public void piglinDeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof Piglin) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
            double chance = 10.0 / 100.0;
            if (random.nextDouble() <= chance) {
                entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.GOLDEN_SWORD, 1));  //needs to add a chance
            }
        }
    }
    @EventHandler
    public void Vindicatordeath(EntityDeathEvent z) {

        LivingEntity entity = z.getEntity();
        if (entity instanceof Vindicator) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.EMERALD, 1)); //needs
            double chance = 05.0 / 100.0;
            if (random.nextDouble() <= chance) {
                entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), itemscreation.Vindicatoraxe);
            }
        }
    }
    @EventHandler
    public void Pillager(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof Pillager) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ARROW, 1));
        }
    }
    @EventHandler
    public void ravagerDeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof Ravager) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.SADDLE, 1));
        }
    }
    @EventHandler
    public void Guardiandeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof Guardian) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.TROPICAL_FISH, 1));
            double chance = 1.0 / 10.0;
            if (random.nextDouble() <= chance) {
                entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.SALMON, 1));
            }
        }
    }
    @EventHandler
    public void ElderGuardiandeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof ElderGuardian) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.SALMON, 1));
            entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.PRISMARINE_SHARD, 1));
            double chance = 1.0 / 10.0;
            if (random.nextDouble() <= chance) {
                entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), new ItemStack(Material.PRISMARINE_CRYSTALS, 1));
            }
        }
    }
    @EventHandler
    public void ShulkerDeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof Shulker) {
            double chance = 20.0 / 100.0;
            if (random.nextDouble() <= chance) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.SHULKER_SHELL, 1));
            }
        }
    }
    @EventHandler
    public void skeletonhorseDeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof SkeletonHorse) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
            double chance = 1.0 / 10.0;
            if (random.nextDouble() <= chance) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.LEATHER, 1));
            }
        }
    }
    @EventHandler
    public void HuskDeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof Husk) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
            double chance = 10.0 / 100.0;
            if (random.nextDouble() <= chance) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.IRON_INGOT, 1));
            }
        }
    }
    @EventHandler
    public void StrayDeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof Stray) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.BOW, 1));
            double chance = 15.0 / 100.0;
            if (random.nextDouble() <= chance) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.BONE, 1));
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.POTION, 1));
            }
        }
    }
    @EventHandler
    public void phantomDeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof Phantom) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.PHANTOM_MEMBRANE, 1));
        }
    }
    @EventHandler
    public void BlazeDeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof Blaze) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.BLAZE_ROD, 1));
        }
    }
    @EventHandler
    public void Creeperdeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof Creeper) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.GUNPOWDER, 1));
        }
    }
    @EventHandler
    public void GhastDeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof Ghast) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.GHAST_TEAR, 1));
        }
    }
    @EventHandler
    public void MagacubeDeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof MagmaCube) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.MAGMA_CREAM, 1));
        }
    }
    @EventHandler
    public void ZombieVillagerDeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof ZombieVillager) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
        }
    }
    @EventHandler
    public void drownedDeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof Drowned) {
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
    @EventHandler
    public void witherskeletonDeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof WitherSkeleton) {
            double chance1 = 15.0 / 100.0;
            if (random.nextDouble() <= chance1) {
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.BONE, 1));
                Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.COAL, 1));
            }
        }
    }
    @EventHandler
    public void Witch(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof Witch) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.NETHER_STAR, 1));
        }
    }
    @EventHandler
    public void PiglinBruteDeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof PiglinBrute) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.GOLDEN_AXE, 1));
        }
    }
    @EventHandler
    public void ZoglinDeath(EntityDeathEvent z) {
        LivingEntity entity = z.getEntity();
        if (entity instanceof Zoglin) {
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItemNaturally(entity.getLocation(), new ItemStack(Material.ROTTEN_FLESH, 1));
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
