package me.minercoffee.minerexpansion.elyra;

import me.minercoffee.minerexpansion.utils.ColorMsg;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.ArrayList;

public class CharcoalElytra {
    public static ItemStack getElytra() {
        ItemStack Elytra = new ItemStack(Material.ELYTRA, 1);
        Damageable meta = (Damageable) Elytra.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add (ColorMsg.color("&2Crouch &7& &2Jump &7 to Launch into the Air."));
        lore.add(ColorMsg.color("&7Press &2Shift &7While Flying To Boost."));
        if (meta != null) {
            meta.setDamage(100);
            meta.setCustomModelData(1234567);
            meta.setUnbreakable(true);
            meta.setLore(lore);
            meta.setDisplayName(ColorMsg.color("&6&lCharcoal Elytra"));
            Elytra.setItemMeta(meta);
        }
        return Elytra;
    }
    public static boolean hasElytra(Player p) {
        ItemStack chest = p.getInventory().getChestplate();
        return chest != null && chest.isSimilar(CharcoalElytra.getElytra());
    }
}
