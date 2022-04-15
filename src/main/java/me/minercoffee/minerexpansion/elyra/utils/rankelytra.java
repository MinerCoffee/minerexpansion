package me.minercoffee.minerexpansion.elyra.utils;

import me.minercoffee.minerexpansion.supplydrop.commands.grapplinghook.GrapplingHookCooldown;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class rankelytra implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;

        if (command.getName().equalsIgnoreCase("elytra")) {
            if (p.hasPermission("miner.active")) {
                GrapplingHookCooldown.setCooldown(p, 3600);
                p.getInventory().addItem(RecipeUtils.getElytra());
            }
        }

        return true;
    }
}
