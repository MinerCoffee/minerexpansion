package me.minercoffee.minerexpansion.elyra.utils.events;

import me.minercoffee.minerexpansion.elyra.utils.RecipeUtils;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class PreventAnvilUse implements Listener {
    @EventHandler
    public void AnvilUse(InventoryClickEvent e) {
        if (!e.isCancelled()) {
            HumanEntity ent = e.getWhoClicked();
            if (ent instanceof Player) {
                Player p = (Player) ent;
                Inventory inv = e.getInventory();
                if (inv instanceof AnvilInventory) {
                    AnvilInventory anvil = (AnvilInventory)inv;
                    InventoryView view = e.getView();
                    int rawSlot = e.getRawSlot();
                   // System.out.println("test_1");
                    if (rawSlot == view.convertSlot(rawSlot)) {
                        if (rawSlot == 2) {
                            ItemStack[] items = anvil.getContents();
                            ItemStack item = items[0];
                            ItemStack item2 = items[1];
                                if (item == null)
                                    return;
                            if (item2 == null)
                                return;
                             //   System.out.println("test_3");
                                if (item.isSimilar(RecipeUtils.getElytra()) || item2.isSimilar(RecipeUtils.getElytra()) ) {
                                   // System.out.println("test_4");
                                    e.setCancelled(true);
                                    p.closeInventory();
                                    p.sendMessage("You must drop the elytra in order to use the anvil");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
