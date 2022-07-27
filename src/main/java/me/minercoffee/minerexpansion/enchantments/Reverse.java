package me.minercoffee.minerexpansion.enchantments;

import me.minercoffee.minerexpansion.MinerExpansion;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reverse implements Listener, CommandExecutor {
    MinerExpansion plugin;
    public Reverse(MinerExpansion plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
            Player player = (Player) event.getEntity();
            ItemStack itemOffHand;
            Damageable damageable;
            LivingEntity livingEntity;
            if ((player).getInventory().getItem(EquipmentSlot.HAND).containsEnchantment(ReverseUtils.Reverse) || (player).getInventory().getItem(EquipmentSlot.OFF_HAND).containsEnchantment(ReverseUtils.Reverse)) {
                if (event.getEntity() instanceof Player) {
                    if (player.isOp()) return;
                if (player.getInventory().getItemInMainHand().getItemMeta() != null) {
                    itemOffHand = player.getInventory().getItemInMainHand();
                            damageable = (Damageable) itemOffHand.getItemMeta();
                            if (event.getDamager() instanceof LivingEntity) {
                                livingEntity = (LivingEntity) event.getDamager();
                                if (event.getFinalDamage() < player.getHealth()) {
                                    livingEntity.damage(event.getDamage());
                                    player.sendMessage(ChatColor.GOLD + "The UNO Reverse Card has reversed all the damage!");
                                    damageable.setDamage(damageable.getDamage() + 1);
                                    itemOffHand.setItemMeta(damageable);
                                } else {
                                    livingEntity.setHealth(0.0);
                                    player.sendMessage(ChatColor.GOLD + "The UNO Reverse Card has been used up!");
                                    player.getInventory().remove(itemOffHand);
                                }
                                event.setDamage(0.0);
                            }
                        }
                    }

                    if (player.getInventory().getItemInOffHand().getItemMeta() != null) {
                        itemOffHand = player.getInventory().getItemInOffHand();
                            damageable = (Damageable) itemOffHand.getItemMeta();
                            if (event.getDamager() instanceof LivingEntity) {
                                livingEntity = (LivingEntity) event.getDamager();
                                if (event.getFinalDamage() < player.getHealth()) {
                                    livingEntity.damage(event.getDamage());
                                    player.sendMessage(ChatColor.GOLD + "The UNO Reverse Card has reversed all the damage!");
                                    damageable.setDamage(damageable.getDamage() + 1);
                                    itemOffHand.setItemMeta(damageable);
                                } else {
                                    livingEntity.setHealth(0.0);
                                    player.sendMessage(ChatColor.GOLD + "The UNO Reverse Card has been used up!");
                                    player.getInventory().remove(itemOffHand);
                                }

                                event.setDamage(0.0);
                            }
                        }
                    }
                }
    @EventHandler
    public void AddEnchantment(PrepareAnvilEvent e) {
        Player damager = (Player) e.getView().getPlayer();
        if (e.getInventory().getItem(1) == null || e.getInventory().getItem(0) == null) return;
        if (Objects.requireNonNull(e.getInventory().getItem(1)).containsEnchantment(ReverseUtils.Reverse)) {
            ItemStack a = new ItemStack(Objects.requireNonNull(e.getInventory().getItem(0)));
            a.addEnchantment(ReverseUtils.Reverse, (Objects.requireNonNull(e.getInventory().getItem(1))).getEnchantmentLevel(ReverseUtils.Reverse));
            ItemMeta meta = a.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "Reverse I");
            if (meta != null && meta.hasLore()) lore.addAll(Objects.requireNonNull(meta.getLore()));
            if (meta != null) {
                meta.setLore(lore);
            }
            a.setItemMeta(meta);
            e.getInventory().setRepairCost(40);
            e.setResult(a);
            damager.updateInventory();
            plugin.getServer().getScheduler().runTask(plugin, () -> e.getInventory().setRepairCost(40));
        }
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.isOp()){
                if (command.getName().equalsIgnoreCase("reverse")) {
                    ItemStack book = new ItemStack((Material.ENCHANTED_BOOK), 1);
                    book.addUnsafeEnchantment(ReverseUtils.Reverse, 1);
                    List<String> lore = new ArrayList<>();
                    lore.add(ChatColor.GRAY + "Reverse I");
                    ItemMeta meta = book.getItemMeta();
                    if (meta != null && meta.hasLore()) lore.addAll(Objects.requireNonNull(meta.getLore()));
                    if (meta != null) {
                        meta.setLore(lore);
                    }
                    book.setItemMeta(meta);
                    p.getInventory().addItem(book);
                    p.closeInventory();
                    p.updateInventory();
                }
            }
        }
        return true;
    }
}
