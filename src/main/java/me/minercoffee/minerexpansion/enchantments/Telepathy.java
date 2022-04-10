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
import java.util.Objects;


public class Telepathy implements Listener, CommandExecutor {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        PlayerInventory inventory = player.getInventory();
        ItemStack item = inventory.getItemInMainHand();
        Block block = event.getBlock();
        GameMode gamemode = player.getGameMode();

        if (item == null)
            return;
        if (!item.hasItemMeta() || !Objects.requireNonNull(item.getItemMeta()).hasEnchant(TelepathyUtils.TELEPATHY))
            return;
        if (gamemode == GameMode.CREATIVE || gamemode == GameMode.SPECTATOR)
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
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
                if (command.getName().equalsIgnoreCase("telepathy")) {
                    ItemStack item = new ItemStack((Material.DIAMOND_PICKAXE));
                    item.addUnsafeEnchantment(TelepathyUtils.TELEPATHY, 1);
                    ItemMeta meta = item.getItemMeta();
                    List<String> lore = new ArrayList<>();
                    lore.add(ChatColor.GRAY + "Telepathy I");
                    if (meta != null && meta.hasLore()) lore.addAll(Objects.requireNonNull(meta.getLore()));
                    if (meta != null) {
                        meta.setLore(lore);
                    }
                    item.setItemMeta(meta);
                    p.getInventory().addItem(item);
                }
            }

        return true;
    }
}
