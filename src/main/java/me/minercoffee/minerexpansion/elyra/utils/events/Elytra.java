package me.minercoffee.minerexpansion.elyra.utils.events;

import me.minercoffee.minerexpansion.elyra.utils.ChargeBar;
import me.minercoffee.minerexpansion.elyra.utils.ChatUtils;
import me.minercoffee.minerexpansion.elyra.utils.Utils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

import static me.minercoffee.minerexpansion.MinerExpansion.plugin;

public class Elytra implements Listener {
    private final List<Player> sneakingPlayers = new ArrayList<>();
    private final List<Player> chargingPlayers = new ArrayList<>();
    private final HashMap<Player, Long> cooldowns = new HashMap<>();

    private boolean isHoldingSneak(Player p) {
        return this.sneakingPlayers.contains(p);
    }

    private static int getCooldown() {
        return 5000;
    }

    private float getVelocityMultiplier() {
        return 1.9F;
    }

    @EventHandler
    public void boost(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (p.isGliding()) {
            if (this.isHoldingSneak(p)) {
                if (Utils.hasElytra(p)) {
                    if (this.cooldowns.containsKey(p) && this.cooldowns.get(p) > System.currentTimeMillis()) {
                        p.sendMessage(ChatUtils.colour("&5You cannot boost yet"));
                    } else {
                        this.cooldowns.put(p, System.currentTimeMillis() + (long) getCooldown());
                        p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 10.0F, 3.0F);
                        p.setVelocity(p.getLocation().getDirection().multiply(this.getVelocityMultiplier()));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 80, 0, false, false, false));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 2, 0, false, false, false));
                    }
                }
            }
        }
    }

    @EventHandler
    public void launch(PlayerStatisticIncrementEvent e) {
        Player p = e.getPlayer();
        if (Utils.hasElytra(p)) {
                if (e.getStatistic().equals(Statistic.JUMP)) {
                    if (ElytraCooldown.checkCooldown(p)) {
                        if (!(p.getLocation().getPitch() < -90.0F)) {
                            ElytraCooldown.setCooldown(p, 5);
                            if (this.chargingPlayers.contains(p) && ChargeBar.charged.contains(p)) {
                                ChargeBar.chargeBar.removePlayer(p);
                                p.setVelocity(p.getLocation().getDirection().multiply(1).setY(3));
                                p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 10.0F, 1.0F);
                            }
                        }
                    } else {
                        p.sendMessage(ChatColor.YELLOW + "Elytra launch is not ready yet");
                        p.sendMessage(ChatColor.YELLOW + "It will be ready in a five seconds");
                    }
                }
        }
    }

    @EventHandler
    public void sneak(PlayerToggleSneakEvent e) {
        final Player p = e.getPlayer();
        if (Utils.hasElytra(p)) {
            if (e.isSneaking()) {
                this.sneakingPlayers.add(p);
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> Elytra.this.sneakingPlayers.remove(p), 1L);
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
            if (Utils.hasElytra(p)) {
                if (e.getCause().equals(EntityDamageEvent.DamageCause.FLY_INTO_WALL) || (e.getCause().equals(EntityDamageEvent.DamageCause.FALL))) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
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
    public void canEnchant(PrepareAnvilEvent e) {
        Player player = (Player) e.getView().getPlayer();
        if (e.getInventory().getItem(1) == null || e.getInventory().getItem(0) == null) return;
            ItemStack a = new ItemStack(Objects.requireNonNull(e.getInventory().getItem(0)));
            ItemMeta meta = a.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "IllusiveMC's Owner!");
            assert meta != null;
            if (meta.hasLore()) lore.addAll(Objects.requireNonNull(meta.getLore()));
            meta.setLore(lore);
            a.setItemMeta(meta);
            e.setResult(null);
            player.updateInventory();
        }
}