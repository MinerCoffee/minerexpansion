package me.minercoffee.minerexpansion.enchantments;

import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
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
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static me.minercoffee.minerexpansion.MinerExpansion.plugin;


public class Telepathy implements Listener, CommandExecutor {
    private final ProtectedCuboidRegion region;

    public Telepathy(ProtectedCuboidRegion region) {
        this.region = region;
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (plugin.getConfig().getBoolean("telepathy")) {
            Player player = event.getPlayer();
            PlayerInventory inventory = player.getInventory();
            Block block = event.getBlock();
            GameMode gamemode = player.getGameMode();
            if (gamemode == GameMode.SPECTATOR)
                return;
            if (inventory.firstEmpty() == -1)
                return;
            if (block.getState() instanceof Container)
                return;
            if (!region.isOwner(player.getDisplayName())) return;
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "Telepathy I");
            // check for if he dont have the telepathy lore then return
            event.setDropItems(true);
            Collection<ItemStack> drops = block.getDrops(player.getInventory().getItemInMainHand());
            if (drops.isEmpty())
                return;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(TelepathyUtils.TELEPATHY) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(TelepathyUtils.TELEPATHY)) {
                inventory.addItem(drops.iterator().next());

            }
        }
    }
    @EventHandler
    public void anvilEvent(PrepareAnvilEvent e){
        Player player = (Player) e.getView().getPlayer();
        if (e.getInventory().getItem(1) == null || e.getInventory().getItem(0) == null) return;
        if (Objects.requireNonNull(e.getInventory().getItem(1)).containsEnchantment(TelepathyUtils.TELEPATHY)){
            ItemStack a = new ItemStack(Objects.requireNonNull(e.getInventory().getItem(0)));
            a.addEnchantment(TelepathyUtils.TELEPATHY, Objects.requireNonNull(e.getInventory().getItem(1)).getEnchantmentLevel(TelepathyUtils.TELEPATHY));
            ItemMeta meta = a.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add (ChatColor.GRAY + "Telepathy I");
            if (meta != null && meta.hasLore()) lore.addAll(Objects.requireNonNull(meta.getLore()));
            if (meta != null) {
                meta.setLore(lore);
            }
            a.setItemMeta(meta);
            e.getInventory().setRepairCost(35);
            e.setResult(a);
            player.updateInventory();
            plugin.getServer().getScheduler().runTask(plugin, () -> e.getInventory().setRepairCost(35));
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (p.hasPermission("miner.staff")) {
                if (command.getName().equalsIgnoreCase("telepathy")) {
                    ItemStack book = new ItemStack((Material.ENCHANTED_BOOK), 1);
                    book.addUnsafeEnchantment(TelepathyUtils.TELEPATHY, 1);
                    List<String> lore = new ArrayList<>();
                    lore.add(ChatColor.GRAY + "Telepathy I");
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
