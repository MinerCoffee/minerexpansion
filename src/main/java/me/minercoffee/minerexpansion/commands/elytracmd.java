package me.minercoffee.minerexpansion.commands;

import me.minercoffee.minerexpansion.elyra.utils.RecipeUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static me.minercoffee.minerexpansion.MinerExpansion.plugin;


public class elytracmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (plugin.getConfig().getBoolean("elytra")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can run this command.");
            } else {
                Player p = (Player) sender;
                if (p.isOp()) {
                    if (command.getName().equalsIgnoreCase("giveelytra")) {
                        p.getInventory().addItem(RecipeUtils.getElytra());
                    }
                } else {
                    sender.sendMessage("You are not allowed to use this command.");
                }
            }
        }
        return true;
    }
}
