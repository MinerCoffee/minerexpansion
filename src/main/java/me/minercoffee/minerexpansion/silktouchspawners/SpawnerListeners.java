package me.minercoffee.minerexpansion.silktouchspawners;

import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import java.util.Random;

import static me.minercoffee.minerexpansion.MinerExpansion.plugin;


public class SpawnerListeners implements Listener {
    Random random = new Random();

    @EventHandler
    public void onSpawnerBreak(SpawnerBreakEvent e) {
        CreatureSpawner cs = (CreatureSpawner) e.getSpawner().getState();
        ItemStack spawner = new ItemStack(cs.getType());
        BlockStateMeta meta = (BlockStateMeta) spawner.getItemMeta();
        CreatureSpawner css = null;
        if (meta != null) {
            css = (CreatureSpawner) meta.getBlockState();
        }
        if (css != null) {
            css.setSpawnedType(cs.getSpawnedType());
        }
        if (meta != null) {
            meta.setBlockState(css);
        }
        spawner.setItemMeta(meta);
        double chance = plugin.getConfig().getDouble("dropChance",50)/100;
        if(random.nextDouble()<= chance){
            e.getBreaker().sendMessage("You have silk touched a spawner.");
            e.getBreaker().getInventory().addItem(spawner);
        }
    }
}
