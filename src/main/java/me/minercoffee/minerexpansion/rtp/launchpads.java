package me.minercoffee.minerexpansion.rtp;

import me.minercoffee.minerexpansion.MinerExpansion;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.Objects;

public class launchpads implements Listener  {
   private final MinerExpansion plugin;

    public launchpads(MinerExpansion plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (plugin.getConfig().getBoolean("launchpad")) {
            Player p = e.getPlayer();
            Location blockUnder = p.getLocation();
            Location blockMiddle = p.getLocation();
            blockMiddle.setY(blockUnder.getBlockY() - 1);
            blockUnder.setY(blockUnder.getBlockY() - 2);
                if (p.getLocation().getBlock().getType().equals(Material.valueOf(plugin.getConfig().getString("top-block")))
                        && blockMiddle.getBlock().getType().equals(Material.valueOf(plugin.getConfig().getString("middle-block")))
                        && blockUnder.getBlock().getType().equals(Material.valueOf(plugin.getConfig().getString("under-block")))) {
                    plugin.launchpad_players.add(p);
                    plugin.saveConfig();
                    Location randomLocation = teleportutils.findSafeLocation(p);
                    Location spawn = teleportutils.findSafeSpawnLocation(randomLocation);
                    p.teleport(spawn);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 64, 10, false, false, false));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 64, 10, false, false, false));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 64, 10, false, false, false));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 64 , 10, false, false, false));
                    if (plugin.getConfig().getBoolean("message")){
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("launch-message"))));
                       p.sendMessage(ChatColor.AQUA + "New Coordinates: " + ChatColor.LIGHT_PURPLE + spawn.getX() + " " + spawn.getY() + " " + spawn.getZ());
                    }
                }
            }
        }
    @EventHandler
    public void onFallDamage(EntityDamageEvent e){
        if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL) || (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE) && plugin.getConfig().getBoolean("disable-fall-damage"))){
            if (plugin.launchpad_players.contains((Entity) e.getEntity())){
                e.setCancelled(true);
                plugin.launchpad_players.remove((Entity) e.getEntity());
            }
        }
    }
}