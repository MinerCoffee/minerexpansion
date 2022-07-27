package me.minercoffee.minerexpansion.commands;

import me.minercoffee.minerexpansion.utils.ColorMsg;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class tax implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        if (command.getName().equalsIgnoreCase("tax")) {
            if (p.hasPermission("illusive.staff") || p.isOp()) {
                    if (args.length >= 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            target.playSound(p.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, 40, 8);
                            target.sendMessage(ColorMsg.color("&l&6You have been taxed!"));
                            target.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 40, 1, true, false, false));
                        }
                    }

                    if (args.length == 1 && args[0].equalsIgnoreCase("all")) {
                        Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                        Bukkit.getServer().getOnlinePlayers().toArray(players);
                        for (Player target : players) {
                            if (target != null) {
                                target.playSound(p.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, 40, 8);
                                target.sendMessage(ColorMsg.color("&l&6You have been taxed!"));
                                target.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 40, 1, true, false, false));
                            }
                        }
                    }

                if (sender instanceof ConsoleCommandSender) {
                    if (args.length >= 1) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            target.playSound(p.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, 40, 8);
                            target.sendMessage(ColorMsg.color("&l&6You have been taxed!"));
                            target.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 40, 1, true, false, false));
                        }
                    }
                }
            }
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            ArrayList<String> subcommandsArguements = new ArrayList<>();
            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(players);
            for (Player player : players) {
                subcommandsArguements.add(player.getName());
            }
            subcommandsArguements.add("all");
            return subcommandsArguements;
        }
        return null;
    }
}