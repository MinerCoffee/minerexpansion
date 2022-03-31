package me.minercoffee.minerexpansion.supplydrop.commands;

import me.minercoffee.minerexpansion.supplydrop.utils.InventorySerializer;
import me.minercoffee.minerexpansion.supplydrop.utils.SendInfoMessages;
import me.minercoffee.minerexpansion.supplydrop.utils.SupplyDropsDataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CommandEditSupplyDrop implements CommandExecutor, Listener, TabCompleter {
    public CommandEditSupplyDrop() {
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("miner.staff")) {
                if (args.length == 0) {
                    SendInfoMessages.sendInfoMessage(p, "SpecifyDropName");
                    return false;
                }

                this.openEditor(p, args[0]);
            } else {
                SendInfoMessages.sendInfoMessage(p, "InsufficientPermissions");
            }
        }
        return false;
    }

    public void openEditor(Player p, String supplyDropName) {
        Inventory inv = Bukkit.createInventory(null, 27, "§9§lEditor: §1§l" + supplyDropName);
        if (SupplyDropsDataManager.getSupplyDropsData().getString("drops." + supplyDropName) != null) {
            try {
                Inventory loadedDropInv = InventorySerializer.fromBase64(SupplyDropsDataManager.getSupplyDropsData().getString("drops." + supplyDropName));
                ItemStack[] dropInvItems = loadedDropInv.getContents();

                for (int i = 0; i < dropInvItems.length; ++i) {
                    if (dropInvItems[i] != null) {
                        inv.setItem(i, dropInvItems[i]);
                    }
                }
            } catch (IOException var7) {
                var7.printStackTrace();
            }
        }

        p.openInventory(inv);
    }

    public void saveSupplyDrop(String dropName, Inventory inv) {
        SupplyDropsDataManager.getSupplyDropsData().set("drops." + dropName, InventorySerializer.inventoryToBase64(inv));
        SupplyDropsDataManager.saveSupplyDropsData();
    }

    @EventHandler
    public void leave(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        String editorName = ChatColor.stripColor(p.getOpenInventory().getTitle());
        if (editorName.startsWith("Editor:")) {
            String[] parts = editorName.split(":");
            String dropName = parts[1];
            SendInfoMessages.sendInfoMessage(p, "SavedDrop", dropName.trim(), "");
            this.saveSupplyDrop(dropName.trim(), e.getInventory());
        }
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equals("supplydrop")) {
            if (args.length == 1) {
                List<String> playerNames = new ArrayList<>();
                Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                Bukkit.getServer().getOnlinePlayers().toArray(players);
                for (Player player : players) {
                    playerNames.add(player.getName());
                }
                return playerNames;
            }
        }
        return null;
    }
}