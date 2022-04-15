package me.minercoffee.minerexpansion.elyra.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.ArrayList;

public class RecipeUtils {
    public RecipeUtils() {
    }
    public static ItemStack getElytra() {
        ItemStack Elytra = new ItemStack(Material.ELYTRA, 1);
        Damageable meta = (Damageable) Elytra.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add (ChatColor.GRAY + "Miner's Plane!");
        if (meta != null) {
            meta.setDamage(100);
            meta.setCustomModelData(1234567);
            meta.setLore(lore);
            meta.setDisplayName(ChatUtils.colour("&6&lCharcoal Elytra"));
            Elytra.setItemMeta(meta);
        }
        return Elytra;
    }
}