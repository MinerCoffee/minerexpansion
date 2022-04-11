package me.minercoffee.minerexpansion.warps;

import me.minercoffee.minerexpansion.MinerExpansion;
import me.minercoffee.minerexpansion.elyra.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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
            lore.add("§6Your Level" + " " + "§2" + p.getLevel());
            lore.add("§6Your XP" + " " + p.getExp());
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
            ItemStack warps1 = new ItemStack(Material.GOLDEN_APPLE, 1);
            ItemMeta meta1 = warps1.getItemMeta();
            if (meta1 != null) {
                meta1.setDisplayName(ChatColor.LIGHT_PURPLE + "Warp 1!");
            }
        warps1.setItemMeta(meta1);
        ItemStack warps2 = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta meta2 = warps1.getItemMeta();
        if (meta2 != null) {
            meta2.setDisplayName(ChatColor.LIGHT_PURPLE + "Warp 2!");
        }
        warps2.setItemMeta(meta2);
        ItemStack warps3 = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta meta3 = warps3.getItemMeta();
        if (meta3 != null) {
            meta3.setDisplayName(ChatColor.LIGHT_PURPLE + "Warp 3!");
        }
        warps3.setItemMeta(meta3);
        ItemStack warps4 = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta meta4 = warps4.getItemMeta();
        if (meta4 != null) {
            meta4.setDisplayName(ChatColor.LIGHT_PURPLE + "Warp 4!");
        }
        warps4.setItemMeta(meta4);
        ItemStack warps5 = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta meta5 = warps1.getItemMeta();
        if (meta5 != null) {
            meta5.setDisplayName(ChatColor.LIGHT_PURPLE + "Warp 5!");
        }
        warps5.setItemMeta(meta5);
        ItemStack warps6 = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta meta6 = warps1.getItemMeta();
        if (meta6 != null) {
            meta6.setDisplayName(ChatColor.LIGHT_PURPLE + "Warp 6!");
        }
        warps6.setItemMeta(meta6);
        ItemStack warp7 = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta meta7 = warps1.getItemMeta();
        if (meta7 != null) {
            meta7.setDisplayName(ChatColor.LIGHT_PURPLE + "Warp 7!");
        }
        warp7.setItemMeta(meta7);
        ItemStack warp8 = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta meta8 = warps1.getItemMeta();
        if (meta8 != null) {
            meta8.setDisplayName(ChatColor.LIGHT_PURPLE + "Warp 8!");
        }
        warp8.setItemMeta(meta8);
        ItemStack warps9 = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta meta9 = warps1.getItemMeta();
        if (meta9 != null) {
            meta9.setDisplayName(ChatColor.LIGHT_PURPLE + "Warp 9!");
        }
        warps9.setItemMeta(meta5);
        ItemStack warps10 = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta meta10 = warps1.getItemMeta();
        if (meta10 != null) {
            meta10.setDisplayName(ChatColor.LIGHT_PURPLE + "Warp 10!");
        }
        warps10.setItemMeta(meta10);
        ItemStack warp11 = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta meta11 = warps1.getItemMeta();
        if (meta11 != null) {
            meta11.setDisplayName(ChatColor.LIGHT_PURPLE + "Warp 11!");
        }
        warp11.setItemMeta(meta11);
        ItemStack warps12 = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta meta12 = warps1.getItemMeta();
        if (meta12 != null) {
            meta12.setDisplayName(ChatColor.LIGHT_PURPLE + "Warp 12!");
        }
        warps12.setItemMeta(meta12);
        ItemStack warps13 = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta meta13 = warps1.getItemMeta();
        if (meta13 != null) {
            meta13.setDisplayName(ChatColor.LIGHT_PURPLE + "Warp 13!");
        }
        warps13.setItemMeta(meta13);
        ItemStack warp14 = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta meta14 = warps1.getItemMeta();
        if (meta14 != null) {
            meta14.setDisplayName(ChatColor.LIGHT_PURPLE + "Warp 14!");
        }
        warp14.setItemMeta(meta14);
        ItemStack warps15 = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta meta15 = warps1.getItemMeta();
        if (meta15 != null) {
            meta15.setDisplayName(ChatColor.LIGHT_PURPLE + "Warp 15!");
        }
        warps15.setItemMeta(meta15);
        ItemStack warps16 = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta meta16 = warps1.getItemMeta();
        if (meta16 != null) {
            meta16.setDisplayName(ChatColor.LIGHT_PURPLE + "Warp 16!");
        }
        warps16.setItemMeta(meta16);
        ItemStack warps17 = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta meta17 = warps1.getItemMeta();
        if (meta17 != null) {
            meta17.setDisplayName(ChatColor.LIGHT_PURPLE + "Warp 17!");
        }
        warps17.setItemMeta(meta17);
        ItemStack warps18 = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta meta18 = warps1.getItemMeta();
        if (meta18 != null) {
            meta18.setDisplayName(ChatColor.LIGHT_PURPLE + "Warp 18!");
        }
        warps18.setItemMeta(meta18);
        ItemStack warps19 = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta meta19 = warps1.getItemMeta();
        if (meta19 != null) {
            meta19.setDisplayName(ChatColor.LIGHT_PURPLE + "Warp 19!");
        }
        warps19.setItemMeta(meta19);
        ItemStack warps20 = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemMeta meta20 = warps1.getItemMeta();
        if (meta20 != null) {
            meta20.setDisplayName(ChatColor.LIGHT_PURPLE + "Warp 20!");
        }
        warps20.setItemMeta(meta20);
            warplistngui.setItem(0, warps1);
            warplistngui.setItem(1, warps2);
            warplistngui.setItem(2, warps3);
            warplistngui.setItem(3, warps4);
            warplistngui.setItem(4, warps5);
            warplistngui.setItem(5, warps6);
            warplistngui.setItem(6, warp7);
            warplistngui.setItem(7, warp8);
            warplistngui.setItem(9, warps9);
            warplistngui.setItem(10, warps10);
            warplistngui.setItem(11, warp11);
            warplistngui.setItem(12, warps12);
            warplistngui.setItem(13, warps13);
            warplistngui.setItem(14, warp14);
            warplistngui.setItem(15, warps15);
            warplistngui.setItem(16, warps16);
            warplistngui.setItem(17, warps17);
            warplistngui.setItem(18, warps18);
            warplistngui.setItem(19, warps19);
            warplistngui.setItem(20, warps20);

            ItemStack Barraier = new ItemStack(Material.BARRIER, 1);
            ItemMeta barrier = Barraier.getItemMeta();
            if (barrier != null) {
                barrier.setDisplayName(ChatColor.RED + "Go back!");
            }
        Barraier.setItemMeta(barrier);
            warplistngui.setItem(8, Barraier);
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
    public void WarpTPList(InventoryClickEvent e) {

        if (!e.getInventory().equals(warplistngui)) {
            return;
        }
        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();
        switch (e.getSlot()) {
            case 0: {
                tpWarps1(p);
                break;
            }
            case 1: {
                tpWarps2(p);
                break;
            }
            case 2: {
                tpWarps3(p);
                break;
            }
            case 3: {
                tpWarps4(p);
                break;
            }
            case 4: {
                tpWarps5(p);
                break;
            }
            case 5: {
                tpWarps6(p);
                break;
            }
            case 6: {
                tpWarps7(p);
                break;
            }
            case 7: {
                tpWarps8(p);
                break;
            }
            case 8: {
                WarpMainGui(p);
                p.sendMessage("Going back to the Main Menu!");
                break;
            }
            case 9: {
                tpWarps10(p);
                break;
            }
            case 10: {
                tpWarps11(p);
                break;
            }
            case 11: {
                tpWarps12(p);
                break;
            }
            case 12: {
                tpWarps13(p);
                break;
            }
            case 13: {
                tpWarps14(p);
                break;
            }
            case 14: {
                tpWarps15(p);
                break;
            }
            case 15: {
                tpWarps16(p);
                break;
            }
            case 16: {
                tpWarps17(p);
                break;
            }
            case 17: {
                tpWarps18(p);
                break;
            }
            case 18: {
                tpWarps19(p);
                break;
            }
            case 19: {
                tpWarps20(p);
                break;
            }
            case 20: {
                tpWarps21(p);
                break;
            }
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (plugin.getConfig().getBoolean("beta")) {
                Player p = (Player) sender;
                WarpMainGui(p);
            }
        }
        return true;
    }
    public void tpWarps1(Player player) {
        String name = plugin.getConfig().getString("warp_1");
        Location loc;
            double x = plugin.getConfig().getDouble("warps." + name + ".X");
            double y = plugin.getConfig().getDouble("warps." + name + ".Y");
            double z = plugin.getConfig().getDouble("warps." + name + ".Z");
            float yaw = (float) plugin.getConfig().getDouble("warps." + name + ".Yaw");
            float pitch = (float) plugin.getConfig().getDouble("warps." + name + ".Pitch");
            String world = plugin.getConfig().getString("warps." + name + ".World");
            if (world != null) {
                loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
                player.teleport(loc);
            }
            player.sendMessage(Color("&aYou've been teleported to &b" + name));
        }
    public void tpWarps2(Player player) {
        String name = plugin.getConfig().getString("warp_2");
        Location loc;
        double x = plugin.getConfig().getDouble("warps." + name + ".X");
        double y = plugin.getConfig().getDouble("warps." + name + ".Y");
        double z = plugin.getConfig().getDouble("warps." + name + ".Z");
        float yaw = (float) plugin.getConfig().getDouble("warps." + name + ".Yaw");
        float pitch = (float) plugin.getConfig().getDouble("warps." + name + ".Pitch");
        String world = plugin.getConfig().getString("warps." + name + ".World");
        if (world != null) {
            loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
            player.teleport(loc);
        }
        player.sendMessage(Color("&aYou've been teleported to &b" + name));
    }
    public void tpWarps3(Player player) {
        String name = plugin.getConfig().getString("warp_3");
        Location loc;
        double x = plugin.getConfig().getDouble("warps." + name + ".X");
        double y = plugin.getConfig().getDouble("warps." + name + ".Y");
        double z = plugin.getConfig().getDouble("warps." + name + ".Z");
        float yaw = (float) plugin.getConfig().getDouble("warps." + name + ".Yaw");
        float pitch = (float) plugin.getConfig().getDouble("warps." + name + ".Pitch");
        String world = plugin.getConfig().getString("warps." + name + ".World");
        if (world != null) {
            loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
            player.teleport(loc);
        }
        player.sendMessage(Color("&aYou've been teleported to &b" + name));
    }
    public void tpWarps4(Player player) {
        String name = plugin.getConfig().getString("warp_4");
        Location loc;
        double x = plugin.getConfig().getDouble("warps." + name + ".X");
        double y = plugin.getConfig().getDouble("warps." + name + ".Y");
        double z = plugin.getConfig().getDouble("warps." + name + ".Z");
        float yaw = (float) plugin.getConfig().getDouble("warps." + name + ".Yaw");
        float pitch = (float) plugin.getConfig().getDouble("warps." + name + ".Pitch");
        String world = plugin.getConfig().getString("warps." + name + ".World");
        if (world != null) {
            loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
            player.teleport(loc);
        }
        player.sendMessage(Color("&aYou've been teleported to &b" + name));
    }
    public void tpWarps5(Player player) {
        String name = plugin.getConfig().getString("warp_5");
        Location loc;
        double x = plugin.getConfig().getDouble("warps." + name + ".X");
        double y = plugin.getConfig().getDouble("warps." + name + ".Y");
        double z = plugin.getConfig().getDouble("warps." + name + ".Z");
        float yaw = (float) plugin.getConfig().getDouble("warps." + name + ".Yaw");
        float pitch = (float) plugin.getConfig().getDouble("warps." + name + ".Pitch");
        String world = plugin.getConfig().getString("warps." + name + ".World");
        if (world != null) {
            loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
            player.teleport(loc);
        }
        player.sendMessage(Color("&aYou've been teleported to &b" + name));
    }
    public void tpWarps6(Player player) {
        String name = plugin.getConfig().getString("warp_6");
        Location loc;
        double x = plugin.getConfig().getDouble("warps." + name + ".X");
        double y = plugin.getConfig().getDouble("warps." + name + ".Y");
        double z = plugin.getConfig().getDouble("warps." + name + ".Z");
        float yaw = (float) plugin.getConfig().getDouble("warps." + name + ".Yaw");
        float pitch = (float) plugin.getConfig().getDouble("warps." + name + ".Pitch");
        String world = plugin.getConfig().getString("warps." + name + ".World");
        if (world != null) {
            loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
            player.teleport(loc);
        }
        player.sendMessage(Color("&aYou've been teleported to &b" + name));
    }
    public void tpWarps7(Player player) {
        String name = plugin.getConfig().getString("warp_7");
        Location loc;
        double x = plugin.getConfig().getDouble("warps." + name + ".X");
        double y = plugin.getConfig().getDouble("warps." + name + ".Y");
        double z = plugin.getConfig().getDouble("warps." + name + ".Z");
        float yaw = (float) plugin.getConfig().getDouble("warps." + name + ".Yaw");
        float pitch = (float) plugin.getConfig().getDouble("warps." + name + ".Pitch");
        String world = plugin.getConfig().getString("warps." + name + ".World");
        if (world != null) {
            loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
            player.teleport(loc);
        }
        player.sendMessage(Color("&aYou've been teleported to &b" + name));
    }
    public void tpWarps8(Player player) {
        String name = plugin.getConfig().getString("warp_8");
        Location loc;
        double x = plugin.getConfig().getDouble("warps." + name + ".X");
        double y = plugin.getConfig().getDouble("warps." + name + ".Y");
        double z = plugin.getConfig().getDouble("warps." + name + ".Z");
        float yaw = (float) plugin.getConfig().getDouble("warps." + name + ".Yaw");
        float pitch = (float) plugin.getConfig().getDouble("warps." + name + ".Pitch");
        String world = plugin.getConfig().getString("warps." + name + ".World");
        if (world != null) {
            loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
            player.teleport(loc);
        }
        player.sendMessage(Color("&aYou've been teleported to &b" + name));
    }
    public void tpWarps10(Player player) {
        String name = plugin.getConfig().getString("warp_10");
        Location loc;
        double x = plugin.getConfig().getDouble("warps." + name + ".X");
        double y = plugin.getConfig().getDouble("warps." + name + ".Y");
        double z = plugin.getConfig().getDouble("warps." + name + ".Z");
        float yaw = (float) plugin.getConfig().getDouble("warps." + name + ".Yaw");
        float pitch = (float) plugin.getConfig().getDouble("warps." + name + ".Pitch");
        String world = plugin.getConfig().getString("warps." + name + ".World");
        if (world != null) {
            loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
            player.teleport(loc);
        }
        player.sendMessage(Color("&aYou've been teleported to &b" + name));
    }
    public void tpWarps11(Player player) {
        String name = plugin.getConfig().getString("warp_11");
        Location loc;
        double x = plugin.getConfig().getDouble("warps." + name + ".X");
        double y = plugin.getConfig().getDouble("warps." + name + ".Y");
        double z = plugin.getConfig().getDouble("warps." + name + ".Z");
        float yaw = (float) plugin.getConfig().getDouble("warps." + name + ".Yaw");
        float pitch = (float) plugin.getConfig().getDouble("warps." + name + ".Pitch");
        String world = plugin.getConfig().getString("warps." + name + ".World");
        if (world != null) {
            loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
            player.teleport(loc);
        }
        player.sendMessage(Color("&aYou've been teleported to &b" + name));
    }
    public void tpWarps12(Player player) {
        String name = plugin.getConfig().getString("warp_12");
        Location loc;
        double x = plugin.getConfig().getDouble("warps." + name + ".X");
        double y = plugin.getConfig().getDouble("warps." + name + ".Y");
        double z = plugin.getConfig().getDouble("warps." + name + ".Z");
        float yaw = (float) plugin.getConfig().getDouble("warps." + name + ".Yaw");
        float pitch = (float) plugin.getConfig().getDouble("warps." + name + ".Pitch");
        String world = plugin.getConfig().getString("warps." + name + ".World");
        if (world != null) {
            loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
            player.teleport(loc);
        }
        player.sendMessage(Color("&aYou've been teleported to &b" + name));
    }
    public void tpWarps13(Player player) {
        String name = plugin.getConfig().getString("warp_13");
        Location loc;
        double x = plugin.getConfig().getDouble("warps." + name + ".X");
        double y = plugin.getConfig().getDouble("warps." + name + ".Y");
        double z = plugin.getConfig().getDouble("warps." + name + ".Z");
        float yaw = (float) plugin.getConfig().getDouble("warps." + name + ".Yaw");
        float pitch = (float) plugin.getConfig().getDouble("warps." + name + ".Pitch");
        String world = plugin.getConfig().getString("warps." + name + ".World");
        if (world != null) {
            loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
            player.teleport(loc);
        }
        player.sendMessage(Color("&aYou've been teleported to &b" + name));
    }
    public void tpWarps14(Player player) {
        String name = plugin.getConfig().getString("warp_14");
        Location loc;
        double x = plugin.getConfig().getDouble("warps." + name + ".X");
        double y = plugin.getConfig().getDouble("warps." + name + ".Y");
        double z = plugin.getConfig().getDouble("warps." + name + ".Z");
        float yaw = (float) plugin.getConfig().getDouble("warps." + name + ".Yaw");
        float pitch = (float) plugin.getConfig().getDouble("warps." + name + ".Pitch");
        String world = plugin.getConfig().getString("warps." + name + ".World");
        if (world != null) {
            loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
            player.teleport(loc);
        }
        player.sendMessage(Color("&aYou've been teleported to &b" + name));
    }
    public void tpWarps15(Player player) {
    String name = plugin.getConfig().getString("warp_15");
    Location loc;
    double x = plugin.getConfig().getDouble("warps." + name + ".X");
    double y = plugin.getConfig().getDouble("warps." + name + ".Y");
    double z = plugin.getConfig().getDouble("warps." + name + ".Z");
    float yaw = (float) plugin.getConfig().getDouble("warps." + name + ".Yaw");
    float pitch = (float) plugin.getConfig().getDouble("warps." + name + ".Pitch");
    String world = plugin.getConfig().getString("warps." + name + ".World");
    if (world != null) {
        loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
        player.teleport(loc);
    }
    player.sendMessage(Color("&aYou've been teleported to &b" + name));
    }
    public void tpWarps16(Player player) {
    String name = plugin.getConfig().getString("warp_16");
    Location loc;
    double x = plugin.getConfig().getDouble("warps." + name + ".X");
    double y = plugin.getConfig().getDouble("warps." + name + ".Y");
    double z = plugin.getConfig().getDouble("warps." + name + ".Z");
    float yaw = (float) plugin.getConfig().getDouble("warps." + name + ".Yaw");
    float pitch = (float) plugin.getConfig().getDouble("warps." + name + ".Pitch");
    String world = plugin.getConfig().getString("warps." + name + ".World");
    if (world != null) {
        loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
        player.teleport(loc);
    }
    player.sendMessage(Color("&aYou've been teleported to &b" + name));
    }
    public void tpWarps17(Player player) {
        String name = plugin.getConfig().getString("warp_17");
        Location loc;
        double x = plugin.getConfig().getDouble("warps." + name + ".X");
        double y = plugin.getConfig().getDouble("warps." + name + ".Y");
        double z = plugin.getConfig().getDouble("warps." + name + ".Z");
        float yaw = (float) plugin.getConfig().getDouble("warps." + name + ".Yaw");
        float pitch = (float) plugin.getConfig().getDouble("warps." + name + ".Pitch");
        String world = plugin.getConfig().getString("warps." + name + ".World");
        if (world != null) {
        loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
        player.teleport(loc);
        }
        player.sendMessage(Color("&aYou've been teleported to &b" + name));
        }
        public void tpWarps18(Player player) {
        String name = plugin.getConfig().getString("warp_18");
        Location loc;
        double x = plugin.getConfig().getDouble("warps." + name + ".X");
        double y = plugin.getConfig().getDouble("warps." + name + ".Y");
        double z = plugin.getConfig().getDouble("warps." + name + ".Z");
        float yaw = (float) plugin.getConfig().getDouble("warps." + name + ".Yaw");
        float pitch = (float) plugin.getConfig().getDouble("warps." + name + ".Pitch");
        String world = plugin.getConfig().getString("warps." + name + ".World");
        if (world != null) {
        loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
        player.teleport(loc);
        }
        player.sendMessage(Color("&aYou've been teleported to &b" + name));
        }
        public void tpWarps19(Player player) {
        String name = plugin.getConfig().getString("warp_19");
        Location loc;
        double x = plugin.getConfig().getDouble("warps." + name + ".X");
        double y = plugin.getConfig().getDouble("warps." + name + ".Y");
        double z = plugin.getConfig().getDouble("warps." + name + ".Z");
        float yaw = (float) plugin.getConfig().getDouble("warps." + name + ".Yaw");
        float pitch = (float) plugin.getConfig().getDouble("warps." + name + ".Pitch");
        String world = plugin.getConfig().getString("warps." + name + ".World");
        if (world != null) {
        loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
        player.teleport(loc);
        }
        player.sendMessage(Color("&aYou've been teleported to &b" + name));
        }
        public void tpWarps20(Player player) {
        String name = plugin.getConfig().getString("warp_20");
        Location loc;
        double x = plugin.getConfig().getDouble("warps." + name + ".X");
        double y = plugin.getConfig().getDouble("warps." + name + ".Y");
        double z = plugin.getConfig().getDouble("warps." + name + ".Z");
        float yaw = (float) plugin.getConfig().getDouble("warps." + name + ".Yaw");
        float pitch = (float) plugin.getConfig().getDouble("warps." + name + ".Pitch");
        String world = plugin.getConfig().getString("warps." + name + ".World");
        if (world != null) {
        loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
        player.teleport(loc);
        }
        player.sendMessage(Color("&aYou've been teleported to &b" + name));
        }
    public void tpWarps21(Player player) {
        String name = plugin.getConfig().getString("warp_21");
        Location loc;
        double x = plugin.getConfig().getDouble("warps." + name + ".X");
        double y = plugin.getConfig().getDouble("warps." + name + ".Y");
        double z = plugin.getConfig().getDouble("warps." + name + ".Z");
        float yaw = (float) plugin.getConfig().getDouble("warps." + name + ".Yaw");
        float pitch = (float) plugin.getConfig().getDouble("warps." + name + ".Pitch");
        String world = plugin.getConfig().getString("warps." + name + ".World");
        if (world != null) {
            loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
            player.teleport(loc);
        }
        player.sendMessage(Color("&aYou've been teleported to &b" + name));
    }
    private String Color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}