package me.minercoffee.minerexpansion.Items;

import me.minercoffee.minerexpansion.MinerExpansion;
import me.minercoffee.minerexpansion.enchantments.MobdropsUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import java.util.*;
import static org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK;

@SuppressWarnings("deprecation")
public class ThrowingAxe implements Listener {

    MinerExpansion plugin;

    public ThrowingAxe(MinerExpansion plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void AddEnchantment(PrepareAnvilEvent e) {
        Player damager = (Player) e.getView().getPlayer();
        if (e.getInventory().getItem(1) == null || e.getInventory().getItem(0) == null) return;
        if (Objects.requireNonNull(e.getInventory().getItem(0)).isSimilar(itemscreation.ThrowingAxe) || (Objects.requireNonNull(e.getInventory().getItem(1)).isSimilar(itemscreation.ThrowingAxe))){
            ItemStack a = new ItemStack(Objects.requireNonNull(e.getInventory().getItem(0)));
            ItemMeta meta = a.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add("§7Damage: §c+5");
            lore.add("");
            lore.add("§6Item Ability: Throw §eRIGHT CLICK");
            lore.add("§7Throw your axe and deal");
            lore.add("§c5 §7damage.");
            if (meta != null && meta.hasLore()) lore.addAll(Objects.requireNonNull(meta.getLore()));
            if (meta != null) {
                meta.setLore(lore);
            }
            a.setItemMeta(meta);
            e.getInventory().setRepairCost(40);
            e.setResult(a);
            damager.updateInventory();
            plugin.getServer().getScheduler().runTask(plugin, () -> e.getInventory().setRepairCost(20));
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInHand();
            if (event.getAction().equals(RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                if (item.getItemMeta() != null && item.getItemMeta().getLore() != null
                        && item.getItemMeta().getLore().contains("§6Item Ability: Throw §eRIGHT CLICK")) {
                    String lore = item.getItemMeta().getLore().get(4);
                    List<String> loresplit = new ArrayList<>(Arrays.asList(lore.split(" ")));
                    String damage = loresplit.get(0);
                    damage = damage.replaceAll("§c", "");
                    damage = damage.replaceAll(",", "");
                    String finaldamage = damage;
                    ArmorStand as = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(0, 0.5, 0), EntityType.ARMOR_STAND);
                    as.setArms(true);
                    as.setGravity(false);
                    as.setVisible(false);
                    as.setSmall(true);
                    as.setMarker(true);
                    as.setItemInHand(new ItemStack(Material.NETHERITE_AXE));
                    as.setRightArmPose(new EulerAngle(Math.toRadians(90), Math.toRadians(0), Math.toRadians(0)));
                    item.setAmount(item.getAmount() - 1);
                    Location dest = player.getLocation().add(player.getLocation().getDirection().multiply(10));
                    Vector vector = dest.subtract(player.getLocation()).toVector();

                    new BukkitRunnable() {
                        final int distance = 30;
                        int i = 0;

                        public void run() {

                            EulerAngle rot = as.getRightArmPose();
                            EulerAngle rotnew = rot.add(20, 0, 0);
                            as.setRightArmPose(rotnew);

                            as.teleport(as.getLocation().add(vector.normalize()));

                            if (as.getTargetBlockExact(1) != null && !Objects.requireNonNull(as.getTargetBlockExact(1)).isPassable()) {
                                if (!as.isDead()) {
                                    as.remove();
                                    if (player.getInventory().firstEmpty() != -1) {
                                        player.getInventory().addItem(itemscreation.ThrowingAxe);
                                    } else {
                                        player.getWorld().dropItemNaturally(player.getLocation(), itemscreation.ThrowingAxe);
                                    }
                                    cancel();
                                }
                            }

                            for (Entity entity : as.getLocation().getChunk().getEntities()) {
                                if (!as.isDead()) {
                                    if (as.getLocation().distanceSquared(entity.getLocation()) <= 1) {
                                        if (entity != player && entity != as) {
                                            if (entity instanceof LivingEntity) {
                                                LivingEntity livingentity = (LivingEntity) entity;
                                                livingentity.damage(Integer.parseInt(finaldamage), player);
                                                as.remove();
                                                if (player.getInventory().firstEmpty() != -1) {
                                                    player.getInventory().addItem(itemscreation.ThrowingAxe);
                                                } else {
                                                    player.getWorld().dropItemNaturally(player.getLocation(), itemscreation.ThrowingAxe);
                                                }
                                                cancel();
                                            }
                                        }
                                    }
                                }
                            }

                            if (i > distance) {
                                if (!as.isDead()) {
                                    as.remove();
                                    if (player.getInventory().firstEmpty() != -1) {
                                        player.getInventory().addItem(itemscreation.ThrowingAxe);
                                    } else {
                                        player.getWorld().dropItemNaturally(player.getLocation(), itemscreation.ThrowingAxe);
                                    }
                                    cancel();
                                }
                            }

                            i++;
                        }
                    }.runTaskTimer(plugin, 0L, 1L);

                    event.setCancelled(true);
                }
            }
        }
    }
