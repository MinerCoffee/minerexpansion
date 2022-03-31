package me.minercoffee.minerexpansion.commands;

import me.minercoffee.minerexpansion.elyra.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.minercoffee.minerexpansion.MinerExpansion.plugin;


public class reload implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        if (p.hasPermission("miner.staff")) {
            plugin.reloadConfig();
            sender.sendMessage(ChatUtils.colour("&4You have reloaded the config!"));
        }
        return true;
    }
}