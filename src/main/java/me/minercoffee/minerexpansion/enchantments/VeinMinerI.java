package me.minercoffee.minerexpansion.enchantments;

import org.bukkit.*;
import org.bukkit.block.Block;
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
import org.bukkit.inventory.meta.Damageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VeinMinerI implements Listener, CommandExecutor {

    @EventHandler
    public void onBreakBlock(BlockBreakEvent e) {
        Player player = e.getPlayer();
        PlayerInventory inventory = player.getInventory();
        ItemStack item = inventory.getItemInMainHand();
        Block block = e.getBlock();
        GameMode gamemode = player.getGameMode();

        if (item == null)
            return;
        if (!item.hasItemMeta() || !Objects.requireNonNull(item.getItemMeta()).hasEnchant(VeinMinerUtilsI.VEINMINERI))
            return;
        if (gamemode == GameMode.CREATIVE || gamemode == GameMode.SPECTATOR)
            return;
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "VeinMiner I");
            breakBlocks(getNearbyBlocks(block, 1, true, true), player);
    }
    public List<Block> getNearbyBlocks(Block origin, int radius, boolean ignoreFirst, boolean sameType) {
        List<Block> blocks = new ArrayList<>();
        for(int y = -radius; y <= radius; y++)
            for(int x = -radius; x <= radius; x++)
                for(int z = -radius; z <= radius; z++) {
                    Block next = origin.getRelative(x,y,z);
                    if(next.getType()!=origin.getType()&&sameType) continue;
                    if(ignoreFirst && x==0&&y==0&&z==0)continue;
                    if(!blocks.contains(next)) blocks.add(next);
                }
        return blocks;
    }
    public List<Block> breakBlocks(List<Block> blocks, Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        for(Block block : blocks) block.breakNaturally();
        ItemStack tool = new ItemStack(item); // Gets the tool that was used to break the block
        Damageable toolMeta = (Damageable) tool.getItemMeta(); // Gets a Damageable object so we can damage the tool
        try {
            if (toolMeta != null) {
                toolMeta.setDamage(toolMeta.getDamage()+300); // We try to damage the tool by 1 for every block in the list
            }
            tool.setItemMeta(toolMeta); // Sets the tools meta to the damaged tool meta we created
            player.getInventory().setItemInMainHand(tool); // Changes their tool to the damaged tool
        } catch (Exception error) {
            error.printStackTrace();
        } // If their tool breaks we cant damage it and a 'null' error is raised so we do nothing
        return blocks;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (command.getName().equalsIgnoreCase("veinminerI")) {
                ItemStack item = new ItemStack((Material.DIAMOND_PICKAXE));
                item.addUnsafeEnchantment(VeinMinerUtilsI.VEINMINERI, 1);
                ItemMeta meta = item.getItemMeta();
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.GRAY + "VeinMiner I");
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
