package me.minercoffee.minerexpansion.elyra.utils.events;

import me.minercoffee.minerexpansion.MinerExpansion;
import me.minercoffee.minerexpansion.elyra.utils.ChargeBar;
import me.minercoffee.minerexpansion.elyra.utils.ChatUtils;
import me.minercoffee.minerexpansion.elyra.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class ElytraEvent implements Listener {
    private final List<Player> sneakingPlayers = new ArrayList<>();
    private final List<Player> chargingPlayers = new ArrayList<>();
    private final HashMap<Player, Long> cooldowns = new HashMap<>();

    public ElytraEvent() {
    }

    private boolean isHoldingSneak(Player p) {
        return this.sneakingPlayers.contains(p);
    }

    private static int getCooldown() {
        return 4000;
    }

    private float getVelocityMultiplier() {
        return 2.3F;
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
                if (!(p.getLocation().getPitch() < 80.0F)) {
                    if (this.chargingPlayers.contains(p) && ChargeBar.charged.contains(p)) {
                        ChargeBar.chargeBar.removePlayer(p);
                        p.setVelocity(p.getLocation().getDirection().multiply(1).setY(5));
                        p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 10.0F, 3.0F);
                    }

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
                Bukkit.getScheduler().scheduleSyncDelayedTask(MinerExpansion.plugin, () -> ElytraEvent.this.sneakingPlayers.remove(p), 1L);
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
                if (e.getCause().equals(DamageCause.FLY_INTO_WALL) || (e.getCause().equals(DamageCause.FALL))){
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
                while(true) {
                    while(true) {
                        if (!var7.hasNext()) {
                            break label33;
                        }

                        String line = (String)var7.next();

                        for (String keyword : MinerExpansion.plugin.getConfig().getStringList("NoRenameLore")) {
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
            }
        }
    }
}