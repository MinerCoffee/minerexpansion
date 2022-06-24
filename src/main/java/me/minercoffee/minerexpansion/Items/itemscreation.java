package me.minercoffee.minerexpansion.Items;

import me.minercoffee.minerexpansion.utils.ColorMsg;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class itemscreation implements Listener {

    public static void init() {
        createNametag();
        createBell();
        createLeather();
        CreateThrowingAxe();
        createGrapplingHook();
        createflare();
        createcobweb();
        CreateVindicatorAxe();
    }

    public static ItemStack Nametag;

    public static void createNametag() {
        ItemStack name = new ItemStack(Material.NAME_TAG);
        ItemMeta meta = name.getItemMeta();
        name.setItemMeta(meta);
        Nametag = name;
    }

    public static ItemStack Bell;

    public static void createBell() {
        ItemStack bell = new ItemStack(Material.BELL);
        ItemMeta meta = bell.getItemMeta();
        bell.setItemMeta(meta);
        Bell = bell;
    }

    public static ItemStack Leather;

    public static void createLeather() {

        ItemStack leather = new ItemStack(Material.LEATHER);
        ItemMeta meta = leather.getItemMeta();
        leather.setItemMeta(meta);
        Leather = leather;
    }

    public static ItemStack ThrowingAxe;

    public static void CreateThrowingAxe() {
        ItemStack item = new ItemStack(Material.NETHERITE_AXE, 1);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName((ColorMsg.color("§6Throwing Axe!")));
            List<String> lore = new ArrayList<>();
            lore.add("§7Damage: §c+5");
            lore.add("");
            lore.add("§6Item Ability: Throw §eRIGHT CLICK");
            lore.add("§7Throw your axe and deal");
            lore.add("§c5 §7damage.");
            meta.setLore(lore);
            AttributeModifier damage = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damage);
            item.setItemMeta(meta);
            ThrowingAxe = item;
        }
    }

    public static ItemStack Vindicatoraxe;

    public static void CreateVindicatorAxe() {
        ItemStack item = new ItemStack(Material.IRON_AXE, 1);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
            item.setItemMeta(meta);
            Vindicatoraxe = item;
        }
    }

    public static ItemStack GrapplingHook;

    public static void createGrapplingHook() {
        ItemStack item = new ItemStack(Material.FISHING_ROD, 1);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§9Grappling Hook");
            List<String> lore = new ArrayList<>();
            lore.add("§9RARE");
            lore.add("§7Travel in style with this tool...");
            lore.add("Item has a 5 second cooldown.");
            meta.setLore(lore);
            meta.setUnbreakable(true);
            item.setItemMeta(meta);
            GrapplingHook = item;
        }
    }

    public static ItemStack Flare;

    public static void createflare() {
        ItemStack item = new ItemStack(Material.BLAZE_ROD, 1);
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta != null) {
            itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Supply Drop &fFlare"));
        }
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7*click to throw*"));
        if (itemMeta != null) {
            itemMeta.setLore(lore);
        }
        if (itemMeta != null) {
            itemMeta.addEnchant(Enchantment.SILK_TOUCH, 1, false);
        }
        if (itemMeta != null) {
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        if (itemMeta != null) {
            item.setItemMeta(itemMeta);
            Flare = item;
        }
    }

    public static ItemStack Cobweb;

    public static void createcobweb() {
        ItemStack item = new ItemStack(Material.COBWEB, 1);
        ItemMeta meta = item.getItemMeta();
        item.setItemMeta(meta);
        Cobweb = item;
    }
}