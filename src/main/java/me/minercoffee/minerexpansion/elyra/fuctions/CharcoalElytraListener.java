package me.minercoffee.minerexpansion.elyra.fuctions;

import me.minercoffee.minerexpansion.Items.itemscreation;
import me.minercoffee.minerexpansion.utils.ColorMsg;
import org.bukkit.*;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

import static me.minercoffee.minerexpansion.MinerExpansion.plugin;

public class CharcoalElytraListener implements Listener {
    private final List<Player> sneakingPlayers = new ArrayList<>();
    private final List<Player> chargingPlayers = new ArrayList<>();
    private final HashMap<Player, Long> cooldowns = new HashMap<>();

    private boolean isHoldingSneak(Player p) {
        return this.sneakingPlayers.contains(p);
    }

    private static int getCooldown() {
        return 3000;
    }

    private float getVelocityMultiplier() {
        return 2.3F;
    }

    @EventHandler
    public void boost(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (p.isGliding()) {
            if (this.isHoldingSneak(p)) {
                if (itemscreation.hasCharcoalElytra(p)) {
                    if (this.cooldowns.containsKey(p) && this.cooldowns.get(p) > System.currentTimeMillis()) {
                        p.sendMessage(ColorMsg.color("&5&lYou cannot boost yet"));
                    } else {
                        this.cooldowns.put(p, System.currentTimeMillis() + (long) getCooldown());
                        p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 10.0F, 3.0F);
                        p.setVelocity(p.getLocation().getDirection().multiply(this.getVelocityMultiplier()));
                    }
                }
            }
        }
    }

    @EventHandler
    public void launch(PlayerStatisticIncrementEvent e) {
        Player p = e.getPlayer();
        if (itemscreation.hasCharcoalElytra(p)){
            if (e.getStatistic().equals(Statistic.JUMP)) {
                    if (!(p.getLocation().getPitch() < -90.0F)) {
                        if (this.chargingPlayers.contains(p) && ChargeBar.charged.contains(p)) {
                            ChargeBar.chargeBar.removePlayer(p);
                            p.setVelocity(p.getLocation().getDirection().multiply(1).setY(3));
                            p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 5.0F, 4.0F);
                        }
                    }
                }
            }
        }

    @EventHandler
    public void sneak(PlayerToggleSneakEvent e) {
        final Player p = e.getPlayer();
        if (itemscreation.hasCharcoalElytra(p)) {
            if (e.isSneaking()) {
                this.sneakingPlayers.add(p);
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> CharcoalElytraListener.this.sneakingPlayers.remove(p), 1L);
                if (!p.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock().getType().isAir()) {
                    p.getLocation().getBlock();
                    if (p.getLocation().getBlock().getType() != Material.WATER) {
                        if (!p.isFlying() && !p.isGliding() && !p.isSwimming()) {
                            this.chargingPlayers.add(p);
                            ChargeBar.run(p);
                        }
                    }
                }
            } else {
                if (!e.isSneaking()) {
                    ChargeBar.stop(p);
                    this.chargingPlayers.remove(p);
                    this.sneakingPlayers.remove(p);
                }

            }
        }
    }

    @EventHandler
    public void damage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (itemscreation.hasCharcoalElytra(p)) {
                if (e.getCause().equals(EntityDamageEvent.DamageCause.FLY_INTO_WALL) || (e.getCause().equals(EntityDamageEvent.DamageCause.FALL))) {
                    e.setCancelled(true);
                }
            }
        }
    }
   @EventHandler
    public void NoRename(PrepareAnvilEvent event) {
        AnvilInventory inventory = event.getInventory();
        ItemStack input = inventory.getItem(0);
        boolean cantBeRenamed = false;
        if (input != null && input.hasItemMeta()) {
            ItemMeta meta = input.getItemMeta();
            List<String> lore = null;
            if (meta != null) {
                lore = meta.getLore();
            }
            if (lore != null) {
                Iterator var7 = lore.iterator();
                label33:
                while (true) {
                    while (true) {
                        if (!var7.hasNext()) {
                            break label33;
                        }

                        String line = (String) var7.next();

                        for (String keyword : plugin.getConfig().getStringList("NoRenameLore")) {
                            if (line.contains(keyword)) {
                                cantBeRenamed = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (cantBeRenamed) {
            ItemStack result = event.getResult();
            if (result != null) {
                ItemMeta inputItemMeta = null;
                if (input != null) {
                    inputItemMeta = input.getItemMeta();
                }
                ItemMeta resultMeta = result.getItemMeta();
                if (resultMeta != null) {
                    if (inputItemMeta != null) {
                        resultMeta.setDisplayName(inputItemMeta.getDisplayName());
                    }
                }
                result.setItemMeta(resultMeta);
                event.setResult(result);
                event.getInventory().setRepairCost(999999);
                Player player = (Player) event.getView().getPlayer();
                player.updateInventory();
                plugin.getServer().getScheduler().runTask(plugin, () -> event.getInventory().setRepairCost(999999));
            }
        }
    }

    @EventHandler
    public void NoEnchantments(PrepareAnvilEvent e) {
        if (e.getInventory().getItem(1) == null || e.getInventory().getItem(0) == null) return;
        Player player = (Player) e.getView().getPlayer();
        ItemStack a = new ItemStack(Objects.requireNonNull(e.getInventory().getItem(1)));
        ItemMeta meta = a.getItemMeta();
        if (itemscreation.hasCharcoalElytra(player)) {
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GREEN + "Crouch" + ChatColor.GRAY + "&" + ChatColor.GREEN + " Jump" + ChatColor.GRAY + " to Launch into the Air.");
            lore.add(ChatColor.GRAY + " Press" + ChatColor.GREEN + " Shift" + ChatColor.GRAY + " while Flying to Boost.");
            if (meta != null && meta.hasLore()) lore.addAll(Objects.requireNonNull(meta.getLore()));
            if (meta != null) {
                meta.setLore(lore);
                a.setItemMeta(meta);
            }
            e.setResult(null);
            player.updateInventory();
        }
    }
    @EventHandler
    public void AnvilUse(InventoryClickEvent e) {
        if (!e.isCancelled()) {
            HumanEntity ent = e.getWhoClicked();
            if (ent instanceof Player) {
                Player p = (Player) ent;
                Inventory inv = e.getInventory();
                if (inv instanceof AnvilInventory) {
                    AnvilInventory anvil = (AnvilInventory) inv;
                    InventoryView view = e.getView();
                    int rawSlot = e.getRawSlot();
                    if (rawSlot == view.convertSlot(rawSlot)) {
                        if (rawSlot == 2) {
                            ItemStack[] items = anvil.getContents();
                            ItemStack item = items[0];
                            ItemStack item2 = items[1];
                            if (item == null) return;
                            if (item2 == null) return;
                            if (item.isSimilar(itemscreation.Elytra) || item2.isSimilar(itemscreation.Elytra)) {
                                e.setCancelled(true);
                                p.closeInventory();
                                p.sendMessage("You cannot add custom enchantments or rename the Charcoal Elytra");
                            }
                        }
                    }
                }
            }
        }
    }
}