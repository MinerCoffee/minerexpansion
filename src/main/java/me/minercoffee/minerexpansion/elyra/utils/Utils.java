package me.minercoffee.minerexpansion.elyra.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Utils {
    public Utils() {
    }

    public static boolean hasElytra(Player p) {
        ItemStack chest = p.getInventory().getChestplate();
        return chest != null && chest.isSimilar(RecipeUtils.getElytra());
    }
}