package me.minercoffee.minerexpansion.elyra.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class RecipeUtils {
    public RecipeUtils() {
    }
    public static ItemStack getElytra() {
        ItemStack Elytra = new ItemStack(Material.ELYTRA, 1);
        ItemMeta meta = Elytra.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add("Night's Sky!");
        if (meta != null) {
            meta.setCustomModelData(1234567);
            meta.setLore(lore);
            meta.setDisplayName(ChatUtils.colour("&6&lHawker"));
            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            Elytra.setItemMeta(meta);
        }
        return Elytra;
    }
}