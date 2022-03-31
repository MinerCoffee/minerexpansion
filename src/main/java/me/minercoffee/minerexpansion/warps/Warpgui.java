package me.minercoffee.minerexpansion.warps;

import me.minercoffee.minerexpansion.MinerExpansion;
import me.minercoffee.minerexpansion.elyra.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class Warpgui implements Listener, CommandExecutor {
    MinerExpansion plugin;
    public Warpgui(MinerExpansion plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        Objects.requireNonNull(plugin.getCommand("warpgui")).setExecutor(this);
    }

    private Inventory maingui;
    private Inventory warplistngui;
    public void WarpMainGui(Player p) {
        maingui = Bukkit.createInventory(null, InventoryType.HOPPER, (ChatUtils.colour("&l&eWarps Main Menu!")));

            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
            ItemMeta meta0 = playerHead.getItemMeta();
            if (meta0 != null) {
                meta0.setDisplayName("Basic Facts");
            }
        List<String> lore = new ArrayList<>();
            lore.add("§6Your XP" + " " + "§2" + p.getLevel() + " " + p.getExp());
            lore.add("§cYour Health" + " " + "§4" + p.getHealth());
        if (meta0 != null) {
            meta0.setLore(lore);
        }
        playerHead.setItemMeta(meta0);
            maingui.setItem(0, playerHead);

        ItemStack item1 = new ItemStack(Material.GREEN_CONCRETE, 1);
        ItemMeta meta1 = item1.getItemMeta();
        if (meta1 != null) {
            meta1.setDisplayName(ChatColor.GREEN + "This is a button");
        }
        item1.setItemMeta(meta1);
        maingui.setItem(2, item1);

        ItemStack item2 = new ItemStack(Material.BARRIER, 1);
        ItemMeta meta2 = item2.getItemMeta();
        if (meta2 != null) {
            meta2.setDisplayName(ChatColor.RED + "Close");
        }
        item2.setItemMeta(meta2);
        maingui.setItem(4, item2);

        p.openInventory(maingui);
    }
    public void WarpListGui(Player p) {
        warplistngui = Bukkit.createInventory(null, InventoryType.SHULKER_BOX, (ChatUtils.colour("&l&eWarp List!")));

        ItemStack warps = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta meta1 = warps.getItemMeta();
        if (meta1 != null) {
            meta1.setDisplayName(ChatColor.DARK_BLUE + "Warp names!");
        }
        warps.setItemMeta(meta1);

        warplistngui.setItem(1, warps);
        ItemStack item2 = new ItemStack(Material.BARRIER, 1);
        ItemMeta meta2 = item2.getItemMeta();
        if (meta2 != null) {
            meta2.setDisplayName(ChatColor.RED + "Go back!");
        }
        item2.setItemMeta(meta2);
        warplistngui.setItem(8, item2);

        p.openInventory(warplistngui);
    }

    @EventHandler
    public void WarpMenu(InventoryClickEvent e) {
        if (!e.getInventory().equals(maingui)) {
            return;
        }
        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();

        switch (e.getSlot()) {
            case 2: {
                WarpListGui(p);
                p.sendMessage("You click the button");
                break;
            }
            case 4: {
                p.closeInventory();
                p.sendMessage("Closing Gui!");
                break;
            }
        }
    }
    @EventHandler
    public void WarpList(InventoryClickEvent e) {
        if (!e.getInventory().equals(warplistngui)) {
            return;
        }
        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();

        switch (e.getSlot()) {
            case 8: {
                WarpMainGui(p);
                p.sendMessage("Going back to the Main Menu!");
                break;
            }
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            WarpMainGui(p);
        }
        return true;
    }
}