package me.minercoffee.minerexpansion.enchantments;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Telepathy implements Listener, CommandExecutor {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        PlayerInventory inventory = player.getInventory();
        ItemStack item = inventory.getItemInMainHand();
        Block block = event.getBlock();

        if (item == null)
            return;
        if (!item.hasItemMeta() || !item.getItemMeta().hasEnchant(CustomEnchants.TELEPATHY))
            return;
        //you could also do GameMode gamemode = player.getGameMode() here and then just use gamemode instead of player.getGameMode()
        if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR)
            return;
        if (inventory.firstEmpty() == -1)
            return;
        if (block.getState() instanceof Container)
            return;
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Telepathy I");
        // check for if he dont have the telepathy lore then return
        event.setDropItems(false);


        Collection<ItemStack> drops = block.getDrops(item);
        if (drops.isEmpty())
            return;
        inventory.addItem(drops.iterator().next());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (s.equalsIgnoreCase("telepathy")) {
            if (!(commandSender instanceof Player)) {
                return true;
            }
            Player p = (Player) commandSender;
            if (strings.length > 1 && p.hasPermission("miner.staff")) {
                ItemStack item = new ItemStack((Material.DIAMOND_PICKAXE));
                item.addUnsafeEnchantment(CustomEnchants.TELEPATHY, 1);
                ItemMeta meta = item.getItemMeta();
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.GRAY + "Telepathy I");
                if (meta.hasLore())
                    for (String l : meta.getLore()) {
                        lore.add(l);
                    }
                meta.setLore(lore);

                item.setItemMeta(meta);
                p.getInventory().addItem(item);
            }
        }
        return true;
    }
}
