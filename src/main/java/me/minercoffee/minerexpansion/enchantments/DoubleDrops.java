package me.minercoffee.minerexpansion.enchantments;

import me.minercoffee.minerexpansion.MinerExpansion;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class DoubleDrops implements Listener, CommandExecutor {
    MinerExpansion plugin;
    public DoubleDrops(MinerExpansion plugin){
       this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent e){
        Block blockBroken = e.getBlock();
        Player player = e.getPlayer();
        PlayerInventory inventory = player.getInventory();
        ItemStack iteminhand = inventory.getItemInMainHand();
        GameMode gamemode = player.getGameMode();

        if (gamemode == GameMode.CREATIVE || gamemode == GameMode.SPECTATOR)
            return;
        if (inventory.firstEmpty() == -1)
            return;
        if (!iteminhand.hasItemMeta() || !Objects.requireNonNull(iteminhand.getItemMeta()).hasEnchant(DoubleDropsUtils.DoubleDrops))
            return;
        if(blockBroken.getType() == Material.DIAMOND_ORE){
            e.setCancelled(true);
            blockBroken.setType(Material.AIR);
            ItemStack item = new ItemStack(Material.DIAMOND, 2);
            blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), item);
        }
        if(blockBroken.getType() == Material.DEEPSLATE_COAL_ORE){
            e.setCancelled(true);
            blockBroken.setType(Material.AIR);
            ItemStack item = new ItemStack(Material.COAL, 2);
            blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), item);
        }
        if(blockBroken.getType() == Material.DEEPSLATE_DIAMOND_ORE){
            e.setCancelled(true);
            blockBroken.setType(Material.AIR);
            ItemStack item = new ItemStack(Material.DIAMOND, 2);
            blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), item);
        }
        if(blockBroken.getType() == Material.DEEPSLATE_LAPIS_ORE){
            e.setCancelled(true);
            blockBroken.setType(Material.AIR);
            ItemStack item = new ItemStack(Material.LAPIS_LAZULI, 2);
            blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), item);
        }
        if(blockBroken.getType() == Material.DEEPSLATE_COPPER_ORE){
            e.setCancelled(true);
            blockBroken.setType(Material.AIR);
            ItemStack item = new ItemStack(Material.COPPER_INGOT, 2);
            blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), item);
        }
        if(blockBroken.getType() == Material.DEEPSLATE_EMERALD_ORE){
            e.setCancelled(true);
            blockBroken.setType(Material.AIR);
            ItemStack item = new ItemStack(Material.EMERALD, 2);
            blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), item);
        }
        if(blockBroken.getType() == Material.COAL_ORE){
            e.setCancelled(true);
            blockBroken.setType(Material.AIR);
            ItemStack item = new ItemStack(Material.COAL, 2);
            blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), item);
        }
    if(blockBroken.getType() == Material.NETHER_QUARTZ_ORE){
            e.setCancelled(true);
            blockBroken.setType(Material.AIR);
            ItemStack item = new ItemStack(Material.QUARTZ, 2);
            blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), item);
        }
        if(blockBroken.getType() == Material.REDSTONE_ORE){
            e.setCancelled(true);
            blockBroken.setType(Material.AIR);
            ItemStack item = new ItemStack(Material.REDSTONE, 2);
            blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), item);
        }
        if(blockBroken.getType() == Material.EMERALD_ORE){
            e.setCancelled(true);
            blockBroken.setType(Material.AIR);
            ItemStack item = new ItemStack(Material.EMERALD, 2);
            blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), item);
        }
        if(blockBroken.getType() == Material.COAL_ORE){
            e.setCancelled(true);
            blockBroken.setType(Material.AIR);
            ItemStack item = new ItemStack(Material.COAL, 2);
            blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), item);
        }
        if(blockBroken.getType() == Material.LAPIS_ORE){
            e.setCancelled(true);
            blockBroken.setType(Material.AIR);
            ItemStack item = new ItemStack(Material.LAPIS_LAZULI, 2);
            blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), item);
        }
        if(blockBroken.getType() == Material.COPPER_ORE){
            e.setCancelled(true);
            blockBroken.setType(Material.AIR);
            ItemStack item = new ItemStack(Material.COPPER_INGOT, 2);
            blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), item);
        }
    }
    @EventHandler
    public void anvilEvent(PrepareAnvilEvent e){
        Player player = (Player) e.getView().getPlayer();
        if (e.getInventory().getItem(1) == null || e.getInventory().getItem(0) == null) return;
        if (Objects.requireNonNull(e.getInventory().getItem(1)).containsEnchantment(DoubleDropsUtils.DoubleDrops)){
            ItemStack a = new ItemStack(Objects.requireNonNull(e.getInventory().getItem(0)));
            a.addUnsafeEnchantment(DoubleDropsUtils.DoubleDrops, Objects.requireNonNull(e.getInventory().getItem(1)).getEnchantmentLevel(DoubleDropsUtils.DoubleDrops));
            ItemMeta meta = a.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add (ChatColor.GRAY + "DoubleDrops I");
            if (meta != null && meta.hasLore()) lore.addAll(Objects.requireNonNull(meta.getLore()));
            if (meta != null) {
                meta.setLore(lore);
                a.setItemMeta(meta);
            }
            e.getInventory().setRepairCost(35);
            e.setResult(a);
            plugin.getServer().getScheduler().runTask(plugin, () -> e.getInventory().setRepairCost(35));
            player.updateInventory();
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.isOp()) {
                if (command.getName().equalsIgnoreCase("doubledrops")) {
                    ItemStack book = new ItemStack((Material.ENCHANTED_BOOK), 1);
                    book.addUnsafeEnchantment(DoubleDropsUtils.DoubleDrops, 1);
                    List<String> lore = new ArrayList<>();
                    lore.add(ChatColor.GRAY + "DoubleDrops I");
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
