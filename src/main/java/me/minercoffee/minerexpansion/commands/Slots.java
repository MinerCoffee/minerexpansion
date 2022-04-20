package me.minercoffee.minerexpansion.commands;

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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static me.minercoffee.minerexpansion.MinerExpansion.plugin;


@SuppressWarnings("deprecation")
public class Slots implements CommandExecutor, Listener {
    List<Inventory> invs = new ArrayList<>();
    public static ItemStack[] contents;
    private int itemIndex = 0;

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,@NotNull String[] args) {

        //System.out.println("slots");
        if (label.equalsIgnoreCase("megamillion")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("No gambling for the console :(");
                return true;
            }
            Player player = (Player) sender;
            if(plugin.getConfig().getBoolean("slots")) {
                ItemStack fee = new ItemStack((Material.valueOf(plugin.getConfig().getString("fee"))));
                fee.setAmount(plugin.getConfig().getInt("fee_amount",5));
                if (player.getItemInHand().getAmount() < (plugin.getConfig().getInt("fee_amount",5))) {
                    player.sendMessage(ChatUtils.colour("&cYou need" + " " + fee + " to play!"));
                    return false;
                }
                if  (player.getInventory().getItemInMainHand().isSimilar(fee) && (player.getInventory().contains((Material.valueOf(plugin.getConfig().getString("fee")))))){
                    player.getInventory().remove(fee);
                    // spin that GUI
                    spin(player);
                    player.sendMessage(ChatUtils.colour("&l&bThank you for playing come again!" + " " + "&d<3"));

                    ItemStack air = new ItemStack(Material.AIR);
                    if (player.getItemInHand().getAmount() > (plugin.getConfig().getInt("fee_amount",5))) {
                        player.getItemInHand().setAmount(player.getItemInHand().getAmount()- fee.getAmount());
                    } else {
                        player.setItemInHand(air);
                    }
                    return true;
                }
                player.sendMessage(ChatUtils.colour("&cYou need" + " " + fee + " to play!"));
                return true;
            }
        }
        return false;
    }

    public void shuffle(Inventory inv) {
        if (contents == null) {
            ItemStack[] items = new ItemStack[14];

            items[0] = new ItemStack(Material.valueOf(plugin.getConfig().getString("reward1")), plugin.getConfig().getInt("reward1_amount",32));
            items[1] = new ItemStack(Material.valueOf(plugin.getConfig().getString("reward2")), plugin.getConfig().getInt("reward2_amount",3));
            items[2] =new ItemStack(Material.valueOf(plugin.getConfig().getString("reward3")), plugin.getConfig().getInt("reward3_amount",2));
            items[3] = new ItemStack(Material.valueOf(plugin.getConfig().getString("reward4")), plugin.getConfig().getInt("reward4_amount",1));
            items[4] = new ItemStack(Material.valueOf(plugin.getConfig().getString("reward5")), plugin.getConfig().getInt("reward5_amount",1));
            items[5] = new ItemStack(Material.valueOf(plugin.getConfig().getString("reward6")), plugin.getConfig().getInt("reward6_amount",3));
            items[6] = new ItemStack(Material.valueOf(plugin.getConfig().getString("reward7")), plugin.getConfig().getInt("reward7_amount",1));
            items[7] = new ItemStack(Material.valueOf(plugin.getConfig().getString("reward8")), plugin.getConfig().getInt("reward8_amount",1));
            items[8] = new ItemStack(Material.valueOf(plugin.getConfig().getString("reward9")), plugin.getConfig().getInt("reward9_amount",24));
            items[9] = new ItemStack(Material.valueOf(plugin.getConfig().getString("reward10")), plugin.getConfig().getInt("reward10_amount",12));
            items[10] = new ItemStack(Material.valueOf(plugin.getConfig().getString("reward11")), plugin.getConfig().getInt("reward11_amount",12));
            items[11] = new ItemStack(Material.valueOf(plugin.getConfig().getString("reward12")), plugin.getConfig().getInt("reward12_amount",16));
            items[12] = new ItemStack(Material.valueOf(plugin.getConfig().getString("reward13")), plugin.getConfig().getInt("reward13_amount",15));
            items[13] = new ItemStack(Material.valueOf(plugin.getConfig().getString("reward14")), plugin.getConfig().getInt("reward14_amount",8));
            contents = items;
        }
        int startingIndex = ThreadLocalRandom.current().nextInt(contents.length);

        for (int index = 0; index < startingIndex; index++) {
            for (int itemstacks = 9; itemstacks < 18; itemstacks++) {
                inv.setItem(itemstacks, contents[(itemstacks + itemIndex) % contents.length]);
            }
            itemIndex++;
        }
        // Customize!!!
        ItemStack item = new ItemStack(Material.HOPPER);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.DARK_GRAY + "|");
        }
        item.setItemMeta(meta);
        inv.setItem(4, item);
    }

    public void spin(final Player player) {

        Inventory inv = Bukkit.createInventory(null, 27, ChatUtils.colour("&l&6Lucky slots"));
        shuffle(inv);
        invs.add(inv);
        player.openInventory(inv);

        Random r = new Random();
        double seconds = 7.0 + (15.0 - 7.0) * r.nextDouble();

        new BukkitRunnable() {
            double delay = 0;
            int ticks = 0;
            boolean done = false;

            public void run() {
                if (done)
                    return;
                ticks++;
                delay += 1 / (20 * seconds);
                if (ticks > delay * 10) {
                    ticks = 0;

                    for (int itemstacks = 9; itemstacks < 18; itemstacks++)
                        inv.setItem(itemstacks, contents[(itemstacks + itemIndex) % contents.length]);
                    itemIndex++;

                    if (delay >= .5) {
                        done = true;
                        new BukkitRunnable() {

                            public void run() {
                                ItemStack item = inv.getItem(13);
                                assert item != null;
                                player.getInventory().addItem(item);
                                player.updateInventory();
                                player.closeInventory();
                                cancel();
                            }
                        }.runTaskLater(MinerExpansion.getPlugin(MinerExpansion.class), 50);
                        cancel();
                    }

                }
            }
        }.runTaskTimer(MinerExpansion.getPlugin(MinerExpansion.class), 0, 1);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!invs.contains(event.getInventory()))
            return;
        event.setCancelled(true);
    }
}
