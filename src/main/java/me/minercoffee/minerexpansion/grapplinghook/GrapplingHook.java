package me.minercoffee.minerexpansion.grapplinghook;

import me.minercoffee.minerexpansion.Items.itemscreation;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.Objects;

import static me.minercoffee.minerexpansion.MinerExpansion.plugin;

public class GrapplingHook implements Listener {

    @EventHandler
    public void onFish(PlayerFishEvent event) {

        Player player = event.getPlayer();
        if (plugin.getConfig().getBoolean("grappinghook")) {
            if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getLore() != null
                    && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(Objects.requireNonNull(Objects.requireNonNull(itemscreation.GrapplingHook.getItemMeta()).getLore()).get(0))) {
                if (event.getState().equals(PlayerFishEvent.State.REEL_IN)) {
                    if (GrapplingHookCooldown.checkCooldown(player)) {
                        Location playerLocation = player.getLocation();
                        Location hookLocation = event.getHook().getLocation();
                        Location change = hookLocation.subtract(playerLocation);
                        player.setVelocity(change.toVector().multiply(0.3));
                        GrapplingHookCooldown.setCooldown(player, 5);
                    } else {
                        player.sendMessage(ChatColor.YELLOW + "Item Ability is not ready yet");
                    }
                }
            }
        }
    }
}